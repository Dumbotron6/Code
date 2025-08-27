package greedy;

import java.util.LinkedList;

public class MaximumSixtyNine {
    /*
    https://leetcode.com/problems/maximum-69-number/
     */

    public int maximum69Number (int num) {
        LinkedList<Integer> stack = new LinkedList<Integer>();

        while(num > 0) {
            stack.push(num%10);
            num = num/10;
        }

        while(!stack.isEmpty()) {
            if(stack.peek() == 9) {
                num = num*10 + stack.pop();
            }else {
                break;
            }
        }
        if(!stack.isEmpty()) {
            num = num*10 + 9;
            stack.pop();
        }

        while(!stack.isEmpty()) {
            num = num*10 + stack.pop();
        }

        return num;
    }
}
