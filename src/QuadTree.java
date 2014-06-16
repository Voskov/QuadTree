import java.util.HashSet;

public class QuadTree {
    private Node node;

    public Node getNode() {
        return node;
    }

    public void insert(double x, double y) {
        if (node == null) {
            node = new Node(x, y);
        } else {
            insert(node, x, y);
        }
    }

    public void insert(Node node, double x, double y) {
        if (x <= node.x && y <= node.y)  {
            if (node.SW == null) {
                node.SW = new QuadTree();
            }
            node.SW.insert(x, y);
        }
        else if (x <= node.x && y > node.y) {
            if (node.NW == null) {
                node.NW = new QuadTree();
            }
            node.NW.insert(x, y);
        }
        else if (x > node.x && y <= node.y)  {
            if (node.SE == null) {
                node.SE = new QuadTree();
            }
            node.SE.insert(x, y);
        }
        else if (x > node.x && y > node.y) {
            if (node.NE == null) {
                node.NE = new QuadTree();
            }
            node.NE.insert(x, y);
        }
    }

    public boolean isWithin(Node center, Node other, double radius) {
        double distance = Math.sqrt((center.x - other.x) * (center.x - other.x) + (center.y - other.y) * (center.y - other.y));
        return (distance < radius);
    }

    public HashSet allWithin(QuadTree tree, Node center, double radius) {
        if (tree == null) return null;
        HashSet allNodes = new HashSet<Node>();
        if (isWithin(center, tree.node, radius)) {
            allNodes.add(tree.node);
            allNodes.add(allWithin(tree.node.NE, center, radius));
            allNodes.add(allWithin(tree.node.NW, center, radius));
            allNodes.add(allWithin(tree.node.SE, center, radius));
            allNodes.add(allWithin(tree.node.SW, center, radius));
        } else if (center.x >= node.x && center.y >= node.y) allNodes.add(allWithin(tree.node.NE, center, radius));
        else if (center.x >= node.x && center.y < node.y) allNodes.add(allWithin(tree.node.SE, center, radius));
        else if (center.x < node.x && center.y >= node.y) allNodes.add(allWithin(tree.node.NW, center, radius));
        else if (center.x < node.x && center.y < node.y) allNodes.add(allWithin(tree.node.SW, center, radius));
        return allNodes;
    }
}
