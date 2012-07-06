import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SOLVING {

    static int[][] puzzle;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int numPuzzles = Integer.parseInt(br.readLine());
        System.out.println(numPuzzles);

        for(int i = 0; i < numPuzzles; i++) {
            String line = br.readLine();
            if(line.equals("")) {
                line = br.readLine();
            }

            int puzzleSize = Integer.parseInt(line);
            System.out.println(puzzleSize);
            readMatrix(puzzleSize);
            
            }
        }
    }

    // parameter size is the dimension of the square matrix to be read
    static void readMatrix(int size) throws Exception {
        puzzle = new int[size][size];

        for(int i = 0; i < size; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < line.length; j++) {
                puzzle[i][j] = new Integer(line[j]);
            }
        }
    }
}
