import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket servidor;
        Socket socket;
        DataInputStream recibir;
        DataOutputStream enviar;

        final int PUERTO = 5000;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor Iniciado");

            while(true){
                socket = servidor.accept();
                System.out.println("Cliente conectado");

                recibir = new DataInputStream(socket.getInputStream());
                enviar = new DataOutputStream(socket.getOutputStream());

                String mensaje = recibir.readUTF();
                System.out.println(mensaje);

                enviar.writeUTF("Mensaje desde el Server");

                socket.close();
                System.out.println("Cliente desconectado");
            }
        } catch (Exception e) {
            System.out.println("Fallo");
        }

    }
}
