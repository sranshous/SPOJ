/* LOOK AT INPUT2 AND TRY TO RUN THATS WHERE THE PROBLEM IS */
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

class MTWALK {
    public static int[][] heights;
    public static boolean[][] used;
    public static Node[][] bestPath;
    public static int MATRIX_SIZE;
    public static final int INFINITY = 10000000;
    public static int numNodes;
    //public static int bestLow, bestHigh;

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            MATRIX_SIZE = Integer.parseInt(br.readLine().trim());
            heights = new int[MATRIX_SIZE][MATRIX_SIZE];
            bestPath = new Node[MATRIX_SIZE][MATRIX_SIZE];
            used = new boolean[MATRIX_SIZE][MATRIX_SIZE];
            numNodes = MATRIX_SIZE * MATRIX_SIZE;

            String line = "";
            String[] splitLine;

            for(int i = 0; i < MATRIX_SIZE; i++) {
                line = br.readLine();  // read the next line of the matrix
                splitLine = line.split(" ");  // split the list to seperate our numbers
                for(int j = 0; j < MATRIX_SIZE; j++) {
                    heights[i][j] = new Integer(splitLine[j]);  // convert from string to int for our matrix
                    used[i][j] = false;  // set every spot to false until we exhaust it
                    bestPath[i][j] = new Node(i, j);
                    bestPath[i][j].height = heights[i][j];
                }
            }

            bestPath[0][0].heightDiff = 0;
            //bestPath[0][0].lowest = heights[0][0];
            //bestPath[0][0].highest = heights[0][0];
            used[0][0] = true;

            for(int i = 0; i < MATRIX_SIZE; i++) {
                for(int j = 0; j < MATRIX_SIZE; j++) {
                    bestPath[i][j].heightDiff = Math.abs(heights[0][0] - heights[i][j]);
                }
            }

            int[] dy = {-1, 0, 1, 0};   // this will be used in the loop to check up, down, left and right
            int[] dx = {0, 1, 0, -1};   // of the current cell.

            PriorityQueue<Node> avail = new PriorityQueue<Node>(10, new NodeComparator());
            avail.add(bestPath[0][0]);

            /*
            while(!avail.isEmpty()) {

                if(numNodes > 15) {
                    Node[] print = avail.toArray(new Node[0]);
                    Arrays.sort(print, new NodeComparator());
                    System.out.println("--------Begin---------");
                    for(int i = 0; i < print.length; i++) {
                        System.out.println(print[i]);
                    }
                    System.out.println("--------END---------");
                }

                Node current = avail.poll();
                //bestLow = current.lowest;
                //bestHigh = current.highest;
                System.out.println("best high: " + bestHigh + "\tbest low: " + bestLow);
                System.out.println("current " + current);

                for(int i = 0; i < dx.length; i++) {
                    int nRow = current.row + dy[i];
                    int nColumn = current.col + dx[i];

                    //System.out.println("nRow " + nRow + " nColumn " + nColumn);
                    if(nRow >= 0 && nRow < MATRIX_SIZE && nColumn >= 0 && nColumn < MATRIX_SIZE && !used[nRow][nColumn]) {
                        //int nHighest = heights[nRow][nColumn] >= heights[0][0] && heights[nRow][nColumn] > current.highest ? heights[nRow][nColumn] : current.highest;
                        //int nLowest = heights[nRow][nColumn] <= heights[0][0] && heights[nRow][nColumn] < current.lowest ? heights[nRow][nColumn] : current.lowest;

                        //System.out.println("\t" + nHighest);
                        //System.out.println("\t" + nLowest);

                        //bestPath[nRow][nColumn].highest = nHighest;
                        //bestPath[nRow][nColumn].lowest = nLowest;

                        System.out.println("\t" + bestPath[nRow][nColumn]); avail.add(bestPath[nRow][nColumn]);
                    }
                    used[current.row][current.col] = true;
                }
                numNodes--;
            }
            */

            System.out.println("---- heights ----");
            for(int i = 0; i < MATRIX_SIZE; i++) {
                System.out.println(Arrays.toString(heights[i]));
            }
            System.out.println("---- bestPath ----");
            for(int i = 0; i < MATRIX_SIZE; i++) {
                System.out.println(Arrays.toString(bestPath[i]));
            }
            System.out.println("---- used ----");
            for(int i = 0; i < MATRIX_SIZE; i++) {
                System.out.println(Arrays.toString(used[i]));
            }

            //System.out.println(bestPath[MATRIX_SIZE-1][MATRIX_SIZE-1].highest - bestPath[MATRIX_SIZE-1][MATRIX_SIZE-1].lowest);
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}

class Node implements Comparable<Node> {
    public int heightDiff;
    public int height;
    //public int lowest;
    //public int highest;
    public int row;
    public int col;
    public static final int INFINITY = 10000000;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        heightDiff = INFINITY;
        //lowest = -1;
        //highest = INFINITY;
    }

    public int compareTo(Node n) {
        if(this.heightDiff > n.heightDiff)
            return 1;
        else if(this.heightDiff < n.heightDiff)
            return -1;
        else
            return 0;
    }

    public String toString() {
        //return "{" + row + "," + col + "," + MTWALK.heights[row][col] + "," + lowest + "," + highest + "}";
        return "{" + row + "," + col + "," + height + "," + heightDiff + "}";
    }
}

class NodeQueue {
    private Node[] theList;
    private int size;
    private int capacity;
    private int rangeLow, rangeHigh;

    public NodeQueue() {
        // default constructor has 10 spots in the array
        theList = new Node[10];
        size = 0; // always points to the next available spot
                  // so return size-1 on getSize()
        capacity = 10;
        rangeLow = 10000000;
        rangeHigh = -1;
    }

    public void add(Node n) {
        int i;
        if(n.height >= rangeLow && n.height <= rangeHigh) {
            //theList[size] = n;
            i = size;
        }
        else {
            // update all of the nodes height differences
            for(i = 0; i <= size; i++) {
                if(theList[i].height >= rangeLow && theList[i].height <= rangeHigh)
                    theList[i].heightDiff = 0;
                else if(theList[i].height < rangeLow)
                    theList[i].heightDiff = rangeLow - theList[i].height;
                else
                    theList[i].heightDiff = theList[i].height - rangeHigh;
            }

            // maintain a sorted list based on heightDiff
            Arrays.sort(theList, Collections.reverseOrder());

            // now insert based on heightDiff
            for(i = size; i >= 0; i--) {
                if(n.heightDiff < theList[i].heightDiff) {
                
                }
            }
        }

        theList[i] = n;
        size++;

        // list is full, so double the capacity
        Node[] temp;
        if(size+2 == capacity) {
            temp = new Node[capacity*2];
            System.arraycopy(theList, 0, temp, 0, capacity);
        }
        theList = temp;
    }

    // return best match which is by design the last element in the array
    public Node poll() {
        rangeLow = rangeLow < theList[size].height ? rangeLow : theList[size].height;
        rangeHigh = rangeHigh > theList[size].height ? rangeHigh : theList[size].height;

        Node temp = theList[size];
        theList[size] = null;
        size--;
        return temp;
    }

    public String toString() {
        String s = "";

        for(int i = 0; i <= size; i++) {
            s += theList[i].toString();
            s += "\n";
        }

        return s;
    }
}
