package dynamicProgramming;

public class QuestionsWithBrainpower {
    /*
    https://leetcode.com/problems/solving-questions-with-brainpower/
     */

    /*
    This is the top-down approach.
     */
    public long mostPoints(int[][] questions) {
        return calcPoints(questions, new Long[questions.length], 0);
    }

    /*
    We have two choices, add the current points and skip to brainpower+1 questions
        or skip current question and go to next question.
    Non-recursive and optimal solution is given below this.
    */
    public long calcPoints(int[][] questions, Long[] dp, int ptr) {
        if(ptr >= questions.length) {
            return 0;
        }
        if(dp[ptr] != null) {
            return dp[ptr];
        }

        long val1 = questions[ptr][0] + calcPoints(questions, dp, ptr+questions[ptr][1]+1);
        long val2 = calcPoints(questions, dp, ptr+1);
        dp[ptr] = Math.max(val1, val2);

        return dp[ptr];
    }

    /*
    Building on the previously submitted solution, we calculate from the back,
        at every point, we store max till that point from the back.
    We can also do it from the front but back is easier to understand and implement(mainly because of dp length -- len+1 etc.)
    len+1 because notTake = dp[i+1]; If not, we'd have to check i+1 < len every time and assign 0.
    This is the bottom-up approach. We take dp[i+1] because we assume the sub-problem (max till i+1) has already been solved.
    */
    public long mostPointsOptimal(int[][] questions) {
        int len = questions.length;
        long[] dp = new long[len+1];

        for(int i = len-1; i >= 0; i--){
            long take = questions[i][0];
            int ptr = i+questions[i][1]+1;
            if(ptr < len) {
                take += dp[ptr];
            }
            long notTake = dp[i+1];
            dp[i] += Math.max(take, notTake);
        }

        return dp[0];
    }
}
