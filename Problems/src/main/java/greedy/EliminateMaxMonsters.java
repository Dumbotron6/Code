package greedy;

import java.util.Arrays;

public class EliminateMaxMonsters {
    /*
    https://leetcode.com/problems/eliminate-maximum-number-of-monsters/description/
     */

    public int eliminateMaximum(int[] dist, int[] speed) {
        int len = dist.length;
        int[] times = new int[len]; //Time to reach fort.

        for(int i = 0; i < len; i++) {
            int time = dist[i]/speed[i];
            if(dist[i]%speed[i] > 0) { //Rounding up.
                time++;
            }
            times[i] = time;
        }

        Arrays.sort(times); //Sort by earliest times.

        for(int i = 0; i < len; i++) {
            if(times[i] <= i) { //When time has exceeded reach time, for example, for time [1,1], only one monster can be eliminated.
                return i;
            }
        }

        return len;
    }
}
