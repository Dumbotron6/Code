package graph;

import java.util.PriorityQueue;

public class MaxAveragePassRatio {
    /*
    https://leetcode.com/problems/maximum-average-pass-ratio/
     */

    /*
    Our goal is to maximize the ratio. So we should look at the potential gain (call in gainRatio) if we add a student, sort it by that value.
    For example, if we have [1,2] and [5,6] classes, the ratios are the same. We need to see which lass will raise its ratio the most
        if we add a student. That's why we calculate gainRation. Here, we add it to [1,2].
    */
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        double[] classRatio = new double[3]; //pass, total and gainRatio
        PriorityQueue<double[]> heap = new PriorityQueue<double[]>((a, b) -> Double.compare(b[2],a[2])); //max sort by gainRatio.

        for(int[] cl : classes) {
            heap.offer(new double[] {(double)cl[0], (double)cl[1], getGainRatio((double)cl[0], (double)cl[1])});
        }

        //Each time, add to the class with max gainRatio.
        while(extraStudents > 0) {
            double[] cl = heap.poll();
            cl[0]++;
            cl[1]++;
            cl[2] = getGainRatio(cl[0], cl[1]);
            heap.offer(cl);
            extraStudents--;
        }

        double totalRatio = 0;
        while(!heap.isEmpty()) {
            totalRatio += heap.peek()[0]/heap.poll()[1];
        }

        return totalRatio/classes.length;
    }

    public double getGainRatio(double pass, double total) {
        double newPass = pass+1;
        double newTotal = total+1;

        return newPass/newTotal - pass/total;
    }
}
