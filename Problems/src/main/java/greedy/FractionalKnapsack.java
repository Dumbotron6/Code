package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {

    /*
    https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
     */

    class Item {
        int value, weight;
        Item(int x, int y){
            this.value = x;
            this.weight = y;
        }
    }

    double fractionalKnapsack(int w, Item arr[], int n) {
        int totalWeight = 0;
        int i = 0;
        double totalValue = 0;

        /*
        To get the maximum value, first we need to sort by value per one weight.
        If value is 120 and weight is 30, then it is 4.
        If value is 1 and weight is 10, then it is 0.1.
        So we need to prioritise 4 over 0.1.
         */

        Arrays.sort(arr, new ItemComparator());
        //Since array is sorted in descending order, we iterate from 0 to end.
        while(i < arr.length && totalWeight < w) {
            double currWeight = (double)arr[i].weight;
            if(currWeight + totalWeight > w) {
                currWeight = w - totalWeight;
                totalWeight = w;
                totalValue += ((double)arr[i].value/(double)arr[i].weight) * currWeight;
            }else {
                totalValue += (double)arr[i].value;
                totalWeight += currWeight;
            }
            i++;
        }

        return totalValue;
    }

    class ItemComparator implements Comparator<Item> {
        /*
        We are doing the sorting in descending order of value per one weight.
         */
        @Override
        public int compare(Item a, Item b) {
            double aRatio = (double)a.value/(double)a.weight;
            double bRatio = (double)b.value/(double)b.weight;

            if(aRatio < bRatio)
                return 1;
            else if(bRatio < aRatio)
                return -1;
            else
                return 0;
        }

    }
}
