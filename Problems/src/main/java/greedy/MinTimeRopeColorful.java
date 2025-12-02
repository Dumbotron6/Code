package greedy;

public class MinTimeRopeColorful {
    /*
    https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
     */

    public int minCost(String colors, int[] neededTime) {
        char prev = '.';
        int time = 0;
        int currMax = 0;

        //If there are any consecutive same characters, only the one with max time will remain, all others will be removed.
        for(int i = 0; i < neededTime.length; i++) {
            if(prev == colors.charAt(i)) {
                if(neededTime[currMax] < neededTime[i]) {
                    time += neededTime[currMax];
                    currMax = i;
                }else {
                    time += neededTime[i];
                }
            }else {
                prev = colors.charAt(i);
                currMax = i;
            }
        }

        return time;
    }
}
