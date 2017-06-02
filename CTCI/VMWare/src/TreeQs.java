import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by admin on 1/28/17.
 */
public class TreeQs {

    /**
     * Gets max height/depth of binary tree.
     * @param root
     * @return
     */
    public static int Height(TreeNode root) {
        return (root == null) ? 0 : Math.max(Height(root.left), Height(root.right)) + 1;
    }

    //My first thought is to use a hashmap for this problem
//Reasoning: A hashmap will let me put in the elements, and keep a count of them then check which is odd.

//Assumptions. There will be exactly one odd appearance number in this list.
//There will be enough space in my hashmap.

//Expected Time complexity: N (going through list) + N (checking values inside hashmap) = O(N)
//Expected Space complexity: N/2 (worst case will be putting in about half of the elements) = O(N)

//Second thought is to sort and then proceed with checking the number of times a number appears. if odd -> return current, else continue
//This would have an expected Time complexity of: N*Log(N) + N = O(N*Log(N))
//Expected Space complexity: no extra space needed = O(1)

//Depending on what is more optimal for the circumstances, the answer would vary. If not worrying about space -> sol 1, else sol 2.

    public static int solutionOne(int[] array) {
        HashMap hashmap = new HashMap();            //initializing my hashmap
        for (int i : array) {                       //going through and putting them in the hashmap
            if (hashmap.containsKey(i)) {              //need to check if the value is already in hashmap
                hashmap.put(i, (int) hashmap.get(i) + 1); //this will increment the number of occurrences by one
            } else {                                //value doesn't already exist in hashmap
                hashmap.put(i, 1);                  //we put in the value to the hashmap as 1 for the current occurrence
            }
        }
        //checking the hashmap api for this (I forget what the method is to retrieve the key set of a hashmap)
        Set keySet = hashmap.keySet();
        Iterator<Integer> iterator = keySet.iterator(); //made a mistake according to the api, need to call an Iterator
        int j;
        while (iterator.hasNext()) {                //here we iterate through the keyset and check if the value is odd
            j = iterator.next();
            if ( (int) hashmap.get(j) % 2 == 1) {         //the value is odd hence is the odd numbered appearance
                return j;
            }
        }
        return -1;                                  //this would be some condition just to check and make sure the odd number exists
    }

    /**
     * Have some error, likely off by one.
     **/
    public static int solutionTwo(int[] arr) {      //implementing second solution with sorting
//failed initial tests because I got all -1, checking again
        if (arr.length == 0) {
            return -1;
        }
        Arrays.sort(arr);                           //this will sort in O(N*Log(N)) using java's double pivoted quicksort
        int index = 0;                            //need to check now to see which is odd
        int counter = 0;
        while (index + 1 < arr.length) {        //prevent index out of bounds
            if (arr[index] != arr[index + 1])
                if (counter % 2 != 0) { //check to see if the current val in index is the same as the next
                    return arr[index];
                }
                else {
                    counter++;
                }
            else {
                index++;
                counter++;
            }
        }
        return -1;
    }



    //testing to see if it's accurate on my ide
//    public static void main(String[] args) {
//
//        int[] arr1 = {1,2,3,2,3}; // initial test
//        int[] arr2 = {1,-1,-1,1,2}; // checking with negatives
//        int[] arr3 = {3}; //checking only one value
//        int[] arr4 = {}; //checking if sparse
//
//        int sol1 = solutionOne(arr1);
//        int sol2 = solutionOne(arr2);
//        int sol3 = solutionOne(arr3);
//        int sol4 = solutionOne(arr4);
//
//        System.out.println(sol1); //expected is 1
//        System.out.println(sol2); //expected is 2
//        System.out.println(sol3); //expected is 3
//        System.out.println(sol4); //expected is -1
//        //got my expected result :)
//    }

    //Let us start by weighing 4 coins on each end. We'll split the solution into cases
//Case 1:
    //the 8 coins (4 on each side) are balanced --> then the heavier one will be in the 3 remaining.
    //simply take one of the coins from the 8 equal weighted ones, and add them to the 3 remaining to have 4.
    //now separate the coins into two and weigh them. the heavier side will be the one with the coin, finally repeat for the last step.
//Case 2: one of the sides is too heavy.
    //that side will definitely contain the heavy coin. Now we separate like above and follow the procedure.

//For the 11 coin case, we will need a maximum of 3 tries (essentially O(1)). CEIL [Log_3 (11)] And have O(1) space complexity.

    //we'll need a method to check if it's balanced or not
    public static boolean isBalanced(int firstArrStart,
                              int firstArrEnd,
                              int secondArrStart,
                              int secondArrEnd,
                              int[] coins) {

        int sum1 = 0;
        int sum2 = 0;

        for (int i = firstArrStart; i <= firstArrEnd; i++) {
            sum1 += coins[i];
        }

        for (int j = secondArrStart; j <= secondArrEnd; j++) {
            sum2 += coins[j];
        }

        return (sum1 == sum2);
    }

