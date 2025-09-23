package greedy;

public class MinDeletesBeautifulArray {
    /*
    https://leetcode.com/problems/minimum-deletions-to-make-array-beautiful/description/
     */

    public int minDeletion(int[] nums) {
        int del = 0;
        int left = 0;
        int right = 1;
        int len = nums.length;
        int pos = 0; //Position of hypothetical index if deletions are performed.

        while(right < len) {
            if(pos%2 == 0) { //If current hypothetical index is even.
                while(right < len && nums[left] == nums[right]) { //Keep deleting till unequal elements found.
                    right++;
                    del++;
                }
            }
            pos++;
            left = right; //Move left pointer to non equal element.
            right++; //So right becomes left+1;
        }

        if((len-del)%2 == 1) { //If odd number of elements left, remove last element.
            del++;
        }

        return del;
    }
}
