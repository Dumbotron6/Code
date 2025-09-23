package greedy;

import java.util.*;

public class NumberOfPeopleToTeach {
    /*
    https://leetcode.com/problems/minimum-number-of-people-to-teach/
     */

    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Set<Integer>[] langTracker = new Set[languages.length];

        //Set of languages each person knows.
        for(int i = 0; i < languages.length; i++) {
            langTracker[i] = new HashSet<Integer>(Arrays.stream(languages[i]).boxed().toList());
        }

        Set<Integer> noTalkers = new HashSet<Integer>();

        //Find people who can't talk and add them to a set.
        for(int[] friendship : friendships) {
            int friend1 = friendship[0]-1;
            int friend2 = friendship[1]-1;
            if(!canTalk(langTracker, friend1, friend2)) {
                noTalkers.add(friend1);
                noTalkers.add(friend2);
            }
        }

        //Among the no talkers, find the most common language so that can be taught to all others.
        Map<Integer, Integer> commonLanguageCount = new HashMap<Integer, Integer>();
        int maxCountCommonLang = 0;
        for(int noTalk : noTalkers) {
            for(int language : langTracker[noTalk]) {
                commonLanguageCount.put(language, commonLanguageCount.getOrDefault(language, 0)+1);
                maxCountCommonLang = Math.max(maxCountCommonLang, commonLanguageCount.get(language));
            }
        }

        return noTalkers.size() - maxCountCommonLang;
    }

    //Can two friends communicate with each other.
    public boolean canTalk(Set<Integer>[] langTracker, int friend1, int friend2) {
        for(int language : langTracker[friend1]) {
            if(langTracker[friend2].contains(language)) {
                return true;
            }
        }
        return false;
    }

    /*
    NOTE: Since the constraints are small, Set<Integer>[] langTracker may not be necessary
        and each user language array can be checked against each other directly with a nested loop.
     */
}
