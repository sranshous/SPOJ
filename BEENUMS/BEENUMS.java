import java.io.BufferedReader;
import java.io.InputStreamReader;

class BEENUMS {
    private static BufferedReader br = null;
    private static StringBuilder sb;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int num, tempNum;

        num = readInt(br);
        do {
            tempNum = 1;

            for(int i = 0; tempNum != num && tempNum < num; i++) {
                tempNum += (i*6);
            }

            if(tempNum == num)
                sb.append("Y\n");
            else
                sb.append("N\n");
            num = readInt(br);
        } while(num != -1);

        System.out.print(sb.toString());
    }

    private static int readInt(BufferedReader br) {
        Integer x = 0;
        try {
            x = Integer.parseInt(br.readLine());
        } catch(Exception e) {}

        return x;
    }
}
