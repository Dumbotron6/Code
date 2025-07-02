package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class JobSequencing {
    /*
    https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
     */
    //Essentially, one job can be done per day, but it has to be done anytime before the deadline.
    int[] JobScheduling(Job arr[], int n)
    {
        //The idea is to maximize the profit. So we first sort by profit.
        Arrays.sort(arr, Comparator.comparingInt(job -> job.profit));

        int max = 0;
        int maxProfit = 0;
        int jobCount = 0;

        //Find the max jobs that can be preformed.
        for(Job job : arr) {
            if(job.deadline > max)
                max = job.deadline;
        }

        int[] maxVals = new int[max];
        int count = 0;

        /*
        There can be 4 jobs with deadline 5, with profits 15,10,9,8.
        There can be 2 jobs with deadline 4, with profits 7 and 3.
        Now, say the sorted array is in the order of [15,10,9,8,7,3] whose deadlines are [5,5,5,5,4,4].
        Since there can only be a max of 5 jobs that can be done, we have max profit for each day
            currently [0,0,0,0,0]
        The idea is the job can be done on the day of the deadline.
        Now assigning based on profit, when we assign the values, we end up with [15,10,9,8,0].
        ie, we opearate under the assumption that doing deadline 5 jobs on day 2,3,4,5 gives us max profit.
        Now when we move to deadline 4, we can't do it on the fourth day as we already have a greater profit
            populated on that day.
        So we move till we find a place where the profit is maximum and end up with [15,10,9,8,7].
         */
        for(int i = arr.length-1; i >= 0; i--) {
            for(int j = arr[i].deadline-1; j >= 0; j--) {
                if(maxVals[j] < arr[i].profit) {
                    maxVals[j] = arr[i].profit;
                    jobCount++;
                    break;
                }
            }
        }

        for(int i = 0; i < maxVals.length; i++)
            maxProfit += maxVals[i];

        return new int[]{jobCount, maxProfit};
    }


    class Job {
        int id, profit, deadline;
        Job(int x, int y, int z){
            this.id = x;
            this.deadline = y;
            this.profit = z;
        }
    }

}
