package greedy;

public class MoneyToMaxChildren {
    /*
    https://leetcode.com/problems/distribute-money-to-maximum-children/description/
     */

    public int distMoney(int money, int children) {
        if(money < children) {
            return -1;
        }

        money -= children; //Give one dollar to each child.
        int distribution = money/7; //Max distribution if remaining money split into 7.
        int rem = money%7; //Balance after splitting into 7.

        if(distribution > children) { //Then one child will be given extra money, so he'll have more than 8.
            distribution = children-1;
        }else if(distribution == children-1 && rem == 3) { //If one child is to be given 3. Breaks rule, so one child with 8 will be given more.
            distribution--;
        }
        if(distribution == children && rem != 0){ //Same as above.
            distribution--;
        }

        return distribution;
    }
}
