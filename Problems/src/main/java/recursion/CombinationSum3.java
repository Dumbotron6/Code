package recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {
    /*
    https://leetcode.com/problems/combination-sum-iii/
     */

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> finalCombos = new ArrayList<List<Integer>>();
        findCombos(k, 0, n, new ArrayList<Integer>(), finalCombos, 1);
        return finalCombos;
    }

    public void findCombos(int k, int sum, int target, List<Integer> stack, List<List<Integer>> finalCombos, int start) {
        if(sum == target && stack.size() == k) {
            finalCombos.add(new ArrayList(stack));
        }

        for(int i = start; i < 10; i++) {
            if(sum > target)
                break;
            if(stack.size() >= k)
                break;
            stack.add(i);
            sum += i;
            findCombos(k, sum, target, stack, finalCombos, i+1);
            sum -= i;
            stack.removeLast();
        }
    }
}
