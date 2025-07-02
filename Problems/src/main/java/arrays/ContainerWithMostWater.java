package arrays;

public class ContainerWithMostWater {

    /*
    https://leetcode.com/problems/container-with-most-water/
     */

    public int maxArea(int[] height) {
        /*
        This problem is like a precursor to the trapping rainwater problem.
        We keep moving left or right depending on which height is lesser.
        Why? Because the greater height will always accommodate the area of the lesser height.
        So if a greater height is on the right, then we move from left to right.
        If a greater height is on the left, we move from right to left.
        */
        int left = 0, right = height.length-1;
        int maxArea = 0;

        while(left < right) {
            int len = right-left;
            int h = Math.min(height[left], height[right]);
            int area = len*h;
            if(height[left] <= height[right]) {
                left++;
            }else {
                right--;
            }
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }
}
