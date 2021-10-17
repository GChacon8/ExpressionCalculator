public class ExpTree {
    private Node root;

    public ExpTree(){
        this.root = null;
    }

    public void insert(Object data){
        Node newTreeNode = new Node(data);
    }

    public void insertNode(Node known){
        Node newTreeNode = known;
    }

    public void display(){}
}
