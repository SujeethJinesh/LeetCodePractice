import java.util.HashSet;
/**
 * Created by Sujeeth on 8/22/16.
 */
public class LinkedLists {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new Node(1));
        linkedList.add(new Node(2));
        linkedList.add(new Node(4));
        linkedList.add(new Node(4));
        linkedList.add(new Node(1));
        linkedList = removeDuplicates(linkedList);

        Node current = linkedList.getHead();

        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    public static LinkedList removeDuplicates(LinkedList linkedList) {
        HashSet<Node> hashSet = new HashSet<>();
        Node current = linkedList.getHead();
        while (current != null) {
            hashSet.add(current);
        }
        return new LinkedList(hashSet.toArray(new Node[hashSet.size()]));
    }

}
