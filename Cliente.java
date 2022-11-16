import java.net.*;
import java.io.*;

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

        Lab_3 lb = new Lab_3();
        lb.Main();
        
    }

}
