import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client{
    static Interfaz ventana;
    private static Client instancia = null;
    final String HOST = "127.0.0.1";
    final int PUERTO = 5000;
    DataInputStream recibir;
    DataOutputStream enviar;
    Socket socket;
    public static void main(String[] args) {
        
        ventana = new Interfaz();
        
        }

    public void mandarOperacion(String operacion){
        try {
            socket = new Socket(HOST, PUERTO);
            recibir = new DataInputStream(socket.getInputStream());
            enviar = new DataOutputStream(socket.getOutputStream());

            enviar.writeUTF(operacion);

            String mensaje = recibir.readUTF();

            System.out.println(mensaje);

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
