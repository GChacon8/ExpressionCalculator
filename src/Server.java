import org.jetbrains.annotations.NotNull;
import java.io.DataOutputStream;
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
    public static @NotNull LinkedList inToPost(@NotNull String exp){
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

    // crea el árbol de expresión usando el string postfixeado
    public static void createExprTree(LinkedList postFixed){
        Node root = null;
        Stack stack = new Stack();
        int len = postFixed.getSize();
        String symbol;
        String prev = "";
        int depth = 1;


        for(int n = 0; n< len; n++){
            symbol = postFixed.nodeAt(n);

            if(n>0){
                prev = postFixed.nodeAt(n-1);
            }

            if(isDigit(symbol) && isOperator(prev)){
                Node rightChild = stack.pop(); // saca el árbol
                root.setRight(rightChild);
                stack.push(root);

            }else if(isDigit(symbol)){
                stack.push(symbol);
            }
            else if(isOperator(symbol)){ // reordena un par de nodos del stack para ir construyendo el árbol de expresión
                root = new Node(symbol);
                Node leftChild = stack.pop();
                root.setLeft(leftChild);

                stack.push(root); // añade el nodo al stack, con sus respectivos hijos asociados
                depth++;
            }
        }

        display(root, depth);
    }

    // calcula el resultado numérico de la expresión postfix
    public static int calcPostFix(String exp){
        System.out.println("Normal: " + exp);
        LinkedList postFixed = inToPost(exp);
        System.out.println("PostFix: " + postFixed.display());
        Stack stack = new Stack();
        int len = postFixed.getSize();
        String symbol;
        int result = 0;


        for(int n = 0; n< len; n++){
            symbol = postFixed.nodeAt(n); //lee cada letra de la expresión en string

            try{
                if(isDigit(symbol)){
                    // si el symbol es un número, lo añade al stack
                    stack.push(Integer.valueOf(symbol));

                }else { // si el symbol es operador. saca 2 nums del stack, los opera y luego mete el result al stack
                    try{
                        int num1 = (int) stack.pop().getData();
                        int num2 = (int) stack.pop().getData();

                        switch (symbol) {
                            case "+" -> {
                                result = num2 + num1;
                                System.out.println(" " + num2 + " + " + num1 + " = " + result);
                            }
                            case "-" -> {
                                result = num2 - num1;
                                System.out.println(" " + num2 + " - " + num1 + " = " + result);
                            }
                            case "/" -> {
                                result = num2 / num1;
                                System.out.println(" " + num2 + " / " + num1 + " = " + result);
                            }
                            case "%" -> {
                                result = (num2 / 100) * num1;
                                System.out.println(" " + num2 + " porciento de " + num1 + " = " + result);
                            }
                            default -> {
                                result = num2 * num1;
                                System.out.println(" " + num2 + " * " + num1 + " = " + result);
                            }
                        }
                        stack.push(result);

                    } catch (Exception e) {
                        System.out.println("Errorrrr");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Errorrrr");
            }
        }
        System.out.println("Resultado: " + result);
        createExprTree(postFixed); // CONSTRUYE EL ÁRBOL DE EXPRESIÓN CON LA LISTA POSTFIXED
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

    public static void display(Node node, int depth){
        String str = "";
        int cont = depth;

        if (node.getLeft() == null){
            str += ".";
        }else{
            str += node.getLeft().getData();
        }

        str += " <- " + node.getData() + " -> ";

        if(node.getRight() == null){
            str += ".";
        }else{
            str += node.getRight().getData();
        }
        depth--;

        System.out.println(str);

        if(node.getLeft() != null){
            display(node.getLeft(), depth);
        }

        if(node.getRight() != null){
            display(node.getRight(), depth);
        }

    }
}

