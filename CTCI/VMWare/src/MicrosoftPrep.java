import apple.laf.JRSUIUtils;
import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
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

    static class Person {
        String name;
        int amount;
        String location;
        int time;

        Person(String name, int amount, String location, int time) {
            this.name = name;
            this.amount = amount;
            this.location = location;
            this.time = time;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    static class PersonComparator implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            if (p1.time < p2.time) {
                return -1;
            } else if (p1.time > p2.time) {
                return 1;
            }
            return 0;
        }
    }

    public static List<Person> getSuspiciousActivity(String[] transactions) {
        Person[] persons = new Person[transactions.length];
        int index = 0;
        for (String string : transactions) {
            String[] personArr = string.split("\\|");
            String name = personArr[0];
            int amount = Integer.parseInt(personArr[1]);
            String location = personArr[2];
            int time = Integer.parseInt(personArr[3]);

            persons[index++] = new Person(name, amount, location, time);
        }

        Comparator<Person> comparator = new PersonComparator();
        PriorityQueue<Person> pq = new PriorityQueue<>(comparator);
        HashSet<String> alreadyCommittedFraud = new HashSet<>();
        HashMap<String, Person> lastSeen = new HashMap<>();

        for (Person person : persons) {
            if (!alreadyCommittedFraud.contains(person.name)) {
                if (lastSeen.containsKey(person.name) &&
                        person.time - lastSeen.get(person.name).time < 60 &&
                        !lastSeen.get(person.name).location.equals(person.location)) {
                    pq.add(lastSeen.get(person.name));
                    alreadyCommittedFraud.add(person.name);
                } else if (person.amount > 3000) {
                    pq.add(person);
                    alreadyCommittedFraud.add(person.name);
                }
            }
            lastSeen.put(person.name, person);
        }

        List<Person> toReturn = new ArrayList<>(pq);
        return toReturn;
    }


    /*********************************************************************************************/

    static class Contractor {
        String name;
        int[] ids;
        boolean start;
        int line;
        String violation;

        Contractor(String name, int[] ids, boolean start, int line) {
            this.name = name;
            this.ids = ids;
            this.line = line;
            this.start = start;
            this.violation = "";
        }

        @Override
        public String toString() {
            return this.name;
        }

        public void setViolation(String violation) {
            this.violation = violation;
        }
    }

    static class ContractorComparator implements Comparator<Contractor> {

        @Override
        public int compare(Contractor p1, Contractor p2) {
            if (p1.line < p2.line) {
                return -1;
            } else if (p1.line > p2.line) {
                return 1;
            }
            return 0;
        }
    }

    static String[] findViolations(String[] datafeed) {
        final String SHORTENED_JOB =  "SHORTENED_JOB";
        final String SUSPICIOUS_BATCH = "SUSPICIOUS_BATCH";
        Contractor[] contractors = new Contractor[datafeed.length];
        int index = 0;
        for (String string : datafeed) {
            String[] personArr = string.split(";");
            String name = personArr[0];
            int line = index + 1;
            if (personArr[1].equals("START")) {
                contractors[index++] = new Contractor(name, new int[0], true, line);
            } else {
                String[] raw = personArr[1].split(",");
                int[] ids = new int[raw.length];
                int newIndex = 0;
                for (String num : raw) {
                    ids[newIndex++] = Integer.parseInt(num);
                }
                contractors[index++] = new Contractor(name, ids, false, line);
            }
        }

        Comparator<Contractor> comparator = new ContractorComparator();
        PriorityQueue<Contractor> pq = new PriorityQueue<>(comparator);
        PriorityQueue<Integer> pq_ids_ordered = new PriorityQueue<>((x, y) -> y - x);
        HashSet<String> alreadyCommittedFraud = new HashSet<>();

        for (Contractor contractor : contractors) {
            if (!alreadyCommittedFraud.contains(contractor.name)) {
                if (contractor.ids.length > 1) {
                    for (int id : contractor.ids) {
                        if (pq_ids_ordered.peek() > id && !alreadyCommittedFraud.contains(contractor.name)) {
                            contractor.setViolation(SUSPICIOUS_BATCH); // Shortened batch violation
                            pq.add(contractor);
                            alreadyCommittedFraud.add(contractor.name);
                        }
                    }
                } else if (pq_ids_ordered.peek() != null &&
                        !contractor.start &&
                        pq_ids_ordered.peek() > contractor.ids[0]) {
                    contractor.setViolation(SHORTENED_JOB);
                    pq.add(contractor);
                    alreadyCommittedFraud.add(contractor.name);
                }
            }
            for (int id : contractor.ids) {
                pq_ids_ordered.add(id);
            }
        }

        Object[] contractors_violating = new ArrayList<>(pq).toArray();
        String[] toReturn = new String[contractors_violating.length];
        int final_indices = 0;

        for (Object contractor : contractors_violating) {
            Contractor contractor1 = (Contractor) contractor;
            toReturn[final_indices++] = contractor1.line + ";" + contractor1.name + ";" + contractor1.violation;
        }

        return toReturn;
    }

    /*********************************************************************************************/

    static int findMutationDistance(String start, String end, String[] bank) {

        if (start.equals(end)) {
            return 0;
        }

        HashSet<String> bankSet = new HashSet<>();

        //Add sequences from bank to set for fast lookup
        bankSet.addAll(Arrays.asList(bank));

        if (!bankSet.contains(end)) {
            return -1;
        }

        char[] acgt = new char[]{'A', 'C', 'G', 'T'};

        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        String current;
        String testing;
        char[] currentCharArr;
        char oldChar;
        int dist;
        int level = 0;

        queue.add(start);

        while(!queue.isEmpty()) {
            dist = queue.size();
            while (dist-- > 0) {
                current = queue.poll();
                currentCharArr = current.toCharArray();
                for(int i = 0; i < currentCharArr.length; i++) {
                    oldChar = currentCharArr[i];
                    for(char c: acgt) {
                        currentCharArr[i] = c;
                        testing = new String(currentCharArr);
                        if(!visited.contains(testing) && bankSet.contains(testing)) {
                            if (testing.equals(end)) {
                                return level + 1;
                            }
                            queue.add(testing);
                            visited.add(testing);
                        }
                    }
                    currentCharArr[i] = oldChar;
                }
            }
            level++;
        }
        return -1;
    }

    public static char[] rotateCharArray(char[] arr, int index) {
        char[] toReturn = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            toReturn[i] = arr[(++index)%arr.length];
        }
        return toReturn;
    }
