/**
 * Define los elementos base para las estructuras de datos a utilizar, sus punteros e hijos
 * @author Jimena Leon Huertas
 */
public class Node {
    private Object data;
    private Node next;
    private Node right;
    private Node left;

    /**
     * Instancia un nodo e inicializa sus atributos
     * @param data El contenido del nodo
     */
    public Node(Object data) {
        this.next = null;
        this.data = data;
        this.right = null;
        this.left = null;
    }

    /**
     * Obtiene el dato contenido en un nodo
     * @return Retorna el dato (object) contenido en un nodo
     */
    public Object getData() {return this.data;}

    /**
     * Obtiene el nodo siguiente a uno actual
     * @return Retorna el nodo (Node) siguiente
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * Establece que el nodo siguiente sea igual al ingresado como parámetro
     * @param node Un nodo existente
     */
    public void setNext(Node node) {
        this.next = node;
    }

    /**
     * Obtiene el hijo derecho de un nodo
     * @return Retorna el hijo derecho (Node) de un nodo
     */
    public Node getRight() {
        return this.right;
    }

    /**
     * Establece que el hijo derecho de un nodo sea igual al ingresado como parámetro. Se usa en los nodos que conforman el árbol binario de expresión
     * @param treenode Un nodo existente
     */
    public void setRight(Node treenode) {
        this.right = treenode;
    }

    /**
     * Obtiene el hijo izquierdo de un nodo
     * @return Retorna el hijo izquierdo (Node) de un nodo
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     * Establece que el hijo izquierdo de un nodo sea igual al ingresado como parámetro. Se usa en los nodos que conforman el árbol binario de expresión
     * @param treenode Un nodo existente
     */
    public void setLeft(Node treenode) {
        this.left = treenode;
    }

}
