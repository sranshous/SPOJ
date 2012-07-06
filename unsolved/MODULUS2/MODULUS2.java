import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MODULUS2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        int[] nums = {-15, -10, -5, -1, 0, 1, 5, 10, 15};

        for(int x : nums) {
            System.out.println("Testing: " + x);
            if(x == 0)
                System.out.print("NOT POSSIBLE");
            else if(x < 0) {
                int i = x;
                while(i < 0) {
                    System.out.println("testing: " + x + " mod " + i + "\t" + (x % i) + " ");
                    i++;
                }
            }
            else {
                int i = x;
                while(i > 0) {
                    System.out.println("testing: " + x + " mod " + i + "\t" + (x % i) + " ");
                    i--;
                }
            }
            System.out.println();
        }
    }
}
