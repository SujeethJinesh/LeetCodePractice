import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else if (i % 15 == 0) {
                list.add("FizzBuzz");
            } else {
                list.add(Integer.toString(i));
            }
        }

        return list;
    }
}