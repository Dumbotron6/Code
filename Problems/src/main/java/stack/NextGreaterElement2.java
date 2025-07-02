package stack;

import java.util.LinkedList;

public class NextGreaterElement2 {
    /*
    https://leetcode.com/problems/next-greater-element-ii/description/
     */

    /*
    We fill returnArray twice. The first time, say for array [2,1,2,5,3,4], it will fill the returnArray as usual with
        the final value being -1.
    i % len gives same value for multiples of i. For example, for i = 2 or 6 when len = 4, i % len will be 2.
    Now during the next iteration, greater() will have filled with last greater which is 5. Now when 4 is hit again,
        5 will be the next greater. returnArray will be [5,2,5,-1,4,5].
    For [2,1,2,5,4,3], returnArray will be [5,2,5,-1,5,5]. If it wasn't circular, it would've been [5,2,5,-1,-1,-1].
    So, for any element that would've had -1 in the first iteration would now be filled with the next greater in the
        circular array.
     */
    public int[] nextGreaterElements(int[] nums) {
        LinkedList<Integer> greater = new LinkedList<Integer>();
        int[] returnArray = new int[nums.length];
        int len = nums.length;

        for(int i = 2 * len - 1; i >= 0 ; i--) {
            while(!greater.isEmpty() && nums[i % len] >= greater.peek()) {
                greater.pop();
            }
            if(greater.isEmpty())
                returnArray[i % len] = -1;
            else
                returnArray[i % len] = greater.peek();
            greater.push(nums[i % len]);
        }

        return returnArray;
    }
}
