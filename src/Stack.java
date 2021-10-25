public class Stack {
    private Node head;
    private int size;

    public Stack() {
        this.head = null;
        this.size = 0;
    }

    public Object peek(){
        return this.head.getData();
    }

    public void push(Object data) { // añade al final del stack y la cabeza ahora es el más reciente
        Node newNode = new Node(data);
        newNode.setNext(this.head);
        this.head = newNode;
        this.size++;
    }

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

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){ // verifies if the list is empty
        return this.head == null;
    }

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