    //returns true if left side > right side
    public static boolean greaterBalanced(int firstArrStart,
                                   int firstArrEnd,
                                   int secondArrStart,
                                   int secondArrEnd,
                                   int[] coins) {

        int sum1 = 0;
        int sum2 = 0;

        for (int i = firstArrStart; i <= firstArrEnd; i++) {
            sum1 += coins[i];
        }

        for (int j = secondArrStart; j <= secondArrEnd; j++) {
            sum2 += coins[j];
        }

        return (sum1 > sum2);
    }

    //let's take in the coins as numbers, all will be of equal weight except 1
//this will return the index of the coin with the greater weight
    public static int heavyCoin(int[] coins) {
        //first step is to separate the left from the right, let's just use indexes for this
        int firstArrStart = 0;
        int firstArrEnd = 3;

        int secondArrStart = 4;
        int secondArrEnd = 7;

        if (isBalanced(firstArrStart,firstArrEnd,secondArrStart,secondArrEnd, coins)) {
            //if balanced then let's select the last index to be our adding coin to the last three.

            if (greaterBalanced(secondArrEnd, secondArrEnd + 1, secondArrEnd + 2, secondArrEnd + 3, coins)) {
                return secondArrEnd + 1; //this means that the 9th coin is the heavier one
            } else if (greaterBalanced(9,9,10,10, coins)) {
                return 9;
            } else {
                return 10;
            }

        } else if (greaterBalanced(firstArrStart, firstArrEnd, secondArrStart, secondArrEnd, coins))  { //here we need to check which is heavier and do the same procedure
            //left greater than right, search in left arr.
            if (greaterBalanced(firstArrStart, firstArrStart + 1, firstArrStart + 2, firstArrStart + 3, coins)) {
                if (greaterBalanced(0,0,1,1,coins)) {
                    return 0;
                } else {
                    return 1;
                }
            } else if (greaterBalanced(secondArrStart, secondArrStart+1,secondArrStart+2,secondArrStart+3, coins)) {
                if (greaterBalanced(2,2,3,3,coins)) {
                    return 2;
                } else {
                    return 3;
                }
            }
        } else {
            if (greaterBalanced(firstArrEnd, firstArrEnd + 1, firstArrEnd + 2, firstArrEnd + 3, coins)) {
                if (greaterBalanced(firstArrEnd,firstArrEnd,firstArrEnd+1,firstArrEnd+1,coins)) {
                    return firstArrEnd;
                } else {
                    return firstArrEnd+1;
                }
            } else if (greaterBalanced(secondArrEnd, secondArrEnd+1,secondArrEnd+2,secondArrEnd+3, coins)) {
                if (greaterBalanced(secondArrEnd,secondArrEnd,secondArrEnd+1,secondArrEnd+1,coins)) {
                    return secondArrEnd;
                } else {
                    return secondArrEnd+1;
                }
            }
        }
        return -1;
    }

    //made a main method to test in ide
    public static void main(String[] args) {

        int[] arr1 = {1,1,1,1,1,1,1,1,1,1,2}; // expected 10
        int[] arr2 = {1,1,1,1,1,1,1,1,1,2,1}; // initial test
        int[] arr3 = {1,1,1,1,1,1,1,1,2,1,1}; // initial test
        int[] arr4 = {1,1,1,1,1,1,1,2,1,1,1}; // initial test
        int[] arr5 = {1,1,1,1,1,1,2,1,1,1,1}; // initial test
        int[] arr6 = {1,1,1,1,1,2,1,1,1,1,1}; // initial test
        int[] arr7 = {1,1,1,1,2,1,1,1,1,1,1}; // initial test
        int[] arr8 = {1,1,1,2,1,1,1,1,1,1,1}; // initial test
        int[] arr9 = {1,1,2,1,1,1,1,1,1,1,1}; // initial test
        int[] arr10 = {1,2,1,1,1,1,1,1,1,1,1}; // initial test
        int[] arr11 = {2,1,1,1,1,1,1,1,1,1,1}; // initial test

        int sol1 = heavyCoin(arr1);
        int sol2 = heavyCoin(arr2);
        int sol3 = heavyCoin(arr3);
        int sol4 = heavyCoin(arr4);
        int sol5 = heavyCoin(arr1);
        int sol6 = heavyCoin(arr2);
        int sol7 = heavyCoin(arr3);
        int sol8 = heavyCoin(arr4);
        int sol9 = heavyCoin(arr1);
        int sol10 = heavyCoin(arr2);
        int sol11 = heavyCoin(arr3);

        System.out.println(sol1); // expected 10
        System.out.println(sol2); // expected 9
        System.out.println(sol3); // expected 8
        System.out.println(sol4); // expected 7

        System.out.println(sol5); // expected 6
        System.out.println(sol6); // expected 5
        System.out.println(sol7); // expected 4
        System.out.println(sol8); // expected 3
        System.out.println(sol9); // expected 2
        System.out.println(sol10); // expected 1
        System.out.println(sol11); // expected 0

    }

}
