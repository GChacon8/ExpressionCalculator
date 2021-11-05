/**
 * Estructura de datos ayudante para crear la expresión matemática en notación PostFix
 * @author Jimena Leon Huertas
 */
public class Stack {
    private Node head;
    private int size;

    /**
     * Crea una instancia e inicializa sus atributos head y size
     */
    public Stack() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Mira el contenido del nodo head del stack. El head en este caso es el nodo ingresado más reciente al Stack
     * @return Retorna el contenido del nodo más reciente. El cual sería el head
     */
    public Object peek(){
        return this.head.getData();
    }

    /**
     * Añade un nuevo nodo al Stack y lo establece como el nuevo head
     * @param data Recibe el contenido del nuevo nodo por añadir
     */
    public void push(Object data) { // añade al final del stack y la cabeza ahora es el más reciente
        Node newNode = new Node(data);
        newNode.setNext(this.head);
        this.head = newNode;
        this.size++;
    }

    /**
     * Elimina el nodo más reciente - el head - del Stack
     * @return Retorna el contenido del nodo eliminado, es decir el head
     */
    public Node pop() { // pierde el primer nodo, pero me lo devuelve para saber cuál fue
        if (this.head != null) {
            Node temp = this.head;
            this.head = this.head.getNext();
            this.size--;
            return temp;
        } else {
            return null;
        }
    }

    /**
     * Indica el tamaño del Stack
     * @return Retorna el tamaño (int) o la cantidad de elementos en el Stack
     */
    public int getSize(){
        return this.size;
    }
}