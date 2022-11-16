import java.net.*;
import java.util.Arrays;
import java.io.*;
import javax.swing.*;


public class Server implements Runnable{
    
    public void Servidor() {
        Thread hilo = new Thread(this);
        hilo.start();
    }
    
    public static void main(String[] args) throws IOException{
        
        ServerSocket ss = new ServerSocket(9999);
        Socket s = ss.accept();
    
        System.out.println("cliente conectado");
        
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("cliente: " + str);

        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("yes");
        pr.flush();

        DataOutputStream enviar_datos = new DataOutputStream(s.getOutputStream());
        DataInputStream recibir_datos = new DataInputStream(s.getInputStream());
        String asd = recibir_datos.readUTF();
        System.out.println(asd);
        String ns = recibir_datos.readUTF();
        int n = Integer.parseInt(ns);
        String texto;
        String quita;
        String Sorted;
        int arrm[] = new int[n];
        int arrh[] = new int[n];
        int arrq[] = new int[n];
        switch (Integer.parseInt(asd)) {
            case 1:
                //texto = recibir_datos.readUTF();
                //quita = texto.replaceAll("[\\[\\]]", "");
                //int[] arrm = Integer.valueOf(quita.split(","));
                //ArrayList<String> arrm = new ArrayList<String>(Arrays.asList(quita.split(",")));
                for (int i = 0; i < n; i++){
                    arrm[i] = Integer.parseInt(recibir_datos.readUTF());
                }
                Merge ms = new Merge();
                ms.mergeSort(arrm, 0, n - 1);
                System.out.println("Sorted array is");
                printArray(arrm);
                Sorted = Arrays.toString(arrm);
                enviar_datos.writeUTF(Sorted);
                break;
            case 2:
                //texto = recibir_datos.readUTF();
                //quita = texto.replaceAll("[\\[\\]]", "");
                //int[] arrh = Integer.valueOf(quita.split(","));
                //ArrayList<String> arrh = new ArrayList<String>(Arrays.asList(quita.split(",")));
                for (int i = 0; i < n; i++){
                    arrh[i] = Integer.parseInt(recibir_datos.readUTF());
                }
                HeapSort hs = new HeapSort();
                hs.sort(arrh);
                System.out.println("Sorted array is");
                printArray(arrh);
                Sorted = Arrays.toString(arrh);
                enviar_datos.writeUTF(Sorted);
                break;
            case 3:
                String inicio2 = recibir_datos.readUTF();
                int inicio = Integer.parseInt(inicio2);
                System.out.println(inicio);
                Quick qs = new Quick();
                if (inicio==1 || inicio==2) {
                    if (inicio == 1) {
                        //texto = recibir_datos.readUTF();
                        //quita = texto.replaceAll("[\\[\\]]", "");
                        //int[] arrq = Integer.valueOf(quita.split(","));
                        //ArrayList<String> arrq = new ArrayList<String>(Arrays.asList(quita.split(",")));
                        for (int i = 0; i < n; i++){
                            arrq[i] = Integer.parseInt(recibir_datos.readUTF());
                        }
                        qs.quickSortizq(arrq, 0, n - 1);
                    }

                    if (inicio==2) {
                        //texto = recibir_datos.readUTF();
                        //quita = texto.replaceAll("[\\[\\]]", "");
                        //int[] arrq = Integer.valueOf(quita.split(","));
                        //ArrayList<String> arrq = new ArrayList<String>(Arrays.asList(quita.split(",")));
                        for (int i = 0; i < n; i++){
                            arrq[i] = Integer.parseInt(recibir_datos.readUTF());
                        }
                        qs.quickSort(arrq, 0, n - 1);
                    }
                }
                System.out.println("Sorted array is");
                printArray(arrq);
                Sorted = Arrays.toString(arrq);
                enviar_datos.writeUTF(Sorted);
                break;

        }
        

        
        
        
    }
    public static class HeapSort {

        public void sort(int arr[]) {
            int N = arr.length;

            // Build heap (rearrange array)
            for (int i = N / 2 - 1; i >= 0; i--) {
                heapify(arr, N, i);
            }

            // One by one extract an element from heap
            for (int i = N - 1; i > 0; i--) {
                // Move current root to end
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;
                printArray(arr);
                // call max heapify on the reduced heap
                heapify(arr, i, 0);
            }
        }

