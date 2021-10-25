public class Node {
    private Object data;
    private Node next;
    private Node right;
    private Node left;

    public Node(Object data) {
        this.next = null;
        this.left = null;
        this.right = null;
        this.data = data;
    }

    public Object getData() {return this.data;}

    public void setData(int data) {
        this.data = data;
    }

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
