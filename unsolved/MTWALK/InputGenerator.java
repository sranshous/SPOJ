import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Random;

public class InputGenerator {
    public static void main(String[] args) {
        File f = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        Random generator = new Random();
        int numInputFiles = 10;

        try {
            for(int i = 0; i < numInputFiles; i++) {
                f = new File("input" + i);
                fw = new FileWriter(f);
                bw = new BufferedWriter(fw);

                int matrix_size = 5;
                /*
                int matrix_size = generator.nextInt(20);
                while(matrix_size < 1) {
                    matrix_size = generator.nextInt(20);
                }
                */

                bw.write(matrix_size + "");
                bw.write("\n");
                for(int j = 0; j < matrix_size; j++) {
                    for(int k = 0; k < matrix_size; k++) {
                        if(k == 0) {
                            bw.write("" + generator.nextInt(20));
                        }
                        else {
                            bw.write(" ");
                            bw.write("" + generator.nextInt(20));
                        }
                    }
                    bw.write("\n");
                    bw.flush();
                }
            }
            bw.close();
        }
        catch(Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
}
