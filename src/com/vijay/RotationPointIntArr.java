package com.vijay;

/**
 * Created by vkbalakr on 7/10/17.
 */
public class RotationPointIntArr {

    public static int findRotationPoint(int[] intArr) {
        if (intArr.length <= 1) {
            throw new RuntimeException("No rotation pt for just 1 int");
        }
        int highIdx = intArr.length - 1;
        int lowIdx = 0;
        while (lowIdx < highIdx) {
            int mid = lowIdx + (highIdx - lowIdx) / 2;
            if (intArr[lowIdx] < intArr[mid]) {
                lowIdx = mid +1;
            }
            if (intArr[mid] < intArr[highIdx]) {
                highIdx = mid - 1;
            }

            if (lowIdx + 1 == highIdx) {//flip point
                break;
            }
        }
        return highIdx;

    }

    public static void main(String[] args) {
        int[] intArr = {5,6,7,8,9,1,2,3,4};
        System.out.println(intArr[findRotationPoint(intArr)]);
    }
}
