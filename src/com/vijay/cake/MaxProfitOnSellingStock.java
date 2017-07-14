package com.vijay.cake;

/**
 * Created by vkbalakr on 6/24/17.
 */
public class MaxProfitOnSellingStock {


    public static int getMaxProfit(int[] stockPricesYesterday) {

        int maxProfit = 0, stockPricesYesterdayLen = stockPricesYesterday.length;
        int buyIdx = 0, sellIdx = 0, lowBuyIdx = 0, highSellIdx = 0;

        for(; buyIdx < stockPricesYesterdayLen; buyIdx++) {

            for (sellIdx = buyIdx + 1; sellIdx < stockPricesYesterdayLen; sellIdx++ ) {
                int latestProfit = stockPricesYesterday[sellIdx] - stockPricesYesterday[buyIdx];
                if (latestProfit > maxProfit) {
                    maxProfit = latestProfit;
                    lowBuyIdx = buyIdx;
                    highSellIdx = sellIdx;
                }
            }
        }
        System.out.println(String.format("lowBuy Price :%d, sell Price:%d", stockPricesYesterday[lowBuyIdx], stockPricesYesterday[highSellIdx]));
        return maxProfit;
    }

    public static int getMaxProfitFaster(int[] stockPricesYesterday) {
        int stockPricesYesterdayLen = stockPricesYesterday.length;
        if (stockPricesYesterdayLen < 2) {
            throw new IllegalArgumentException("There should be at least 2 stock prices to calculate profit.");
        }
        int maxProfit = stockPricesYesterday[1] - stockPricesYesterday[0];
        int latestProfit = 0;
        int minPrice = Math.min(stockPricesYesterday[0], stockPricesYesterday[1]);


        for (int buyIdx = 2; buyIdx < stockPricesYesterdayLen - 1; buyIdx++) {
            int sellIdx = buyIdx + 1;
            int currSellPrice = stockPricesYesterday[sellIdx];
            minPrice = Math.min(stockPricesYesterday[buyIdx], minPrice);//min of prev prices & current price
            latestProfit = currSellPrice - minPrice;
            maxProfit = Math.max(latestProfit, maxProfit);


            /*if (latestProfit > maxProfit) {
                maxProfit = latestProfit;
                System.out.println(String.format("minBuyPrice:%d, highSellPrice:%d", minPrice, stockPricesYesterday[sellIdx]));
            }*/
        }

        return maxProfit;
    }


    public static void main(String[] args) {
        //int[] stockPricesYesterday = new int[]{10, 7, 5, 8, 11, 9};
        int[] stockPricesYesterday = new int[]{10, 7, 5, 4, 3, 2};

        System.out.println(getMaxProfitFaster(stockPricesYesterday));

    }
}
