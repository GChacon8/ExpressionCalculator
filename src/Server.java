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

                String operacion = mensaje.getExpresion();
                int result = calcPostFix(operacion);
                enviar.writeUTF(String.valueOf(result));


                socket.close();
                System.out.println("Cliente desconectado");
            }
        } catch (Exception ex) {
            System.out.println("Fallo");
        }

    }

    // convierte la expresión normal a postfix
    public static String inToPost(String exp){
        int len = exp.length();
        String symbol;
        String postFixed = "";
        Stack stack = new Stack();
        String top = "";

        for(int n = 0; n< len; n++){
            symbol = String.valueOf(exp.charAt(n));

            if(symbol.equals("(")){ // añade el ( al stack
                stack.push("(");

            } else if(symbol.equals("0") || symbol.equals("1") || symbol.equals("2") || symbol.equals("3")
                    || symbol.equals("4") || symbol.equals("5") || symbol.equals("6")
                    || symbol.equals("7") || symbol.equals("8") || symbol.equals("9")){
                // append al string postfixed
                postFixed += symbol;

            } else if(!symbol.equals(")")){ // cuando sea un operador, lo añade al stack
                stack.push(symbol);

            } else{ // cuando sea un )
                while(stack.peek().equals(")") || stack.peek().equals("(")){
                    stack.pop();
                }
                postFixed += stack.pop().getData();
            }
            top = (String) stack.peek();
        }

        // esto es para añadir los operadores más viejos que quedaron en el stack
        int restantes = stack.getSize();
        for(int k = 0; k<restantes; k++){
            if (stack.peek().equals(")") || stack.peek().equals("(")){
                stack.pop();
            }else{
                postFixed += stack.pop().getData();
            }
        }
        return postFixed;
    }

    // crea el árbol de expresión usando el string postfixeado
    public static void createExprTree(String exp){
        String postFixed = inToPost(exp); // CONVIERTE LA EXPRESIÓN A POSTFIX
        ExpTree expTree = new ExpTree();
        Stack stack = new Stack();
        int len = exp.length();
        String symbol;

        for(int n = 0; n< len; n++){
            symbol = String.valueOf(exp.charAt(n));

            if(symbol.equals("0") || symbol.equals("1") || symbol.equals("2") || symbol.equals("3")
                    || symbol.equals("4") || symbol.equals("5") || symbol.equals("6")
                    || symbol.equals("7") || symbol.equals("8") || symbol.equals("9")){
                // inserta elementos al árbol de expresión
                expTree.insert(symbol);

            }else{ // reordena un par de nodos del stack para ir construyendo el árbol de expresión
                Node root = new Node(symbol);

                Node right = stack.pop();
                root.setRight(right);

                Node left = stack.pop();
                root.setLeft(left);

                expTree.insertNode(root);

                stack.push(root); // añade el nodo al stack, con sus respectivos hijos asociados
            }
        }

    }

    // calcula el resultado numérico de la expresión postfix
    public static int calcPostFix(String exp){
        System.out.println("Normal: " + exp);
        String postFixed = inToPost(exp);
        System.out.println("PostFix: " + postFixed);

        createExprTree(exp); // CONSTRUYE EL ÁRBOL DE EXPRESIÓN
        Stack stack = new Stack();

        int len = postFixed.length();
        String symbol;
        int result = 0;


        for(int n = 0; n< len; n++){
            symbol = String.valueOf(postFixed.charAt(n)); //lee cada letra de la expresión en string

            if(symbol.equals("0") || symbol.equals("1") || symbol.equals("2") || symbol.equals("3")
                    || symbol.equals("4") || symbol.equals("5") || symbol.equals("6")
                    || symbol.equals("7") || symbol.equals("8") || symbol.equals("9")){
                // si el symbol es un número, lo añade al stack
                stack.push(Integer.valueOf(symbol));

            }else { // si el symbol es operador. saca 2 nums del stack, los opera y luego mete el result al stack
                int num1 = (int) stack.pop().getData();
                int num2 = (int) stack.pop().getData();

                if(symbol.equals("+")){
                    result = num2 + num1;
                    System.out.println(" " + num2 + " + " + num1 + " = " + result);
                }else if(symbol.equals("-")){
                    result = num2 - num1;
                    System.out.println(" " + num2 + " - " + num1 + " = " + result);
                }else if(symbol.equals("/")){
                    result = num2 / num1;
                    System.out.println(" " + num2 + " / " + num1 + " = " + result);
                }else if(symbol.equals("%")){
                    result = (num2/100) * num1;
                    System.out.println(" "+ num2 + " porciento de " + num1 + " = " + result);
                }
                else{
                    result = num2 * num1;
                    System.out.println(" " + num2 + " * " + num1 + " = " + result);
                }
                stack.push(result);
            }
        }
        System.out.println("Resultado: " + result);
        return result;
    }
}

