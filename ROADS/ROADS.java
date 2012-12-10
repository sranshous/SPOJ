import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class ROADS {
    private static BufferedReader br;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        int numCases = readInt();

        for(int i = 0; i < numCases; i++) {
            int numCoins = readInt();
            int numCities = readInt();
            int numRoads = readInt();

            ArrayList<ArrayList<Edge>> graph = readGraph(numRoads, numCities);

            System.out.println(findPath(graph, numCoins, numCities));
        }
    }

    private static int readInt() throws Exception {
        return Integer.parseInt(br.readLine());
    }

    private static ArrayList<ArrayList<Edge>> readGraph(int numRoads, int numCities) throws Exception {
        ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>(numCities+1);
        for(int j = 0; j < numCities+1; j++) {
            graph.add(new ArrayList<Edge>());
        }

        for(int j = 0; j < numRoads; j++) {
            /* road[0] = source
             * road[1] = destination
             * road[2] = distance
             * road[3] = cost */
            String[] road = br.readLine().split(" ");

            // dont add a self loop
            if(road[0].equals(road[1]))
                continue;

            graph.get(Integer.parseInt(road[0])).add(new Edge(Integer.parseInt(road[0]),
                                                              Integer.parseInt(road[1]),
                                                              Integer.parseInt(road[2]),
                                                              Integer.parseInt(road[3])));
        }

        return graph;
    }

    private static int findPath(ArrayList<ArrayList<Edge>> graph, int numCoins, int target) {
        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();

        /* Find the shortest path that is within budget or -1 if we
         * cannot get there with our coins */
        pq.offer(new Vertex(1, 0, 0)); // starting at city 1
        while(!pq.isEmpty()) {
            Vertex v = pq.poll();
            if(v.vertexNum == target)
                return v.totalDistance;

            for(Edge e : graph.get(v.vertexNum)) {
                if(v.totalCost + e.cost <= numCoins) {
                   //(v.totalCost + e.cost == numCoins && e.destination == target))) {
                    //!visited[e.destination]) {
                    Vertex w = new Vertex(e.destination, v.totalDistance + e.distance,
                                        v.totalCost + e.cost);
                    pq.offer(w);
                }
            }
        }

        return -1; // we didn't find a path to the target
    }

    private static void printGraph(ArrayList<ArrayList<Edge>> graph) {
        for(int i = 1; i < graph.size(); i++) {
            for(Edge e : graph.get(i)) {
                System.out.println("source: " + e.source);
                System.out.println("\tdestination: " + e.destination);
                System.out.println("\t\tdistance: " + e.distance);
                System.out.println("\t\tcost: " + e.cost);
            }
        }
    }
}

class Edge {
    public Integer source, destination, distance, cost;

    public Edge(Integer source, Integer destination, Integer distance, Integer cost) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.cost = cost;
    }

    public String toString() {
        return "source " + source + " destination " + destination + " distance " + distance + " cost " + cost;
    }
}

class Vertex implements Comparable<Vertex> {
    public Integer vertexNum, totalDistance, totalCost;

    public Vertex(Integer vertexNum, Integer totalDistance, Integer totalCost) {
        this.vertexNum = vertexNum;
        this.totalDistance = totalDistance;
        this.totalCost = totalCost;
    }

    public int compareTo(Vertex v) {
        if(this.totalDistance < v.totalDistance)
            return -1;
        else if(this.totalDistance > v.totalDistance)
            return 1;
        else
            return 0;
    }

    public String toString() {
        return "vertexNum = " + vertexNum + " totalDistance = " + totalDistance + " totalCost = " + totalCost;
    }
}
