package strings;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    /*
    https://leetcode.com/problems/roman-to-integer/

    2 is written as II in Roman numeral, just two ones added together.
    12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II

    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.
     */

    public int romanToInt(String s) {
        Map<Character, Integer> mapValues = new HashMap<Character, Integer>();
        int total = 0;

        mapValues.put('I', 1);
        mapValues.put('V', 5);
        mapValues.put('X', 10);
        mapValues.put('L', 50);
        mapValues.put('C', 100);
        mapValues.put('D', 500);
        mapValues.put('M', 1000);

        char prev = s.charAt(s.length()-1);
        total += mapValues.get(prev);

        for(int i = s.length()-2; i >= 0; i--) {
            if(s.charAt(i) == 'I' && (prev == 'V' || prev == 'X')) {
                total -= mapValues.get(s.charAt(i));
            } else if(s.charAt(i) == 'X' && (prev == 'L' || prev == 'C')) {
                total -= mapValues.get(s.charAt(i));
            } else if(s.charAt(i) == 'C' && (prev == 'D' || prev == 'M')) {
                total -= mapValues.get(s.charAt(i));
            } else
                total += mapValues.get(s.charAt(i));
            prev = s.charAt(i);
        }
        return total;
    }

    public int romanToIntOpt(String s) {
        Map<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = map.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2; i >= 0; --i) {
            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                sum -= map.get(s.charAt(i));
            } else {
                sum += map.get(s.charAt(i));
            }
        }
        return sum;
    }

    public String intToRoman(int num) {

        String[] keys = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {

            if (num - values[i] >= 0) {
                while (num - values[i] >= 0) {
                    sb.append(keys[i]);
                    num -= values[i];
                }
            }
        }

        return sb.toString();
    }
}
