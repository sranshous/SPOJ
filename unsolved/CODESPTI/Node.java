import java.util.ArrayList;

public class Node {
    // Properties
    private ArrayList<Integer> adjacent;
    private boolean marked = false;
    private Integer vertexNum;

    public Node(Integer vertex) {
        adjacent = new ArrayList<Integer>();
        vertexNum = vertex;
    }

    public void mark() {
        marked = true;
    }

    public void unmark() {
        marked = false;
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public int getNumAdjacent() {
        return adjacent.size();
    }

    public void addAdjacent(Node n) {
        adjacent.add(n.getVertexNum());
    }

    public void addAdjacent(Integer n) {
        adjacent.add(n);
    }

    public String toString() {
        String s = "Vertex Number: " + vertexNum + "\tMarked: " + marked + "\tAdjacent to:";
        for(int i = 0; i < adjacent.size(); i++) {
            s += " " + adjacent.get(i);
        }
        return s;
    }
}
