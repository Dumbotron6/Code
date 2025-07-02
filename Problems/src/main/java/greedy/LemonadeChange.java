package greedy;

public class LemonadeChange {
    /*
    https://leetcode.com/problems/lemonade-change/description/
     */

    public boolean lemonadeChange(int[] bills) {
        if(bills[0] != 5)
            return false;

        int fiveChange = 1;
        int tenChange = 0;

        for(int i = 1; i < bills.length; i++){
            if(bills[i] == 5)
                fiveChange++;
            else{
                if(fiveChange == 0)
                    return false;
                if(tenChange > 0 && bills[i] > 10) {
                    fiveChange--;
                    tenChange--;
                }else {
                    fiveChange-= (bills[i]/5 - 1);
                    if(bills[i] == 10)
                        tenChange++;
                }

            }
            if(fiveChange < 0)
                return false;
        }

        return true;
    }
}
