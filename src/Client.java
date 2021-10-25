import javax.swing.*;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client{
    private static Interfaz ventana;
    private static Client instancia = null;
    private final String HOST = "127.0.0.1";
    private final int PUERTO = 5000;
    private DataInputStream recibir;
    private ObjectOutputStream enviar;
    private Socket socket;

    public static void main(String[] args) {
        ventana = new Interfaz();
        }

    public void mandarOperacion(Mensaje mensaje){
        String respuesta;

        try {
            socket = new Socket(HOST, PUERTO);
            recibir = new DataInputStream(socket.getInputStream());
            enviar = new ObjectOutputStream(socket.getOutputStream());

            enviar.writeObject(mensaje);

            respuesta = recibir.readUTF();

            System.out.println(respuesta);

            ventana.setTextResult(respuesta);

            socket.close();
            ventana.setTextResult(respuesta);
        } 
        catch (Exception e) {
             JOptionPane.showMessageDialog(null, "¡Escribiste mal la expresión!");
        }
    }
    public static Client getInstancia(){
        if (instancia == null){
            instancia = new Client();
        }
        return instancia;
    }

    
}
