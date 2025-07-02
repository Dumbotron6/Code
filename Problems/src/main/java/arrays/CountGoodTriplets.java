package arrays;

public class CountGoodTriplets {
    /*
    https://leetcode.com/problems/count-good-triplets/
     */

    //Only brute force. There is no other solution.
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        int len = arr.length;

        for(int i = 0; i < len; i++) {
            for(int j = i+1; j < len; j++) {
                if(Math.abs(arr[i]-arr[j]) <= a) {
                    for(int k = j+1; k < len; k++) {
                        if(Math.abs(arr[j]-arr[k]) <= b && Math.abs(arr[i]-arr[k]) <= c) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
