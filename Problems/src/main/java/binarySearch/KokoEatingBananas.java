package binarySearch;

public class KokoEatingBananas {
    /*
    https://leetcode.com/problems/koko-eating-bananas/

    The pile needs to be finished in exactly the number of hours. Not lesser than that.
    Not stated explicitly in the problem. This is implied in 'Koko likes to eat slowly'.
     */

    public int minEatingSpeed(int[] piles, int h) {
        /*
        If the pile has [30,11,23,4,20] and the hours is 5, then it's enough if the eating speed
            is maximum of the array.
        So the range will be between 1 and max of the array.
         */
        int right = findMax(piles);
        int left = 1;

        /*
        When the speed is okay, then it is a possible value.
        But we need to find minimum speed that is okay. So we need to find if there is a better speed.
         */
        while(left < right) {
            int mid = left + (right-left)/2;
            if(isSpeedOk(piles, mid, h))
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }

    /*
    If pile is 9 and the speed is 4, then pile/speed will give 2 hours and the remaining 1 will be eaten in 1 more hour.
     */
    public boolean isSpeedOk(int[] piles, int speed, int h) {
        int hours = 0;
        for(int pile : piles) {
            hours += pile/speed;
            if(pile % speed != 0)
                hours++;
            if(hours > h)
                return false;
        }
        return true;
    }

    public int findMax(int[] piles){
        int max = piles[0];
        for(int i = 0; i < piles.length; i++) {
            if(piles[i] > max)
                max = piles[i];
        }
        return max;
    }
}
