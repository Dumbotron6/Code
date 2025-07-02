package stack;

import java.util.Stack;

public class DecodeString {
    /*
    https://leetcode.com/problems/decode-string/
     */

    public String decodeString(String s) {
        Stack<String> stringStack = new Stack<String>();
        Stack<Integer> repeatStack = new Stack<Integer>();
        StringBuilder currentString = new StringBuilder();
        StringBuilder numString = new StringBuilder();

        for(char c : s.toCharArray()) {
            if(c == '[') {
                /*
                When a [ is encountered, it means some operation needs to be performed to the string after this
                    and added to the current string.
                So we add the current string to a stack and proceeed to collect the next string.
                Why numString = new StringBuilder("0");?
                Because even if there is no repeat, for the string, we must say so in the stack.
                    ie, every stringStack must have a corresponding numStack.
                */
                repeatStack.push(Integer.parseInt(numString.toString()));
                stringStack.push(currentString.toString());
                currentString = new StringBuilder();
                numString = new StringBuilder("0");
            }else if(c == ']') {
                /*
                If a ] is encountered, then it means an encoded string is ending, which means
                    the current string must be repeated.
                We can take the repeat count from the repeatStack. Then loop and repeat the current string,
                    add it to the previous string. The output will become the new current string.
                If there is no previous string, no issue because of stringStack.push(currentString.toString());
                    so the output will just be the current string, repeated.
                */
                int rep = repeatStack.pop();
                String str = currentString.toString();
                currentString = new StringBuilder(stringStack.pop());
                for(int r = 0; r < rep; r++) {
                    currentString.append(str);
                }
            }else if(c >= '0' && c <= '9') { //Form the number.
                numString.append(c);
            }else { //Form the string.
                currentString.append(c);
            }
        }

        return currentString.toString();
    }
}
