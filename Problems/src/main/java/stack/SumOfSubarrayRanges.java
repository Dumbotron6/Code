package stack;

import java.util.LinkedList;

public class SumOfSubarrayRanges {
    /*
    https://leetcode.com/problems/sum-of-subarray-ranges/description/
     */

    /*
    Same as SumOfSubarrayMinimums. Here, we calculate both max and min and return the difference.
     */
    public long subArrayRanges(int[] nums) {
        LinkedList<Integer> minStack = new LinkedList<Integer>();
        LinkedList<Integer> maxStack = new LinkedList<Integer>();
        int n = nums.length-1;
        int[] minRight = new int[n+1];
        int[] minLeft = new int[n+1];
        int[] maxRight = new int[n+1];
        int[] maxLeft = new int[n+1];
        long mintotal = 0;
        long maxtotal = 0;

        for(int i = 0; i <= n; i++) {
            while(!minStack.isEmpty() && nums[minStack.peek()] >= nums[i]) {
                minRight[i]+=minRight[minStack.peek()];
                minStack.pop();
            }

            while(!maxStack.isEmpty() && nums[maxStack.peek()] <= nums[i]) {
                maxRight[i]+=maxRight[maxStack.peek()];
                maxStack.pop();
            }

            minRight[i]++;
            minStack.push(i);

            maxRight[i]++;
            maxStack.push(i);
        }

        minStack = new LinkedList<Integer>();
        maxStack = new LinkedList<Integer>();

        for(int i = n; i >= 0; i--) {
            while(!minStack.isEmpty() && nums[minStack.peek()] > nums[i]) {
                minLeft[i]+=minLeft[minStack.peek()];
                minStack.pop();
            }

            while(!maxStack.isEmpty() && nums[maxStack.peek()] < nums[i]) {
                maxLeft[i]+=maxLeft[maxStack.peek()];
                maxStack.pop();
            }

            minLeft[i]++;
            minStack.push(i);

            maxLeft[i]++;
            maxStack.push(i);
        }

        for(int i = 0; i <= n; i++) {
            maxtotal += (long)nums[i]*maxRight[i]*maxLeft[i];
            mintotal += (long)nums[i]*minRight[i]*minLeft[i];
        }


        return maxtotal-mintotal;
    }
}
