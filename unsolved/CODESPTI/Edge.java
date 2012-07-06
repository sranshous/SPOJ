public class Edge {
    // Properties
    private Node head;
    private Node tail;
    private boolean marked = false;

    public Edge(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    public void mark() {
        marked = true;
    }

    public void unmark() {
        marked = false;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public boolean equals(Edge e) {
        if(e.getHead() == head && e.getTail() == tail)
            return true;
        return false;
    }

    public String toString() {
        String s = "Head: " + head.toString();
        s += "\nTail: " + tail.toString();
        s += "\nMarked: " + marked;
        return s;
    }
}
