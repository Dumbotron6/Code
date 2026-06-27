package heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class CarPooling {
    /*
    https://leetcode.com/problems/car-pooling/
     */

    //This takes O(nlogn) time.
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> a[1]-b[1]); //Sort by startTime.
        int curr = 0;

        //Sort by endTime, ie, when the passengers will be ejected at the earliest.
        Queue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[2]-b[2]);
        int currCapacity = 0;

        while(curr < trips.length) {
            int[] currTrip = trips[curr];

            //Eject all passengers whose endTime is <= current trip start time.
            while(!pq.isEmpty() && pq.peek()[2] <= currTrip[1]) {
                currCapacity -= pq.poll()[0];
            }

            //If current trip capacity exceeds limit.
            if(currCapacity + currTrip[0] > capacity) {
                return false;
            }

            currCapacity += currTrip[0]; //Add current trip to current capacity.
            pq.offer(currTrip); //Add to pq so they can be ejected when the time comes.
            curr++; //Iterate through trip.
        }

        return true;
    }

    //Below approach uses Line Sweep. This given O(2N) time complexity.
    public boolean carPoolingAlt(int[][] trips, int capacity) {
        int n = trips.length;
        int pref[] = new int [1001];
        int temp =0;

        for (int i =0; i<n; i++){
            int num = trips[i][0];
            int left = trips[i][1];
            int right = trips[i][2];
            pref[left] = pref[left] + num;
            if (right + 1 <1001){ pref[right] = pref[right] - num; }
        }
        for (int i =0; i<1001; i++){
            temp = temp + pref[i];
            pref[i] = temp;
            if (pref[i] > capacity){ return false; }
        }
        return true;
    }
}
