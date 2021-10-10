import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client{
    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PUERTO = 5000;
        DataInputStream recibir;
        DataOutputStream enviar;
        Socket socket;

        Interfaz ventana = new Interfaz();
        try {
            socket = new Socket(HOST, PUERTO);
            recibir = new DataInputStream(socket.getInputStream());
            enviar = new DataOutputStream(socket.getOutputStream());

            enviar.writeUTF("Mensaje desde el cliente");

            String mensaje = recibir.readUTF();

            System.out.println(mensaje);

            socket.close();

        } catch (Exception e) {
            System.out.println("fallo");
        }

    }
}
