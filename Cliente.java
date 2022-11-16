import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {
    public void Socket(String asd){
        try {

            Socket socket = new Socket("192.168.1.8", 9999);
            DataOutputStream enviar_datos = new DataOutputStream(socket.getOutputStream());

            enviar_datos.writeUTF(asd);
            socket.close();


        }catch(Exception e) {
            System.out.println(e);
        }
    }

}
