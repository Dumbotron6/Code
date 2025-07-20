package greedy;

import java.util.Arrays;

public class MatchPlayersTrainers {
    /*
    https://leetcode.com/problems/maximum-matching-of-players-with-trainers/
     */

    //Same as AssignCookies.
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int play = 0;
        int train = 0;

        while(play < players.length && train < trainers.length) {
            if(players[play] <= trainers[train]) {
                play++;
            }
            train++;
        }

        return play;
    }
}
