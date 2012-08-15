import java.io.BufferedReader;
import java.io.InputStreamReader;

class TWENDS {
    // scores
    public static int p1;
    public static int p2;

    // pointers to which card we're on
    public static int leftSide, rightSide;

    // total amount of cards
    public static int numCards;

    // keep track of game #
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
}
