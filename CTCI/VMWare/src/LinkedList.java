/**
 * Created by lab on 5/31/2016.
 */
public class LinkedList {

    Node head;
    Node tail;

    public LinkedList(Node[] Nodes) {
        if (Nodes.length > 0) {
            head = Nodes[0];
            tail = Nodes[Nodes.length-1];
            for (int i = 0; i < Nodes.length - 1; i++) {
                Nodes[i].next = Nodes[i + 1];
            }
        }
    }

    public LinkedList() {
        this.head = new Node();
        this.tail = head;
    }

    void add(Node node) {
        tail.next = node;
        node.prev = tail;
        node.next = null;
    }

    Node getHead() {
        return head;
    }

    Node getTail() {
        return tail;
    }

    int CompareLists(Node headA, Node headB) {
        // This is a "method-only" submission.
        // You only need to complete this method

        Node currentA = headA;
        Node currentB = headB;

        while (true) {
            if (currentA == null && currentB == null) {
                break;
            }
            if (currentA == null || currentB == null || currentA.data != currentB.data) {
                return 0;
            }
            currentA = currentA.next;
            currentB = currentB.next;
        }
        return 1;
    }

    boolean hasCycle(Node head) {

        Node slow = head;
        Node fast = head;

        while (slow != null || fast != null) {
            slow = slow.next;
            fast = fast.next;

            if (slow == null || fast == null) {
                return false;
            }

            fast = fast.next;

            if (fast == null) {
                return false;
            }

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    Node RemoveDuplicates(Node head) {
        // This is a "method-only" submission.
        // You only need to complete this method.

        Node current = head.next;
        Node prev = head;

        while (current != null) {
            if (prev.data == current.data) {
                head = deleteNode(head, prev.data);
            }
            prev = current;
            current = current.next;
        }
        return head;
    }

    Node deleteNode(Node head, int number) {
        Node prev = head;
        Node current = head.next;

        if (prev.data == number) { //base case
            Node temp = head;
            head = head.next;
            temp.next = null;
        } else {
            while (current.data != number) {
                prev = current;
                current = current.next;
            }
            prev.next = current.next;
            current.next = null;
        }
        return head;
    }

    Node Delete(Node head, int position) {

        if (position == 0) { //base case
            Node temp = head;
            head = head.next;
            temp.next = null;
        } else {            // special case
            int counter = 1;

            Node prev = head;
            Node current = head.next;

            while (counter != position) {
                counter++;
                prev = current;
                current = current.next;
            }
            prev.next = current.next;
            current.next = null;
        }
        return head;
    }

    /**
     * Runs in O(n)
     * @param head given head node
     * @param data given data
     * @param position position to add node
     * @return head
     */
    Node InsertNth(Node head, int data, int position) {
        // This is a "method-only" submission.
        // You only need to complete this method.

        if (position == 0) {

            Node node = new Node();
            node.data = data;
            node.next = head;
            head = node;

        } else {
            int counter = 1;

            Node current = head.next;
            Node prev = head;

            Node newNode = new Node();
            newNode.data = data;

            while (counter != position) {
                counter++;
                prev = current;
                current = current.next;
            }
            prev.next = newNode;
            newNode.next = current;
        }
        return head;
    }

    int FindMergeNode(Node headA, Node headB) {
        // Complete this function
        // Do not write the main method.

        Node currentA = headA;
        Node currentB = headB;
        Node tempA = currentA;


        while (true) {
            while (true) {
                if (currentA == currentB) {
                    return currentA.data;
                }
                if (currentA == null) {
                    break;
                }
                currentA = currentA.next;
            }
            currentB = currentB.next;
            currentA = tempA.next;
        }

    }

    int GetNode(Node head,int n) {
        // This is a "method-only" submission.
        // You only need to complete this method.

        Node first = head;
        Node second = head;

        int counter = 0;

        while (first.next != null) {
            counter++;
            first = first.next;
        }

        counter -= n;

        int placeHolder = 0;

        while (placeHolder != counter) {
            second = second.next;
            placeHolder++;
        }
        return second.data;
    }

    /**
     * Reverses a linked list
     * @param head head of the linked list
     * @return updated head
     */
    Node Reverse(Node head) {

        if (head == null) {
            return head;
        } else {
            Node current = head;
            Node next = current.next;
            Node prev = current;

            head.next = null;

            while (true) {
                prev = current;
                current = next;
                next = next.next;

                current.next = prev;
                if (next == null) {
                    head = current;
                    break;
                }

            }
        }
        return head;
    }

//    Node SortedInsert(Node head,int data) {
//        Node current = head;
//        Node newNode = new Node();
//        Node tempNext;
//        Node tempPrev;
//
//        newNode.data = data;
//
//        if (current == null) {
//            newNode.next = null;
//            newNode.prev = null;
//            head = newNode;
//        } else if(data == current.data || data >= current.data && data <= current.next.data) {
//            head = newNode;
//            newNode.next = current.next;
//            newNode.prev = current;
//            tempNext = current;
//            tempNext.prev = newNode;
//            return head;
//        } else {
//            while(current != null) {
//                if(data >= current.data && current.next != null &&  data <= current.next.data) {
//                    newNode.next = current.next;
//                    newNode.prev = current;
//                    tempNext = current.next;
//                    tempNext.prev = newNode;
//                    tempPrev = current;
//                    tempPrev.next = newNode;
//                    return head;
//                }
//                current = current.next;
//            }
//        }
//        return head;
//    }


//    Node ReverseDoublyLinked(Node head) {
//
//        if (head == null) {
//            return head;
//        } else {
//            Node current = head;
//            Node next = current.next;
//            Node prev = current;
//
//            head.next = null;
//
//            while (true) {
//                prev = current;
//                current = next;
//                next = next.next;
//
//                current.next = prev;
//                if (next == null) {
//                    head = current;
//                    break;
//                }
//
//            }
//        }
//        return head;
//
//    }
}
