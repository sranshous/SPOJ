import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class MINVEST {
    public static int[] cost;
    public static int[] interest;

    public static void main(String[] args) {
       try {
            BufferedReader br = new BufferedReader(
                                        new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();

            int numCases = Integer.parseInt(br.readLine());

            for(int i = 0; i < numCases; i++) {
                String[] input = br.readLine().split(" ");
                int capital = Integer.parseInt(input[0]);
                int lowCapital = (capital / 1000);
                int years = Integer.parseInt(input[1]);

                int numBonds = Integer.parseInt(br.readLine());


                cost = new int[numBonds];
                interest = new int[numBonds];

                int[] profit = new int[45000];

                for(int j = 0; j < numBonds; j++) {
                    input = br.readLine().split(" ");
                    cost[j] = Integer.parseInt(input[0]) / 1000;
                    interest[j] = Integer.parseInt(input[1]);
                }

                calculateProfit(profit);

                for(int j = 0; j < years; j++) {
                    capital += profit[lowCapital];
                    lowCapital = (capital / 1000);
                }
                sb.append(capital + "\n");
            }

            System.out.print(sb.toString());
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void calculateProfit(int[] profit) {
        for(int i = 0; i < cost.length; i++) {
            for(int j = cost[i]; j < profit.length; j++) {
                profit[j] = Math.max(profit[j], profit[j-cost[i]] + interest[i]);
            }
        }
    }
}
