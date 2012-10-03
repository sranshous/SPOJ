import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * Approach: Use breadth first search
 * We can start with 1 node that is the current room number
 * From there we add the reachable nodes that are also prime
 * We continue this until we reach the target door number
 */

class PPATH {
    public static BufferedReader br = null;
    public static StringBuilder sb = new StringBuilder();

    /* All primes for the problem are 4 digits */
    public static final int MAX_NUM = 10000;
    public static boolean[] PRIMES = new boolean[MAX_NUM+1];

    /* this will be used by BFS to make sure we dont waste time */
    public static boolean[] visited = new boolean[MAX_NUM+1];
    public static boolean[] added = new boolean[MAX_NUM+1];

    /* Adjacency List */
    public static ArrayList<ArrayList<Integer>> al;


    public static void main(String[] args) {
        /* Get primes */

        int i, j;
        for(i = 0; i < PRIMES.length; i++)
            PRIMES[i] = true;

        for(i = 2; i < PRIMES.length; i++)
            if(PRIMES[i])
                for(j = 2*i; j < PRIMES.length; j += i)
                    PRIMES[j] = false;

        br = new BufferedReader(new InputStreamReader(System.in));

        buildGraph();

        try {
            int numCases = Integer.parseInt(br.readLine());

            for(i = 0; i < numCases; i++) {
                String[] line = br.readLine().split(" ");
                int roomNum = Integer.parseInt(line[0]);
                int targetNum = Integer.parseInt(line[1]);
                int cost;
                Node root = new Node(roomNum, 0);

                if((cost = BFS(root, targetNum)) == -1)
                    sb.append("Impossible\n");
                else
                    sb.append(cost + "\n");
            }
            System.out.print(sb.toString());
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    /*
     * This method is what will find the cheapest path to
     * the target prime.
     * If found it will return the cost
     * If not found it will return -1
     */
    public static int BFS(Node root, int target) {
        visited = new boolean[MAX_NUM];
        // Our queue for the breadth first search
        ArrayDeque<Node> queue = new ArrayDeque<Node>();
        queue.add(root);
        visited[root.primeNum] = true;

        while(!queue.isEmpty()) {
            Node current = queue.poll();
            //System.out.println("Current value " + current.primeNum + "\tCost " + current.cost);
            if(current.primeNum == target) {
                return current.cost;
            }

			/* We subtract 1000 because of the offset of the adjacency list mentioned below */
            for(int j = 0; j < al.get(current.primeNum-1000).size(); j++) {
                if(!visited[al.get(current.primeNum-1000).get(j)]) {
                    queue.add(new Node(al.get(current.primeNum-1000).get(j), current.cost+1));
                    visited[al.get(current.primeNum-1000).get(j)] = true;
                }
            }
        }

        return -1;
    }

    /*
     * Build the entire graph of primes for the program.
     * It will be represented in an adjacency list.
     * The adjacency list will begin at index 0 which
     * will represent the numbers adjacent to 1000
     */
    public static void buildGraph() {
        al = new ArrayList<ArrayList<Integer>>(MAX_NUM);

        for(int i = 1000; i < MAX_NUM; i++) {
            // dont bother including composite numbers in the graph
            // add an empty placeholder for indexing purposes
            if(PRIMES[i] == false) {
                //System.out.println("Skipping " + i);
                ArrayList<Integer> adjacentPrimes = new ArrayList<Integer>();
                al.add(adjacentPrimes);
                continue;
            }

            ArrayList<Integer> adjacentPrimes = new ArrayList<Integer>();

            int ones = i % 10;
            int tens = (i / 10) % 10;
            int hundreds = (i / 100) % 10;
            int thousands = (i / 1000);

            // do ones column, tens column, and thousands column
            for(int j = 0; j < 10; j++) {
                if(j != ones) {
                    int temp = (i - ones) + j;
                    if(temp < 10000 && temp > 1000 && PRIMES[temp]) // we are adjacent to the prime so add it
                        adjacentPrimes.add(temp);
                }
            }
            for(int j = 0; j < 10; j++) {
                if(j != tens) {
                    int temp = (i - (10*tens)) + (j*10);
                    if(temp < 10000 && temp > 1000 && PRIMES[temp]) // we are adjacent to the prime so add it
                        adjacentPrimes.add(temp);
                }
            }
            for(int j = 0; j < 10; j++) {
                if(j != hundreds) {
                    int temp = (i - (100*hundreds)) + (j*100);
                    if(temp < 10000 && temp > 1000 && PRIMES[temp]) // we are adjacent to the prime so add it
                        adjacentPrimes.add(temp);
                }
            }
            for(int j = 0; j < 10; j++) {
                if(j != thousands) {
                    int temp = (i - (1000*thousands)) + (j*1000);
                    if(temp < 10000 && temp > 1000 && PRIMES[temp]) // we are adjacent to the prime so add it
                        adjacentPrimes.add(temp);
                }
            }
            al.add(adjacentPrimes);
        }
    }
}

class Node {
    public int primeNum, cost;

    public Node(int primeNum, int cost) {
        this.primeNum = primeNum;
        this.cost = cost;
    }
}
