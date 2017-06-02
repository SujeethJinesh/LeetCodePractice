public class Node {
    int data;
    Node next;
    Node prev;
    Node left;
    Node right;

    public Node(int data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }

    public Node() {
        this.next = null;
        this.prev = null;
    }
}