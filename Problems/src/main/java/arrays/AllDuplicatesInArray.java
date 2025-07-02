package arrays;

import java.util.ArrayList;
import java.util.List;

public class AllDuplicatesInArray {
    /*
    https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
     */

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> dups = new ArrayList<Integer>();
        int n = nums.length;
        /*
        Since the numbers range between 1 to n, we take each number, mark that index to negative.
        So we when we encounter same number again, if that number's index is -ve, we know it is repeated.
        This uses O(1) space. Math.abs(nums[i])-1 because indexes are from 0 to n-1, not 1 to n.
        */
        for(int i = 0; i < n; i++) {
            int val = Math.abs(nums[i])-1;
            if(nums[val] < 0) {
                dups.add(val+1);
            }else {
                nums[val] *= -1;
            }
        }

        return dups;
    }
}
