package greedy;

import java.util.LinkedList;

public class Dota2Senate {
    /*
    https://leetcode.com/problems/dota2-senate/
     */

    public String predictPartyVictory(String senate) {
        LinkedList<Integer> rQueue = new LinkedList<Integer>();
        LinkedList<Integer> dQueue = new LinkedList<Integer>();
        int n = senate.length();

        for(int i = 0; i < n; i++) { //We collect all positions and add them to respective queue.
            if(senate.charAt(i) == 'R') {
                rQueue.add(i);
            }else {
                dQueue.add(i);
            }
        }

        while(!rQueue.isEmpty() && !dQueue.isEmpty()) {
            int rIndex = rQueue.pop();
            int dIndex = dQueue.pop();

            /*
            We compare the first R to the first D. The lower index will negate the higher index vote.
            The lower index can vote in the future. So we add it to the head of the queue.
            Say R was the lower index. Now it's added to head. Since this can only vote in the next round, we give it +n.
            By doing that, we can ensure that it does not repeat-negate any D in the same round.
            */
            if(rIndex < dIndex) {
                rQueue.add(rIndex+n);
            }else {
                dQueue.add(dIndex+n);
            }
        }

        return !rQueue.isEmpty() ? "Radiant" : "Dire";
    }

    /*
    Take example [RDD]. rQueue [0], dQueue [1,2].
    In the first round, 1 will be negated, but 0 should not negate 2. So we add n.
    By the end of the first round, the queue will be rQueue [3], dQueue [2].
    Technically, 2 negates 0 from voting in the next round. We do that by placing 0 after 2 for the next round.
    That is what we simulate by making 3 as 0.
    */
}
