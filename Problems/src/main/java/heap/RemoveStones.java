package heap;

import java.util.PriorityQueue;

public class RemoveStones {
    /*
    https://leetcode.com/problems/remove-stones-to-minimize-the-total/description/
     */

    //Use priority queue to sort by max pile. Do the operation on max pile each time.
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b-a);
        int sum = 0;

        for(int pile : piles) {
            pq.offer(pile);
            sum += pile;
        }

        for(int i = 0; i < k; i++) {
            int val = pq.poll();
            sum -= val/2;
            val -= val/2;
            pq.offer(val);
        }

        return sum;
    }

    public int minStoneSumOld(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b) -> b-a);

        for(int pile : piles) {
            pq.offer(pile);
        }

        for(int i = 0; i < k; i++) {
            int val = pq.poll();
            val -= (int)(val/2);
            pq.offer(val);
        }

        int minSum = 0;

        while(!pq.isEmpty()) {
            minSum += pq.poll();
        }

        return minSum;
    }
}
