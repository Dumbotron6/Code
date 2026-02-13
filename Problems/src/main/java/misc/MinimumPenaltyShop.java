package misc;

public class MinimumPenaltyShop {
    /*
    https://leetcode.com/problems/minimum-penalty-for-a-shop/description/
     */

    //Simple matter of counting penalty if shop closes at each index.
    public int bestClosingTime(String customers) {
        int len = customers.length();
        int pen = 0;

        //Total penalty if shop closes at end.
        for(char c : customers.toCharArray()) {
            if(c == 'N') {
                pen++;
            }
        }

        //Calculate penalty at each index.
        int minPen = pen;
        int hour = len;
        for(int i = len-1; i >= 0; i--) {
            if(customers.charAt(i) == 'N') {
                pen--;
            }else {
                pen++;
            }
            if(pen <= minPen) {
                minPen = pen;
                hour = i;
            }
        }

        return hour;
    }
}
