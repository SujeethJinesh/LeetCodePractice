import javax.xml.crypto.Data;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution2 {

    //SELECT Department.NAME AS deptNames, COUNT(Employee.DEPT_ID) AS thisCount FROM Employee RIGHT JOIN Department USING (DEPT_ID) GROUP BY DEPT_ID ORDER BY thisCount DESC, deptNames ASC
    private static Map<String, DataObject> backingTreeMap;
    private static String startDate;
    private static String endDate;

    /**
     * Driver for the Solution class. This grabs the start date, end date and data from
     * STDIN, and outputs using STDOUT.
     *
     * Using TreeMap because it is the most efficient data structure, O(log(n)) for most
     * methods, that sorts in order without much headway.
     *
     * Another implementation would be to replace my TreeMaps with HashMaps (if there is
     * a single thread) or HashTables (multiple threads), and perform a sort right before
     * printing out. Albeit, it would not be most effective for sorting. One would need to
     * input the values into an array and then sort from there, which would add to the
     * space and time complexities.
     *
     * Run time of TreeMap implementation: O(n*log(n))
     * Space complexity of TreeMap Implementation: O(n)
     * @param args
     * @throws Exception
     */
    public static void main(String args[] ) throws Exception {

        //Grabs start date and end date
        Scanner sc = new Scanner(System.in);
        String[] splitInputDatesArray = sc.nextLine().split(", ");
        sc.nextLine();
        startDate = splitInputDatesArray[0];
        endDate = splitInputDatesArray[1];

        //Using a TreeMap for sorting in descending order by default.
        backingTreeMap = new TreeMap<>(Collections.reverseOrder()); //Using hashtable because it is synchronized

        //Loop until all lines have been used
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] splitterArray = line.split(", ");

            //grabbing date, keys, parameters, and counts.
            String date = splitterArray[0];
            //Using StringBuilder because it is more efficient than concatenating strings
            String yearMonthKey = new StringBuilder(date.split("-")[0])
                    .append("-")
                    .append((date.split("-")[1]))
                    .toString();
            String parameter = splitterArray[1];
            int count = Integer.parseInt(splitterArray[2]);

            //Check that the date is in the start and end dates, and the count is greater than 0.
            if (yearMonthKey.compareTo(startDate) >= 0
                    && yearMonthKey.compareTo(endDate) < 0
                    && count > 0) {
                //check to see if the value is already in the TreeMap.
                if (backingTreeMap.containsKey(yearMonthKey)) {
                    backingTreeMap.get(yearMonthKey).put(parameter, count);
                } else {
                    DataObject dO = new DataObject();
                    dO.put(parameter, count);
                    backingTreeMap.put(yearMonthKey, dO);
                }
            }
        }

        //Printing via STDOUT
        for (Object o : backingTreeMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }

    private static class DataObject {

        Map<String, Integer> map;

        /**
         * Constructor.
         */
        DataObject() {
            map = new TreeMap<>(); //Initialize TreeMap for sorting
        }

        /**
         * This is the custom put method for my DataObject, takes into
         * account collisions.
         * @param param parameter we use
         * @param count number of hits the parameter received
         */
        void put(String param, int count) {
            if (map.containsKey(param)) {
                map.replace(param, map.get(param) + count);
            } else {
                map.put(param, count);
            }
        }

        @Override
        public String toString() {
            StringBuilder toReturn = new StringBuilder("");

            Iterator treeMapIterator = map.entrySet().iterator();

            while (treeMapIterator.hasNext()) {
                Map.Entry entry = (Map.Entry) treeMapIterator.next();
                if (treeMapIterator.hasNext()) {
                    toReturn.append(entry.getKey()).append(",").append(entry.getValue().toString() + ", ");
                } else {
                    toReturn.append(entry.getKey()).append(", ").append(entry.getValue().toString());
                }
            }

            return toReturn.toString();
        }

    }

}