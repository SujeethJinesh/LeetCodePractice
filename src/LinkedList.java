/**
 * Created by admin on 1/10/17.
 */
public class LinkedList {

    static Node head;

    /**
     * Given a sorted link list, remove duplicates
     *
     * Time Complexity: O(n)
     *
     * Space Complexity: O(1)
     */
    public static void removeDuplicates() {

        if (head == null) {
            return;
        }

        Node second = head.next;

        for (Node current = head; second != null;) {
            if (current.data == second.data) {
                current.next = second.next;
                second = current.next;
            } else {
                second = second.next;
                current = current.next;
            }
        }
    }

    /**
     * Test case courtesy of GeeksForGeeks
     *
     * http://www.geeksforgeeks.org/remove-duplicates-from-a-sorted-linked-list/
     *
     * @param args
     */
    public static void main(String[] args) {
        LinkedList llist = new LinkedList();
        llist.push(20);
        llist.push(13);
        llist.push(13);
        llist.push(11);
        llist.push(11);
        llist.push(11);

        System.out.println("List before removal of duplicates");
        llist.printList();

        llist.removeDuplicates();

        System.out.println("List after removal of elements");
        llist.printList();

    }

    public static void push(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
    }

    public static void printList() {
        for (Node current = head; current != null; current = current.next) {
            System.out.print(current.data + " ");
        }
    }


    static class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
