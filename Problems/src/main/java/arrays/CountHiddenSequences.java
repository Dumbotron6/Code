package arrays;

public class CountHiddenSequences {
    /*
    https://leetcode.com/problems/count-the-hidden-sequences/
     */

    /*
    Take example [1,-3,4] with lower = 1 and upper = 6.
    The possible sequences are [3, 4, 1, 5] and [4, 5, 2, 6] if the digits 3 and 4 are used.
    The prefix sum for the sequence is [1,-2,2]. Take 3 and 4 and add the prefix sum. The above sequences will be obtained.
    Digits 5 and 6 will exceed the bounds.
    Since we only need the count, we can take the min and max of the prefix sums and check if adding the digit to them will be
        within bounds.
    Why does this work? Consider sequence [3, 4, 1, 5]. 1 is obtained by adding 4 and -3. This itself is the prefix sum,
        but using previous numbers in the sequence. The only real difference is, we use the same prefix sum, but with the digit added
        to it.
    So we simply add it at the end and check, i.e, we take the min and max and add the digit to them directly.
    */
    public int numberOfArrays(int[] differences, int lower, int upper) {
        int min = differences[0];
        int max = differences[0];
        int prefixSum = 0;

        for(int i = 0; i < differences.length; i++) {
            prefixSum += differences[i];
            min = Math.min(min, prefixSum);
            max = Math.max(max, prefixSum);
        }

        int count = 0;
        for(int i = lower; i <= upper; i++) {
            if(i+min >= lower && i+max <= upper) {
                count++;
            }
        }

        return count;
    }
}
