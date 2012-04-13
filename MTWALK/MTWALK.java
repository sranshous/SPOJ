import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class MTWALK {
    public static int[][] heights;
    public static boolean[][] used;
    public static int[][] bestPath;
    public static int MATRIX_SIZE;
    public static final int INFINITY = 10000000;
    public static int numNodes;
    public static int x, y; // keeps track of current node

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            MATRIX_SIZE = Integer.parseInt(br.readLine().trim());
            heights = new int[MATRIX_SIZE][MATRIX_SIZE];
            bestPath = new int[MATRIX_SIZE][MATRIX_SIZE];
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
                    bestPath[i][j] = INFINITY;
                }
            }

            bestPath[0][0] = 0;

            int[] dy = {-1, 0, 1, 0};   // this will be used in the loop to check up, down, left and right
            int[] dx = {0, 1, 0, -1};   // of the current cell.

            while(numNodes > 0) {
                findNext();

                for(int i = 0; i < dx.length; i++) {
                    int nRow = y + dy[i];
                    int nColumn = x + dx[i];

                    if(nRow >= 0 && nRow < MATRIX_SIZE && nColumn >= 0 && nColumn < MATRIX_SIZE && !used[nRow][nColumn]) {
                        if(bestPath[nRow][nColumn] > Math.abs(heights[0][0] - heights[nRow][nColumn])) {
                            bestPath[nRow][nColumn] = Math.abs(heights[0][0] - heights[nRow][nColumn]);
                        }
                    }
                }

                used[y][x] = true;
                numNodes--;
            }


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
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    // really shitty way to find the next.
    // time complexity is theta(n^2)
    public static void findNext() {
        int lowest = INFINITY;

        for(int i = 0; i < MATRIX_SIZE; i++) {
            for(int j = 0; j < MATRIX_SIZE; j++) {
                if(!used[i][j] && bestPath[i][j] < lowest) {
                    x = j;
                    y = i;
                }
            }
        }
    }
}

class Node {
    public int heightDiff;
    public int lowest;
    public int highest;

    public Node() {
        heightDiff = INFINITY;
        lowest = INFINITY;
        highest = -1;
    }
}
