import java.net.*;
import java.util.Arrays;
import java.io.*;
import javax.swing.*;

public class Cliente {
    public static void main(String[] args) throws IOException{
        Socket s = new Socket("localhost", 9999);

        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("Funciona?");
        pr.flush();
        
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("server: " + str);

        DataOutputStream enviar_datos = new DataOutputStream(s.getOutputStream());
        DataInputStream recibir_datos = new DataInputStream(s.getInputStream());


        
        
        JOptionPane.showMessageDialog(null,
                "¡Bienvenido al programa de ordenamientos!, Digite que ordenamiento desea usar: 1.Mergesort - 2.Heapsort - 3.Quicksort");
        int Option;
        do{
        Option = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el número del ordenamiento que desea usar"));
        enviar_datos.writeUTF(String.valueOf(Option));
        }while (Option<0 || Option>4);
        int n = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el tamaño del vector :"));
        int arrm[] = new int[n];
        int arrh[] = new int[n];
        int arrq[] = new int[n];
        switch (Option) {
            case 1:
                JOptionPane.showMessageDialog(null,
                        "Ordenamiento Mergesort");
                for (int i = 0; i < n; i++) {
                    arrm[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el elemento " + (i + 1)));
                }
                String convertir = Arrays.toString(arrm);
                enviar_datos.writeUTF(convertir);
                System.out.println(arrm);

                break;
            case 2:
                JOptionPane.showMessageDialog(null,
                        "Ordenamiento heapsort");
                for (int i = 0; i < n; i++) {
                    arrh[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el elemento " + (i + 1)));
                }
                enviar_datos.writeUTF(Arrays.toString(arrh));
                System.out.println(arrh);

                break;
            case 3:
                JOptionPane.showMessageDialog(null,
                "Ordenamiento quicksort");
                for (int i = 0; i < n; i++) {
                    arrq[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el elemento " + (i + 1)));
                }
                int inicio = Integer.parseInt(JOptionPane.showInputDialog(null, "Elija el pivote: 1. Izquierda y 2. Derecha"));
                // Validacion sobre la eleccion
                if (inicio==1 || inicio==2) {
                    enviar_datos.writeUTF(String.valueOf(inicio));
                    if (inicio == 1) {
                        JOptionPane.showMessageDialog(null, "Pivote a la izquierda");
                    }

                    if (inicio==2) {
                        JOptionPane.showMessageDialog(null, "Pivote a la derecha");
                    }

                    
                } else {
                    JOptionPane.showMessageDialog(null, "Escoja un valor correcto");
                }
                enviar_datos.writeUTF(Arrays.toString(arrq));
                System.out.println(arrq);

                break;
        }
        String SortedArray = recibir_datos.readUTF();
        System.out.println("El array ordenado es: " + SortedArray);
    }

}
