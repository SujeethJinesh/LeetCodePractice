import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * UNIMPLEMENTED
     * @param root
     * @param node
     * @return
     * @throws Exception
     */
    public TreeNode BSTRemove(TreeNode root, TreeNode node) throws Exception {
        if (root == null) {
            throw new Exception();
        } else if (root.data > node.data) {
            root.left = BSTRemove(root.left, node);
        } else if (root.data < node.data) {
            root.right = BSTRemove(root.right, node);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else {
                //Find inorder successor and return that
            }
        }
        return root;
    }

<<<<<<< HEAD
    public List<Node> DFS(Node node, Graph graph) {
=======
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
>>>>>>> 3cec76dcc2a8198da68fc36fbbf8a27e1657852b
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

    public static String changeDateFormat(String paragraph) {
        String[] words = paragraph.split("[\\s\\.]+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                word = fixDate(word);
            }
            sb.append(word + " ");
        }
        return sb.toString().trim();
    }

    private static String fixDate(String date) {
        String[] dates = date.split("/");
        return dates[1] + "/" + dates[0] + "/" + dates[2];
    }

    public static void main(String[] args) {
//     ArrayList<String> strings = new ArrayList<String>();
//     strings.add("Hello, World!");
//     strings.add("Welcome to CoderPad.");
//     strings.add("This pad is running Java 8.");

//     for (String string : strings) {
//       System.out.println(string);
//     }

        int[] arr = new int[] {9, 5, 2, 18, 5, 10, 3, 5}; //15
        // //int[] arr1 = new int[] {5}; //exception
        int[] arr2 = new int[] {-1, -18, 5, 11, 22, 64}; //17

        System.out.println(shareMin(arr));
        //System.out.println(shareMin(arr1));
        System.out.println(shareMin(arr2));

    }

    public static int shareMin(int[] shares) {

        if (shares == null) {
            throw new IllegalArgumentException("Cannot have null value.");
        }

        if (shares.length < 2) {
            throw new IllegalArgumentException("Cannot have array length less than 2.");
        }

        int toReturn = Integer.MIN_VALUE;
        int maximum = shares[0];
        int currentDifference;

        for (int i = 1; i < shares.length; i++) {
            if (shares[i] > maximum) {
                maximum = shares[i++];
            }

            currentDifference = maximum - shares[i];

            if (currentDifference > toReturn) {
                toReturn = currentDifference;
            }
        }
        return toReturn;
    }

//    public int maxProfit(int[] prices, int fee) {
//
//
//
//    }

    static String[] replace(String[] lines) {

        String[] arr = new String[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String string = lines[i];
            if (string.contains("API")) {
                arr[i] = api(string);
            } else if (string.contains("BANK")) {
                arr[i] = bank(string);
            }
        }

        return arr;
    }

    static String api(String string) {
        Pattern p = Pattern.compile("card=(\\d*)");
        Matcher matcher = p.matcher(string);
        if (matcher.find()) {
            String number = matcher.group();
            String newNumber = number.substring(5, 11) + "XXXXXXXXXXXXXXXXXXXX" + number.substring(number.length() - 4);
            return string.substring(0, string.indexOf("card=") + 5) + newNumber + string.substring(string.indexOf("card=") + number.length());
        } else {
            return null;
        }
    }

    static String bank(String string) {
        Pattern p = Pattern.compile("card=(\\d*XXXXXXXXXXXXXXXXXXXX\\d*)");
        Matcher matcher = p.matcher(string);
        if (matcher.find()) {
            String number = matcher.group();
            System.out.println(number);
            String newNumber = number.substring(5, 11) + number.substring(5, 11) + number.substring(number.length() - 4, number.length());
            return string.substring(0, string.indexOf("card=") + 5) + newNumber + string.substring(string.indexOf("card=") + number.length());
        } else {
            return null;
        }
    }

}

class Sum3 {

    private HashSet<Integer> sums;
    private ArrayList<Integer> list;
    private int lastIndex;

    public Sum3() {
        this.lastIndex = 0;
        this.list = new ArrayList<>();
        this.sums = new HashSet<>();
    }

    /**
     * Adds/appends list of integers at the end of internal list.
     */
    public void addLast(int[] list) {
        for (int i : list) {
            this.list.add(i);
        }
        int sum = 0;
        for (; this.lastIndex < this.list.size() - 2; this.lastIndex++) {
            for (int j = this.lastIndex; j < 3; j++) {
                sum += this.list.get(j);
            }
            this.sums.add(sum);
        }
        if (this.lastIndex > this.list.size() - 3) {
            this.lastIndex = this.list.size() - 3;
        }
    }

    /**
     * Returns boolean representing if any three consecutive integers in the
     * internal list have given sum.
     */
    public boolean containsSum3(int sum) {
        return this.sums.contains(sum);
    }

    public static void main(String[] args) {
        Sum3 s = new Sum3();

        s.addLast(new int[] { 1, 2, 3 });

        System.out.println(s.containsSum3(6));
        System.out.println(s.containsSum3(9));

        s.addLast(new int[] { 4 });

        System.out.println(s.containsSum3(9));
    }
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
