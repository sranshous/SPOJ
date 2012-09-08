/* All my WA's were caused by stupidity - I was using ints everywhere
 * instead of long so it was overflowing. Fail.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class MBALL {
    static BufferedReader br;
    static StringBuilder sb;
    static final int[] POINT_OPTIONS = {2, 3, 6, 7, 8};
    static final int MAX_POINTS = 100000;
    static long[] pointsPoss = new long[MAX_POINTS+1];

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        /*
         * safety = 2
         * field goal = 3
         * touchdown = 6
         * touchdown w/ PA = 7
         * touchdown w/ 2PC = 8
         */

        pointsPoss[0] = 1; // we can always achieve a score of zero
        for(int j = 0; j < POINT_OPTIONS.length; j++) {
            for(int k = POINT_OPTIONS[j]; k < pointsPoss.length; k++) {
                if(pointsPoss[k-POINT_OPTIONS[j]] != 0) {
                    pointsPoss[k] += pointsPoss[k-POINT_OPTIONS[j]];
                }
            }
        }

        int numCases = Integer.parseInt(br.readLine());

        for(int i = 0; i < numCases; i++) {
            int points = Integer.parseInt(br.readLine());

            sb.append(pointsPoss[points] + "\n");
        }

        System.out.print(sb.toString());
    }
}
