import java.io.BufferedReader;
import java.io.InputStreamReader;

class DIG {
    private static BufferedReader br;
    private static StringBuilder sb;
    private static final long mod_by = 24 * 1000000007L;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int numCases = 0;;

        try {
            numCases = nextInt(br);

            for(int i = 0; i < numCases; i++) {
                long n = nextInt(br);
                long x = (n*(n-1)) % (mod_by);
                long y = (x*(n-2)) % (mod_by);
                long z = (y*(n-3)) % (mod_by);
                long answer = z / 24;
                sb.append(answer + "\n");
            }

            System.out.println(sb.toString());
        } catch(Exception e) {}
    }

    public static int nextInt(BufferedReader br) throws Exception {
        return Integer.parseInt(br.readLine().trim());
    }
}
