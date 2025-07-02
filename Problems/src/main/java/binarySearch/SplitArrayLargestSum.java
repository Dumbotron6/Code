package binarySearch;

public class SplitArrayLargestSum {
    /*
    https://leetcode.com/problems/split-array-largest-sum/description/
    https://www.naukri.com/code360/problems/allocate-books_1090540?utm_source=youtube&utm_medium=affiliate&utm_campaign=codestudio_Striver_BinarySeries
     */

    /*
    Take an array say [6,2,3,1,5] and k is 4. We need to figure out distribution. Easier to explain using book allocation.
    Say 4 students have to hold these books. The max pages a student can cold is 6+2+3+1+5 = 17. Assume the max pages a
        student can hold is 6. Now we have a range. 6-17.
    If we keep allocating books to a student till the max pages(6) are reached, we'd end up with [6,6,5] which is 3 students.
    This is less than k. We can also end up with students > k (Ex. [6,6,6,6,6] and k = 4). So we adjust the mid(essentially max pages).
    For students > k, we increase mid, and for students <= we decrease mid.
    Finally, whatever we end up with will have students == k.
     */
    public int splitArray(int[] nums, int k) {
        int length = nums.length;
        int maxSize = 0;
        int minSize = 0;

        for(int num : nums) {
            maxSize += num;
            minSize = Math.max(minSize, num);
        }

        while(minSize <= maxSize) {
            int mid = minSize + (maxSize-minSize)/2;
            if(isAllocated(nums, length, k, mid)) {
                maxSize = mid-1;
            }else {
                minSize = mid+1;
            }
        }

        return maxSize+1;
    }

    public boolean isAllocated(int[] nums, int length, int k, int mid) {
        int arraySize = 0;
        int assigned = 0;
        int sum = 0;
        while(assigned <= length-1) {
            int num = nums[assigned];
            if(sum + num <= mid) {
                sum += num;
            }else {
                sum = num;
                arraySize++;
            }
            assigned++;
        }
        return arraySize <= k-1;
    }
}
