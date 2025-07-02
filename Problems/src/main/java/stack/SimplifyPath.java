package stack;

import java.util.Stack;

public class SimplifyPath {
    /*
    https://leetcode.com/problems/simplify-path/
     */

    public String simplifyPath(String path) {
        /*
        Stack instead of LinkedList for stack as final loop executes like a queue.
            ie, gives in order of adding, while LinkedList acts like a stack ie, gives last added.
        If path is /home/foo, looping Stack gives /home/foo while LinkedList gives /foo/home.
        */
        Stack<String> stack = new Stack<String>();
        StringBuilder currString = new StringBuilder();

        for(char c : path.toCharArray()) {
            if(c != '/') {
                currString.append(c); //Build string till '/' is encountered.
            }else { //At every '/' check if the built string is '..' or '.'
                if(currString.toString().equals("..")) { //If '..', don't add it to stack, pop last element from stack.
                    if(!stack.isEmpty()) {
                        stack.pop();
                    }
                }else if(!currString.isEmpty() && !currString.toString().equals(".")){ //If not '.', add to stack.
                    stack.push(currString.toString());
                }
                currString = new StringBuilder();
            }
        }

        //Repeating same as above as currString might have some value once for loop ends.
        if(currString.toString().equals("..")) {
            if(!stack.isEmpty()) {
                stack.pop();
            }
        }else if(!currString.isEmpty() && !currString.toString().equals(".")){
            stack.push(currString.toString());
        }

        StringBuilder finalString = new StringBuilder();
        for(String s : stack) {
            finalString.append("/").append(s);
        }
        if(finalString.isEmpty()) { //At least '/' should be returned.
            finalString.append("/");
        }
        /*
        return "/"+String.join("/", stack);
            can be used but didn't do so as you didn't know it.
        */
        return finalString.toString();
    }
}
