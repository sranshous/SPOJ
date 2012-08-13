/**
 * This solution finds the sum of the proper divisors for
 * every number [1, 500000]. Thus it is too slow for SPOJ
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

class DIVSUM {
    private static final int NUM = 500001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numCases = Integer.parseInt(br.readLine());
        int[] solutions = new int[NUM];
        solutions[1] = 0; // 1 has no proper divisor

        for(int i = 2; i < solutions.length; i++) {
            // start the sum at 1 because 1 is a proper divisor of
            // everything. this is why we start j=2
            int sum = 1;
            for(int j = 2; j <= Math.sqrt(i); j++) {
                if(i % j == 0) {
                    // if it is a divisor < sqrt(n) then it has a partner
                    // which is n/j (e.g. {2,10} for 20)
                    sum += j + (i / j);
                }
            }
            solutions[i] = sum;
        }

        for(int i = 0; i < numCases; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.print(n + ": ");
            System.out.println(solutions[n]);
        }
    }
}
