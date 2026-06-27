package heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class IPOMaxProfit {
    /*
    https://leetcode.com/problems/ipo/
     */

    /*
    We add all the projects to minQueue, sorted by capital.
    At each i, add all possible projects that can be executed to maxQueue, sorted by profits descending.
    So at any point, maxQueue will have all possible projects that can be executed with current capital.
    Then at each i, we take the max profit project from maxQueue and add it to our capital.
    We do this till k is reached.
    */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int[][] shares = new int[profits.length][2];

        for(int i = 0; i < profits.length; i++) {
            shares[i][0] = capital[i];
            shares[i][1] = profits[i];
        }

        Queue<int[]> minQueue = new PriorityQueue<int[]>((a, b) -> a[0]-b[0]); //Sort by capital.
        Queue<int[]> maxQueue = new PriorityQueue<int[]>((a,b) -> b[1]-a[1]); //Sort by profit descending.

        for(int i = 0; i < profits.length; i++) {
            minQueue.offer(shares[i]);
        }

        for(int i = 0; i < k; i++) {
            while(!minQueue.isEmpty() && minQueue.peek()[0] <= w) { //All possible projects with current capital.
                maxQueue.offer(minQueue.poll());
            }

            if(maxQueue.isEmpty()) { //No more projects within current capital.
                break;
            }
            w += maxQueue.poll()[1]; //Take max profit and add to capital.
        }

        return w;
    }

    /*
    Same approach as previous solution. Difference is, we don't need minQueue as the order won't change after sorting
        whereas maxQueue will change. So we can just sort shares directly instead of using minQueue.
    */
    public int findMaximizedCapitalOptimal(int k, int w, int[] profits, int[] capital) {
        int[][] shares = new int[profits.length][2];

        for(int i = 0; i < profits.length; i++) {
            shares[i][0] = capital[i];
            shares[i][1] = profits[i];
        }

        Arrays.sort(shares, (a, b) -> a[0]-b[0]); //Sort by capital.
        Queue<int[]> maxQueue = new PriorityQueue<int[]>((a,b) -> b[1]-a[1]); //Sort by profit descending.

        int j = 0;
        for(int i = 0; i < k; i++) {
            while(j < profits.length && shares[j][0] <= w) { //All possible projects with current capital.
                maxQueue.offer(shares[j]);
                j++;
            }

            if(maxQueue.isEmpty()) { //No more projects within current capital.
                break;
            }
            w += maxQueue.poll()[1]; //Take max profit and add to capital.
        }

        return w;
    }
}
