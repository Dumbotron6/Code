package recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    /*
    https://leetcode.com/problems/combination-sum/
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> stack = new ArrayList<Integer>();
        List<List<Integer>> finalCombo = new ArrayList<List<Integer>>();
        findCombos(candidates, target, 0, stack, finalCombo, 0);
        return finalCombo;
    }

    /*
    If the sum is equal to target, we add to finalCombo.
    Now let's take the second findCombos loop and example [4,2,8] with target 8.
    When FC ends with possible combos for 4, then the stack.removeLast() would remove 4 and leave us with an empty stack.
    Now FC2 would move to the next element 2. Inside FC2 recursion, FC is hit first, with [2] as the stack.
    Now this FC takes care of 2 combos. Once FC is exhausted, then 2 is removed, leaving us with an empty stack.
    Now FC2 moves to i+1 and the recursed FC would add 8 to the empty stack.

    Consider the combo [4,2,2]. Now this would be added to the stack, then FC would happen, the if condition fails,
        nothing would be added, and removeLast happens leaving us with [4,2]. Now FC2 happens, giving us [4,2,8].
    In this recursion, both FC1 and FC2 will fail, leaving us with parent [4,2] whose parent will pop and leave us
        with [4] whose parent would leave us with [].
     */
    public void findCombos(int[] candidates, int target, int sum, List<Integer> stack, List<List<Integer>> finalCombo, int i) {
        if(sum < target && i < candidates.length) {
            sum += candidates[i];
            stack.add(candidates[i]);
            if(sum == target) {
                List<Integer> combo = new ArrayList<Integer>();
                combo.addAll(stack);
                finalCombo.add(combo);
            }

            findCombos(candidates, target, sum, stack, finalCombo, i);
            stack.removeLast();
            sum -= candidates[i];
            findCombos(candidates, target, sum, stack, finalCombo, i+1);
        }
    }
}
