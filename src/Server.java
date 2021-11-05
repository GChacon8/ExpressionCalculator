import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
import java.util.Date;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

/**
 * Devuelve las respuestas o el historial al cliente y registra las solicitudes. 
 * 
 * @author Gabriel Chacón Alfaro
 * @author Jimena León Huertas
 */
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
                String ID = mensaje.getIdentificador();
                String operacion = mensaje.getExpresion();

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Date date = new Date();  
                String fecha = formatter.format(date).toString();
                System.out.println(mensaje.toString());
                String result = "";

                if(mensaje.getTipo() == 1){
                    result = String.valueOf(calcResult(operacion));
                    registrar(ID, operacion, result, fecha);
                }
                else if(mensaje.getTipo() == 2){
                    result = leerArchivo(".\\Historial.csv", ID);
                }

                enviar.writeUTF(result);
                
                socket.close();
                System.out.println("Cliente desconectado");
            }
        } catch (Exception ex) {
            System.out.println("Fallo");
        }

    }

    // convierte la expresión normal a postfix
    public static LinkedList inToPost(String exp){
        int len = exp.length();
        String symbol;
        String previous = "";
        String ultimo;
        int num;
        LinkedList postFixed = new LinkedList();
        Stack stack = new Stack();

        for(int n = 0; n< len; n++){
            symbol = String.valueOf(exp.charAt(n));

            if (n>0){
                previous = String.valueOf(exp.charAt(n-1));
            }

            if(symbol.equals("(")){ // añade el ( al stack
                stack.push("(");

            }else if((n > 0) && isDigit(symbol) && isDigit(previous)){ // si el char actual y el anterior son #, vamos construyendo el num
                ultimo = postFixed.deleteLast();
                num = (Integer.parseInt(ultimo) * 10) + Integer.parseInt(symbol);
                postFixed.insertLast(String.valueOf(num));

            } else if((n > 0) && isDigit(symbol) && !isDigit(previous)){ // si sólo el actual es #, inserta ese char tal cual al postFixed
                postFixed.insertLast(symbol);

            } else if(isOperator(symbol)){ // cuando sea un operador, lo añade al stack
                stack.push(symbol);

            } else if(symbol.equals(")")){ // cuando sea un )
                while(stack.peek().equals(")") || stack.peek().equals("(")){ // nos aseguramos de quitar los paréntesis en medio
                    stack.pop();
                }

                postFixed.insertLast(stack.pop().getData()); // insertamos el operador

            }else{
                System.out.println("Incorrecto");
                postFixed.insertLast("#");
                return postFixed;
            }
        }

        // esto es para añadir los operadores más viejos que quedaron en el stack
        int restantes = stack.getSize();
        for(int k = 0; k<restantes; k++){
            if (stack.peek().equals(")") || stack.peek().equals("(")){ // nos aseguramos de quitar los paréntesis en medio
                stack.pop();
            }else{
                postFixed.insertLast(stack.pop().getData()); // insertamos el operador
            }
        }

        return postFixed;
    }

    public static int calcResult(String exp){
        int result;
        try{
            LinkedList postFixed = inToPost(exp);
            int lastPos = postFixed.getSize() - 1;
            ExpTree expTree = new ExpTree();


            if(postFixed.nodeAt(lastPos) == "#"){
                result = 0;
                JOptionPane.showMessageDialog(null, "¡Escribiste mal la expresión!");
                return result;

            }else{
                Node root = expTree.buildTree(postFixed);
                expTree.printPostFix(root);

                result = expTree.evalTree(root);
                System.out.println("\tResultado: " + result);
            }
        } catch (HeadlessException e) {
            result = 0;
            return result;
        }


        return result;
    }

    public static boolean isDigit(String str){
        boolean digit = false;

        try{
            Integer.parseInt(str);
            digit = true;
        } catch (NumberFormatException e) {
            digit = false;
        }
        return digit;
    }

    public static boolean isOperator(String str){
        boolean operator = false;
        if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("%")){
            operator = true;
        }
        return operator;
    }

    /**
     * Registra la solicitud del cliente en el archivo .csv
     * @param ID
     * @param expresion
     * @param resultado
     * @param fecha
     */
    public static void registrar(String ID, String expresion, String resultado, String fecha){
        StringBuilder sb = new StringBuilder();
        sb.append(ID).append(",").append(expresion).append(",").append(resultado).append(",").append(fecha).append("\n");
        appendArchivo(".\\Historial.csv", sb.toString());
    }

    /**
     * Lee el archivo, separa los elementos y genera un string excluyendo el ID.
     * @param archivo
     * @param ID
     * @return Retorna un string con la operacion, resultado y fecha separados.
     */
    public static String leerArchivo(String archivo, String ID){
        BufferedReader reader = null;
        String line = "";
        String lines = "";
        try {
            reader = new BufferedReader(new FileReader(archivo));
            StringBuilder sb = new StringBuilder();
            while((line = reader.readLine()) != null){
                String[] splitLine = line.split(",");
                if(splitLine[0].equals(ID)){
                    sb.append(splitLine[1]).append("\t").append(splitLine[2]).append("\t").append(splitLine[3]).append("\n\n");
                }
            }
            lines = sb.toString();
        } catch (Exception e) {
            System.out.println("Error leyendo archivo");
        }
        return lines;
    }

    /**
     * Agrega la nueva linea de datos de solicitud en el .csv
     * @param archivo
     * @param line
     */
    public static void appendArchivo(String archivo, String line){
        try {
            FileWriter fw = new FileWriter(new File(archivo), true);
            fw.write(line);
            fw.close();
        } catch (Exception e) {
            System.out.println("Error de registro");
        }
    }
}

