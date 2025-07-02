package stack;

public class TrappingRain {
    /*
    https://leetcode.com/problems/trapping-rain-water/description/
     */

    /*
    Consider an array [5,4,3,2,1] or [1,2,3,4,5].
    In the first one, when we go from left to right, we keep finding the peek. When we find anything lesser, we fill
        it with water. When we find something greater, we make it the peak. Same is the case but from right to left
        for the second array.
    But how do we know if we need to go from left to right or right to left? That is where height[left] <= height[right]
        comes in.
    If we have a greater height on the right, then it means we can keep filling because we know it's not always downward.
    For example, in [5,4,3,2,6,2,3], when height[right] is 6, then we can keep filling from the left till we reach 6.
    If the array is [3,2,6,2,3,4,5], the if condition will kick in till left reaches 6, then the else condition will
        kick in and start filling from the right.
    Why leftpeak-height[left] or rightpeak-height[right]? Because left or right peak is the max amount of water that can
        be filled.
     */
    public int trap(int[] height) {

        int water = 0;
        int left = 0;
        int right = height.length-1;
        int leftpeak, rightpeak;
        leftpeak = rightpeak = 0;

        while(left < right) {
            if(height[left] <= height[right]) {
                if(height[left] < leftpeak) {
                    water = water + (leftpeak - height[left++]);
                } else {
                    leftpeak = height[left++];
                }
            } else {
                if(height[right] < rightpeak) {
                    water = water + (rightpeak - height[right--]);
                } else {
                    rightpeak = height[right--];
                }
            }
        }

        return water;
    }
}
