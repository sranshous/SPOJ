/* We will employ the hill climbing algorithm and search for the optimal heights */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class MTWALK {
    private static BufferedReader br = null;
    private static int[][] grid = null;
    private static boolean[][] used = null;
    private static int MATRIX_SIZE;
    private static int highest = -100000, lowest = 100000;
    private static boolean reachedHome = false;

    public static void main(String[] args) throws Exception {
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

            // Begin mountain walking
            climb();

            System.out.println("high = " + (highest+1) + "\tlow = " + (lowest-1));
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

    private static void climb() {
        boolean highestChanged = true, lowestChanged = true;
        int previousHigh = highest, previousLow = lowest;
        System.out.println("MATRIX_SIZE-1 = " + (MATRIX_SIZE-1));

        /*
         * THE INDIVIDUAL DFS METHODS APPEAR TO WORK
         * SO NOW WE MUST INTEGRATE THEM SOMEWHAT AND
         * ADD CHECKS SO THAT THE PATH IT TAKES ALSO
         * FOLLOWS A PATH THAT SATISFIES THE OPPOSITE
         * CONSTRAINT
         */
        thisiswhereweshouldgotomorrowwhenwestart

        do {
            used = new boolean[MATRIX_SIZE][MATRIX_SIZE];
            reachedHome = false;
            previousHigh = highest;
            highest--;
            System.out.println("Highest: " + highest + "\tPrevious Highest: " + previousHigh);
            dfsHighest(0, 0);
            System.out.println("DID WE REACH HOME? " + reachedHome);
        } while(reachedHome);

        System.out.println("---- END OF HIGHEST REACHED ----");
        System.out.println("Best high: " + previousHigh);

        do {
            used = new boolean[MATRIX_SIZE][MATRIX_SIZE];
            reachedHome = false;
            previousLow = lowest;
            lowest++;
            System.out.println("Lowest: " + lowest + "\tPrevious Lowest: " + previousLow);
            dfsLowest(0, 0);
            System.out.println("DID WE REACH HOME? " + reachedHome);
        } while(reachedHome);

        System.out.println("---- END OF LOWEST REACHED ----");
        System.out.println("Best low: " + previousLow);
    }

    private static void dfsHighest(int row, int col) {
        if(reachedHome) return;

        System.out.println("row: " + row + "\tcol: " + col + "\theight: " + grid[row][col]);
        if(row-1 >= 0 && grid[row-1][col] <= highest && !used[row-1][col]) {
            // row-1 can never be the target (bottom left) so just return
            // a recursive call
            used[row-1][col] = true;
            dfsHighest(row-1, col);
        }
        //else if(row+1 < MATRIX_SIZE && grid[row+1][col] <= highest && !used[row+1][col]) {
        if(row+1 < MATRIX_SIZE && grid[row+1][col] <= highest && !used[row+1][col]) {
            if(row+1 == MATRIX_SIZE-1 && col == MATRIX_SIZE-1) {
                System.out.println("FOUND HOME");
                reachedHome = true;
            }
            else {
                used[row+1][col] = true;
                dfsHighest(row+1, col);
            }
        }
        //else if(col-1 >= 0 && grid[row][col-1] <= highest && !used[row][col-1]) {
        if(col-1 >= 0 && grid[row][col-1] <= highest && !used[row][col-1]) {
            used[row][col-1] = true;
            dfsHighest(row, col-1);
        }
        //else if(col+1 <= MATRIX_SIZE-1 && grid[row][col+1] <= highest && !used[row][col+1]) {
        if(col+1 <= MATRIX_SIZE-1 && grid[row][col+1] <= highest && !used[row][col+1]) {
            if(row == MATRIX_SIZE-1 && col+1 == MATRIX_SIZE-1) {
                System.out.println("FOUND HOME");
                reachedHome = true;
            }
            else {
                used[row][col+1] = true;
                dfsHighest(row, col+1);
            }
        }
    }


    private static void dfsLowest(int row, int col) {
        if(reachedHome) return;

        System.out.println("row: " + row + "\tcol: " + col + "\theight: " + grid[row][col]);
        if(row-1 >= 0 && grid[row-1][col] >= lowest && !used[row-1][col]) {
            // row-1 can never be the target (bottom left) so just return
            // a recursive call
            used[row-1][col] = true;
            dfsLowest(row-1, col);
        }
        //else if(row+1 < MATRIX_SIZE && grid[row+1][col] <= highest && !used[row+1][col]) {
        if(row+1 < MATRIX_SIZE && grid[row+1][col] >= lowest && !used[row+1][col]) {
            if(row+1 == MATRIX_SIZE-1 && col == MATRIX_SIZE-1) {
                System.out.println("FOUND HOME");
                reachedHome = true;
            }
            else {
                used[row+1][col] = true;
                dfsLowest(row+1, col);
            }
        }
        //else if(col-1 >= 0 && grid[row][col-1] <= highest && !used[row][col-1]) {
        if(col-1 >= 0 && grid[row][col-1] >= lowest && !used[row][col-1]) {
            used[row][col-1] = true;
            dfsLowest(row, col-1);
        }
        //else if(col+1 <= MATRIX_SIZE-1 && grid[row][col+1] <= highest && !used[row][col+1]) {
        if(col+1 <= MATRIX_SIZE-1 && grid[row][col+1] >= lowest && !used[row][col+1]) {
            if(row == MATRIX_SIZE-1 && col+1 == MATRIX_SIZE-1) {
                System.out.println("FOUND HOME");
                reachedHome = true;
            }
            else {
                used[row][col+1] = true;
                dfsLowest(row, col+1);
            }
        }
    }
}
