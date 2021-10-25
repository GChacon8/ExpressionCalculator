public class LinkedList {
    private Node head;
    private Node last;
    private int size;

    public LinkedList(){
        this.head = null;
        this.last = null;
        this.size = 0;
    }

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

    public String deleteLast(){
        int len = this.getSize();
        Node current = this.head;
        Node deleted;

        if(len > 1){
            while(current.getNext().getNext() != null){
                current = current.getNext();
            }

            deleted = current.getNext();
            System.out.println("Deleted" + deleted.getData());
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

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){ // verifies if the list is empty
        return this.head == null;
    }

    // recorre al linkedList de izq a der para mostrar el nodo
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

    public String display(){
        String expPF = "";
        Node current = this.head;
        int cont = 0;
        int len = getSize();

        while(current != null && cont < size){
            if(cont >= 1){
                expPF += " -> ";
            }
            expPF += current.getData();
            current = current.getNext();
            cont++;
        }
        return expPF;
    }
}
