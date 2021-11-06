import javax.swing.*;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Solicita la respuesta de una expresión o el historial al servidor
 * @author Gabriel Chacon Alfaro
 * @author Jimena Leon Huertas
 */
public class Client{
    private static Client instancia = null;

    /**
     * Inicializa la clase del Cliente e invoca la clase "interfaz" para crear la interfaz de la calculadora
     * @param args Un array de valores tipo String
     */
    public static void main(String[] args) {
        Interfaz ventana = new Interfaz();
        }

    /**
     * Comunica al cliente con el servidor para hacer una solicitud y posteriormente recibir el resultado o historial.
     * @param mensaje Recibe el objeto mensaje que se desea enviar.
     * @return Retorna ya sea la respuesta de la operación o el historial solicitado contenido en el objeto mensaje.
     */
    public String mandarOperacion(Mensaje mensaje){
        String respuesta = "";
        DataInputStream recibir;
        ObjectOutputStream enviar;
        Socket socket;
        final String HOST = "127.0.0.1";
        final int PUERTO = 5000;

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
