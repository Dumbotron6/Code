package strings;

public class ValidWords {
    /*
    https://leetcode.com/problems/number-of-valid-words-in-a-sentence/description/
     */

    //Just a bunch of if else. Can be structured better by putting them in a single method.
    public int countValidWords(String sentence) {
        String[] words = sentence.split("\\s+");

        int valid = 0;
        for(String word : words) {
            int len = word.length();
            if(len == 0) {
                continue;
            }
            char ch = word.charAt(0);
            if(ch == '-' || ((ch == '.' || ch == '!' || ch == ',') && len != 1)) {
                continue;
            }
            ch = word.charAt(len-1);
            if(ch == '-' || (ch >= '0' && ch <= '9')) {
                continue;
            }

            int hip = 0;
            for(int i = 0; i < len-1; i++) {
                ch = word.charAt(i);
                if(ch == '-') {
                    char aft = word.charAt(i+1);
                    if(!(aft >= 'a' && aft <= 'z') || hip > 0) {
                        valid--;
                        break;
                    }
                    hip++;
                }else if(!(ch >= 'a' && ch <= 'z')) {
                    valid--;
                    break;
                }
            }
            valid++;
        }

        return valid;
    }
}
