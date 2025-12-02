package stack;

import java.util.LinkedList;

public class MinOperationsToZero {
    /*
    https://leetcode.com/problems/minimum-operations-to-convert-all-elements-to-zero/
     */

    /*
    We use monotonic stack. If we encounter a smaller number, everything prior to it would ultimately be a separate subarray.
    We skip over same number in stack as they would count as 1 element ie, all same numbers in array are removed at same time,
        so only unique numbers are kept in the array.
    When we encounter an 0, then existing elements in the stack is counted as a separate subarray and all elements would take 1
        step to make it zero. So steps += stack.size();
    Ex. [1,2,3,2,1]. When we encounter the second 2, we know all greater elements prior to it would take 1 step each. So we pop.
        We skip adding 2 as it is already present. Now we repeat the process for the second 1.
    Essentially, whenever we encounter a dip, we take app prior greater elements and add it to step.
    Ex. [2,3,4,2,3,4]. The first 3 and 4 are counted into steps and the second 3 and 4 are counted into steps.
    This is because although there are two 3 and 4, the 2 between them divides them into two separate arrays.
    We can only do all this because the requirement is to remove the smallest element each time.
    */
    public int minOperations(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int steps = 0;

        for(int num : nums) {
            if(num == 0) {
                steps += stack.size();
                stack = new LinkedList<Integer>();
                continue;
            }
            while(!stack.isEmpty() && num < stack.peek()) {
                stack.pop();
                steps++;
            }
            if(!stack.isEmpty() && stack.peek() != num) {
                stack.push(num);
            }else if(stack.isEmpty()) {
                stack.push(num);
            }
        }

        return steps+stack.size();
    }
}
