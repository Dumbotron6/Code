package arrays;

public class SentenceSimilarity3 {
    /*
    https://leetcode.com/problems/sentence-similarity-iii/description/
     */

    /*
    Most important thing is to recognize that this should be solved by prefix and suffix.
    We can't use two pointers to count common words. Because take example "Of" and "Word Of Worlds".
    Shorter sentence is missing words both left and right.
    Or take example "One Two" and "Hello One By Two".
     */
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");

        if(words1.length > words2.length) {
            return compareSentences(words1, words2);
        }

        return compareSentences(words2, words1);
    }

    /*
    Prefix counts common words from the left, suffix counts common words from the right.
    Now when adding prefix and suffix equals shorter length, it means longer words has all the words in the shorter words,
        from the left, from the right, or from both.
    Which means words are missing entirely on the right, or entirely on the left, or in the middle.
    */
    public boolean compareSentences(String[] longer, String[] shorter) {
        int len1 = longer.length;
        int len2 = shorter.length;

        int i = 0, j = 0;
        int prefix = 0, suffix = 0;

        while(i < len1 && j < len2) {
            if(shorter[j].equals(longer[i])) {
                prefix++;
            }else {
                break;
            }
            i++; j++;
        }

        i = len1-1; j = len2-1;

        while(j >= prefix) {
            if(shorter[j].equals(longer[i])) {
                suffix++;
            }else {
                break;
            }
            i--; j--;
        }

        return prefix+suffix == len2;
    }
}
