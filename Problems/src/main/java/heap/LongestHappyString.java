package heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class LongestHappyString {
    /*
    https://leetcode.com/problems/longest-happy-string/
     */

    /*
    Although problem is different, it takes similar approach as reorganizeStringAlt.
    We take the highest char, add it to result, add it back to pq.
    If same character is added twice, next highest char is taken and then added to result. Both are then
        added back to pq.
     */
    public String longestDiverseString(int a, int b, int c) {
        Queue<int[]> pq = new PriorityQueue<int[]>((x, y) -> y[1]-x[1]);

        if(a > 0) pq.offer(new int[] {'a',a});
        if(b > 0) pq.offer(new int[] {'b',b});
        if(c > 0) pq.offer(new int[] {'c',c});

        StringBuilder result = new StringBuilder();
        int prev = 0; int prevCount = 0;

        while(!pq.isEmpty()) {
            int[] addChar = pq.poll();
            if(prevCount == 2 && addChar[0] == prev) { //If current char is same as prev and already added twice continuously.
                int[] temp = addChar;
                if(pq.isEmpty()) { //No more characters left to add.
                    return result.toString();
                }
                addChar = pq.poll(); //Take the next highest char and add it to result.
                result.append((char)addChar[0]);
                addChar[1]--;
                if(addChar[1] > 0) { //Make this the new prev.
                    pq.offer(addChar);
                    prev = addChar[0]; prevCount = 1;
                }else { //If char is exhausted after adding.
                    prev = 0; prevCount = 0;
                }
                pq.offer(temp);
            }else {
                result.append((char)addChar[0]);
                addChar[1]--;
                if(addChar[1] > 0) {
                    pq.offer(addChar);
                    if(prev == addChar[0]) { //Same as previous char, increment count.
                        prevCount = 2;
                    }else { //Not same as previous char.
                        prev = addChar[0]; prevCount = 1;
                    }
                }else {
                    prev = 0; prevCount = 0;
                }
            }
        }

        return result.toString();
    }
}
