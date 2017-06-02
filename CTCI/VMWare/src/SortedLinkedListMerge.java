import java.util.LinkedList;

/**
 * Created by admin on 10/2/16.
 */
public class SortedLinkedListMerge {

    public static void main(String[] args) {
        Node head1 = new Node(2);
        Node head2 = new Node(4);

        head1.next = new Node(3);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(3);
        head1.next.next.next.next = new Node(3);
        head1.next.next.next.next.next = new Node(3);

        head2.next = new Node(5);
        head2.next.next = new Node(7);
        head2.next.next.next = new Node(12);
        head2.next.next.next.next = new Node(127);

        System.out.println(isSorted(sorter(head1, head2)));
    }

    public static Node sorter(Node head1, Node head2) {
        Node pointer1 = head1;
        Node pointer2 = head2;
        Node newHead;
        Node curr;

        if (pointer1.data <= pointer2.data) {
            newHead = pointer1;
            curr = newHead;
            pointer1 = pointer1.next;
        } else {
            newHead = pointer2;
            curr = newHead;
            pointer2 = pointer2.next;
        }

        while (true) {
            if (pointer1 != null && pointer1.data <= pointer2.data) {
                curr.next = pointer1;
                curr = curr.next;
                pointer1 = pointer1.next;
            } else if (pointer1 != null && pointer2.data <= pointer1.data) {
                curr.next = pointer2;
                curr = curr.next;
                pointer2 = pointer2.next;
            } else if (pointer1 == null && pointer2 != null) {
                curr.next = pointer2;
                curr = curr.next;
                pointer2 = pointer2.next;
            } else {
                break;
            }
        }

        return newHead;
    }

    public static boolean isSorted(Node head) {
        if (head == null) {
            return true;
        }
        int starter = head.data;
        while (head.next != null) {

            System.out.println(head.data);

            if (starter > head.next.data) {
                return false;
            }
            starter = head.data;
            head = head.next;
        }
        return true;
    }

}
