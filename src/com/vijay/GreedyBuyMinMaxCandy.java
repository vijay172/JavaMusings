package com.vijay;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/find-minimum-maximum-amount-buy-n-candies/
 * Created by vkbalakr on 7/5/17.
 */
public class GreedyBuyMinMaxCandy {

    public static int findMin(int[] arr, int k) {

        Arrays.sort(arr);
        int n = arr.length;
        int minAmt = 0;
        for (int i=0; i < n; i++) {

            minAmt += arr[i];

            n = n - k;//2 free candy from the priciest

        }
        return minAmt;
    }

    public static int findMax(int[] arr, int k) {

        Arrays.sort(arr);
        int n = arr.length;
        int maxAmt = 0;
        //start from the end;
        int end = 0;
        for (int i = n-1; i >= end; i--)  {
            maxAmt += arr[i];
            end = end + k;//min 2 free candies
        }
        return maxAmt;


    }

    public static void main(String[] args) {
        int[] arr = {3,2,1,4};
        int k=2;//2 free candies
        //System.out.println(findMin(arr, k));
        System.out.println(findMax(arr, k));
    }
}
