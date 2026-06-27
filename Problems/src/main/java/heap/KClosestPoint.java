package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestPoint {
    /*
    https://leetcode.com/problems/k-closest-points-to-origin/description/
     */

    public int[][] kClosest(int[][] points, int k) {
        Queue<int[]> queue = new PriorityQueue<int[]>(k, new OrgCompare());
        for(int i = 0; i < points.length; i++) {
            queue.add(points[i]);
        }
        int[][] returnPoints = new int[k][2];

        for(int i = 0; i < k; i++) {
            returnPoints[i] = queue.poll();
        }
        return returnPoints;
    }

    class OrgCompare implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            int val1 = a[0]*a[0] + a[1]*a[1];
            int val2 = b[0]*b[0] + b[1]*b[1];

            return val1-val2;
        }
    }
}
