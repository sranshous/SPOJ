import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class MAXSUMSQ {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numCases = Integer.parseInt(br.readLine());

        for(int i = 0; i < numCases; i++) {
            int numNumbers = Integer.parseInt(br.readLine().trim());
            int[] numbers = new int[numNumbers];
            String[] stringNumbers = br.readLine().split(" ");
            for(int j = 0; j < stringNumbers.length; j++) {
                numbers[j] = Integer.parseInt(stringNumbers[j]);
            }

            findIt(numbers);
        }
    }

    public static void findIt(int[] a) {
        int[] sums = new int[a.length+1];
        int max = -100000;
        int count = 0;

        for(int i = 1; i < sums.length; i++) {
            sums[i] = Math.max(sums[i-1] + a[i-1], a[i-1]);

            if(sums[i] > max)
                max = sums[i];
        }

        //System.out.println(Arrays.toString(a));
        //System.out.println(Arrays.toString(sums));

        for(int i = 1; i < sums.length; i++) {
            if(sums[i] == max)
                count++;
            if(i != 1 && sums[i] == max && sums[i-1] == 0)
                count++;
        }

        System.out.println(max + " " + count);
    }
}
