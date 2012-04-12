import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Graph {
    // Properties
    private HashMap<Integer, Node> nodes;
    private ArrayList<Edge> edges;

    public Graph() {
        nodes = new HashMap<Integer, Node>();
        edges = new ArrayList<Edge>();
    }

    // add a node only if our hashmap does not already
    // have a key/value pair for it
    public void addNode(Node n) {
        if(!nodes.containsKey(n.getVertexNum()))
            nodes.put(n.getVertexNum(), n);
    }

    // replace the value in the hashmap with null
    public void removeNode(Node n) {
        nodes.put(n.getVertexNum(), null);
    }

    // add an edge only if our arraylist does not already
    // have a matching edge
    public void addEdge(Edge e) {
        if(!edges.contains(e))
            edges.add(e);
    }

    // remove edge from list
    public void removeEdge(Edge e) {
        edges.remove(e);
    }

    public ArrayList<Edge> getNodesEdges() {
        ArrayList<Edge> e = new ArrayList<Edge>();

        for(int i = 0; i < edges.size(); i++) {
            if(edges.get)
        }
    }

    public void removeLeaves() {
        Iterator it = nodes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            Node n = (Node)(pairs.getValue());
            if(n.getNumAdjacent() == 1) {
                System.out.println(pairs.getKey() + " = " + pairs.getValue());
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    public String toString() {
        String s = "Graph:\n------------\nNodes:";
        for(Integer key : nodes.keySet()) {
            s += "\n" + nodes.get(key);
        }
        s += "\nEdges:";
        for(int i = 0; i < edges.size(); i++) {
            s += "\nEdge " + i + ":\n";
            s += edges.get(i);
        }
        return s;
    }
}
