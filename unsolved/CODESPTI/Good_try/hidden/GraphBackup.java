import java.util.HashMap;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class Graph {
    // A map where the Integer key is the vertex
    // and the array list is all the adjacent vertices
    private HashMap<Integer, ArrayList<Integer>> vertices;

    // Edges to ensure we do not use the same edge twice
    ArrayList<Edge> edges;

    // constructor
    public Graph() {
        vertices = new HashMap<Integer, ArrayList<Integer>>();
        edges = new ArrayList<Edge>();
    }

    // Add a vertex if it does not already exist
    public boolean addVertex(Integer vertex) {
        if(!vertices.containsKey(vertex)) {
            vertices.put(vertex, new ArrayList<Integer>());
            return true;
        }
        else
            return false;
    }

    // Place an adjacent vertex in the list of the correct vertex
    public boolean addAdjacent(Integer vertex, Integer adjacentVertex) {
        if(vertices.containsKey(vertex)) {
            vertices.get(vertex).add(adjacentVertex);
            return true;
        }
        else
            return false;
    }

    public boolean removeAdjacent(Integer vertex, Integer adjacentVertex) {
        return vertices.get(vertex).remove(adjacentVertex);
    }

    public void addEdge(int head, int tail) {
        edges.add(new Edge(head, tail));
    }

    // No longer needed because our implementation will
    // remove edges that have been used from the list
    /*
    public boolean edgeUsed(int head, int tail) {
        Edge e = null;
        for(int i = 0; i < edges.size(); i++) {
            if(edges.get(i).getHead() == head && edges.get(i).getTail() == tail) {
                e = edges.get(i);
            }
        }

        if(e == null)
            return true;
        else
            return e.isUsed();
    }
    */

    // this method will only remove the first instance of an edge
    // so if there are multiple of the same edges it will only delete
    // one of them
    public void removeEdge(int head, int tail) {
        for(int i = 0; i < edges.size(); i++) {
            // we check both because it is an undirected graph and we only have one edge
            // for both A->B and B->A
            if((edges.get(i).getHead() == head && edges.get(i).getTail() == tail)
                    || (edges.get(i).getHead() == tail && edges.get(i).getTail() == head)) {
                edges.remove(i);
                return;
            }
        }
    }

    public int robotSearch() {
        // When this hits 0 we have repaired all the roads
        // and know to stop
        int numEdgesRemaining = edges.size();
        int numRobotsNeeded = 1;
        ArrayDeque<Integer> availableNodes = new ArrayDeque<Integer>();

        // add the first element to begin our search
        availableNodes.add(0);

        while(numEdgesRemaining != 0) {
            Integer current = availableNodes.poll();
            System.out.println("Current before the for loop: " + current);

            // if we are out of available nodes but there are still
            // edges to be repaired search through the vertices list
            // and find one not yet used and add a robot
            if(current == null) {
                for(int i = 0; i < vertices.size(); i++) {
                    if(vertices.get(i).size() != 0) {
                        current = i;
                    }
                }
                numRobotsNeeded++;
            }

            System.out.println("Current node being analyzed: " + current);
            for(int i = 0; i < vertices.get(current).size(); i++) {
                System.out.println(vertices.get(current).get(i));
            }

            Integer next = null;
            ArrayList<Integer> toRemove = new ArrayList<Integer>();

            for(int i = 0; i < vertices.get(current).size(); i++) {
                System.out.println("i = " + i + "\t" + vertices.get(current).get(i));
                System.out.println("Array for " + vertices.get(current).get(i) + "\t" + vertices.get(vertices.get(current).get(i)));
                // we need to repair the road (traverse the edge) of
                // the nodes with no extra edges attached so that we
                // maximize the path. Once we have exhausted all of
                // the nodes with no extra edges we may then pick
                // to follow one with extra edges (for now arbitrarily)
                // we will save it as we find it so that we do not
                // have to traverse the list again
                if(vertices.get(vertices.get(current).get(i)).size() == 0) {
                    toRemove.add(vertices.get(current).get(i));
                    numEdgesRemaining--;
                }
                else {
                    next = vertices.get(current).get(i);
                    System.out.println("next: " + next);
                    numEdgesRemaining--;
                }
            }

            // remove all adjacent nodes outside other loop for consistency
            // and to avoid messing up the loop
            for(int i = 0; i < toRemove.size(); i++) {
                System.out.println("Removed Edge: " + current + " " + toRemove.get(i));
                removeEdge(current, toRemove.get(i));
                System.out.println("Removed Adjacent: " + current + " " + toRemove.get(i));
                removeAdjacent(current, toRemove.get(i));
            }

            if(next != null)
                availableNodes.add(next);

            System.out.println("Num Robots Needed So Far: " + numRobotsNeeded);
            System.out.println("Num edges left: " + numEdgesRemaining);
        }

        return numRobotsNeeded;
    }

    public String toString() {
        String s = "Graph in somewhat of an adjacency list\n";
        for(int i = 0; i < vertices.size(); i++) {
            s += i;
            for(int j = 0; j < vertices.get(i).size(); j++) {
                s += " " + vertices.get(i).get(j);
            }
            s += "\n";
        }

        return s;
    }
}

class Edge {
    private int head; // head vertex
    private int tail; // tail vertex
    boolean used = false; // False is not used, once used we set to true

    public Edge(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public void use() {
        used = true;
    }

    public boolean isUsed() {
        return used;
    }
}
