package com.vijay.cake;

/**
 * Created by vkbalakr on 6/25/17.
 */
public class HighestProductOf2 {


    public static int getHighestProductOf3(int[] intArr) {
        if (intArr.length < 3) {
            throw new IllegalArgumentException("need 3 integers at least");
        }

        int highest = Math.max(intArr[0], intArr[1]);
        int lowest = Math.min(intArr[0], intArr[1]);

        int highestProd2 = intArr[0] * intArr[1];
        int lowestProd2 = intArr[0] * intArr[1];

        int highestProd3 = intArr[0] * intArr[1] * intArr[2];
        int lowestProd3 = intArr[0] * intArr[1] * intArr[2];
        int highestProd4 = intArr[0] * intArr[1] * intArr[2] * intArr[3];

        for (int i=3; i < intArr.length; i++) {
            int curr = intArr[i];
            //calculate prod of 4 1st
            highestProd4 = Math.max(Math.max(highestProd4, curr * highestProd3), curr * lowestProd3);

            lowestProd3 =   Math.min(Math.min(lowestProd3, curr * lowestProd2), curr * highestProd2);
            highestProd3 = Math.max(Math.max(highestProd3, curr * highestProd2), curr * lowestProd2); //lowest for 2 -ve values
            //then update all the underlying values before & including curr to min and max
            highestProd2 = Math.max(Math.max(highestProd2, curr * highest), curr * lowest); // if curr & lowest r both -ve
            lowestProd2 = Math.min(Math.min(lowestProd2, curr * lowest), curr * highest);

            highest = Math.max(curr, highest);
            lowest = Math.min(curr, lowest);
        }
        return highestProd4;
    }
    public static void main(String[] args) {
        int[] inputArr = new int[]{10,5,7,6,8,9,3,4};
        System.out.println(getHighestProductOf3(inputArr));
    }
}
