public class QuadTree {
    private Node root;

    public void insert(double x, double y) {
        if (root == null) {
            root = new Node(x, y);
        } else {
            insert(root, x, y);
        }
    }

    public void insert(Node node, double x, double y) {
        if (x <= node.x && y <= node.y) node.SW.insert(x, y);
        else if (x <= node.x && y > node.y) node.NW.insert(x, y);
        else if (x > node.x && y <= node.y) node.SE.insert(x, y);
        else if (x > node.x && y > node.y) {
            node.NE = new QuadTree();
            node.NE.insert(x, y);
        } ;
    }


}
