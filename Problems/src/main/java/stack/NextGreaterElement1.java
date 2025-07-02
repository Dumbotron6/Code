package stack;

import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElement1 {
    /*
    https://leetcode.com/problems/next-greater-element-i/
     */

    /*
    We use the stack 'greater' to store the last encountered element.

    For [2,1,2,5,3,4], for 4, the stack is empty. Then we store 4 in the stack. For 3, put 4 as the next greater
        element in the map and then add 3 to the stack. Then for 5, we keep popping the stack looking for a greater
        element. When it is not found, we keep -1 as the next greater element. Then we store 5 in the stack.
    So for 2, the top of the stack is 5, which is greater than 2. Then we store 2 in the stack. For 1, top of the
        stack is 2 which is the next greater. For the final 2, we keep popping till the top of the stack is 5 which
        is the next greater.
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> greater = new Stack<Integer>();
        HashMap<Integer, Integer> greaterMap = new HashMap<Integer, Integer>();
        int[] returnArray = new int[nums1.length];

        for(int i = nums2.length-1; i >= 0; i--) {
            while(!greater.empty()) {
                if(greater.peek() < nums2[i])
                    greater.pop();
                else {
                    greaterMap.put(nums2[i], greater.peek());
                    break;
                }
            }
            if(greater.empty())
                greaterMap.put(nums2[i], -1);
            greater.push(nums2[i]);
        }

        for(int i = 0; i < nums1.length; i++) {
            returnArray[i] = greaterMap.get(nums1[i]);
        }

        return returnArray;
    }
}
