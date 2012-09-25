import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Approach: Use breadth first search
 * We can start with 1 node that is the current room number
 * From there we add the reachable nodes that are also prime
 * We continue this until we reach the target door number
 */

public class PPATH {
    // All primes for the problem are 4 digits
    public static final int MAX_NUM = 10000;
    public static boolean[] PRIMES = new boolean[MAX_NUM];

    public static void main(String[] args) {
        /* Get primes */
        for(int i = 0; i < PRIMES.length; i++)
            PRIMES[i] = true;

        for(int i = 2; i < PRIMES.length; i++)
            if(PRIMES[i])
                for(int j = 2*i; j < PRIMES.length; j += i)
                    PRIMES[j] = false;

        //System.out.println(Arrays.toString(PRIMES));
    }
}
