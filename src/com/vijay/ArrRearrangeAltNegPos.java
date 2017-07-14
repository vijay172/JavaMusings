package com.vijay;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vkbalakr on 7/9/17.
 */
public class ArrRearrangeAltNegPos {
    public static void main(String[] args) {
        int[] intArr = new int[]{-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        ArrayList<Integer> negLst = new ArrayList<>();
        ArrayList<Integer> posLst = new ArrayList<>();
        int len = intArr.length;
        int[] outArr = new int[len];
        for (int i=0;i < len;i++) {
            if (intArr[i] < 0) {
                negLst.add(intArr[i]);
            } else {
                posLst.add(intArr[i]);
            }
        }
        for (int i=0; i < len; ) {
            if (!negLst.isEmpty()) {
                outArr[i++] = negLst.get(0);
                negLst.remove(0);
            }
            if (!posLst.isEmpty()) {
                outArr[i++] = posLst.get(0);
                posLst.remove(0);
            }
            while (posLst.isEmpty() && !negLst.isEmpty()) {
                outArr[i++] = negLst.get(0);
                negLst.remove(0);
            }
            while (negLst.isEmpty() && !posLst.isEmpty()) {
                outArr[i++] = posLst.get(0);
                posLst.remove(0);
            }
            //output: {-5, 5, -2, 2, -8, 4, 7, 1, 8, 0}
        }

        System.out.println(Arrays.toString(outArr));

    }
}
