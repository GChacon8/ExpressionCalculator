import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket servidor;
        Socket socket;
        ObjectInputStream recibir;
        DataOutputStream enviar;

        final int PUERTO = 5000;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor Iniciado");

            while(true){
                socket = servidor.accept();
                System.out.println("Cliente conectado");

                recibir = new ObjectInputStream(socket.getInputStream());
                enviar = new DataOutputStream(socket.getOutputStream());

                Mensaje mensaje = (Mensaje) recibir.readObject();

                System.out.println(mensaje.toString());

                enviar.writeUTF("Me mandaste esto:"+ mensaje.toString());

                socket.close();
                System.out.println("Cliente desconectado");
            }
        } catch (Exception ex) {
            System.out.println("Fallo");
        }

    }
}

