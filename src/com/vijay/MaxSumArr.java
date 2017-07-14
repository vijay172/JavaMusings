package com.vijay;

import java.util.Arrays;

/**
 * Created by vkbalakr on 7/11/17.
 */
public class MaxSumArr {

    private static int seqStart = 0;
    private static int seqEnd = -1;

    public static int maxSumSubSeq(int[] a) {
        int maxSum = Integer.MIN_VALUE, thisSum = 0;

        for (int i = 0, j = 0; j < a.length; j++) {//i tags seqStart, j tags running pointer seqEnd
            thisSum += a[j];
            if (thisSum >= maxSum) {
                maxSum = thisSum;
                seqStart = i;
                seqEnd = j; //till now
            } else if (maxSum < 0){
                i = j + 1;//reset by jumping to next pointer of j
                thisSum = 0;
            }
        }

        return maxSum;
    }

    public static int maxSumSeq(int[] a) {
        int currSum=a[0], maxSum = a[0];
        for (int i=1; i < a.length;i++) {
            currSum = Math.max(currSum + a[i], a[i]);//currSum becomes -ve, start with new currSum
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }

    public static int findMissingElement(int[] a, int[] withMissingArr) {
        int missingNbr = 0;

        for (int i=0; i< a.length; i++) {
            missingNbr ^= a[i];
        }
        for (int i=0; i< withMissingArr.length; i++) {
            missingNbr ^= withMissingArr[i];
        }
        return missingNbr;
    }

    private static int getIndex(int currIdx, int n) {
        int i=3;
        return (currIdx % i) * n + (currIdx / i);
    }

    private static int[] convertArr(int[] arr) {
        int n = arr.length / 3;
        for (int currIdx = 0; currIdx < arr.length; currIdx++) {
            int swapIdx = getIndex(currIdx, n);
            /**
             The element at swapIndex is the final element to appear at currentIndex.
             So we swap the elements at currentIndex and swapIndex, if swapIndex>=currentIndex.
             But if swapIndex<currentIndex then it means that the element at swapIndex was replaced with another element
             at previous iterations. Now it’s somewhere else and we should keep looking for that element.
             We again call getIndex with swapIndex as new input to find the element it was replaced with.
             If the new swapIndex>=currentIndex, we swap the elements as before. Otherwise, we repeat this procedure
             until swapIndex>=currentIndex, which is we find the final element that’s supposed to appear at currentIndex.
             */
            while (swapIdx < currIdx) {//why ???
                swapIdx = getIndex(swapIdx, n);
            }
            //swap
            int tmp = arr[currIdx];
            arr[currIdx] = arr[swapIdx];
            arr[swapIdx] = tmp;
        }
        return arr;
    }

    public static void main(String[] args) {
        //int[] intArr = {5, -6, 4, -8, 9, -8, 2, -6};
        int[] intArr = {5, 6, 4, 8, 9, 2, 3};
        int[] missingElemArr = {5, 6, 8, 9, 2, 3};
        //int[] intArr = {-5, -6, -4, -8, -9, -8, -2, -6};
        //System.out.println(maxSumSeq(intArr));
        //System.out.println(findMissingElement(intArr, missingElemArr));
        int[] testArr = {1,2,3,11,12,13,21,31,41};
        System.out.println(Arrays.toString(convertArr(testArr)));
    }
}
