package binarySearch;

import java.util.Arrays;

public class PairSpellsPotions {
    /*
    https://leetcode.com/problems/successful-pairs-of-spells-and-potions/
     */

    /*
    We have to find the minimum potion that will satisfy the condition. Every potion greater than that will also satisfy the condition.
    So we use binary search(after sorting potions) to find the minimum potion.
    */
    int potLen;
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        potLen = potions.length;
        int[] result = new int[spells.length];

        for(int i = 0; i < spells.length; i++) {
            int pos = minSuccessPos(spells[i], potions, success);
            result[i] = potLen-pos;
        }

        return result;
    }

    //Binary search to find minimum potion.
    public int minSuccessPos(int spell, int[] potions, long success) {
        int left = 0;
        int right = potLen-1;

        while(left <= right) {
            int mid = left+(right-left)/2;
            long strength = (long)potions[mid]*(long)spell;
            if(strength >= success) {
                right = mid-1;
            }else {
                left = mid+1;
            }
        }

        return right+1;
    }
}
