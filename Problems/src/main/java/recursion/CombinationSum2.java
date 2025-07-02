package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    /*
    https://leetcode.com/problems/combination-sum-ii/description/
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> finalCombo = new ArrayList<List<Integer>>();
        List<Integer> combo = new ArrayList<Integer>();
        Arrays.sort(candidates);
        findCombos(candidates, 0, target, combo, finalCombo, 0);
        return new ArrayList(finalCombo);
    }

    /*
    We sort the array to avoid duplicates.
    If we take the array [2,2,4,8] and the target is 8, when start is 0, 2 is added to the stack
        and it enters findCombos inside the loop.
    Inside the recursive findCombos, start is 1 and 2 is added to the stack. So now the stack has [2,2].
    In the next recursion, [2,2,4] is found and added to finalCombos.
    The parent findCombos caller pops till start is back at 0- thereby leaving the stack empty.
    Now it checks candidates[i] == candidates[i - 1] which is 2==2. This helps skip duplicates. Now the combo check
        continues with [4,...].

    So what happens is, we are avoiding adding to the stack if it has previously been added and checked before.
    Because when we began with start as 0, we already checked [2,2,4], [2,4,8], with 0's recursion.
    It is unnecessary to repeat the same with index 1's recursion.
    Let's say the array is [2,2,4,8,8], [8] would have already been checked when start is 0 and i is 3.
    There is no need to check the same with start is 0 and i is 4.
    If you are worried about [8,8] combo or [2,2,8] combo etc, they were already checked when i and start were both 0.
    They would have been covered by the inner recursions.
     */
    public void findCombos(int[] candidates, int sum, int target, List<Integer> stack, List<List<Integer>> finalCombo, int start) {
        if(sum == target) {
            List<Integer> combo = new ArrayList<Integer>();
            combo.addAll(stack);
            finalCombo.add(combo);
        }

        for(int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1])
                continue;
            if(sum > target)
                break;
            sum += candidates[i];
            stack.add(candidates[i]);
            findCombos(candidates, sum, target, stack, finalCombo, i+1);
            sum -= candidates[i];
            stack.removeLast();
        }
    }
}
