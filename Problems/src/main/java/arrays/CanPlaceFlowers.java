package arrays;

public class CanPlaceFlowers {
    /*
    https://leetcode.com/problems/can-place-flowers/description/
     */

    //No adjacent indexes should have 1
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int placed = 0;
        int len = flowerbed.length-1;

        if(flowerbed[0] == 0) {
            if(len == 0) {
                placed++;
                flowerbed[0] = 1;
            }else if(flowerbed[1] == 0) {
                placed++;
                flowerbed[0] = 1;
            }
        }
        for(int i = 1; i < len; i++) {
            if(flowerbed[i] == 0 && flowerbed[i-1] == 0 && flowerbed[i+1] == 0) {
                placed++;
                flowerbed[i] = 1;
            }
        }
        if(flowerbed[len] == 0 && len > 0 && flowerbed[len-1] == 0) {
            placed++;
        }

        return placed >= n;
    }
}
