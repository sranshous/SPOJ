import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

class SmallInputGenerator {
    private static Random r = new Random();

    public static void main(String[] args) throws Exception {
        File f = null;
        PrintWriter pw = null;

        f = new File("smallInputSample");
        pw = new PrintWriter(f);

        pw.println(10); // 10 test case per file

        for(int i = 0; i < 10; i++) {
            pw.println(20); // 20 numbers per case
            for(int j = 0; j < 20; j++) {
                // this writes random numbers on the interval
                // [-1000, 1000]
                pw.print((r.nextInt(2001) - 1000) + " ");
            }
            pw.println();
            pw.flush();
        }
        pw.flush();
    }
}
