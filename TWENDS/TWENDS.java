import java.io.BufferedReader;
import java.io.InputStreamReader;

class TWENDS {
    public static int p1;
    public static int p2;
    public static int leftSide, rightSide;
    public static int numCards;
    public static int gameNum = 1;
    public static String[] input;

    public static void main(String[] args) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));

            input = br.readLine().split(" ");
            leftSide = 1;
            rightSide = input.length - 1;
            numCards = input.length - 1;

            while(Integer.parseInt(input[0]) != 0) {
                while(numCards > 0) {
                    
                }
                gameNum++;
            }
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void p1Move() {
    }

    public static void p2Move() {
        int toAdd = 0;
        if(Integer.parseInt(input[leftSide]) == Integer.parseInt(input[rightSide])) {
            p2 += Integer.parseInt(input[leftSide]);
            leftSide++;
        }
        else {
            int lo = Integer.parseInt(input[leftSide]);
            int ro = Integer.parseInt(input[rightSide]);
            if(lo > ro) {
                p2 += lo;
                leftSide++;
            }
            else {
                p2 += ro;
                rightSide--;
            }
        }
    }
}
