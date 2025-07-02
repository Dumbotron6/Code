package binarySearch;

import java.util.Arrays;
import java.util.Comparator;

public class MaxProfitAssigningWork {
    /*
    https://leetcode.com/problems/most-profit-assigning-work/
     */

    //This uses two pointers. This is better.
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int workSize = difficulty.length;
        WorkDiff[] work = new WorkDiff[workSize];

        for(int i = 0; i < workSize; i++) { //Assign difficulty and profit to a single array.
            work[i] = new WorkDiff(difficulty[i], profit[i]);
        }

        Arrays.sort(work, Comparator.comparingInt(a -> a.diff)); //Sort by difficulty.
        Arrays.sort(worker);

        int i = 0;
        int maxProfit = 0;
        int finalProfit = 0;

        /*
        The idea is, for each worker, we find the max difficulty he can do.
        Till we reach there, we find the max profit he can achieve.
        Now a worker with greater capacity can do all the previous worker's difficulty that was covered plus his own capacity.
        So as we move along the WorkDiff[] array, we calculate max profit till each point and store it in maxProfit.
        */
        for(int w : worker) {
            while(i < workSize && w >= work[i].diff) {
                maxProfit = Math.max(maxProfit, work[i].prof);
                i++;
            }

            finalProfit += maxProfit;
        }


        return finalProfit;
    }

    //This uses binary search.
    //Building on previously submitted solution, we use binary search to find max difficulty for each employee.
    public int maxProfitAssignmentAlt(int[] difficulty, int[] profit, int[] worker) {
        int workSize = difficulty.length;
        WorkDiff[] work = new WorkDiff[workSize];

        for(int i = 0; i < workSize; i++) { //Assign difficulty and profit to a single array.
            work[i] = new WorkDiff(difficulty[i], profit[i]);
        }

        Arrays.sort(work, Comparator.comparingInt(a -> a.diff)); //Sort by difficulty.

        //Assign max profit at each point.
        for(int i = 1; i < workSize; i++) {
            work[i].prof = Math.max(work[i].prof, work[i-1].prof);
        }

        int finalProfit = 0;

        /*
        We use binary search to find the max difficulty work that each employee can do.
        Then we take the profit of that point.
        */
        for(int capacity : worker) {
            int index = findMaxDifficulty(work, capacity);

            if(index > -1) {
                finalProfit += work[index].prof;
            }
        }


        return finalProfit;
    }

    public int findMaxDifficulty(WorkDiff[] work, int capacity) {
        int left = 0, right = work.length-1;

        while(left <= right) {
            int mid = left+(right-left)/2;
            if(work[mid].diff <= capacity) {
                left = mid+1;
            }else {
                right = mid-1;
            }
        }

        return right;
    }

    class WorkDiff {
        int diff;
        int prof;

        public WorkDiff(int diff, int prof) {
            this.diff = diff;
            this.prof = prof;
        }
    }
}
