package com.vijay;

import java.util.Arrays;

/**
 * Created by vkbalakr on 7/5/17.
 */
public class RecursiveStepsToClimb {


    public static long noOfWays(int totalSteps) {

        long[] noOfSteps = new long[totalSteps + 1];//0..steps
        noOfSteps[0] = 0;//base
        noOfSteps[1] = 1;//base

        for (int stepsTaken = 1; stepsTaken <= 3; stepsTaken++) {//taking 1,2,3 steps
            for (int step = stepsTaken; step <= totalSteps; step++) {//total ways to reach all the steps

                noOfSteps[step] += noOfSteps[step - stepsTaken];
            }
        }

        return noOfSteps[3];//use 3 instead of totalSteps
    }

    public static long noOfWaysRecursive(int totalSteps) {
        if (totalSteps < 0) {
            return 0;
        } else if (totalSteps == 0) {
            return 1;
        } else {
            return noOfWaysRecursive(totalSteps - 1) + noOfWaysRecursive(totalSteps - 2) + noOfWaysRecursive(totalSteps - 3);
        }


    }

    public int countWays(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return countWays(n, memo);
    }

    public int countWays(int n, int[] memo) {
            if (n< 0) {return 0; }
            else if (n== 0) {return 1; }
            else if (memo[n] > -1) {
                return memo[n];
            } else {
                memo[n] = countWays(n-1) + countWays(n-2) + countWays(n-3);
            }
        return memo[n];
    }

    public static void main(String[] args) {
        int steps = 10;
        //System.out.println(noOfWaysRecursive(steps));
        System.out.println(noOfWaysRecursive(steps));
    }
}
