package com.vijay.cake;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vkbalakr on 6/24/17.
 */
public class ProductOfAllIntegersExceptIdx {

    public static int[] getProductsOfAllIntsExceptAtIndex(int[] intArr) {

        int arrLen = intArr.length;
        int[] productArr = new int[arrLen];
        int products = 1;
        //int[] productsOfAllIntsBeforeIdx = new int[arrLen];
        for (int i=0; i < arrLen; i++ ){
            //productsOfAllIntsBeforeIdx[i] = products;
            productArr[i] = products;
            products = products * intArr[i];
        }

        //int[] productsOfAllIntsAfterIdx = new int[arrLen];
        int productsSoFar = 1;
        for (int i=arrLen - 1; i >= 0; i--) {
            //productsOfAllIntsAfterIdx[i] = productsSoFar;
            //productArr[i] = productsSoFar * productsOfAllIntsBeforeIdx[i];
            productArr[i] = productsSoFar * productArr[i];
            productsSoFar = productsSoFar * intArr[i];
        }

        /*for (int startIdx = 0; startIdx < arrLen; startIdx++) {

           //productArr[startIdx] = product(0, startIdx, intArr) * product(startIdx + 1, arrLen , intArr);
           //productArr[startIdx] = productsOfAllIntsBeforeIdx[startIdx] * product(startIdx + 1, arrLen , intArr);
           productArr[startIdx] = productsOfAllIntsBeforeIdx[startIdx] * productsOfAllIntsAfterIdx[startIdx];

        }*/
        return productArr;
    }

    public static int product(int low, int high, int[] intArr) {
        if (low == high) {
            return 1;
        }
        Map<String, Integer> memo = new HashMap<>();

        return intArr[low] * product(low +1, high, intArr);
    }

    public static void main(String[] args) {
        int[] intArr = new int[]{1,2,6,5,9};
        //int[] intArr = new int[]{1};
        if (intArr.length <= 1) {
            throw new IllegalArgumentException(String.format("Cannot have input array length:%d < than 2", intArr.length));
        }
        System.out.println(Arrays.toString(getProductsOfAllIntsExceptAtIndex(intArr)));
    }
}
