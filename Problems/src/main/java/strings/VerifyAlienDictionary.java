package strings;

public class VerifyAlienDictionary {
    /*
    https://leetcode.com/problems/verifying-an-alien-dictionary/
     */

    public boolean isAlienSorted(String[] words, String order) {
        int[] orderInd = new int[26];

        for(int i = 0; i < order.length(); i++) { //Mark lexicographical position.
            orderInd[order.charAt(i)-'a'] = i+1;
        }

        for(int i = 0; i < words.length-1; i++) { //Compare every adjacent words, i and i+1.
            for(int j = 0; j < words[i].length(); j++) {
                if(j == words[i+1].length()){ //This happens if all the alphabets of i+1 match i but i is longer, which is lexi wrong.
                    return false;
                }
                int ind1 = orderInd[words[i].charAt(j)-'a'];
                int ind2 = orderInd[words[i+1].charAt(j)-'a'];
                if(ind1 < ind2) {
                    break;
                }else if(ind1 > ind2) {
                    return false;
                }
            }
        }

        return true;
    }
}
