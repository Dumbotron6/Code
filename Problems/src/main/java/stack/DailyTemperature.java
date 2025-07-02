package stack;

import java.util.LinkedList;

public class DailyTemperature {
    /*
    https://leetcode.com/problems/daily-temperatures/description/
     */

    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] days = new int[len];

        //The temperature is stored in index 0 and the day is stored in index 1.
        LinkedList<int[]> stack = new LinkedList<int[]>();

        /*
        We keep popping till the current temperature is greater than last element in stack.
        The day difference would be current day(i) - day in which temp is less(stack.peek()[1]).
        This is why we store both the temp and the day.
        */
        for(int i = 0; i < len; i++) {
            while(!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                days[stack.peek()[1]] = i-stack.pop()[1];
            }
            stack.push(new int[]{temperatures[i], i});
        }

        return days;
    }

    //Slightly optimized.
    public int[] dailyTemperaturesOptimal(int[] temperatures) {
        int len = temperatures.length;
        int[] days = new int[len];

        //Used to store i(aka the day).
        LinkedList<Integer> stack = new LinkedList<Integer>();

        /*
        Below solution is slightly more optimized than previously submitted one.
        We don't need to store both i and temp. We only have to store i as we can get the temp using the stored i.
        We keep popping till the current temperature is greater than last element in stack.
        The day difference would be current day(i) - day in which temp is less(stack.peek()).
        */
        for(int i = 0; i < len; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                days[stack.peek()] = i-stack.pop();
            }
            stack.push(i);
        }

        return days;
    }
}
