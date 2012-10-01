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

/*
 * TODO: Improve time by creating the entire graph (adjacency list) that
 * will be used by the program the entire time. Never need to alter the graph
 * only create new queues each time.
 * TODO: Write the BFS using the adjacency list.
 */

public class PPATH {
    public static BufferedReader br = null;

    /* All primes for the problem are 4 digits */
    public static final int MAX_NUM = 10000;
    public static boolean[] PRIMES = new boolean[MAX_NUM];

    /* this will be used by BFS to make sure we dont waste time */
    public static boolean[] visited;

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

        try {
            int numCases = Integer.parseInt(br.readLine());

            for(i = 0; i < numCases; i++) {
                int roomNum = Integer.parseInt(br.readLine());
                int targetNum = Integer.parseInt(br.readLine());
                int cost;
                Node root = new Node(roomNum, 0);

                if((cost = BFS(root, targetNum)) == -1)
                    System.out.println("Impossible");
                else
                    System.out.println(cost);
            }
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

        while(!queue.isEmpty()) {
            
        }

        return -1;
    }

    /*
     * Build the entire graph of primes for the program.
     * It will be represented in an adjacency list.
     */
    public static void buildGraph() {
        al = new ArrayList<ArrayList<Integer>>(MAX_NUM);

        for(int i = 0; i < MAX_NUM; i++) {
            al.add(new ArrayList<Integer>());
        }
    }
}

class Node {
    int primeNum, cost;

    public Node(int primeNum, int cost) {
        this.primeNum = primeNum;
        this.cost = cost;
    }
}
