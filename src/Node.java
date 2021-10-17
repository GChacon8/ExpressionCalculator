public class Node {
    public Object data;
    public Node next;
    public Node right;
    public Node left;

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
