package strings;

public class LexicographicallyLargestString1 {
    /*
    https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/
    Revise this.
     */

    /*
    We use two pointers. We store the higher alphabet in first. We use second to find a matching or greater aplhabet.
    If we find matching alphabet, we compare subsequent characters using iter.
    When we encounter a higher alphabet, we make second as first then reassign second to +1 to check further.
    This way, even if we get "bacbad", when first and seond are at 'b' and 'b',
        first+iter and second+iter will be 'a' and 'a'. In the next loop, they will be 'c' and 'd'.
    Now we fond that second has higher lexi order, so we make second as first.
    At the end, the resulting substring will end at either max length of string, or the maximum length that can be allowed after splitting
        between numFriends.
    */
    public String answerString(String word, int numFriends) {
        if(numFriends == 1) {
            return word;
        }
        int first = 0;
        int second = 1;
        int len = word.length();
        //Max result substring length if we assign 1 character to 1 friend. Ex. "badf" and 2 will have maxSize of 3.
        int maxSize = len-numFriends+1;
        char currMax = word.charAt(0);
        int iter = 0;

        while(second + iter < len) {
            char a = word.charAt(first+iter);
            char b = word.charAt(second+iter);

            if(b > word.charAt(first)) { //Greater than first found.
                first = second+iter;
                second = first+1;
                iter = 0;
            }else if(a == b) {
                iter++;
            }else if(a > b) { //If lower lexi, move second.
                second = second+iter+1;
                iter = 0;
            }else {//If higher lexi, reassign.
                first = second;
                second = second+iter+1;
                iter = 0;
            }
        }

        return word.substring(first, Math.min(len, first+maxSize));
    }
}
