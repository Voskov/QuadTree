import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        QuadTree t = new QuadTree();
        t.insert(0,0);
        t.insert(0,1);
        t.insert(-1,0);
        t.insert(1,1);
        HashSet all = t.allWithin(t, new Node(2, 2), 2.0);
        System.out.println(all.toString());
    }
}
