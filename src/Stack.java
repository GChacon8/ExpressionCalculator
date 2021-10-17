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

    public void push(Object data) {
        Node newNode = new Node(data);
        newNode.next = this.head;
        this.head = newNode;
        this.size++;
    }

    public Node pop() { // pierde el primer nodo, pero me lo devuelve para saber cuál fue
        if (this.head != null) {
            Node temp = this.head;
            this.head = this.head.next;
            this.size--;
            return temp;
        } else {
            return null;
        }
    }

    public int getSize(){
        return this.size;
    }
}