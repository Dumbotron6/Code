package stack;

import java.util.LinkedList;

public class MaximalRectangle {
    /*
    https://leetcode.com/problems/maximal-rectangle/description/
     */

    /*
    This problem is basically LargestRectangleInHistogram with an added layer on top of it.
    We traverse every row. At every row, we calculate the area of that column.
    For example, in [[1,1,1,1],[0,1,1,0]] the area of the first row is [1,1,1,1] and the second row is [0,2,2,0].
    If we consider the problem like this, then it basically becomes the histogram problem.
     */
    public int maximalRectangle(char[][] matrix) {
        int[] row = new int[matrix[0].length];
        int maxArea = 0;
        for(char[] arr : matrix) {
            int i = 0;
            for(char num : arr) {
                row[i] = num == '0' ? 0 : (num - '0')+row[i];
                i++;
            }
            maxArea = calcArea(row, maxArea);
        }
        return maxArea;
    }

    public int calcArea(int[] row, int maxArea) {
        int i = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();

        while(i < row.length) {
            if(stack.isEmpty() || row[stack.peek()] <= row[i]) {
                stack.push(i);
                i++;
            }else {
                int temp = stack.pop();
                int area = 0;
                if(stack.isEmpty()) {
                    area = row[temp]*i;
                }else {
                    area = row[temp]*(i - stack.peek() - 1);
                }
                maxArea = Math.max(maxArea, area);
            }
        }

        while(!stack.isEmpty()) {
            int temp = stack.pop();
            int area = 0;
            if(stack.isEmpty()) {
                area = row[temp]*i;
            }else {
                area = row[temp]*(i - stack.peek() - 1);
            }
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
