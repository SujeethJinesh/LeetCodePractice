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

    public List<List<Integer>> printByLevel(TreeNode root) {

        if (root == null) {
            return new LinkedList<List<Integer>>();
        }

        List<List<Integer>> toReturn = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode curr = root;
        queue.add(curr);

        while(!queue.isEmpty()) {
            int numLevel = queue.size();
            List<Integer> sublist = new LinkedList<>();
            for (int i = 0; i < numLevel; i++) {
                curr = queue.poll();
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                sublist.add(curr.data);
            }
            toReturn.add(sublist);
        }
        return toReturn;
    }

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

    public static void main(String[] args) {
        System.out.println();
    }

}





















