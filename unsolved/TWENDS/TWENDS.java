import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class TWENDS {
    // constant for infinity
    public static int INFINITY = 100000;

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
    public static int[][] memoization;

    public static void main(String[] args) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine().split(" ");

            do {
                int cards[] = new int[input.length-1];

                memoization = new int[input.length-1][input.length-1];
                for(int i = 0; i < memoization.length; i++) {
                    Arrays.fill(memoization[i], INFINITY);
                }

                for(int i = 1; i < input.length; i++) {
                cards[i-1] = Integer.parseInt(input[i]);
                }

                System.out.println("In game " + gameNum++ + ", the greedy strategy might lose by as many as " + play(cards, 0, cards.length-1) + " points.");
                input = br.readLine().split(" ");
            } while(Integer.parseInt(input[0]) != 0);

            /*
            do {
                input = br.readLine().split(" ");
                leftSide = 1;
                rightSide = input.length - 1;
                numCards = input.length - 1;

                //p1 = new int[input.length-1];
                //p2 = new int[input.length-1];
                p1 = p2 = 0;

                while(numCards > 0) {
                    /*
                     * Find difference between first pick and p2
                     * then pick max between 2 avail.
                     *
                     * Find difference between other choice for first pick and p2
                     * then pick max between 2 avail.
                     *



                    numCards -= 2;
                }
                gameNum++;
            } while(Integer.parseInt(input[0]) != 0);
            */
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

/*
 * Write recursively to try to figure it out then transform to DP
 */

    public static int play(int[] cards, int left, int right) {
        if(left >= right) {
            return 0;
        }

        if(memoization[left][right] != INFINITY)
            return memoization[left][right];

        int l = 0, r = 0;
        //int shared = play(cards, left+1, right-1);

        // you pick left
        if(cards[left+1] >= cards[right]) {
            l = (cards[left] - cards[left+1]);
            if(left+2 < cards.length)
                l += play(cards, left+2, right);
        }
        else /*(cards[left+1] < cards[right])*/ {
            l =  (cards[left] - cards[right]) + play(cards, left+1, right-1);
            //l = cards[left] - cards[right] + shared;
        }

        // you pick right
        if(cards[left] >= cards[right-1]) {
            r = (cards[right] - cards[left]) + play(cards, left+1, right-1);
            //r = cards[right] - cards[left] + shared;
        }
        else /*(cards[left] < cards[right-1])*/ {
            r = (cards[right] - cards[right-1]);
            if(right-2 > 0)
                r += play(cards, left, right-2);
        }

        int max = Math.max(l, r);
        if(memoization[left][right] == INFINITY)
            memoization[left][right] = max;
        return max;
    }

}
