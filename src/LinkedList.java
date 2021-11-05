/**
 * Estructura de datos para almacenar cada elemento de la expresión postFix creada
 * @author Jimena Leon Huertas
 */
public class LinkedList {
    private Node head;
    private int size;

    /**
     * Constructor de la lista enlazada simple. Inicializa sus atributos.
     */
    public LinkedList(){
        this.head = null;
        this.size = 0;
    }

    /**
     * Añade un nodo al final de la lista
     * @param data el contenido del nuevo elemento (Node) por añadir
     */
    public void insertLast(Object data){ // añade un nuevo nodo al final de la lista
        Node newNode = new Node(data);

        if (this.isEmpty()){ // si la lista está vacía se define el nuevo nodo como la cabeza
            this.head = newNode;

        }else{
            Node current = this.head; // nodo auxiliar o temporal
            while (current.getNext() != null){ // recorre la lista mientras haya elementos
                current = current.getNext();
            }
            current.setNext(newNode); //= newNode; // se define el "siguiente" como el nuevo nodo
        }
        this.size++;
    }

    /**
     * Elimina un nodo al final de la lista
     * @return Retorna el contenido (string)del nodo eliminado
     */
    public String deleteLast(){
        int len = this.getSize();
        Node current = this.head;
        Node deleted;

        if(len > 1){
            while(current.getNext().getNext() != null){
                current = current.getNext();
            }

            deleted = current.getNext();
            current.setNext(null);

            this.size--;

        } else{
            deleted = current;
            current = null; // perdemos el elemento = lo borramos
            this.head = null;
            this.size--;

        }

        return (String) deleted.getData();
    }

    /**
     * Obtiene la cantidad de nodos que hay en la lista. Es decir, el tamaño.
     * @return Retorna el tamaño (int) de la lista
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Indica si la lista está vacía o no
     * @return Retorna un booleano indicando si el head de la lista es nulo o no
     */
    public boolean isEmpty(){ // verifies if the list is empty
        return this.head == null;
    }

    /**
     * Recorre la lista en orden hasta llegar al nodo en la posición indicada en el parámetro
     * @param position Recibe la posición en donde está el nodo
     * @return Retorna el contenido (string) del nodo ubicado en la posición indicada en el parámetro
     */
    public String nodeAt(int position){
        int len = this.getSize();
        Node current = this.head;
        int cont = 0;

        while(current != null && cont < position){
            current = current.getNext();
            cont++;
        }
        return (String) current.getData();
    }
}
