package stack;

import java.util.LinkedList;

public class BaseballGame {
    /*
    https://leetcode.com/problems/baseball-game/description/
     */

    public int calPoints(String[] operations) {
        LinkedList<Integer> stack = new LinkedList<Integer>();

        for(int i = 0; i < operations.length; i++) {
            String val = operations[i];
            if(val.equals("D")) {
                stack.push(stack.peek()*2);
            }else if(val.equals("C")) {
                stack.pop();
            }else if(val.equals("+")) {
                int prev = stack.pop();
                int newScore = stack.peek()+prev;
                stack.push(prev);
                stack.push(newScore);
            }else {
                stack.push(Integer.parseInt(val));
            }
        }

        int finalScore = 0;
        for(int score : stack) { //Can also pop elements from stack and add.
            finalScore += score;
        }

        return finalScore;
    }
}
