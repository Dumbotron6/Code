package stack;

import java.util.LinkedList;

public class StockSpanner {

    LinkedList<int[]> stockSpan;

    public StockSpanner() {
        stockSpan = new LinkedList<int[]>();
    }

    public int next(int price) {
        int span = 0;
        while(!stockSpan.isEmpty() && stockSpan.peek()[0] <= price) { //As long as price is equal or less, add span.
            span += stockSpan.pop()[1];
        }
        span++; //Add current day.

        stockSpan.push(new int[]{price, span}); //Stores price and span till this is max.

        return span;
    }

    /*
    One important thing to note is that we don't need previous stock prices if they are lesser than current, so we can safely pop them.
    We only need to see if a price being popped has a span, if so, add it to current span.
    For example, in [1,2,6,4,7], when the stack would be [1,1] when price is 1, [2,2] when price is 2, [6,3] when 6, [6,3][4,1] when 4.
    Since we store [6,3], which has span, we don't need any other prices that were lesser before that. Now when 7 happens, all [6,3][4,1] will
        be consumed and added and become [7,5].
    */
}
