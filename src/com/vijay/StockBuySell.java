package com.vijay;

import java.util.ArrayList;

/**
 * Created by vkbalakr on 7/6/17.
 */
public class StockBuySell {

    class Interval {
        int buy,sell;
    }

    void stockBuySell(int[] price, int n) {

        ArrayList<Interval> intervals = new ArrayList<>();

        int i=0;
        while (i < n-1) {

            //Local Minima when to buy
            while ((i < n-1) && price[i+1] <= price[i])//limit is n-2 as we look ahead i+1 here
                i++;
            if (i == n-1) {
                break;//no dip in price
            }
            //price goes up afer dip here
            Interval e = new Interval();
            e.buy = i++;//<==== important

            //Local maxima when to sell on high
            while ((i < n) && (price[i] >= price[i-1])) //limit is n-1 as we look back
                i++;
            //price is not going up anymore
            e.sell = i-1;
            intervals.add(e);
        }

        int count = intervals.size();
        if (count == 0) {
            System.out.println("No profit to be made");
        } else {
            for (int j = 0; j < count; j++) {
                System.out.println(intervals.get(j).buy + " " + intervals.get(j).sell);
            }
        }

    }

    public static void main(String args[])
    {
        StockBuySell stock = new StockBuySell();

        // stock prices on consecutive days
        int price[] = {100, 180, 260, 310, 40, 535, 695};
        int n = price.length;

        // fucntion call
        stock.stockBuySell(price, n);
    }
}
