package misc;

public class WaterBottles {
    /*
    https://leetcode.com/problems/water-bottles/
     */

    public int numWaterBottles(int numBottles, int numExchange) {
        int rem = 0;
        int total = 0;

        while(numBottles > 0) {
            total += numBottles;
            rem += numBottles%numExchange;
            numBottles = numBottles/numExchange;
            if(rem >= numExchange) {
                numBottles++;
                rem -= numExchange;
            }
        }

        return total;
    }
}
