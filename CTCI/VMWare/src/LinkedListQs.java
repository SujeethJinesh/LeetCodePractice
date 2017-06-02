/**
 * Created by admin on 1/28/17.
 */
public class LinkedListQs {


    /**
     * Cycle detection real fast
     * @param head
     * @return
     */
    public static boolean cycleDetection(Node head) {

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;

    }

    /**
     * Second last node in linkedlist
     * @param head
     * @return
     */
    public static Node secondLast(Node head) {

        if (head == null) {
            throw new IllegalArgumentException("cannot have null head");
        }

        Node leader = head.next;
        Node toReturn = head;

        while (leader != null) {
            toReturn = leader;
            leader = leader.next;
        }
        return toReturn;
    }

}
