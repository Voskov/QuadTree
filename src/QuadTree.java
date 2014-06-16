import java.util.HashSet;

public class QuadTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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
        }
    }

    public boolean isWithin(Node center, Node other, double radius) {
        double distance = Math.sqrt(other.x * other.x + other.y * other.y);
        return (distance < radius);
    }

    public HashSet allWithin(QuadTree tree, Node center, double radius) {
        HashSet allNodes = new HashSet<Node>();
        if (isWithin(center, this.root, radius)) {
            allNodes.add(this.root);
            allNodes.add(this.root.NE);
            allNodes.add(this.root.NW);
            allNodes.add(this.root.SE);
            allNodes.add(this.root.SW);
        }
        else if (center.x >= root.x && center.y >= root.y) allNodes.add(allWithin(this.root.NE, center, radius));
        else if (center.x >= root.x && center.y < root.y) allNodes.add(allWithin(this.root.SE, center, radius));
        else if (center.x < root.x && center.y >= root.y) allNodes.add(allWithin(this.root.NW, center, radius));
        else if (center.x < root.x && center.y < root.y) allNodes.add(allWithin(this.root.SW, center, radius));
        return allNodes;
    }
}
