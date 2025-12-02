package binarySearch;

public class MinSpeedArriveOnTime {
    /*
    https://leetcode.com/problems/minimum-speed-to-arrive-on-time/
     */

    /*
    We use binary search on the answer range to find the answer.
    The return type is an int. So the min speed will be 1. The max speed is given in the problem statement.
    */
    public int minSpeedOnTime(int[] dist, double hour) {
        int left = 1;
        int right = (int)1e9 + 7;
        int max = right;

        while(left <= right) {
            int mid = left+(right-left)/2;
            if(speedEnough(dist, hour, mid)) {
                right = mid-1;
            }else {
                left = mid+1;
            }
        }

        return right >= max ? -1 : right+1;
    }

    //Every station before the last one will have a wait time to upper integer. So we do a +1 if necessary.
    public boolean speedEnough(int[] dist, double hour, int speed) {
        double time = 0;

        for(int i = 0; i < dist.length-1; i++) {
            if(dist[i]%speed != 0) {
                time += dist[i]/speed + 1;
            }else {
                time += dist[i]/speed;
            }
        }

        time += (double)dist[dist.length-1]/(double)speed;

        return time <= hour;
    }
}
