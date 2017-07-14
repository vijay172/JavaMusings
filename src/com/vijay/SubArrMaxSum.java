package com.vijay;

/**
 * Created by vkbalakr on 7/9/17.
 */
public class SubArrMaxSum {
    public static class Tuple {
        int start;
        int end;
        public Tuple(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static int findMaxSumSeq(int[] intArr) {

        int maxSum=0,startIdx=0,endIdx=0;
        Tuple tuple = new Tuple(0,0);
        for (int i=0; i < intArr.length; i++) {
            int sum=0;
            int j = i;
            while (j < intArr.length) {
                sum += intArr[j];
                if (sum < maxSum) {
                    break;
                } else {
                    maxSum = sum;
                    tuple.start=i;
                    tuple.end = j;
                }
                j++;
            }
        }
        System.out.println(String.format("tuple start:%d, end:%d",tuple.start, tuple.end));
        return maxSum;
    }

    public static int findMaxProdSeq(int[] intArr) {

        int maxProd=1;
        Tuple tuple = new Tuple(0,0);
        for (int i=0; i < intArr.length; i++) {
            int prod=1;
            int j = i;
            while (j < intArr.length) {
                prod *= intArr[j];
                if (prod < maxProd) {
                    break;
                } else {
                    maxProd = prod;
                    tuple.start=i;
                    tuple.end = j;
                }
                j++;
            }
        }
        System.out.println(String.format("tuple start:%d, end:%d",tuple.start, tuple.end));
        return maxProd;
    }

    public static void main(String[] args) {
        // run your function through some test cases here
        // remember: debugging is half the battle!
        //int[] testInput = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] intArr = new int[]{-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};


        System.out.println(findMaxSumSeq(intArr));

    }
}
