package recursion;

import java.util.ArrayList;
import java.util.List;

public class SubsetsCombination {
    /*
    https://leetcode.com/problems/subsets/description/
    Subsets of [1,2,3] are [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> returnList = new ArrayList<List<Integer>>();
        getSubSets(returnList, new ArrayList<Integer>(), nums, 0);
        return returnList;
    }

    /*
    This involves backtracking. Backtracking means going back on any action we did.
    In this case, it is numList.remove the last element we inserted.
    Reason, numsList.add inserts 1. The recursive getSubSets works with 1 as the first elements.
    [1],[1,2],[1,3],[1,2,3] are generated.
    When the remove happens, 1 is removed and the list becomes empty.
    Due to looping, 2 is inserted and 2 becomes the first element.
    [2] and [2,3] are generated.
    Note: [1,3] is generated because after generating [1,2,3], 3 and 2 are removed in the parent recursive calls
        leaving the array with [1]. Which then adds [3] becoming [1,3].
     */
    public void getSubSets(List<List<Integer>> returnList, List<Integer> numList, int[] nums, int start){
        returnList.add(numList);

        for(int i = start; i < nums.length; i++){
            numList.add(nums[i]);
            getSubSets(returnList, new ArrayList<Integer>(numList), nums, i+1);
            numList.remove(numList.size()-1);
        }
    }
}
