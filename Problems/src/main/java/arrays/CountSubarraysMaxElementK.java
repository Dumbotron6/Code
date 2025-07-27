package arrays;

import java.util.Arrays;

public class CountSubarraysMaxElementK {
    /*
    https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/description/
     */

    //Revise this.
    public long countSubarrays(int[] nums, int k) {
        int len = nums.length;
        int max = Arrays.stream(nums).max().orElse(0);
        long subArray = 0;
        int left = 0, right = 0;
        int cnt = 0;

        while(right < len) {
            if(nums[right] == max) { //Keep moving right pointer, incrementing counter as we go.
                cnt++;
            }
            right++;

            /*
            When right pointer has covered enough max, move left pointer till we get cnt < k.
            When that happens, left-1 to right window will have k max elements.
            */
            while(cnt == k) {
                if(nums[left] == max) {
                    cnt--;
                }
                left++;
            }

            //Since we have the window now, that window and every subarray to the left of left pointer will satisfy the question condition.
            subArray += left;
        }

        return subArray;
    }
    /*
    Have a doubt about why only subArray += left? What happened to the subarrays to the right of right pointer?
    Take [1,3,3,2,1,3] and k=2 as an example. When left=1 and right=2, cnt=2. Now while(cnt==k) will execute till left=2.
    subArray will become 2. Now right pointer will keep moving right and subArray += left will keep executing.
    When right = 3 or 4, subArray += 2 will still happen. So all that matters is the first time we get cnt==k.
     */
}
