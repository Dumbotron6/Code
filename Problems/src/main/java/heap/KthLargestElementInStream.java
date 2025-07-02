package heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElementInStream {

    /*
    https://leetcode.com/problems/kth-largest-element-in-a-stream/
     */

    /*
    We keep adding to the heap till the size exceeds k in which case we remove the top element.
    The top element would be the integer with the least value.
    So at anytime, the heap will have k elements, with the top element having the least value of the k elements.
    So it gives kth largest.
     */
    class KthLargest {

        Queue<Integer> pq = new PriorityQueue<Integer>();
        int k;
        public KthLargest(int k, int[] nums) {
            this.k = k;
            for(int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            pq.add(val);
            if(pq.size() > k)
                pq.poll();
            return pq.peek();
        }
    }
}
