package recursion;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumber {
    /*
    https://leetcode.com/problems/letter-combinations-of-a-phone-number/
     */

    /*
    Remember numbers 2 to 9 have alphabets ["abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"]
        with 7 and 9 having 4 letters while others have 3.
    Reason for loopCounter for 7 and 9 is because they have extra letters. So the additional letter needs to be added
        in the combo.
     'a' + j + (digits.charAt(start) - '2')*3 + adder -- say digits.charAt(start) is 4. Then this would give
        'a' + 0 + ('4'-'2') * 3 + 0 which is 'g'. When j becomes 1, then it is 'h'.
     The reason for adder is again because 7 and 9 have extra letters. Now when we get 8, this formula would give us
        's' instead of 't' because the formula has (digits.charAt(start) - '2')*3. To accommodate for the extra letter
        from 7, we add 1 to the formula for 8 and 9.
     */

    /*
    'a' + j + (digits.charAt(start) - '2')*3 + adder For easier understanding, say the digit is '2'.
    digits.charAt(start) - '2' is 0. *3 also gives us 0.
    Now when j is 0, then 'a' + 0 + 0. When j is 1, 'a' + 1 + 0 is b and 'a' + 2 + 0 is 'c'.
    When digits.charAt(start) is 3, digits.charAt(start) - '2' is 1 and *3 give us 3.
    So 'a' + 0 + 3 is 'd' and 'a' + 1 + 3 is 'e' and  'a' + 2 + 3 is 'f'.
     */
    public List<String> letterCombinations(String digits) {
        List<String> combos = new ArrayList<String>();
        if(digits.length() == 0)
            return combos;
        findCombos(digits, 0, combos, new StringBuilder());
        return combos;
    }

    public void findCombos(String digits, int start, List<String> combos, StringBuilder str) {
        if(str.length() == digits.length())
            combos.add(str.toString());
        else {
            int loopCounter = (digits.charAt(start) == '7' || digits.charAt(start) == '9') ? 4 : 3;
            int adder = (digits.charAt(start) == '8' || digits.charAt(start) == '9') ? 1 : 0;
            for(int j = 0; j < loopCounter; j++) {
                str.append((char)('a' + j + (digits.charAt(start) - '2')*3 + adder));
                findCombos(digits, start+1, combos, str);
                str.deleteCharAt(str.length()-1);
            }
        }
    }

    /*
    Easier solution below. Use this solution in an interview.
    int digit = digits.charAt(index)-'0';
    String letter = array[digit];
    These two lines grab the alphabets according to the number.
    Now they are iterated through. Each iteration (say a or b or c) recurses through the rest of the digits.
    Rest of the logic is similar to CombinationSum2.
     */
    public List<String> letterCombinations2(String digits) {
        List<String> result = new ArrayList();
        if (digits.length() == 0) {
            return result;
        }

        String[] array = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        helper(result, array, 0, new StringBuilder(), digits);
        return result;
    }

    public void helper(List<String> result, String[] array, int index, StringBuilder sb, String digits) {
        if (index >= digits.length()) {
            result.add(sb.toString());
            return;
        }
        int digit = digits.charAt(index)-'0';
        String letter = array[digit];
        for (int i=0; i<letter.length(); i++) {
            char c = letter.charAt(i);
            sb.append(c);
            helper(result, array, index+1, sb, digits);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
