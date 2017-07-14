package com.vijay;

import java.util.Arrays;

/**
 * Created by vkbalakr on 7/9/17.
 */
public class ArrRemoveElementInPlace {


    public static int removeElementInPlace(int[] inputArr, int elem) {
        int length = 0;

        for (int i=0; i < inputArr.length; i++) {
            if (inputArr[i] == elem) {
               continue;
            } else {
                inputArr[length++] = inputArr[i];
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOf(inputArr, length)));
        return length;
    }

    public static int removeDupsInPlace(int[] inputArr) {
        int length = 0;
        Arrays.sort(inputArr);
        int prev = inputArr[0];
        inputArr[length++] = prev;
        for (int i=1; i < inputArr.length; i++) {
            int curr = inputArr[i];
            if ( curr == prev) {
                continue;
            } else {
                inputArr[length++] = inputArr[i];
            }
            prev = curr;
        }
        System.out.println(Arrays.toString(Arrays.copyOf(inputArr, length)));
        return length;
    }
    public static void main(String[] args) {
        int[] testArr = {1,7,2,3,7,46,7,8,7};
        System.out.println(removeDupsInPlace(testArr));
    }
}
