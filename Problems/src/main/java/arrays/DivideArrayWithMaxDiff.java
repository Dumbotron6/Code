package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DivideArrayWithMaxDiff {
    /*
    https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/
     */

    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        List<int[]> finalList = new ArrayList<int[]>();
        int start = 0, end = 2;


        //Since array is sorted, every 1st and 3rd element will have to satisfy the condition of <= k
        while(end < len) {
            if(nums[end] - nums[start] <= k) {
                finalList.add(new int[]{nums[start],nums[start+1],nums[end]});
                start = end+1;
                end = start+2;
            }else {
                return new int[][]{};
            }
        }

        int[][] resultArray = new int[finalList.size()][3];


        for(int i = 0; i < finalList.size(); i++) {
            resultArray[i] = finalList.get(i);
        }

        return resultArray;
    }
}
