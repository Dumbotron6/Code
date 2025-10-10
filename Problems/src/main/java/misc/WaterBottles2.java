package misc;

public class WaterBottles2 {
    /*
    https://leetcode.com/problems/water-bottles-ii/
     */

    int maxBottlesDrunk(int numBottles, int numExchange) {
        int total = 0;

        while(numBottles >= numExchange) {
            //numExhange+1 indicates numExchange bottles exchanged for 1.
            numBottles = numBottles - numExchange + 1;
            total += numExchange;
            numExchange++;
        }

        //Total bottles drank so far plus remaining bottles that can be drunk.
        return total+numBottles;
    }
}
