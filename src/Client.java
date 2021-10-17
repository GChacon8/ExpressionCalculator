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
        try {
            socket = new Socket(HOST, PUERTO);
            recibir = new DataInputStream(socket.getInputStream());
            enviar = new ObjectOutputStream(socket.getOutputStream());

            enviar.writeObject(mensaje);

            String respuesta = recibir.readUTF();

            System.out.println(respuesta);

            socket.close();

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
