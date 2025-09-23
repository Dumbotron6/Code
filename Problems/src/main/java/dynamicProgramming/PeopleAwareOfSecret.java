package dynamicProgramming;

public class PeopleAwareOfSecret {
    /*
    https://leetcode.com/problems/number-of-people-aware-of-a-secret/
     */

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int[] newLearners = new int[n]; //Number of people who know on that day, new people.
        newLearners[0] = 1; //On the first day, only one person knows.
        long share = 0; //This is basically the number of new learners on each day.

        int mod = (int)1e9 + 7;

        for(int i = 1; i < n; i++) {
            if(i >= delay) {
                share += (newLearners[i-delay]); //Current people who can share plus new people who can start sharing.
            }
            if(i >= forget) { //People who can share will reduce by number of people who will forget.
                share -= newLearners[i-forget];
            }
            newLearners[i] = (int)(share%mod); //'share' people will spawn 'share' new learners.
        }

        int totalKnown = 0;
        for(int i = n-forget; i < n; i++) { //Number of learners post max forget days is the total.
            totalKnown = (totalKnown+newLearners[i])%mod;
        }

        return totalKnown;
    }
}
