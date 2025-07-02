package stack;

import java.util.LinkedList;

public class ReversePolishNotation {
    /*
    https://leetcode.com/problems/evaluate-reverse-polish-notation/
     */

    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int total = 0;

        for(String token : tokens) {
            if(token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            }else if(token.equals("-")) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(num1 - num2);
            }else if(token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            }else if(token.equals("/")) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(num1 / num2);
            }else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