//
//    public static char[] staticallyRotateCharArray(char[] arr, int index) {
//        for (int i = 0, j = index; i < index; i++, j++) {
//            char temp = arr[i];
//            arr[i] = arr[j];
//            arr[j] = temp;
//        }
//        return arr;
//    }

    public static Node reverseLinkedList(Node head) {
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

    public static Node reverseDoublyLinkedList(Node head) {
        if (head == null) {
            return null;
        }

        Node prev = null;
        Node curr = head;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.prev = next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }

    public static Node mergeLists(Node headA, Node headB) {

        if (headA == null && headB == null) {
            return null;
        }

        if (headA == null) {
            return headB;
        }

        if (headB == null) {
            return headA;
        }

        Node ANode = headA;
        Node ANodeNext = ANode.next;

        Node BNode = headB;
        Node BNodeNext = BNode.next;

        Node toReturn = (ANode.data > BNode.data) ? BNode : ANode;

        while (ANode != null && BNode != null) {
            if (ANode.data >= BNode.data) {

                while (BNodeNext != null && ANode.data >= BNodeNext.data) {
                    BNode = BNodeNext;
                    BNodeNext = BNode.next;
                }

                BNodeNext = BNode.next;
                BNode.next = ANode;
                BNode = BNodeNext;
            } else {

                while (ANodeNext != null && BNode.data >= ANodeNext.data) {
                    ANode = ANodeNext;
                    ANodeNext = ANode.next;
                }

                ANodeNext = ANode.next;
                ANode.next = BNode;
                ANode = ANodeNext;
            }
        }

        return toReturn;
    }

    public static int binarySearchIterative(int[] arr, int k) {
        if (arr == null) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        int middle;

        while (left <= right) {
            middle = left + (right - left)/2;

            if (arr[middle] > k) {
                right = middle - 1;
            } else if (arr[middle] < k) {
                left = middle + 1;
            } else {
                return middle;
            }
        }

        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int k, int l, int r) {
        if (r >= 1) {
            int m = l + (r-l)/2;

            if (arr[m] > k) {
                return binarySearchRecursive(arr, k, l, m - 1);
            } else if (arr[m] < k) {
                return binarySearchRecursive(arr, k, m + 1, r);
            } else {
                return m;
            }
        }
        return -1;
    }

    public static int findPeakElement(int[] arr) {
        return findPeakElementHelper(arr, 0, arr.length - 1);
    }

    private static int findPeakElementHelper(int[] arr, int low, int high) {

        int mid = low + (high - low)/2;

        if ((mid == 0 || arr[mid-1] <= arr[mid]) &&
                (mid == arr.length - 1 || arr[mid+1] <= arr[mid])) {
            return mid;
        } else if (mid > 0 && arr[mid-1] > arr[mid]) {
            return findPeakElementHelper(arr, low, (mid -1));
        } else {
            return findPeakElementHelper(arr, (mid + 1), high);
        }

    }

    public static int findLocalMinElement(int[] arr) {
        return findLocalMinElementHelper(arr, 0, arr.length - 1);
    }

    private static int findLocalMinElementHelper(int[] arr, int low, int high) {

        int mid = low + (high - low)/2;

        if ((mid == 0 || arr[mid - 1] >= arr[mid]) && (mid == arr.length - 1 || arr[mid + 1] > arr[mid])) {
            return mid;
        } else if (mid > 0 && arr[mid - 1] < arr[mid]) {
            return findLocalMinElementHelper(arr, low, mid - 1);
        } else {
            return findLocalMinElementHelper(arr, mid + 1, high);
        }

    }

    public static int findMinElementSortedRotatedArray(int[] arr) {

        int low = 0;
        int high = arr.length - 1;
        int middle;

        while (low <= high) {
            middle = low + (high - low)/2;

            if ((middle == 0 || arr[middle - 1] >= arr[middle]) && (middle == arr.length - 1 || arr[middle + 1] >= arr[middle]) ) {
                return arr[middle];
            } else if (middle > 0 && arr[middle - 1] < arr[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }

        }
        return arr[0];
    }

    public static int findElementSortedRotatedArray(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        int mid;

        while (low <= high) {
            mid = low + (high - low)/2;

            if (arr[mid] == k) {
                return mid;
            } else if (arr[low] <= arr[mid]) { // means is sorted
                if (arr[low] < k && arr[mid] > k) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (arr[high] > k && arr[mid] < k) {
                    low = mid + 1;
                } else {
                    high = mid -1;
                }
            }

        }
        return -1;
    }

    public static int largestSumUnsortedArray(int[] arr) {

        if (!(arr.length >= 2)) {
            return -1;
        }

        int first = arr[1];
        int second = arr[0];
        int index_first = 1;

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] > first) {
                second = first;
                first = arr[i];
                index_first = i;
            } else if (arr[i] > second && i != index_first) {
                second = arr[i];
            }
        }

        return first + second;
    }

    public static int closestPairs(int[] arr1, int[] arr2, int k) {
        int left = 0; // starts on arr1
        int right = arr2.length - 1; //starts on arr2
        int closestSum = Integer.MAX_VALUE;
        int sum;

        while (left < arr1.length && right >= 0) {
            sum = arr1[left] + arr2[right];

            if (Math.abs(sum - k) < Math.abs(closestSum - k)) {
                closestSum = sum;
            }

            if (sum > k) {
                right--;
            } else if (sum < k) {
                left++;
            } else {
                return sum;
            }
        }
        return closestSum;
    }

    public static int substringSearching(String longer, String shorter) {
        if (shorter.length() > longer.length()) {
            return -1;
        }

        int j;

        for (int i = 0; i < longer.length() - shorter.length() + 1; i++) {
            j = 0;
            while (longer.charAt(i+j) == shorter.charAt(j++)) {
                if (j == shorter.length()) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static String findingNearestSmallerNumbersOnLeftSide(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                sb.append("_");
            } else {
                sb.append(", " + stack.peek());
            }
            stack.push(arr[i]);
        }
        return sb.toString();
    }

    public static String subarrayWithGivenSum(int[] arr, int k) {

        int sum = arr[0];
        int highIndex = 1;

        while (sum < k && highIndex < arr.length) {
            sum += arr[highIndex++];
        }

        int lowIndex = 0;

        if (sum == k) {
            return String.valueOf(lowIndex) + ", " + String.valueOf(highIndex - 1);
        }

        while (sum > k && lowIndex < highIndex) {
            sum -= arr[lowIndex++];
        }

        if (sum == k) {
            return String.valueOf(lowIndex) + ", " + String.valueOf(highIndex - 1);
        }

        return String.valueOf(-1);
    }

    int minDepth(Node root)
    {
        return root == null ? 0 : (Math.min(minDepth(root.left) + 1, minDepth(root.right) + 1));
    }

    public static int possibleNumOfDecodings(int[] digits) {

        int[] counts = new int[digits.length + 1];
        counts[0] = 1;
        counts[1] = 1;

        for (int i = 2; i <= digits.length; i++) {
            counts[i] = 0;

            if (digits[i-1] > 0) {
                counts[i] = counts[i-1];
            }

            if (digits[i-2] == 1 || digits[i-2] == 2 && digits[i-1] < 7) {
                counts[i] += counts[i-2];
            }
        }

        return counts[digits.length];
    }

    static class Index {
        int i;
        int j;

        Index(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private static List<Index> getLegalNeighbors(char[][] board, Index index, char nextChar) {

        ArrayList<Index> toReturn = new ArrayList<>();

        int i = index.i;
        int j = index.j;
        int length = board.length;
        int width = board[0].length;

        if (i - 1 > 0 && board[i-1][j] == nextChar) {
            toReturn.add(new Index(i - 1, j));
        }
        if (i + 1 < length && board[i+1][j] == nextChar) {
            toReturn.add(new Index(i + 1, j));
        }
        if (j - 1 > 0 && board[i][j-1] == nextChar) {
            toReturn.add(new Index(i, j-1));
        }
        if (j + 1 < width && board[i][j+1] == nextChar) {
            toReturn.add(new Index(i, j+1));
        }
        return toReturn;

    }

    public static boolean boggle(char[][] board, String string) {
        Queue<Index> queue = new LinkedList<>();
        HashSet<Index> visited = new HashSet<>();
        Index current;
        List<Index> legalNeighbors = new ArrayList<>();
        int indexOfChar = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (string.charAt(indexOfChar) == board[i][j]) {
                    queue.add(new Index(i,j));
                }
            }
        }

        indexOfChar++;

        while (!queue.isEmpty()) {
            current = queue.poll();
            visited.add(current);
            legalNeighbors = getLegalNeighbors(board, current, string.charAt(indexOfChar));
            for (Index neighbor : legalNeighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    indexOfChar++;
                }
            }
            if (indexOfChar == string.length() - 1) {
                return true;
            }
        }
        return false;
    }

    int strstr(String str, String target)
    {
        if (target.length() > str.length()) {
            return -1;
        }

        for (int i = 0; i < str.length() - target.length() + 1; i++) {
            int j = 0;
            while (str.charAt(i+j) == target.charAt(j++)) {
                if (j == target.length()) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int sumOfTree(TreeNode root) {
        return (root == null) ? 0 : root.data + sumOfTree(root.left) + sumOfTree(root.right);
    }

    public static int toSumTree(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int oldValue = root.data;

        root.data = toSumTree(root.left) + toSumTree(root.right);

        return root.data + oldValue;
    }

    public static int maxSumFromLeaf(TreeNode root) {
        return (root == null) ? 0 : Math.max(maxSumFromLeaf(root.left) + root.data, maxSumFromLeaf(root.right) + root.data);
    }

    static class Solution {

        public int findKthLargest(int[] nums, int k) {
            int start = 0, end = nums.length - 1, index = nums.length - k;
            while (start < end) {
                int pivot = partion(nums, start, end);
                if (pivot < index) start = pivot + 1;
                else if (pivot > index) end = pivot - 1;
                else return nums[pivot];
            }
            return nums[start];
        }

        private int partion(int[] nums, int start, int end) {
            int pivot = start, temp;
            while (start <= end) {
                while (start <= end && nums[start] <= nums[pivot]) start++;
                while (start <= end && nums[end] > nums[pivot]) end--;
                if (start > end) break;
                temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
            }
            temp = nums[end];
            nums[end] = nums[pivot];
            nums[pivot] = temp;
            return end;
        }


        static class Entry {
            int year;
            int month;
            ArrayList<String> types;
            ArrayList<Integer> numbers;
            String dateMonth;

            Entry(int year, int month, ArrayList<String> type, ArrayList<Integer> numbers, String dateMonth) {
                this.year = year;
                this.month = month;
                this.types = type;
                this.numbers = numbers;
                this.dateMonth = dateMonth;
            }

            void update(String type, int number) {

                String currentType;
                boolean newType = false;

                for (int i = 0; i < this.types.size(); i++) {
                    currentType = this.types.get(i);
                    if (currentType.equals(type)) {
                        this.numbers.set(i, this.numbers.get(i) + number);
                        newType = !newType;
                        break;
                    }
                }

                if (!newType) {
                    types.add(type);
                    numbers.add(number);
                }
            }

            @Override
            public String toString() {

                StringBuilder sb = new StringBuilder();

                sb.append(this.dateMonth + ", ");

                ArrayList<TypeNumber> typeNumbers = new ArrayList<>();

                for (int i = 0; i < this.types.size(); i++) {
                    typeNumbers.add(new TypeNumber(this.types.get(i), this.numbers.get(i)));
                }

                Collections.sort(typeNumbers, new TypeNumberComparator());

                for (int i = 0; i < this.types.size(); i++) {
                    sb.append(typeNumbers.get(i));
                }

                return sb.toString().replaceAll(", $", "");
            }
        }

        static class TypeNumberComparator implements Comparator<TypeNumber> {
            public int compare(TypeNumber c1, TypeNumber c2)
            {
                return c1.type.compareTo(c2.type);
            }
        }

        static class TypeNumber {
            String type;
            int number;

            TypeNumber(String type, int number) {
                this.type = type;
                this.number = number;
            }

            @Override
            public String toString() {
                return this.type + " " + this.number + ", ";
            }
        }

        static class EntryComparator implements Comparator<Entry> {

            @Override
            public int compare(Entry e1, Entry e2) {
                if (e1.year < e2.year || e1.year == e2.year && e1.month < e2.month) {
                    return 1;
                } else if (e1.year > e2.year || e1.year == e2.year && e1.month > e2.month) {
                    return -1;
                }
                return 0;
            }
        }

        public static void main(String args[] ) throws Exception {

            Scanner scan = new Scanner(System.in);
            List<Entry> entries = new ArrayList<>();

            String[] startString = scan.next().split("[-,]+");
            String[] endString = scan.next().split("[-,]+");

            Entry start = new Entry(Integer.parseInt(startString[0]), Integer.parseInt(startString[1]), null, null, null);
            Entry end = new Entry(Integer.parseInt(endString[0]), Integer.parseInt(endString[1]), null, null, null);

            while (scan.hasNext()) {
                String[] date = scan.next().split("[-,]+");
                String type = scan.next();
                int number = scan.nextInt();

                entries.add(new Entry(Integer.parseInt(date[0]), Integer.parseInt(date[1]), new ArrayList<String>(){{add(type);}}, new ArrayList<Integer>(){{add(number);}}, date[0] + "-" + date[1]));
            }

            printInformation(start, end, entries);
        }

        private static void printInformation(Entry start, Entry end, List<Entry> entries) {
            Comparator<Entry> comparator = new EntryComparator();
            PriorityQueue<Entry> priorityQueue = new PriorityQueue<>(comparator);
            HashMap<String, Entry> hashMap = new HashMap<>();

            for (Entry entry : entries) {
                if (inDateRange(start, end, entry)) {
                    if (hashMap.containsKey(entry.dateMonth)) {
                        hashMap.get(entry.dateMonth).update(entry.types.get(0), entry.numbers.get(0));
                    } else {
                        hashMap.put(entry.dateMonth, entry);
                    }
                }
            }

            for (String key : hashMap.keySet()) {
                priorityQueue.add(hashMap.get(key));
            }

            while (priorityQueue.peek() != null) {
                System.out.println(priorityQueue.poll());
            }
        }

        private static boolean inDateRange(Entry start, Entry end, Entry searching) {
            return searching.year > start.year && searching.year < end.year ||
                    searching.year == start.year && searching.month >= start.month ||
                    searching.year == end.year && searching.month <= end.month;

        }
    }

    public static int longestChain(String[] words) {
        Set<String> bank = new HashSet<>(); // has a set of all the legal words for O(1) lookup

        bank.addAll(Arrays.asList(words));

        int highest = -1;

        Queue<String> queue = new LinkedList<>();
        String currentWord;
        int level;
        String newWord;

        for (String word : words) {
            queue.add(word);
            level = 0;
            while (!queue.isEmpty()) {
                level++;
                for (int i = 0; i < queue.size(); i++) {
                    currentWord = queue.poll();
                    for (int j = 0; j < currentWord.length(); j++) {
                        newWord = currentWord.substring(0, j) + currentWord.substring(j + 1);
                        if (bank.contains(newWord)) {
                            queue.add(newWord);
                        }
                    }
                }

            }
            if (level > highest) {
                highest = level;
            }
        }
        return highest;
    }

    public static void main(String[] args) {
        System.out.println(longestChain(new String[]{"cat", "catc", "catche","rabbit"}));
    }

}
