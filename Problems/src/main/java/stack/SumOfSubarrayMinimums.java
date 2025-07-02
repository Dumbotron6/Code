package stack;

import java.util.LinkedList;

public class SumOfSubarrayMinimums {
    /*
    https://leetcode.com/problems/next-greater-element-ii/description/
     */

    /*
    We identify the next smaller element from left to right and right to left here and store them in different arrays.
     */
    public int sumSubarrayMins(int[] arr) {
        LinkedList<Integer> minStack = new LinkedList<Integer>();
        int n = arr.length-1;
        long mod = (long) 1e9 + 7;
        int[] minRight = new int[n+1];
        int[] minLeft = new int[n+1];
        long total = 0;

        /*
        We identify next smallest element.
        If [4,5,2,1] is the array, minRight would be [1,1,3,4]. Going by the loop, when the ith element is smaller
            than minStack.peek(), then we add that count to minRight[i].
        For example, when i is at 2, the element is 2. Now 2 is the smallest elements for 2 elements to its left.
        [2] is covered by minRight[i]++. [4,5,2] and [5,2] are covered by minRight[i]+=minRight[minStack.peek()]
            ie, minRight[i] += minRight[1] and minRight[i] += minRight[0].
         */
        for(int i = 0; i <= n; i++) {
            while(!minStack.isEmpty() && arr[minStack.peek()] >= arr[i]) {
                minRight[i]+=minRight[minStack.peek()];
                minStack.pop();
            }
            minRight[i]++;
            minStack.push(i);
        }
        /*
        The same logic is applied from right and from left. This way, we have a count of the number of subarrays
            where ith element is smallest from the right and from the left.
         */
        minStack = new LinkedList<Integer>();
        for(int i = n; i >= 0; i--) {
            while(!minStack.isEmpty() && arr[minStack.peek()] > arr[i]) {
                minLeft[i]+=minLeft[minStack.peek()];
                minStack.pop();
            }
            minLeft[i]++;
            minStack.push(i);
        }

        /*
        Now why do we multiply instead of add?
        Take [1,4,6,7,3,7,8,1]. From left minRight[i] for 3 would be 4 and minLeft[i] would be 4.
        This covers [4,5,7,3] and [3,7,8]. But what about [4,5,7,3,7] or any other combination?
        Hence the multiplication.
         */
        for(int i = 0; i <= n; i++) {
            total = (total + (long)arr[i]*minRight[i]*minLeft[i]) % mod;
        }

        return (int)total;
    }
}
