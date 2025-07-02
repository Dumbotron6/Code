package graph;

import java.util.*;

public class OpenTheLock {
    /*
    https://leetcode.com/problems/open-the-lock/description/
     */

    /*
    Although the concept is a regular BFS, the solution is tricky as there ar multiple nested loops.
    Harder to come up with the idea that this is a graph shortest path problem.
     */
    Set<String> stopVals;
    int[] moveVals;
    public int openLock(String[] deadends, String target) {
        /*
        Any string can have 8 possibilities.
        The first character can go up or down, same goes for second, third and fourth.
        Using BFS, we can find the shortest path to the target.
        We move each digit up and down in a single step, so that's 4 digits, which gives us 8 new values added to queue.
        */
        int steps = 0;

        LinkedList<String> queue = new LinkedList<String>();
        queue.add("0000");
        moveVals = new int[]{-1,1};

        stopVals = new HashSet<String>(Arrays.asList(deadends)); //Making it hashset makes checking easier.

        if(stopVals.contains("0000")) {
            return -1;
        }
        stopVals.add("0000"); //Keep track of already visited elements also in this set.


        while(!queue.isEmpty()) {
            int qSize = queue.size();
            for(int q = 0; q < qSize; q++) {
                String curr = queue.pop();
                if(curr.equals(target)) {
                    return steps;
                }
                char[] currArr = curr.toCharArray();
                addToQueue(currArr, queue); //Max 8 possibilities will be added.
            }
            steps++;
        }

        return -1;
    }

    public void addToQueue(char[] currArr, LinkedList<String> queue) {
        for(int i = 0; i < 4; i++) { //Move all 4 digits up and down in sequence.
            char currChar = currArr[i];
            for(int move : moveVals) { //Move digit up or down, for 0 and 9, wrap around.
                if(currChar == '9' && move == 1) {
                    currArr[i] = '0';
                }else if(currChar == '0' && move == -1) {
                    currArr[i] = '9';
                }else {
                    currArr[i] = (char)(currChar + move);
                }

                String newStr = new String(currArr);
                if(!stopVals.contains(newStr)) { //Not already checked or not in deadends.
                    queue.add(newStr);
                    stopVals.add(newStr);
                }
                currArr[i] = currChar; //Replace with the original digit.
            }
        }
    }
}
