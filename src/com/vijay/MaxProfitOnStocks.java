package com.vijay;

/**
 * Created by vkbalakr on 6/18/17.
 */
public class MaxProfitOnStocks {



        public static void getMaxProfit(int[] a) {
            int maxProfit =0, buy=0, sell=0, profit=0, lowBuy=0, maxSell = 0;
            int n = a.length;
            for (int i=0; i< n; i++) {
                buy =  a[i];
                for (int j=i+1; j < n; j++) {
                    sell = a[j];
                    profit = sell -buy;
                    System.out.println("profit:"+profit + "maxProfit:"+maxProfit);
                    if (profit > maxProfit) {
                        lowBuy = a[i]; maxSell = a[j];maxProfit=profit;
                    }
                }
            }
            System.out.println("buy:"+lowBuy + " sell:" + maxSell);
        }

        public static void maxProfit(int[] a) {
            if (a.length < 2) {
                throw new IllegalStateException("Need 2 prices at least for profit");
            }
            int  currPrice=0;
            int minPrice = a[0];
            int maxProfit = a[1] - a[0];
            int profit = 0;
            for (int i = 0; i < a.length; i++) { //O(n)
                currPrice = a[i];
                minPrice = Math.min(minPrice, currPrice);
                if (currPrice != minPrice) {//imp check
                    profit = currPrice - minPrice;
                    maxProfit = Math.max(profit, maxProfit);
                }

            }
            System.out.println("maxProfit:"+maxProfit);
        }

        public static void main(String[] args) {

            int[] stockPricesYesterday = new int[] {10, 7, 5, 8, 11, 9};
            //int[] stockPricesYesterday = new int[] {10, 7, 5, 4,3,2};
            //getMaxProfit(stockPricesYesterday);
            maxProfit(stockPricesYesterday);
        }

}
