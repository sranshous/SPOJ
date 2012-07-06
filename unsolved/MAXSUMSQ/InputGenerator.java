import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

class InputGenerator {
    private static Random r = new Random();

    public static void main(String[] args) throws Exception {
        File f = null;
        PrintWriter pw = null;

        f = new File("inputSample");
        pw = new PrintWriter(f);

        pw.println(35); // 35 test case per file

        for(int i = 0; i < 35; i++) {
            pw.println(100000); // 100000 numbers per case
            for(int j = 0; j < 100000; j++) {
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
