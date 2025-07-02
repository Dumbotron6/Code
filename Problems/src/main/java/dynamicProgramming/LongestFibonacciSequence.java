package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class LongestFibonacciSequence {
    /*
    https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/
     */

    /*
    There is a better, DP solution to this problem. Below is not the DP solution.
     */
    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> arrMap = new HashMap<Integer, Integer>();

        int count = 0, maxCount = 0;
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            arrMap.put(arr[i], i);
        }

        for(int i = 0; i < n; i++) {
            //Why arr[n-1]/2? It's impossible to from fibo series after that point.
            if(arr[i] > arr[n-1]/2)
                break;
            for(int j = i+1; j < n; j++) {
                int iTemp = i;
                int jTemp = j;
                int fVal = arr[iTemp];
                int sVal =  arr[jTemp];
                int sum = fVal+sVal;
                /*
                We have iTemp and jTemp incase we do find a fibo series, we have to keep changing i and j.
                When it ends, we have to go back to the original i and j. So we mainting a iTemp and jTemp to do the changing.
                */
                while(arrMap.containsKey(sum)) {
                    count++;
                    iTemp = jTemp;
                    jTemp = arrMap.get(sum);
                    fVal = arr[iTemp];
                    sVal =  arr[jTemp];
                    sum = fVal+sVal;
                }
                //Why +2? Take [1,2,3]. The while loop will execute only once and the count will be 1. But there are 3 elements.
                if(count > 0) {
                    count+=2;
                }
                maxCount = Math.max(count, maxCount);
                count = 0;
            }
        }
        return maxCount;
    }

    //Below is the optimal DP solution. Revise it.
    public int lenLongestFibSubseqOptimal(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        //dp[i][j] stores the length of fibo starting at i and ending at j.

        int maxFibo = 0;

        for(int curr = 2; curr < n; curr++) {
            int start = 0;
            int end = curr-1;

            while(start < end) {
                int sum = arr[start]+arr[end];
                if(sum < arr[curr]) {
                    start++;
                }else if(sum > arr[curr]){
                    end--;
                }else {
                    /*
                    If a valid pair is found, then update the dp.
                    What we update is, length of current fibo(see above explanation for dp).
                    Since we move from low to high(curr goes from 2 to n), we already know the length of fibo from start to end.
                    So we take that, add 1 to it since we just found a valid pair.
                    */
                    dp[end][curr] = dp[start][end] + 1;
                    maxFibo = Math.max(maxFibo, dp[end][curr]);
                    start++;
                    end--;
                }
            }
        }

        //Why +2? Take [1,2,3]. The while loop will execute only once and the count will be 1. But there are 3 elements.
        return maxFibo == 0 ? 0 : maxFibo+2;
    }
}
