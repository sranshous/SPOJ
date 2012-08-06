/* We will employ the hill climbing algorithm and search for the optimal heights */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

class MTWALK {
    private static BufferedReader br = null;
    private static int[][] grid = null;
    private static boolean[][] used = null;
    private static int MATRIX_SIZE;
    private static int highest = -100000, lowest = 100000;
    private static int INFINITY = 100000;

    public static void main(String[] args) throws IOException {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));

            MATRIX_SIZE = Integer.parseInt(br.readLine().trim());
            grid = new int[MATRIX_SIZE][MATRIX_SIZE];

            String inputLine[];
            for(int i = 0; i < MATRIX_SIZE; i++) {
                inputLine = br.readLine().split(" ");
                for(int j = 0; j < MATRIX_SIZE; j++) {
                    grid[i][j] = Integer.parseInt(inputLine[j]);
                    if(grid[i][j] < lowest)
                        lowest = grid[i][j];
                    if(grid[i][j] > highest)
                        highest = grid[i][j];
                }
            }

            for(int i = 0; i < MATRIX_SIZE; i++) {
                System.out.println(Arrays.toString(grid[i]));
            }

            System.out.println("Starting\tHigh: " + highest + "\tLow: " + lowest);

            climb();

            System.out.println("Ending\tHigh: " + highest + "\tLow: " + lowest);
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        finally {
            if(br != null) {
                br.close();
            }
        }
    }

    public static void climb() {
        // binary search for high and low while checking if it
        // decreases the total difference

        int tempHigh = highest/2, tempLow = (lowest == 0) ? lowest++ : lowest*2;
        int d0 = INFINITY, d1 = INFINITY, d2 = INFINITY;

        // run once with temphigh, once with templow, once with both
        boolean changed = true;
        while(changed) {
            used = new boolean[MATRIX_SIZE][MATRIX_SIZE];
            if(tryToClimb(0, 0, highest, tempLow)) {
                d0 = highest - tempLow;
                changed = true;
            }
            System.out.println("d0 = " + d0);

            used = new boolean[MATRIX_SIZE][MATRIX_SIZE];
            if(tryToClimb(0, 0, tempHigh, lowest)) {
                d1 = tempHigh - lowest;
                changed = true;
            }
            System.out.println("d1 = " + d1);

            used = new boolean[MATRIX_SIZE][MATRIX_SIZE];
            if(tryToClimb(0, 0, tempHigh, tempLow)) {
                d2 = tempHigh - tempLow;
                changed = true;
            }
            System.out.println("d2 = " + d2);

            if(d2 != INFINITY) {
                highest = tempHigh;
                lowest = tempLow;
            }
            else if(d1 < d2) {
                highest = tempHigh;
            }
            else if(d2 < d1){
                lowest = tempLow;
            }
            else {
                changed = false;
            }
        }
    }

    public static boolean tryToClimb(int row, int col, int high, int low) {
        // found home
        if(row == MATRIX_SIZE-1 && col == MATRIX_SIZE-1)
            return true;

        used[row][col] = true;

        // deltas
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // loop through the different possibilities
        for(int i = 0; i < 4; i++) {
            int nr = row + dx[i];
            int nc = col + dy[i];


            if(valid(nr, nc)) {
                System.out.println("entered if");
                // found home
                if(row == MATRIX_SIZE-1 && col == MATRIX_SIZE-1)
                    return true;
                else
                    return tryToClimb(nr, nc, high, low);
                //else if(grid[nr][nc] <= high && grid[nr][nc] >= low)
                    //return tryToClimb(nr, nc, high, low);
            }
        }

        return false;
    }

    public static boolean valid(int row, int col) {
        if(row >= 0 && col >= 0 && row < MATRIX_SIZE && col < MATRIX_SIZE && !used[row][col])
            return true;
        return false;
    }
}
















