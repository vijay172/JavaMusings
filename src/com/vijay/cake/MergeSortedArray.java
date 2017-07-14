package com.vijay.cake;

import java.util.Arrays;

/**
 * Created by vkbalakr on 6/23/17.
 */
public class MergeSortedArray {

    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        //TODO: check arr1 and arr2 lengths
        int[] mergedArr = new int[arr1.length + arr2.length];
        int count = 0;
        int arr1Idx = 0;
        int arr2Idx = 0;
        while(arr1Idx < arr1.length  && arr2Idx < arr2.length ) {
            System.out.println("arr1Idx:" + arr1Idx + ",arr2idx:"+ arr2Idx);
                if (arr1[arr1Idx] < arr2[arr2Idx]) {
                    mergedArr[count++] = arr1[arr1Idx++];
                } else {
                    mergedArr[count++] = arr2[arr2Idx++];
                }
        }

        if (arr1Idx == arr1.length) {//arr1 reached its end-so populate rest of arr2
           while (arr2Idx < arr2.length) {
               mergedArr[count++] = arr2[arr2Idx++];
           }
        } else if (arr2Idx == arr2.length) {
            while (arr1Idx < arr1.length) {
                mergedArr[count++] = arr1[arr1Idx++];
            }
        }
        System.out.println(Arrays.toString(mergedArr));
        return mergedArr;
    }

    public static int[] mergeLibs(int[] arr1, int[] arr2) {

        int[] mergedArr = Arrays.copyOf(arr1,arr1.length + arr2.length);
        System.arraycopy(arr2, 0, mergedArr, arr1.length, arr2.length);
        Arrays.sort(mergedArr);
        return mergedArr;
    }

    public static void main(String[] args) {
        int[] myArray     = new int[]{3, 4, 6, 10, 11, 15};
        int[] alicesArray = new int[]{1, 5, 8, 12, 14, 19};

        System.out.println(mergeArrays(myArray, alicesArray));
// prints [1, 3, 4, 5, 6, 8, 10, 11, 12, 14, 15, 19]
    }
}
