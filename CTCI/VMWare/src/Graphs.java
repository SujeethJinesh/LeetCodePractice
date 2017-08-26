import java.util.*;

/**
 * Created by admin on 2/2/17.
 */
public class Graphs {

    /*
        LEARN TREE OPERATIONS LIKE ADDING AND DELETING FROM BST
    */

    public TreeNode BSTAdd(TreeNode root, TreeNode node) {
        if (root == null) {
            return node;
        } else if (root.data > node.data) {
            root.left = BSTAdd(root.left, node);
        } else if (root.data < node.data) {
            root.right = BSTAdd(root.right, node);
        }
        return root;
    }

    /**
     * NOT SURE IF THIS WORKS
     * @param root
     * @param node
     * @return
     * @throws Exception
     */
    public TreeNode BSTRemove(TreeNode root, TreeNode node) throws Exception {
        if (root == null) {
            throw new Exception();
        } else if (root.data > node.data) {
            root.left = BSTAdd(root.left, node);
        } else if (root.data < node.data) {
            root.right = BSTAdd(root.right, node);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else {
                return root.left;
            }
        }
        return root;
    }

    public LinkedList BFS(Node node, Graph graph) {

        Queue queue = (Queue) new LinkedList();
        HashSet visited = new HashSet();
        LinkedList list = new LinkedList();
        List<Node> currentNeighbors;

        Map<Node, List<Node>> adjacencyList = graph.getAdjacencyList();

        queue.add(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            Node removed = (Node) queue.remove();
            list.add(removed);

            currentNeighbors = adjacencyList.get(removed);

            for (Node item : currentNeighbors) {
                if (!visited.contains(item)) {
                    queue.add(item);
                    visited.add(item);
                }
            }
        }
        return list;
    }

    public LinkedList DFS(Node node, Graph graph) {
        Map<Node, List<Node>> adjacencyList = graph.getAdjacencyList();
        LinkedList list = new LinkedList();

        HashSet<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        List<Node> currentNeighbors = adjacencyList.get(node);

        stack.push(node);
        visited.add(node);

        while (!stack.isEmpty()) {
            Node popped = stack.pop();
            list.add(popped);

            currentNeighbors = adjacencyList.get(popped);
            for (Node item : currentNeighbors) {
                if (!visited.contains(item)) {
                    stack.push(item);
                    visited.add(item);
                }
            }
        }

        return list;
    }

