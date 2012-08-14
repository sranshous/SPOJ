import java.io.BufferedReader;
import java.io.InputStreamReader;

class DIVSUM {
    private static final int NUM = 500001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numCases = Integer.parseInt(br.readLine());

        // dont forget case where input is 1 (output 0)
        for(int i = 0; i < numCases; i++) {
            int sum = 1;
            int n = Integer.parseInt(br.readLine());

            if(n == 1) {
                System.out.println("0");
                continue;
            }

            for(int j = 2; j < Math.sqrt(n); j++) {
                if(n % j == 0)
                    sum += j + (n / j);
            }
            System.out.println(sum);
        }
    }
}
