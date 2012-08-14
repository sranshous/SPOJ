/**
 * Current state: implement memoization for factors.
 * For 120 remember the factors of 120, 60, 30, 15
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

class DIVSUM {
    private static final int NUM = 500001;
    private static HashMap<Integer, List<Integer>> hm = new HashMap<Integer, List<Integer>>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numCases = Integer.parseInt(br.readLine());

        for(int i = 0; i < numCases; i++) {
            int n = Integer.parseInt(br.readLine());
            int number = n;
            int sr = (int)Math.sqrt(n);
            int[] nums = new int[sr+3];

            // prime factors.
            List<Integer> factors = new ArrayList<Integer>();
            int j = 2;
            while(j < nums.length) {
                if(n % j == 0) {
                    n /= j;
                    nums[j]++;
                    factors.add(j);
                }
                else {
                    j++;
                }
            }

            // remember factors in an attempt to speed up
            hm.put(number, factors);

            List<Integer> toMult = new ArrayList<Integer>();
            j = 2;

            while(j < nums.length) {
                if(nums[j] > 0) {
                    int temp = 1;
                    while(nums[j] > 0) {
                        temp += Math.pow(j, nums[j]);
                        nums[j]--;
                    }
                    toMult.add(temp);
                }
                j++;
            }

            int sum = 1;
            for(Integer x : toMult) {
                sum *= x;
            }

            sum -= number;

            System.out.println(sum);
        }
    }
}
