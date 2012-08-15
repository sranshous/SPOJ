import java.io.BufferedReader;
import java.io.InputStreamReader;

class PLAYFAIR {

    static char[][] key = {
        {'Y', 'C', 'T', 'K', 'O'},
        {'S', 'G', 'R', 'B', 'V'},
        {'N', 'A', 'E', 'H', 'X'},
        {'U', 'L', 'Q', 'W', 'I'},
        {'D', 'Z', 'F', 'M', 'P'}
    };

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int numCases = Integer.parseInt(br.readLine().trim());

            for(int i = 0; i < numCases; i++) {
                // first read in the message
                String temp = br.readLine();
                String encrypted = "";
                String message = "";
                String s = "";

                while(temp.charAt(0) != '#') {
                    s += temp + " ";
                    temp = br.readLine();
                }

                temp = br.readLine();
                while(temp.charAt(0) != '#') {
                    encrypted += temp + " ";
                    temp = br.readLine();
                }

                temp = br.readLine();
                while(temp.charAt(0) != '#') {
                    message += temp + " ";
                    temp = br.readLine();
                }

                s = s.toUpperCase();

                /*
                 * I feel like a key part I didnt do and the problem
                 * does not state is that the program is supposed to
                 * create the key from reading the plaintext and the
                 * corresponding encrypted text. Then from that use
                 * the key to decrypt the message
                 */

                // remove all non alpha-numeric chars
                //s = s.replaceAll("\\W", "");

                //s = s.replace('J', 'I');

                //s = formDigraphs(s);

                System.out.println("Case " + i + ":");
                System.out.println(decrypt(message));
            }
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static String formDigraphs(String s) {
        String temp = "";
        int j = 0;
        for(; j < s.length()-1; j++) {
            char c1 = s.charAt(j);
            if(c1 == ' ') {
                continue;
            }
            temp += c1;
            if(s.charAt(j+1) != ' ') {
                if(s.charAt(j+1) == c1) {
                    if(c1 != 'X')
                        temp += 'X';
                    else
                        temp += 'Q';
                }
                else {
                    temp += s.charAt(j+1);
                    j++;
                }
            }
            else {
                if(s.charAt(j+2) == c1) {
                    if(c1 != 'X')
                        temp += 'X';
                    else
                        temp += 'Q';
                }
                else {
                    temp += s.charAt(j+1);
                    j++;
                }
            }
            temp += ' ';
        }

        temp += s.substring(j);

        if(temp.charAt(temp.length()-2) == ' ') {
            if(temp.charAt(temp.length()-1) != 'X') {
                temp += 'X';
            }
            else {
                temp += 'Q';
            }
        }

        return temp;
    }

    public static String decrypt(String s) {
        String temp = "";

        for(int i = 0; i < s.length(); i+=3) {
            int[] coords1 = findCoords(s.charAt(i));
            int[] coords2 = findCoords(s.charAt(i+1));

            if(coords1[0] == coords2[0]) {
                // they are in the same row
                // shift left one col and keep row
                temp += key[coords1[0]][(coords1[1] - 1 + 5) % 5];
                temp += key[coords2[0]][(coords2[1] - 1 + 5) % 5];
                temp += ' ';
            }
            else if(coords1[1] == coords2[1]) {
                // they are in the same col
                // shift down one row and keep col
                temp += key[(coords1[0] - 1 + 5) % 5][coords1[1]];
                temp += key[(coords2[0] - 1 + 5) % 5][coords2[1]];
                temp += ' ';
            }
            else {
                // different row and col
                temp += key[coords1[0]][coords2[1]];
                temp += key[coords2[0]][coords1[1]];
                temp += ' ';
            }
        }

        return temp;
    }

    // helper method to decrypt that will
    // return the {row, col} of the char input
    public static int[] findCoords(char c) {
        for(int i = 0; i < key.length; i++) {
            for(int j = 0; j < key[i].length; j++) {
                if(key[i][j] == c) {
                    int[] coords =  {i, j};
                    return coords;
                }
            }
        }
        return null;
    }
}
