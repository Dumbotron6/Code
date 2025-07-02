package graph;

public class FindTownJudge {
    /*
    https://leetcode.com/problems/find-the-town-judge/
     */

    public int findJudge(int n, int[][] trust) {
        /*
        No need to keep track of who trusts who.
        We know that since the judge is trusted by everyone, they will show up in person[0] at least once.
        Since the judge trusts no one, he will not show up in person[0].
        The judge will be trusted by n-1 people.
        If there are multiple such people, it means the judge can't be identified.
        */
        boolean[] trustTrack = new boolean[n+1];
        int[] trusted = new int[n+1];

        for(int[] person : trust) {
            trustTrack[person[0]] = true;
            trusted[person[1]]++;
        }

        int judge = -1;
        for(int i = 1; i < n+1; i++) {
            if(!trustTrack[i] && trusted[i] == n-1) {
                if(judge == -1) {
                    judge = i;
                }else {
                    return -1;
                }
            }
        }
        return judge;
    }

    public int findJudgeAlt(int n, int[][] trust) {
        /*
        This uses O(N) storage instead of O(2N) like above solution.
        Building on previous solution, we know the judge will be trusted by n-1 people.
        Of course, if there is no judge, then more than 1 person will be trusted by n-1 people.
        How do we solve that? If a person is trusted by n-1 people, but he trusts someone, then he isn't the judge.
        So add to trusted[] whenever someone is trusted by the person, subtract from trusted[] whenever he trusts someone.
        */
        int[] trusted = new int[n+1];

        for(int[] person : trust) {
            trusted[person[0]]--;
            trusted[person[1]]++;
        }

        for(int i = 1; i < n+1; i++) {
            if(trusted[i] == n-1) {
                return i;
            }
        }
        return -1;
    }
}
