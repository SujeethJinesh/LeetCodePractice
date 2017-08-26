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
        HashSet<String> bankSet = new HashSet<>();

        //Add sequences from bank to set for fast lookup
        for (String sequence : bank) {
            bankSet.add(sequence);
        }

        int minimum = Integer.MAX_VALUE;
        int startHammingDistance;
        int endHammingDistance;
        int sum;

        for (String sequence : bank) {
            startHammingDistance = findHammingDistance(start, sequence, bankSet);
            endHammingDistance = findHammingDistance(end, sequence, bankSet);

            sum = startHammingDistance + endHammingDistance;

            if (sum < minimum) {
                minimum = sum;
            }

        }

        if (minimum == Integer.MAX_VALUE) {
            return -1;
        }

        return minimum;
    }

    static int findHammingDistance(String current, String bankSequence, Set<String> bankSet) {
        int distance = 0;

        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != bankSequence.charAt(i) &&
                    bankSet.contains(current.substring(0, i) + bankSequence.charAt(i) + current.substring(i + 1, current.length()))) {
                distance++;
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        int dist = findMutationDistance(
                "AAAAAAAA",
                "AAAAAATT",
                new String[]{
                        "AAAAAAAA",
                        "AAAAAAAT",
                        "AAAAAATT",
                        "AAAAATTT"
                }
        );

        System.out.println(dist);
    }

//    static class Solution {
//
//        static class Entry {
//            int year;
//            int month;
//            ArrayList<TypeNumber> typeNumbers;
//            String dateMonth;
//
//            Entry(int year, int month, ArrayList<TypeNumber> typeNumbers, String dateMonth) {
//                this.year = year;
//                this.month = month;
//                this.typeNumbers = typeNumbers;
//                this.dateMonth = dateMonth;
//            }
//
//            void update(String type, int number) {
//
//                String currentType;
//                boolean newType = false;
//
//                for (int i = 0; i < this.typeNumbers.size(); i++) {
//                    currentType = this.typeNumbers.get(i);
//                    if (currentType.equals(type)) {
//                        this.typeNumbers.update(i, this.type.get(i) + number);
//                        newType = !newType;
//                        break;
//                    }
//                }
//
//                if (!newType) {
//                    types.add(type);
//                    numbers.add(number);
//                }
//            }
//
//            @Override
//            public String toString() {
//
//                StringBuilder sb = new StringBuilder();
//
//                sb.append(this.dateMonth + ", ");
//
//                Collections.sort(this.types);
//
//                for (int i = 0; i < this.types.size(); i++) {
//                    sb.append(this.types.get(i).toString());
//                }
//
//                return sb.toString().replaceAll(", $", "");
//            }
//        }
//
//        static class TypeNumber {
//            String type;
//            int number;
//
//            TypeNumber(String type, int number) {
//                this.type = type;
//                this.number = number;
//            }
//
//            @Override
//            public String toString() {
//                return this.type + " " + this.number + ", ";
//            }
//        }
//
//        static class EntryComparator implements Comparator<Entry> {
//
//            @Override
//            public int compare(Entry e1, Entry e2) {
//                if (e1.year < e2.year || e1.year == e2.year && e1.month < e2.month) {
//                    return 1;
//                } else if (e1.year > e2.year || e1.year == e2.year && e1.month > e2.month) {
//                    return -1;
//                }
//                return 0;
//            }
//        }
//
//        public static void main(String args[] ) throws Exception {
//
//            Scanner scan = new Scanner(System.in);
//            List<Entry> entries = new ArrayList<>();
//
//            String[] startString = scan.next().split("[-,]+");
//            String[] endString = scan.next().split("[-,]+");
//
//            Entry start = new Entry(Integer.parseInt(startString[0]), Integer.parseInt(startString[1]), null, null, null);
//            Entry end = new Entry(Integer.parseInt(endString[0]), Integer.parseInt(endString[1]), null, null, null);
//
//            while (scan.hasNext()) {
//                String[] date = scan.next().split("[-,]+");
//                String type = scan.next();
//                int number = scan.nextInt();
//
//                entries.add(new Entry(Integer.parseInt(date[0]), Integer.parseInt(date[1]), new ArrayList<String>(){{add(type);}}, new ArrayList<Integer>(){{add(number);}}, date[0] + "-" + date[1]));
//            }
//
//            printInformation(start, end, entries);
//        }
//
//        private static void printInformation(Entry start, Entry end, List<Entry> entries) {
//            Comparator<Entry> comparator = new EntryComparator();
//            PriorityQueue<Entry> priorityQueue = new PriorityQueue<>(comparator);
//            HashMap<String, Entry> hashMap = new HashMap<>();
//
//            for (Entry entry : entries) {
//                if (inDateRange(start, end, entry)) {
//                    if (hashMap.containsKey(entry.dateMonth)) {
//                        hashMap.get(entry.dateMonth).update(entry.types.get(0), entry.numbers.get(0));
//                    } else {
//                        hashMap.put(entry.dateMonth, entry);
//                    }
//                }
//            }
//
//            for (String key : hashMap.keySet()) {
//                priorityQueue.add(hashMap.get(key));
//            }
//
//            while (priorityQueue.peek() != null) {
//                System.out.println(priorityQueue.poll());
//            }
//        }
//
//        private static boolean inDateRange(Entry start, Entry end, Entry searching) {
//            return searching.year > start.year && searching.year < end.year ||
//                    searching.year == start.year && searching.month >= start.month ||
//                    searching.year == end.year && searching.month <= end.month;
//
//        }
//    }

}
