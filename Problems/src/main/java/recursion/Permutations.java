package recursion;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    /*
    https://leetcode.com/problems/permutations/
     */

    //We use the boolean array to keep track whether a number has been added or not.
    public List<List<Integer>> permute(int[] nums) {
        boolean[] tracker = new boolean[nums.length];
        List<List<Integer>> finalList = new ArrayList<List<Integer>>();
        recursionHelper(nums, tracker, new ArrayList<Integer>(), finalList);
        return finalList;
    }

    /*
    When we have used all elements, then the list size will be equal to nums size. That's our stopping condition.
    We backtrack and remove the added element, so the recursion call will add it in the downstream loop,
        thus changing the order.
    The for loop will no longer call recursively when all indexes in tracker is true, thus exiting the method.
    We always copy the list to a new list before adding to finalList because adding currList would result in it losing elements
        when going up in recursion.
    */
    public void recursionHelper(int[] nums, boolean[] tracker, List<Integer> currList, List<List<Integer>> finalList) {
        if(currList.size() == nums.length) {
            finalList.add(new ArrayList<Integer>(currList));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(!tracker[i]) {
                tracker[i] = true;
                currList.add(nums[i]);
                recursionHelper(nums, tracker, currList, finalList);
                tracker[i] = false;
                currList.remove(currList.size()-1); // Can re replaced with currList.removeLast(); Java 21.
            }
        }
    }

    //NOTE: Since all the elements are unique, a set can also be used instead if a list.
}
