import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class ACODE {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            // string to decode
            String input = br.readLine().trim();

            while(input.charAt(0) != '0') {
                long[] dp = new long[input.length()+1];
                dp[0] = 1;
                dp[1] = 1;

                for(int i = 1; i < input.length(); i++) {
                    int j = i+1;
                    int charVal = input.charAt(i) - 48;
                    int pVal = input.charAt(i-1) - 48;
                    int nVal = -1;

                    if(i < input.length()-1)
                        nVal = input.charAt(i+1) - 48;

                    if(nVal == 0) {
                        dp[j] = dp[j-1];
                    }
                    else if(((charVal != 0 && pVal == 1) || (charVal < 7 && pVal == 2)) &&  charVal != 0) {
                        // because it could be either a single num char
                        // or double num char, so we add the totals of the dp
                        // solution thus far because for every combination preceeding
                        // we added another possibility
                        dp[j] = dp[j-1] + dp[j-2];
                    }
                    else {
                        dp[j] = dp[j-1];
                    }
                }

                System.out.println(dp[dp.length-1]);

                input = br.readLine().trim();
            }
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
