import javax.swing.*;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Solicita la respuesta de una expresion o el historial al servidor
 * 
 * @author Gabriel Chacón Alfaro
 * @author Jimena León Huertas
 */
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

    /**
     * Comunica al cliente con el servidor para hacer una solicitud y posteriormente recibir el resultado o historial.
     * @param mensaje
     * @return Retorna ya sea la respuesta del la operacion o el historial solicitado.
     */
    public String mandarOperacion(Mensaje mensaje){
        String respuesta = "";

        try {
            socket = new Socket(HOST, PUERTO);
            recibir = new DataInputStream(socket.getInputStream());
            enviar = new ObjectOutputStream(socket.getOutputStream());

            enviar.writeObject(mensaje);

            respuesta = recibir.readUTF();

            System.out.println(respuesta);

            socket.close();
        } 
        catch (Exception e) {
             JOptionPane.showMessageDialog(null, "¡Escribiste mal la expresión!");
        }
        return respuesta;
    }

    /**
     * Singleton de la clase cliente.
     * @return Retorna la clase cliente.
     */
    public static Client getInstancia(){
        if (instancia == null){
            instancia = new Client();
        }
        return instancia;
    }

    
}
