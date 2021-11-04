import java.util.Stack;

public class ExpTree extends Server{
    Stack stack;
    Node root, right, left;

    public ExpTree(){
        stack = null;
        this.root = this.right = this.left = null;
    }

    public Node buildTree(LinkedList postFixed){
        Stack<Node> stack = new Stack<Node>();
        int len = postFixed.getSize();
        String symbol;

        for(int i=0; i<len; i++){
            symbol = postFixed.nodeAt(i);

            if(!isOperator(symbol)){
                root = new Node(symbol);
                stack.push(root);
            }else{
                root = new Node(symbol);

                if(len>1){
                    right = stack.pop();

                    root.setRight(right);

                    left = stack.pop();
                    root.setLeft(left);
                }


                stack.push(root);
            }
        }
        root = (Node) stack.peek();
        stack.pop();
        System.out.println("");
        System.out.println("El árbol de expresión es:");
        //this.display(root);
        System.out.println("");
        return root;



    }

    public void printPostFix(Node node) {
        if (node == null) {
            return;
        }

        // first recur on left subtree
        printPostFix(node.getLeft());

        // then recur on right subtree
        printPostFix(node.getRight());

        // now deal with the node
        System.out.print(node.getData() + " ");

    }

    private int toInt(String str) {
        int num = 0;

        for(int i = 0; i < str.length(); i++)
            num = num * 10 + ((int)str.charAt(i) - 48);

        return num;
    }

    public int evalTree(Node root) {
        double aux;
        int resultado = 0;

        // Empty tree
        if (root == null)
            return 0;

        // Leaf node is an integer
        if (root.getLeft() == null && root.getRight() == null)
            return toInt((String) root.getData());

        // Evaluate left subtree
        int leftEval = evalTree(root.getLeft());

        // Evaluate right subtree
        int rightEval = evalTree(root.getRight());

        // Check which operator to apply
        if (root.getData().equals("+")){
            resultado = leftEval + rightEval;

        }else if (root.getData().equals("-")){
            resultado = leftEval - rightEval;

        } else if (root.getData().equals("*")){
            resultado = leftEval * rightEval;

        } else if (root.getData().equals("%")){
            aux = ((double) leftEval/100) * rightEval;
            resultado = (int) aux;

        } else if (root.getData().equals("/")){
            resultado = leftEval/rightEval;

        }else{
            resultado = -1; // en caso de haber escrito un símbolo sin sentido
        }


        return resultado;
    }
}