    public int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (key > arr[mid]) {
                low = mid + 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int[] merge(int[] a, int[] b) {
        int[] toReturn = new int[a.length + b.length];
        int indexA = 0;
        int indexB = 0;
        int indexReturn = 0;

        while (indexA < a.length && indexB < b.length) {
            if (a[indexA] <= b[indexB]) {
                toReturn[indexReturn++] = a[indexA++];
            } else {
                toReturn[indexReturn++] = b[indexB++];
            }
        }

        if (indexA == a.length) {
            for (int i = indexB; i < b.length; i++) {
                toReturn[indexReturn++] = b[i];
            }
        } else {
            for (int i = indexA; i < a.length; i++) {
                toReturn[indexReturn++] = a[i];
            }
        }

        return toReturn;
    }

    /**
     * quickselect algo
     ****************************************/

    private int quickselect(int[] G, int first, int last, int k) {
        if (first <= last) {
            int pivot = partition(G, first, last);
            if (pivot == k) {
                return G[k];
            }
            if (pivot > k) {
                return quickselect(G, first, pivot - 1, k);
            }
            return quickselect(G, pivot + 1, last, k);
        }
        return Integer.MIN_VALUE;
    }

    private int partition(int[] G, int first, int last) {
        int pivot = first + new Random().nextInt(last - first + 1);
        swap(G, last, pivot);
        for (int i = first; i < last; i++) {
            if (G[i] > G[last]) {
                swap(G, i, first);
                first++;
            }
        }
        swap(G, first, last);
        return first;
    }

    private void swap(int[] G, int x, int y) {
        int tmp = G[x];
        G[x] = G[y];
        G[y] = tmp;
    }

    /***********************************************************/
    // root: null - return null
    // root: 1 -
    // root:  1
    //       / \
    //      2   3
    public Node invert(Node node) {
        if (node == null) {
            return null;
        }

        Node left = invert(node.left);
        Node right = invert(node.right);

        node.right = left;
        node.left = right;

        return node;
    }


    //are there any duplicates?
    // root > left, root < right
    //
    public boolean validBST(Node root) {
        ArrayList<Node> list = inOrderTraversal(root, new ArrayList<Node>());
        return checkOrder(list);
    }

    private boolean checkOrder(ArrayList<Node> list) {
        if (list.size() < 2) {
            return true;
        }
        Node less;
        Node greater;

        for (int i = 1; i < list.size(); i++) {
            less = list.get(i - 1);
            greater = list.get(i);
            if (less.data > greater.data) {
                return false;
            }
        }
        return true;
    }
/*******************************************************************/
    public boolean validBSTFast(TreeNode root) {
        return validBSTFastHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validBSTFastHelper(TreeNode root, int low, int high) {
        if (root == null) {
            return true;
        }

        if (root.data < low || root.data > high) {
            return false;
        }

        return validBSTFastHelper(root.left, low, root.data) && validBSTFastHelper(root.right, root.data, high);
    }
/***********************************************************************/
    private ArrayList<Node> inOrderTraversal(Node root, ArrayList<Node> nodes) {
        if (root != null) {
            inOrderTraversal(root.left, nodes);
            nodes.add(root);
            inOrderTraversal(root.right, nodes);
        }
        return nodes;
    }

    public int height(Node root) {
        return (root == null) ? 0 : Math.max(height(root.right), height(root.left)) + 1;
    }

    public int diameter(Node root) {

        if (root == null) {
            return 0;
        }

        int leftMaxHeight = height(root.left);
        int rightMaxHeight = height(root.right);

        int leftDiameter = diameter(root.left);
        int rightDiameter = diameter(root.right);

        return Math.max(leftMaxHeight + rightMaxHeight + 1, Math.max(leftDiameter, rightDiameter));
    }

    /**
     * all permutations of a string
     *
     * @param str
     * @param l
     * @param r
     */
//    private void permute(String str, int l, int r)
//    {
//        if (l == r)
//            System.out.println(str);
//        else
//        {
//            for (int i = l; i <= r; i++)
//            {
//                str = swap(str,l,i);
//                permute(str, l+1, r);
//                str = swap(str,l,i);
//            }
//        }
//    }
    private void permute(String str, int l, int r) {

        if (l == r) {
            System.out.println(str);
        } else {
            for (int i = l; i < r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }

    }

    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public int minTreeHeight(TreeNode root) {
        return (root == null) ? 0 : Math.min(minTreeHeight(root.left) + 1, minTreeHeight(root.right) + 1);
    }

    public TreeNode makeBSTFromPreOrder(int pre[]) {
        int size = pre.length;

        // The first element of pre[] is always root
        TreeNode root = new TreeNode(pre[0]);

        Stack<TreeNode> s = new Stack<TreeNode>();

        // Push root
        s.push(root);

        // Iterate through rest of the size-1 items of given preorder array
        for (int i = 1; i < size; ++i) {
            TreeNode temp = null;

            /* Keep on popping while the next value is greater than
             stack's top value. */
            while (!s.isEmpty() && pre[i] > s.peek().data) {
                temp = s.pop();
            }

            // Make this greater value as the right child and push it to the stack
            if (temp != null) {
                temp.right = new TreeNode(pre[i]);
                s.push(temp.right);
            }

            // If the next value is less than the stack's top value, make this value
            // as the left child of the stack's top node. Push the new node to stack
            else {
                temp = s.peek();
                temp.left = new TreeNode(pre[i]);
                s.push(temp.left);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("abc"));
        System.out.println(isPalindrome("ab234aasc"));
        System.out.println(isPalindrome("abdsgesc"));
        System.out.println(isPalindrome("abcxssss"));
        System.out.println(isPalindrome("tacocat"));
        System.out.println(isPalindrome("abcdcba"));
    }

    public static String reverseString(String string) {
        char[] chars = string.toCharArray();
        int i = 0;
        int j = string.length() - 1;
        char temp;
        while (i < j) {
            temp = chars[i];
            chars[i++] = chars[j];
            chars[j--] = temp;
        }
        return new String(chars);
    }

    public static boolean isPalindrome(String string) {
        int i = 0;
        int j = string.length() - 1;

        while (i < j) {
            if (string.charAt(i++) != string.charAt(j--)){
                return false;
            }
        }

        return true;
    }

    public Node deleteNodesGreaterThanK(Node head, int k) {
        if (head == null) {
            return null;
        }

        for (Node curr = head, prev = curr; curr != null; prev = curr, curr = curr.next) {
            if (curr.data > k) {
                if (curr == head) {
                    head = curr.next;
                } else {
                    prev.next = curr.next;
                }
            }
        }
        return head;
    }

//    Node Reverse(Node head) {
//        if (head == null) {
//            return null;
//        }
//
//        Node current = head;
//        Node prev = current.prev;
//        Node next = current.next;
//        Node temp;
//
//        while (current != null) {
//            head = prev;
//
//        }
//    }

}

/**
 * CAN USE QUICKSELECT FOR THIS AS WELL. IF YOU SEE HEAPS, ALSO SEE IF QUICKSELECT IS AN OPTION
 * @return
 */
class medianOfUnsortedArray {
    private Queue<Integer> low = new PriorityQueue<>(Comparator.reverseOrder());
    private Queue<Integer> high = new PriorityQueue<>();

    public void add(int number) {
        Queue<Integer> target = low.size() <= high.size() ? low : high;
        target.add(number);
        balance();
    }

    private void balance() {
        while(!low.isEmpty() && !high.isEmpty() && low.peek() > high.peek()) {
            Integer lowHead= low.poll();
            Integer highHead = high.poll();
            low.add(highHead);
            high.add(lowHead);
        }
    }

    public double median() {
        if(low.isEmpty() && high.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        } else {
            return low.size() == high.size() ? (low.peek() + high.peek()) / 2.0 : low.peek();
        }
    }

}
