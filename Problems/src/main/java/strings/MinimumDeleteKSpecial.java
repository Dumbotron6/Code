package strings;

public class MinimumDeleteKSpecial {
    /*
    https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/
     */

    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];

        for(int i = 0; i < word.length(); i++) {
            freq[word.charAt(i)-'a']++;
        }

        int minDeletes = Integer.MAX_VALUE;

        for(int i = 0; i < 26; i++) {
            if(freq[i] > 0) {
                minDeletes = Math.min(minDeletes, getMinDeletes(freq, i, k));
            }
        }

        return minDeletes;
    }

    /*
    Take the string "aabcaba", k = 0. We can delete 2 'a' and 1 'c'. This is by assuming we keep the frequency of 'b' unchanged.
    We try this out for each character. Assume that character frequency is unchanged. If so, what will we have to remove from each to make the difference k.
    We can delete all freq lesser than current assumed frequency. Why? Assume 'aabbbccccc' we have freq of 2,3,5 and k = 1. When we assume 3 is unchanged,
        Now 1 have to be deleted from 5. But we can't add anything to 2. So we delete them entirely so that remaining ones will have max diff of 1.
        The total adds up to 3.
    If we had assumed 2 is constant, then we'd have had to delete 0 from 3, 2 from 5. The total adds up to 2.
    */
    public int getMinDeletes(int[] freq, int pos, int k) {
        int min = freq[pos];
        int minDeletes = 0;
        for(int i = 0; i < 26; i++) {
            if(freq[i] < min) {
                minDeletes += freq[i];
                continue;
            }
            int val = freq[i]-min;
            if(val > k) {
                minDeletes += val-k;
            }
        }
        return minDeletes;
    }
}
