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
                String s = "";

                while(temp.charAt(0) != '#') {
                    s += temp + " ";
                    temp = br.readLine();
                }

                s = s.toUpperCase();

                // remove all non alpha-numeric chars
                s = s.replaceAll("\\W", "");

                s = s.replace('J', 'I');

                s = formDigraphs(s);

                System.out.println(s);
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

    public static String encrypt(String s) {
        // do shit with the digraph key
    }
}
