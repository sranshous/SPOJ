import java.io.BufferedReader;
import java.io.InputStreamReader;

// naive solution

class DIVSUM {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numCases = Integer.parseInt(br.readLine().trim());

        for(int i = 0; i < numCases; i++) {
            long num = Long.parseLong(br.readLine().trim());
            long x = 2;
            long divisorSum = 1;

            if(num%2 == 0) {
                while(num/x > x) {
                    if(num % x == 0) {
                    divisorSum += num/x;
                    divisorSum += x;
                    }
                    x++;
                }
            }
            else {
                while(num/x > x) {
                    if(num % x == 0) {
                    divisorSum += num/x;
                    divisorSum += x;
                    }
                    x += 2;
                }
            }

            System.out.println(divisorSum);
        }

    }
}
