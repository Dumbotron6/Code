package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSetsCombination2 {
    /*
    https://leetcode.com/problems/subsets-ii/
     */

    /*
    The difference between SubSetsCombination and this is, we need to avoid duplicate combos.
    This has the same concept as CombinationSum2 to avoid duplicates.
    We sort the array, and then using (i > start && nums[i] == nums[i-1]), we skip over duplicates in the subsequent
        recursion calls.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> returnList = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        getSubSets(returnList, new ArrayList<Integer>(), nums, 0);
        return returnList;
    }

    public void getSubSets(List<List<Integer>> returnList, List<Integer> numList, int[] nums, int start){
        returnList.add(numList);

        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1])
                continue;
            numList.add(nums[i]);
            getSubSets(returnList, new ArrayList<Integer>(numList), nums, i+1);
            numList.removeLast();
        }
    }
}
