import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Start at a node and iterate through all of its edges
 * using the findLongestPath method. For each call pass
 * the vertex which called it so that we don't enter an
 * infinite loop
 *
 * we need to return two values each call:
 *      1. the sum of the max 2 recursive calls
 *      2. the max value of a recursive call
 *
 *  1 accounts for if the calling vertex is not included in the path
 *  2 accounts for the calling vertex being part of the path
 *
 */

class PT07Z {
    private static BufferedReader br;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int numNodes = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i <= numNodes; i++) {
            al.add(new ArrayList<Integer>());
        }

        // read the tree in
        for(int i = 0; i < numNodes-1; i++) {
            String[] edge = br.readLine().split(" ");
            Integer u = Integer.parseInt(edge[0]);
            Integer v = Integer.parseInt(edge[1]);

            al.get(u).add(v);
            al.get(v).add(u);
        }

        /*
        for(ArrayList<Integer> u : al) {
            for(int i = 0; i < u.size(); i++)
                System.out.print(u.get(i) + " ");

            System.out.println();
        }
        */

        int[] paths = findLongestPath(0, 1, al);
        System.out.println((paths[0] > paths[1]) ? paths[0] : paths[1]);
    }

    /*
     * keep the highest 2 path lengths in maxPaths. the return value
     * will be longestPath[0] is the path that continues back through to the
     * caller. longestPath[1] is the path using the current vertex as a stop
     * along the path and not going back to the caller
     */
    public static int[] findLongestPath(Integer callingVertex, Integer newVertex,
            ArrayList<ArrayList<Integer>> al) {
        int[] longestPath = {0, 0};
        int[] maxPaths = {0, 0};
        ArrayList<Integer> neighbors = al.get(newVertex);

        for(int i = 0; i < neighbors.size(); i++) {
            if(neighbors.get(i).equals(callingVertex))
                continue;

            int pathLength[] = findLongestPath(newVertex, neighbors.get(i), al);

            // set the path the disconnects from the caller
            if(pathLength[1] > longestPath[1])
                longestPath[1] = pathLength[1];

            if(pathLength[0]+1 > maxPaths[0]) {
                if(maxPaths[0] > maxPaths[1])
                    maxPaths[1] = maxPaths[0];

                maxPaths[0] = pathLength[0] + 1; // +1 to include current vertex
            }
            else if(pathLength[0]+1 > maxPaths[1]) {
                if(maxPaths[1] > maxPaths[0])
                    maxPaths[0] = maxPaths[1];

                maxPaths[1] = pathLength[0] + 1; // +1 to include current vertex
            }
        }

        // check if we found a new longest disconnected path
        if((maxPaths[0] + maxPaths[1]) > longestPath[1])
            longestPath[1] = maxPaths[0] + maxPaths[1];

        longestPath[0] = (maxPaths[0] > maxPaths[1]) ? maxPaths[0] : maxPaths[1];

        return longestPath;
    }
}
