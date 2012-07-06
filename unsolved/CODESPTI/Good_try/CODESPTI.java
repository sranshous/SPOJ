import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.PriorityQueue;


class CODESPTI {
    public static void main(String[] args) {
        BufferedReader br = null;

        // begin the analysis
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int numCases = Integer.parseInt(br.readLine().trim());

            for(int i = 0; i < numCases; i++) {
                Graph g = new Graph();
                int numCities = Integer.parseInt(br.readLine().trim());

                for(int j = 0; j < numCities; j++) {
                    g.addVertex(j);
                }

                // one less road (edge) than city
                for(int j = 0; j < numCities-1; j++) {
                    String[] edgeInfo = br.readLine().split(" ");
                    g.addAdjacent(Integer.parseInt(edgeInfo[0]), Integer.parseInt(edgeInfo[1]));
                    g.addEdge(Integer.parseInt(edgeInfo[0]), Integer.parseInt(edgeInfo[1]));
                }
                System.out.println("--------------------------");
                System.out.println(g.robotSearch(0));
                System.out.println(g.robotSearch(g.vertices.size()/2));
                System.out.println(g.robotSearch(g.vertices.size()-1));
           }

            if(br != null)
                br.close();
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}

class Graph {
    // A map where the Integer key is the vertex
    // and the array list is all the adjacent vertices
    public HashMap<Integer, ArrayList<Integer>> vertices;

    // Edges to ensure we do not use the same edge twice
    private ArrayList<Edge> edges;

    // robots needed
    private int numRobotsNeeded = 1;
    private ArrayDeque<Integer> availableNodes = new ArrayDeque<Integer>();

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
        //System.out.println("node: " + vertex + ", " + adjacentVertex);
        return vertices.get(vertex).remove(adjacentVertex);
    }

    public void addEdge(int head, int tail) {
        edges.add(new Edge(head, tail));
    }

    // this method will only remove the first instance of an edge
    // so if there are multiple of the same edges it will only delete
    // one of them
    public void removeEdge(int head, int tail) {
        for(int i = 0; i < edges.size(); i++) {
            // we check both because it is an undirected graph and we only have one edge
            // for both A->B and B->A
            if((edges.get(i).getHead() == head && edges.get(i).getTail() == tail)
                    || (edges.get(i).getHead() == tail && edges.get(i).getTail() == head)) {
                //System.out.println(edges.get(i));
                edges.remove(i);
                return;
            }
        }
    }

    public int robotSearch(Integer startVertex) {
        System.out.println("start vertex is: " + startVertex);
        availableNodes.add(startVertex);
        while(!availableNodes.isEmpty()) {
            robos(availableNodes.poll());
        }
        return numRobotsNeeded;
    }

    private void robos(int vertex) {
        // base case
        if(vertices.get(vertex).size() == 0)
            return;

        // we need to explore all the nodes connected that have no extra edges
        for(int i = 0; i < vertices.get(vertex).size(); i++) {
            if(vertices.get(vertices.get(vertex).get(i)).size() == 0) {
                removeEdge(vertex, vertices.get(vertex).get(i));
                removeAdjacent(vertex, vertices.get(vertex).get(i));
                i--; // because we removed an element they all shift left
            }
        }

        if(vertices.get(vertex).size() == 1) {
            robos(vertices.get(vertex).get(0));
            removeEdge(vertex, vertices.get(vertex).get(0));
            removeAdjacent(vertex, vertices.get(vertex).get(0));
        }
        else if(vertices.get(vertex).size() == 0) {
            return;
        }
        else {
            //System.out.println("there are " + vertices.get(vertex).size() + " elements to explore");
            for(int i = 0; i < vertices.get(vertex).size()-1; i++) {
                availableNodes.add(vertices.get(vertex).get(i));
                removeEdge(vertex, vertices.get(vertex).get(i));
                removeAdjacent(vertex, vertices.get(vertex).get(i));
                numRobotsNeeded++;
            }
            robos(vertices.get(vertex).get(vertices.get(vertex).size()-1));
            removeEdge(vertex, vertices.get(vertex).get(vertices.get(vertex).size()-1));
            removeAdjacent(vertex, vertices.get(vertex).get(vertices.get(vertex).size()-1));
        }
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

    public String toString() {
        return "Edge connecting " + head + " and " + tail;
    }
}
