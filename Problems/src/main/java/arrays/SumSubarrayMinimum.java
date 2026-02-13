package arrays;

import java.util.LinkedList;

public class SumSubarrayMinimum {
    /*
    https://leetcode.com/problems/sum-of-subarray-minimums/description/
     */

    /*
    At each index, if it's the last element of a subarray from the left, we determine till what index will it be the minimum.
    Same in the next loop, but from the right.
    For example, in [3,2,1,4,5], at index 1, it will be minimum till [3,2]. Now At index 2, value 1 will be minimum as part of [3,2,1].
    If value 2 is the min of two indexes on the left, it stands to reason value 1 will be new minimum if it's part of those two indexes.
    Which is why we do minLeft[i] += minLeft[minStack.peek()]; We pop till the current index is no longer the minimum.
    Now minLeft[i]*minRight[i] is because index 1 will make minLeft 3 and minRight 3. So multiplying that will give use total number of subarrays
        where index 1 is the minimum(revise this part).
    */
    public int sumSubarrayMins(int[] arr) {
        LinkedList<Integer> minStack = new LinkedList<Integer>();
        int n = arr.length-1;
        long mod = (long) 1e9 + 7;
        int[] minRight = new int[n+1];
        int[] minLeft = new int[n+1];

        for(int i = 0; i <= n; i++) {
            while(!minStack.isEmpty() && arr[minStack.peek()] >= arr[i]) {
                minLeft[i] += minLeft[minStack.peek()];
                minStack.pop();
            }
            minLeft[i]++;
            minStack.push(i);
        }

        minStack = new LinkedList<Integer>();

        for(int i = n; i >= 0; i--) {
            while(!minStack.isEmpty() && arr[minStack.peek()] > arr[i]) {
                minRight[i] += minRight[minStack.peek()];
                minStack.pop();
            }
            minRight[i]++;
            minStack.push(i);
        }

        long total = 0;
        for(int i = 0; i <= n; i++) {
            total += (long)arr[i]*(minLeft[i]*minRight[i]);
            total %= mod;
        }

        return (int)total;
    }
    /*
    Why >= in the first loop and only > in the second loop? Because coming from the right, we don't want to double
        count what already came in the left.
     */
}
