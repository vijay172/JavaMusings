package com.vijay.cake;

import java.util.Arrays;

/**
 * Created by vkbalakr on 6/26/17.
 */
public class ChangeDenominations {


    public static int changeDenominations(int amount, int[] denominations) {
        return changeDenominations(amount, denominations, 0);
    }

    public static int changeDenominations(int amtLeft, int[] denominations, int currIdx) {

        if (amtLeft == 0) return 1;//match
        if (amtLeft <= 0) return 0; //over amount, no match
        if (currIdx == denominations.length) return 0;//exhausted coins
        System.out.println(String.format("checking ways to make %d change with currIdx:%d with %s", amtLeft, currIdx, Arrays.toString(Arrays.copyOfRange(denominations, currIdx, denominations.length))));
        /*
        checking ways to make 4 change with currIdx:0 with [1, 2, 3]
        checking ways to make 4 change with currIdx:1 with [2, 3]
        checking ways to make 4 change with currIdx:2 with [3]
        checking ways to make 2 change with currIdx:2 with [3]
        checking ways to make 3 change with currIdx:1 with [2, 3]
        checking ways to make 3 change with currIdx:2 with [3]
        checking ways to make 1 change with currIdx:2 with [3]
        checking ways to make 2 change with currIdx:1 with [2, 3]
        checking ways to make 2 change with currIdx:2 with [3]
        checking ways to make 1 change with currIdx:1 with [2, 3]
        checking ways to make 1 change with currIdx:2 with [3]
         */
        int currCoin = denominations[currIdx];//crucial
        int nbrOfWays = 0;
        while (amtLeft >= 0) {

            nbrOfWays += changeDenominations(amtLeft, denominations, currIdx + 1);//next coin permutations
            amtLeft = amtLeft - currCoin;
        }
        return nbrOfWays;

    }

    public static int changeDenominationsNonRecursive(int amount, int[] denominations) { //bottom up
        int[] wayOfDoingNcents = new int[amount + 1];//0..amount
        //missed base case of doing 0 is 1
        wayOfDoingNcents[0] = 1;
        for (int coin: denominations) {

            for (int higherAmt = coin; higherAmt <= amount; higherAmt++) {//<= amount here Missed that

                int higherAmtRemainder = higherAmt - coin;
                System.out.println(String.format("wayOfDoingNcents[higherAmt]:%d, higherAmt:%d",wayOfDoingNcents[higherAmt], higherAmt));
                //no of ways to account for a new coin because higherAmt - coin already accounts for combinations involving coin
                wayOfDoingNcents[higherAmt] += wayOfDoingNcents[higherAmtRemainder];
                System.out.println(String.format("wayOfDoingNcents[higherAmt]:%d, higherAmtRemainder:%d",wayOfDoingNcents[higherAmt], higherAmtRemainder));
            }
        }
        return wayOfDoingNcents[amount];
    }

    public static int createDenominationsBottomUp(int amount, int[] denominations) {
        int[] nbrOfWays = new int[amount + 1];//0..amount
        //base case 1 way to create 0 amount
        nbrOfWays[0]=1;
        //for each coin in denominations
        for (int coin: denominations) {
            //walk thru and see how many ways u can build the amt from 0 to amount
            for (int higherAmt = coin; higherAmt <= amount; higherAmt++) { //full row for
                int higherAmtRem = higherAmt - coin;
                System.out.println(String.format("nbrOfWays[higherAmt]:%d, higherAmt:%d",nbrOfWays[higherAmt], higherAmt));
                nbrOfWays[higherAmt] += nbrOfWays[higherAmtRem];//no of ways lower value has been calculated
                System.out.println(String.format("nbrOfWays[higherAmt]:%d, higherAmt:%d, higherAmtRem:%d, nbrOfWays[higherAmtRem]:%d ",nbrOfWays[higherAmt],higherAmt, higherAmtRem, nbrOfWays[higherAmtRem]));
            }
        }
        return nbrOfWays[amount];
    }
    public static void main(String[] args) {
        int amount = 4;
        int[] denominations = {1,2,3};
        //System.out.println(changeDenominations(amount, denominations));
        //System.out.println(changeDenominationsNonRecursive(amount, denominations));
        System.out.println(createDenominationsBottomUp(amount, denominations));
    }
}
