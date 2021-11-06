import java.util.Stack;

/**
 * Árbol binario de expresión utilizado para calcular el resultado de la expresión matemática ingresada en la calculadora
 * @author Jimena Leon Huertas
 */
public class ExpTree extends Server{
    Node root, right, left;

    /**
     * Inicializa una instancia de la clase con sus atributos nulos
     */
    public ExpTree(){
        this.root = this.right = this.left = null;
    }

    /**
     * Construye el árbol nodo a nodo utilizando un Stack
     * @param postFixed Recibe la lista que contiene la expresión matemática en notación postFix
     * @return Retorna el root del árbol, el cual estará relacionado con los hijos
     */
    public Node buildTree(LinkedList postFixed){
        Stack<Node> stack = new Stack<Node>();
        int len = postFixed.getSize();
        String symbol;

        for(int i=0; i<len; i++){
            symbol = postFixed.nodeAt(i);

            if(!isOperator(symbol)){
                root = new Node(symbol);
                stack.push(root);
            }else{
                root = new Node(symbol);

                if(len>1){
                    right = stack.pop();

                    root.setRight(right);

                    left = stack.pop();
                    root.setLeft(left);
                }
                stack.push(root);
            }
        }
        root = (Node) stack.peek();
        stack.pop();
        System.out.println("");
        System.out.println("El árbol de expresión es:");

        return root;
    }


    /**
     * Recorre recursivamente el árbol e imprime en consola sus nodos en notación postFix
     * @param node Recibe el root para empezar a recorrer el árbol
     */
    public void printPostFix(Node node) {
        if (node == null) {
            return;
        }

        // recorre subárbol izquierdo
        printPostFix(node.getLeft());

        // recorre subárbol derecho
        printPostFix(node.getRight());

        // muestra el contenido del nodo
        System.out.print(node.getData() + " ");

    }

    /**
     * Convierte los nodos numéricos del árbol en su notación numérica normal
     * @param str El contenido del nodo por convertir
     * @return Retorna el número ya construido
     */
    private int toInt(String str) {
        int num = 0;

        for(int i = 0; i < str.length(); i++)
            num = num * 10 + ((int)str.charAt(i) - 48);

        return num;
    }

    /**
     * Evalúa recursivamente el árbol para calcular el resultado de la expresión matemática contenida en él.
     * @param root Recibe el root del árbol para empezar a recorrerlo recursivamente
     * @return Retorna el resultado final de la expresión conformada por todos sus nodos
     */
    public int evalTree(Node root) {
        double aux;
        int resultado = 0;

        // Arbol vacio
        if (root == null)
            return 0;

        // Cuando el nodo es un numero
        if (root.getLeft() == null && root.getRight() == null)
            return toInt((String) root.getData());

        // Evalua el subarbol izquierdo
        int leftEval = evalTree(root.getLeft());

        // Evalua el subarbol derecho
        int rightEval = evalTree(root.getRight());

        // Aplica un operador dependiendo del caso y obtiene el resultado de esa operacion
        if (root.getData().equals("+")){
            resultado = leftEval + rightEval;

        }else if (root.getData().equals("-")){
            resultado = leftEval - rightEval;

        } else if (root.getData().equals("*")){
            resultado = leftEval * rightEval;

        } else if (root.getData().equals("%")){
            aux = ((double) leftEval/100) * rightEval;
            resultado = (int) aux;

        } else if (root.getData().equals("/")){
            resultado = leftEval/rightEval;

        }else{
            resultado = -1; // en caso de haber escrito un símbolo sin sentido
        }


        return resultado;
    }
}
