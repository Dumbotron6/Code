package arrays;

public class CaptureEnemyForts {
    /*
    https://leetcode.com/problems/maximum-enemy-forts-that-can-be-captured/
     */

    /*
    Question is misleading. Only forts from 1 to -1 or -1 to 1 are counted 1 to 1 and -1 to -1 and 0 to 1 etc don't count.
    Essentially, only own fort to free fort or free fort to own fort count.
    */
    public int captureForts(int[] forts) {
        int pos = 0;
        int max = 0;
        int count = 0;
        int begin = 0;
        while(pos < forts.length) {
            if(forts[pos] == 1 || forts[pos] == -1) { //Start at own or free fort.
                begin = forts[pos];
                break;
            }
            pos++;
        }
        while(pos < forts.length) {
            if(forts[pos] == 1 || forts[pos] == -1) {
                if(forts[pos] == -1*begin) { //If 1 to -1 or -1 to 1.
                    max = Math.max(max, count);
                }
                begin = forts[pos]; //Set new beginning position.
                count = 0;
            }else {
                count++;
            }
            pos++;
        }

        return max;
    }

    //Note: Instead of begin and noting -1 and 1, we can simply note start index and check forts[start] == forts[pos].
}
