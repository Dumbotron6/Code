package stack;

import java.util.LinkedList;

public class LargestRectangleInHistogram {
    /*
    https://leetcode.com/problems/largest-rectangle-in-histogram/
     */

    /*
    The idea is that using stack we keep track of the previous smallest elements.
    Take an example of [1,2,3,1,2,3].
    It keeps adding to the stack till it reaches the second 1. Now since the area can't increase past here,
        popping and area calculation starts happening.
     */
    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> smaller = new LinkedList<Integer>();
        int area = 0;
        int i = 0;
        while(i < heights.length) {
            /*
            As long as the current element is greater than the last, we add to stack.
             */
            if(smaller.isEmpty() || heights[smaller.peekLast()] <= heights[i]) {
                smaller.add(i);
                i++;
                /*
                The moment we encounter a smaller element to the right, it means, the rectangle can't go any further to the right.
                So we stop and calculate the area till the previous smaller element.
                 */
            }else if(heights[smaller.peekLast()] > heights[i]){
                int top = smaller.removeLast();
                int temp;
                /*
                If the element we popped is the last element in the stack, it means that is the smallest element so far
                    which means the rectangle for that covers the area from the beginning of the array till that point.
                So basically, top is the element whose area needs to be calculates, i is the right smallest element
                    and peekLast is the left smallest element.
                So for [1,2,3,1,2,3], the stack keeps adding till it reaches the second 1.
                Then area for 3 is calculated which becomes 3.
                Then the area for 2 is calculated which is 2*(length between right most and left most)
                    ie. 2*(i - peekLast).
                 */
                if(smaller.isEmpty())
                    temp = heights[top]*(i);
                else
                    temp = heights[top]*(i - smaller.peekLast() - 1);
                area = Math.max(area, temp);
            }
        }
        /*
        At this point, we end up with [1,1,2,3] in the stack.
        For 3, the larges area will be 3.
        For 2, the largest area will be 2*(array length - last smaller element length).
        Array length because there is no smaller element to the right.
         */
        while(!smaller.isEmpty()) {
            int top = smaller.removeLast();
            int temp;
            if(smaller.isEmpty())
                temp = heights[top]*(i);
            else
                temp = heights[top]*(i - smaller.peekLast() - 1);
            area = Math.max(area, temp);
        }
        return area;
    }
}
