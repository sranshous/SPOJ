public class Test {
    public static void main(String[] args) {
        Graph g = new Graph();

        // Create some nodes
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        // Give them adjacent nodes
        n1.addAdjacent(n2);
        n1.addAdjacent(n3);
        n2.addAdjacent(n2);
        n2.addAdjacent(n3);
        n3.addAdjacent(n2);
        n3.addAdjacent(n3);
        n4.addAdjacent(n2);
        n4.addAdjacent(n3);
        n5.addAdjacent(n2);
        n5.addAdjacent(n2);
        // n6/n7 are the leaves with only 1 adjacent node
        n6.addAdjacent(n3);
        n7.addAdjacent(n3);

        // build edges
        Edge e1 = new Edge(n1, n2);
        Edge e2 = new Edge(n2, n3);
        Edge e3 = new Edge(n3, n4);
        Edge e4 = new Edge(n4, n5);
        Edge e5 = new Edge(n5, n6);
        Edge e6 = new Edge(n5, n7);

        // put them in the graph
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);
        g.addNode(n6);
        g.addNode(n7);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        System.out.println(g);
        g.removeLeaves();
        System.out.println(g);

        // Need to test if the contains method works on our ArrayList of Edges
    }
}
