import apple.laf.JRSUIUtils;
import sun.awt.image.ImageWatched;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by admin on 4/12/17.
 */
public class MicrosoftPrep {

    public Node reverse(Node head) {

        if (head == null) {
            return null;
        }

        Node prev = null;
        Node curr = head;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }

    public Node reverseEveryK(Node head, int k) {

        if (head == null) {
            return null;
        }

        Node prev = null;
        Node curr = head;
        Node next = null;

        int count = 0;

        while (count++ < k && curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        if (next != null) {
            head.next = reverseEveryK(next, k);
        }

        head = prev;

        return head;
    }

//    public List<List<Integer>> printByLevel(TreeNode root) {
//
//        if (root == null) {
//            return new LinkedList<List<Integer>>();
//        }
//
//        List<List<Integer>> toReturn = new LinkedList<>();
//        Queue<TreeNode> queue = new LinkedList<>();
//
//        TreeNode curr = root;
//        queue.add(curr);
//
//        while(!queue.isEmpty()) {
//            int numLevel = queue.size();
//            List<Integer> sublist = new LinkedList<>();
//            for (int i = 0; i < numLevel; i++) {
//                curr = queue.poll();
//                if (curr.left != null) {
//                    queue.add(curr.left);
//                }
//                if (curr.right != null) {
//                    queue.add(curr.right);
//                }
//                sublist.add(curr.data);
//            }
//            toReturn.add(sublist);
//        }
//        return toReturn;
//    }

    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
/*******************************************************/
    public static char[] reverseStringArray(String s) {
        char[] toReturn = s.toCharArray();
        int j = s.length() - 1;
        for (int i = 0; i < s.length()/2; i++) {
            swap(toReturn, i, j--);
        }
        return toReturn;
    }

    public static void swap(char[] arr, int left, int right) {
        char c = arr[left];
        arr[left] = arr[right];
        arr[right] = c;
    }
/*******************************************************/

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    public char findTheDifference(String s, String t) {

        HashMap<Character, Integer> hm = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hm.containsKey(c)) {
                hm.put(c, hm.get(c) + 1);
            } else {
                hm.put(c, 1);
            }
        }

        for (int j = 0; j < t.length(); j++) {
            char c = t.charAt(j);
            if (!hm.containsKey(c) || hm.get(c) - 1 == -1) {
                return c;
            } else {
                hm.put(c, hm.get(c) - 1);
            }
        }
        return 'a';
    }

    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        TreeNode right = root.right;
        TreeNode left = root.left;

        root.right = invertTree(left);
        root.left = invertTree(right);

        return root;
    }

    public static boolean substringSearch(String searching, String checking) {
        if (checking.length() > searching.length()) {
            return false;
        }

        for (int i = 0; i < searching.length() - checking.length() + 1; i++) {
            int j = 0;
            while (searching.charAt(i + j) == checking.charAt(j++)) {
                if (j == checking.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List duplicateInt(int[] array) {
      HashSet set = new HashSet();
      List list = new ArrayList();
      for (int integer : array) {
        if (set.contains(integer)) {
            list.add(integer);
        } else {
          set.add(integer);
        }
      }
      return list;
    }


    public static int nthItemThroughSum(int[] arr1, int[] arr2, int k) {

        HashSet set = new HashSet();

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                set.add(arr1[i] + arr2[j]);
            }
        }

        Object[] sortedArray = set.toArray();
        Arrays.sort(sortedArray);

        return (int) sortedArray[k-1];
    }

    public static int carry(int int1, int int2) {
        int sum = int1 + int2;
        int int1_digit;
        int int2_digit;
        int sum_digit;

        int toReturn = 0;

        while (int1 != 0 || int2 != 0) {
            int1_digit = int1 % 10;
            int2_digit = int2 % 10;
            sum_digit = sum % 10;

            if (int1_digit >= sum_digit && int2_digit >= sum_digit) {
                toReturn++;
            }

            int1 /= 10;
            int2 /= 10;
            sum /= 10;
        }

        return toReturn;
    }

    /********************************************************************************************************************/

    public static void main(String[] args) {
        int[][] arr1 = {{2, 1, 0, 2, 1}, {1,0,1,2,1}, {1,0,0,2,1}};
        int[][] arr2 = {{2,1,0,2,1}, {0,0,1,2,1}, {1,0,0,2,1}};

        System.out.println(rottenOranges(arr1));
        System.out.println(rottenOranges(arr2));

//        System.out.println(carry(123, 456));
//        System.out.println(carry(555, 555));
//        System.out.println(carry(123, 594));
//        System.out.println(carry(99, 1));

    //        List<Integer> list = duplicateInt(arr);
    //        for (int item : list) {
    //            System.out.println(item);
    //        }
    }

    public static class Index {
        int i;
        int j;

        public Index(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    // Returns a HashSet of the indices changed to 2
    private static HashSet<Index> BFS(int[][] arr, Index index) {

        HashSet<Index> toReturn = new HashSet<>();
        Queue<Index> q = new LinkedList<>();
        Index polled_index;
        ArrayList<Index> legalNeighbors;

        toReturn.add(index);

        while (!q.isEmpty()) {
            polled_index = q.poll();
            legalNeighbors = getLegalNeighbors(arr, polled_index);
            for (Index i : legalNeighbors) {
                if (!toReturn.contains(i)) {
                    q.add(i);
                    toReturn.add(i);
                }
            }
        }
        return toReturn;
    }

    private static ArrayList<Index> getLegalNeighbors(int[][] arr, Index index) {
        ArrayList<Index> toReturn = new ArrayList<>();

        int i = index.i;
        int j = index.j;
        int length = arr.length;
        int width = arr[0].length;

        if (i - 1 > 0 && arr[i-1][j] == 1) {
            toReturn.add(new Index(i - 1, j));
        }
        if (i + 1 < length && arr[i+1][j] == 1) {
            toReturn.add(new Index(i + 1, j));
        }
        if (j - 1 > 0 && arr[i][j-1] == 1) {
            toReturn.add(new Index(i, j-1));
        }
        if (j + 1 < width && arr[i][j+1] == 1) {
            toReturn.add(new Index(i, j+1));
        }
        return toReturn;
    }

    public static int rottenOranges(int[][] arr) {
        int sum = 0;
        HashSet<Index> set = new HashSet<>(); // contains all indices that are already rotten
        Index index;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 2) {
                    index = new Index(i, j);
                    set.addAll(BFS(arr, index));
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1 && !set.contains(new Index(i,j))) {
                    return -1;
                }
                if (arr[i][j] == 2 && !set.contains(new Index(i,j))) {
                    sum++;
                }
            }
        }
        return sum;
    }
    /********************************************************************************************************************/

    public static List<List<TreeNode>> printByLevel(TreeNode root) {

        if (root == null) {
            return new LinkedList<>();
        }

        List<List<TreeNode>> toReturn = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        List<TreeNode> sublist;
        TreeNode current;

        q.add(root);

        while (!q.isEmpty()) {
            sublist = new ArrayList<>();
            for (int i = 0; i < q.size(); i++) {
                current = q.poll();
                if (current.left != null) {
                    q.add(current.left);
                }
                if (current.right != null) {
                    q.add(current.right);
                }
                sublist.add(current);
            }
            toReturn.add(sublist);
        }
        return toReturn;
    }
}
