package heap;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler {
    /*
    https://leetcode.com/problems/task-scheduler/
     */

    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];

        for(char c : tasks) {
            counts[c-'A']++;
        }

        /*
        This is the execution queue.
        We take high frequency transactions from this pq first and execute.
        At any time, pq will have elements that are not in the queue.
        */
        Queue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b-a);
        for(int count : counts) {
            if(count > 0) {
                pq.add(count);
            }
        }

        /*
        This is the schedule queue.
        Tasks will be scheduled from this queue. Size will be 2, index 0 have count of the same character, index 1 will have the minimum interval
            after which the task can be executed.
        High interval will be at the tail and low interval will be at the tail.
        If n = 2, the queue will look like [[A,6],[C,4],[B,2]]. Only instead of A,B,C, we store their frequency, [[2,6],[2,4],[6,2]].
        We don't actually need to know whether the task is A or B or C.
        So when currInterval == 2, that means B can be scheduled for execution and hence added to pq.
        */
        LinkedList<int[]> queue = new LinkedList<int[]>();
        int currInterval = 0;

        while(!pq.isEmpty() || !queue.isEmpty()) {
            if(!pq.isEmpty()) {
                int val = pq.poll();
                if(val > 1) { //Take this task, execute it, reduce count and add to schedule queue.
                    queue.add(new int[]{val-1, currInterval+n});
                }
            }
            //If the head of the queue has minimum interval reached, add it to pq to be executed.
            if(!queue.isEmpty() && queue.peek()[1] == currInterval) {
                pq.add(queue.pop()[0]);
            }
            currInterval++;
        }

        return currInterval;
    }
}
