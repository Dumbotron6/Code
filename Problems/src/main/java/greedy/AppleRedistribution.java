package greedy;

import java.util.Arrays;

public class AppleRedistribution {
    /*
    https://leetcode.com/problems/apple-redistribution-into-boxes/description/
     */

    public int minimumBoxes(int[] apple, int[] capacity) {
        int i = apple.length-1, j = capacity.length-1;
        Arrays.sort(capacity); //To fill the largest boxes first.
        boolean boxEnd = false;

        while(i >= 0) {
            if(apple[i] == capacity[j]) {
                i--;
                j--;
                if(i < 0) { //Because even though box filled, we moved on to next box despite no apples left.
                    boxEnd = true;
                }
            }else if(apple[i] < capacity[j]) {
                capacity[j] -= apple[i];
                i--;
            }else {
                apple[i] -= capacity[j];
                j--;
            }
        }

        //Return based on whether we moved on when it was not needed ie, boxEnd.
        return boxEnd ? capacity.length-j-1 : capacity.length-j;
    }

    public int minimumBoxesAlt(int[] apple, int[] capacity) {
        int totalApples = 0;
        for(int apples : apple){
            totalApples += apples;
        }

        Arrays.sort(capacity);
        int count = 0;
        int capacityYet = 0;
        for(int j=capacity.length-1; j>=0 && capacityYet < totalApples; j--){
            capacityYet += capacity[j];
            count++;
        }

        return count;
    }
}
