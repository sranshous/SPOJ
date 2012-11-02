/*
 * A program using Nim theory
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class MMMGAME {
    private static BufferedReader br;
    private static StringBuilder sb;

    public static void main(String[] args) {
        int numCases = 0;
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        try {
            numCases = Integer.parseInt(br.readLine());

            for(int i = 0; i < numCases; i++) {
                int[] MMs = readMMs(br);
                //System.out.println("M&Ms before playGame");
                String winner = playGame(MMs);
                //System.out.println(winner);
                sb.append(winner + "\n");
            }
        } catch(Exception e) {}

        System.out.print(sb.toString());
    }

    public static int[] readMMs(BufferedReader br) {
        String[] MMstring = null;
        int[] MMs = null;

        try {
            int numHeaps = Integer.parseInt(br.readLine());
            MMstring = br.readLine().split(" ");
            MMs = new int[numHeaps];

            for(int i = 0; i < MMstring.length; i++) {
                MMs[i] = Integer.parseInt(MMstring[i]);
            }
        } catch(Exception e) {}

        return MMs;
    }

    public static String playGame(int[] MMs) {
        int nimSum = 0;
        int numGreaterThanOne = 0;
        int numPilesInPlay = 0;

        for(int i = 0; i < MMs.length; i++) {
            nimSum ^= MMs[i];
            if(MMs[i] > 1) numGreaterThanOne++;
            if(MMs[i] > 0) numPilesInPlay++;
        }

        //System.out.println("numPilesInPlay = " + numPilesInPlay + "\tnumGreaterThanOne = " + numGreaterThanOne);

        if(nimSum > 0 && numGreaterThanOne > 0) {
            return "John";
        }
        else if(nimSum > 0) {
            // if there is an odd number of piles with 1 brother wins
            // even means John wins
            return (numPilesInPlay % 2 == 0) ? "John" : "Brother";
        }
        else {
            // nimSum == 0
            if(numGreaterThanOne == 0)
                return (numPilesInPlay % 2 == 0) ? "John" : "Brother";
            else
                return "Brother";
        }
    }
}
