package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permutations2 {
    /*
    https://leetcode.com/problems/permutations-ii/description/
     */

    int len;
    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<Integer, Integer>();
        len = nums.length;

        for(int i = 0; i < nums.length; i++) { //Keep track of each number frequency.
            counter.put(nums[i], counter.getOrDefault(nums[i], 0)+1);
        }

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfsPermute(counter, new ArrayList<Integer>(), result);

        return result;
    }

    public void dfsPermute(Map<Integer, Integer> counter, List<Integer> curr, List<List<Integer>> result) {
        if(curr.size() == len) {
            result.add(new ArrayList<Integer>(curr));
            return;
        }

        //Go through the map at every recursion. If count > 0, add it to the list. Then backtrack.
        for(Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if(entry.getValue() > 0) {
                entry.setValue(entry.getValue()-1);
                curr.add(entry.getKey());
                dfsPermute(counter, curr, result);
                curr.removeLast();
                entry.setValue(entry.getValue()+1);
            }
        }
    }
}
