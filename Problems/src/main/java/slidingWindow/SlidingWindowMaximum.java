package slidingWindow;

import java.util.LinkedList;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        /*
        https://leetcode.com/problems/sliding-window-maximum/
         */

        //Can use Deque <Integer> q = new ArrayDeque <> (); peek, poll, peeklast, polllast, offer.
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int[] result = new int[nums.length-k+1];

        for(int i = 0; i < nums.length; i++) {
            //Remove first element from the front as window slides if it exists in queue.
            if(!queue.isEmpty() &&  i-k >= queue.peek()) {
                queue.pop();
            }
            //Remove smaller numbers from the back of the queue and make sure queue has decreasing elements.
            while(!queue.isEmpty() &&  nums[i] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.add(i);

            if(i >= k-1) {
                result[i-k+1] = nums[queue.peek()];
            }
        }
        return result;
    }
}
