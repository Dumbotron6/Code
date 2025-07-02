package arrays;

import java.util.ArrayList;

public class FindEvenNumbers {
    /*
    https://leetcode.com/problems/finding-3-digit-even-numbers/
     */

    public int[] findEvenNumbers(int[] digits) {
        int[] digCount = new int[10];
        ArrayList<Integer> result = new ArrayList<Integer>();

        for(int d : digits) {
            digCount[d]++;
        }

        for(int i = 100; i < 1000; i+=2) {
            if(checkDigit(digCount, i)) {
                result.add(i);
            }
        }

        int[] resultArray = new int[result.size()];

        for(int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }

    public boolean checkDigit(int[] digCount, int digit) {
        int[] count = new int[]{-1,-1,-1};
        int i = 2;
        while(digit > 0) {
            int rem = digit%10;
            digit /= 10;
            if(digCount[rem] > 0) {
                count[i--] = rem;
                digCount[rem]--;
            }else {
                break;
            }
        }
        boolean present = (i == -1) ? true : false;

        while(i < 2) {
            digCount[count[++i]]++;
        }

        return present;
    }
}
