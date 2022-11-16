import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void server(){
        try{
            ServerSocket server = new ServerSocket(9999);

            Socket misocket = server.accept();
            DataInputStream recibir_datos = new DataInputStream(misocket.getInputStream());
        
            String mensajes = recibir_datos.readUTF();
            System.out.println(mensajes);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
