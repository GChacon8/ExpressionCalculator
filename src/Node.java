public class Node {
    private Object data;
    private Node next;
    private Node right;
    private Node left;

    public Node(Object data) {
        this.next = null;
        this.data = data;
        this.right = null;
        this.left = null;
    }

    public Object getData() {return this.data;}

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public Node getRight() {
        return this.right;
    }

    public void setRight(Node treenode) {
        this.right = treenode;
    }

    public Node getLeft() {
        return this.left;
    }

    public void setLeft(Node treenode) {
        this.left = treenode;
    }

}
