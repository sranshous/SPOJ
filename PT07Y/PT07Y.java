import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class PT07Y {
    public static int[] visited;
    public static ArrayList<ArrayList<Integer>> nodes;

    public static void main(String[] args) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int numNodes = 0;
            int numEdges = 0;

            String[] input = br.readLine().split(" ");
            numNodes = Integer.parseInt(input[0]);
            numEdges = Integer.parseInt(input[1]);
            if(numNodes == numEdges+1) {

                // adjacency list
                nodes = new ArrayList<ArrayList<Integer>>(numNodes+1);
                for(int i = 0; i < numNodes+1; i++) {
                    nodes.add(new ArrayList<Integer>());
                }

                // keeps track of nodes we have already visited so as not to revisit
                visited = new int[numNodes+1];

                for(int i = 0; i < numEdges; i++) {
                    input = br.readLine().split(" ");
                    int n1 = Integer.parseInt(input[0]);
                    int n2 = Integer.parseInt(input[1]);
                    nodes.get(n1).add(n2);
                    nodes.get(n2).add(n1);
                }

                dfs(1);

                for(int i = 1; i < visited.length; i++) {
                    if(visited[i] == 0) {
                        System.out.println("NO");
                        return;
                    }
                }
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
        catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void dfs(int startingVertex) {
        if(visited[startingVertex] == 1 || nodes.get(startingVertex).size() == 0)
            return;
        else {
            visited[startingVertex] = 1;
            for(int i = 0; i < nodes.get(startingVertex).size(); i++) {
                dfs(nodes.get(startingVertex).get(i));
            }
        }
    }
}
