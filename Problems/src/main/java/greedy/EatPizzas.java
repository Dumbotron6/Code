package greedy;

import java.util.Arrays;

public class EatPizzas {
    /*
    https://leetcode.com/problems/eat-pizzas/description/
     */

    public long maxWeight(int[] pizzas) {
        int len = pizzas.length;
        long weight = 0;
        int days = len/4; //Total number of days.
        int oddDays = (days-1)/2 + 1; //Ceiling. 2.5 will give 3. oddDays will always be >= evenDays.
        int evenDays = days-oddDays;
        int pos = len-1;

        Arrays.sort(pizzas);

        //For every odd day, we want to give the maximum Z. So we pick odd days first.
        for(int i = 0; i < oddDays; i++, pos--) {
            weight += pizzas[pos];
        }
        pos--; //Because pos is at Z, not Y. So move to Y. pos-=2 will now always point to Y.
        for(int i = 0; i < evenDays; i++, pos-=2) {
            weight += pizzas[pos];
        }

        return weight;
    }
}