        // To heapify a subtree rooted with node i which is
        // an index in arr[]. n is size of heap
        void heapify(int arr[], int N, int i) {
            int largest = i; // Initialize largest as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is larger than root
            if (l < N && arr[l] > arr[largest]) {
                largest = l;
            }

            // If right child is larger than largest so far
            if (r < N && arr[r] > arr[largest]) {
                largest = r;
            }

            // If largest is not root
            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Recursively heapify the affected sub-tree
                heapify(arr, N, largest);
            }
        }

        /* A utility function to print array of size n */
        // Driver's code
    }

    static void printArray(int arr[]) {
        int N = arr.length;

        for (int i = 0; i < N; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static class Merge {

        /* Function to merge the subarrays of a[] */
        void merge(int[] arrm, int beg, int mid, int end) {
            int i, j, k;
            int n1 = mid - beg + 1;
            int n2 = end - mid;

            /* temporary Arrays */
            int LeftArray[] = new int[n1];
            int RightArray[] = new int[n2];

            /* copy data to temp arrays */
            for (i = 0; i < n1; i++) {
                LeftArray[i] = arrm[beg + i];
            }
            for (j = 0; j < n2; j++) {
                RightArray[j] = arrm[mid + 1 + j];
            }

            i = 0;
            /* initial index of first sub-array */
            j = 0;
            /* initial index of second sub-array */
            k = beg;
            /* initial index of merged sub-array */

            while (i < n1 && j < n2) {
                if (LeftArray[i] <= RightArray[j]) {
                    arrm[k] = LeftArray[i];
                    i++;
                } else {
                    arrm[k] = RightArray[j];
                    j++;
                }
                k++;
            }
            while (i < n1) {
                arrm[k] = LeftArray[i];
                i++;
                k++;
            }

            while (j < n2) {
                arrm[k] = RightArray[j];
                j++;
                k++;
            }
        }

        void mergeSort(int[] arrm, int beg, int end) {
            if (beg < end) {
                int mid = (beg + end) / 2;
                mergeSort(arrm, beg, mid);
                mergeSort(arrm, mid + 1, end);
                merge(arrm, beg, mid, end);
            }
        }
    }

    public static class Quick {

        public static void quickSortizq(int A[], int izq, int der) {
            // Extraido de: http://puntocomnoesunlenguaje.blogspot.com/2012/12/java-quicksort.html
            
            int pivote=A[izq]; // tomamos primer elemento como pivote
            int i=izq;         // i realiza la búsqueda de izquierda a derecha
            int j=der;         // j realiza la búsqueda de derecha a izquierda
            int aux;
           
            while(i < j){                          // mientras no se crucen las búsquedas                                   
               while(A[i] <= pivote && i < j) i++; // busca elemento mayor que pivote
               while(A[j] > pivote) j--;           // busca elemento menor que pivote
               if (i < j) {                        // si no se han cruzado                      
                   aux= A[i];                      // los intercambia
                   A[i]=A[j];
                   A[j]=aux;
               }
             }
             
             A[izq]=A[j];      // se coloca el pivote en su lugar de forma que tendremos                                    
             A[j]=pivote;      // los menores a su izquierda y los mayores a su derecha
             
             if(izq < j-1)
                quickSortizq(A,izq,j-1);          // ordenamos subarray izquierdo
             if(j+1 < der)
                quickSortizq(A,j+1,der);          // ordenamos subarray derecho
             
        }

        // A utility function to swap two elements
        static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            printArray(arr);
        }

        /*
         * This function takes last element as pivot, places
         * the pivot element at its correct position in sorted
         * array, and places all smaller (smaller than pivot)
         * to left of pivot and all greater elements to right
         * of pivot
         */
        static int partition(int[] arr, int low, int high) {

            // pivot
            int pivot = arr[high];

            // Index of smaller element and
            // indicates the right position
            // of pivot found so far
            int i = (low - 1);

            for (int j = low; j <= high - 1; j++) {

                // If current element is smaller
                // than the pivot
                if (arr[j] < pivot) {

                    // Increment index of
                    // smaller element
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
            return (i + 1);
        }

        /*
         * The main function that implements QuickSort
         * arr[] --> Array to be sorted,
         * low --> Starting index,
         * high --> Ending index
         */
        static void quickSort(int[] arr, int low, int high) {
            if (low < high) {

                // pi is partitioning index, arr[p]
                // is now at right place
                int pi = partition(arr, low, high);

                // Separately sort elements before
                // partition and after partition
                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
            }
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
    

}
