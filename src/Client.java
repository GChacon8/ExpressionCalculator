import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Client{
    static Interfaz ventana;
    private static Client instancia = null;
    final String HOST = "127.0.0.1";
    final int PUERTO = 5000;
    DataInputStream recibir;
    ObjectOutputStream enviar;
    Socket socket;
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
            System.out.println("fallo");
        }
    }
    public static Client getInstancia(){
        if (instancia == null){
            instancia = new Client();
        }
        return instancia;
    }

    
}
