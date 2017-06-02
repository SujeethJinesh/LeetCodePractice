import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by admin on 11/4/16.
 */
public class Solution4 {

    static String[] missingWords(String s, String t) {

        int someCount = 1;

        String[] sArray = s.split(" ");
        LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();

        for (String sItem : sArray) {
            if (hashMap.containsKey(sItem)) {
                hashMap.put(sItem + someCount++, hashMap.get(sItem) + 1);
            } else {
                hashMap.put(sItem, 1);
            }
        }

        String[] tArray = t.split(" ");

        for (String tItem : tArray) {
            if (hashMap.containsKey(tItem) && hashMap.get(tItem) > 1) {
                hashMap.put(tItem + hashMap.get(tItem), hashMap.get(tItem) - 1);
            } else {
                hashMap.remove(tItem);
            }
        }

        String[] toReturn = new String[hashMap.size()];
        int counter = 0;

        Iterator iterator = hashMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            toReturn[counter++] = pair.getKey().toString();
            iterator.remove();
        }

        return toReturn;
    }

    static void arrangeCoins(long[] coins) {

        for (long num : coins) {
            System.out.println((int) ((Math.sqrt(num * 8 + 1) - 1)/2));
        }

    }

    static String[] buildSubsequences(String s) {

        return null;

    }

    public static void main(String[] args) {
        for (String word : missingWords("I like programming lots lots programming", "I programming lots")) {
            System.out.println(word);
        }
    }

}



//    static String[] missingWords(String s, String t) {
//
//        String[] sArray = s.split(" ");
//        LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();
//
//        for (String sItem : sArray) {
//            if (hashMap.containsKey(sItem)) {
//                hashMap.put(sItem, hashMap.get(sItem) + 1);
//            } else {
//                hashMap.put(sItem, 1);
//            }
//        }
//
//        String[] tArray = t.split(" ");
//
//        for (String tItem : tArray) {
//            if (hashMap.containsKey(tItem) && hashMap.get(tItem) > 1) {
//                hashMap.put(tItem, hashMap.get(tItem) - 1);
//            } else {
//                hashMap.remove(tItem);
//            }
//        }
//
//        String[] toReturn = new String[hashMap.size()];
//        int counter = 0;
//
//        Iterator iterator = hashMap.entrySet().iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry pair = (Map.Entry) iterator.next();
//            toReturn[counter++] = pair.getKey().toString();
//        }
//
//        return toReturn;
//    }