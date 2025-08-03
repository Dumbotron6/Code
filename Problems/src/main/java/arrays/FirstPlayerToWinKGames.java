package arrays;

import java.util.LinkedList;

public class FirstPlayerToWinKGames {
    /*
    https://leetcode.com/problems/find-the-first-player-to-win-k-games-in-a-row/description/
     */

    /*
    If k > skills.length, the max element would always win.
    Using that logic, we can say that when no element reaches k wins before the end of array,
        the max element will always win, because after that point, nothing can win against max element no matter how many times
        the comparison happens.
    */
    public int findWinningPlayer(int[] skills, int k) {
        int max = 0;
        int wins = 0;
        for(int i = 1; i < skills.length; i++) {
            if(skills[max] < skills[i]) { //If greater encountered, make that max.
                max = i;
                wins = 0;
            }
            wins++;
            if(wins == k) { //If k wins before end of array reached.
                return max;
            }
        }

        return max;
    }

    //Below solution uses monotonic stack to simulate the solution. Worse than above solution which is O(N).
    public int findWinningPlayerStack(int[] skills, int k) {
        if(k >= skills.length) {
            int max = 0;
            for(int i = 1; i < skills.length; i++) {
                if(skills[i] > skills[max]) {
                    max = i;
                }
            }
            return max;
        }

        LinkedList<Integer> queue = new LinkedList<Integer>();

        for(int i = 0; i < skills.length; i++) {
            queue.add(i);
        }

        int wins = 0;
        int curr = queue.pop();
        while(!queue.isEmpty()) {
            int max = Math.max(skills[curr], skills[queue.peek()]);
            if(max != skills[curr]) {
                queue.add(curr);
                curr = queue.pop();
                wins = 1;
            }else {
                queue.add(queue.pop());
                wins++;
            }
            if(wins == k) {
                return curr;
            }
        }

        return curr;
    }
}
