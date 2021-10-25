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

    public void display(Node node){
        String str = "";

        if (node.getLeft() == null){
            str += ".";
        }else{
            str += node.getLeft().getData();
        }

        str += " <- " + node.getData() + " -> ";

        if(node.getRight() == null){
            str += ".";
        }else{
            str += node.getRight().getData();
        }
        display(node.getLeft());
        display(node.getRight());
    }
}
