package com.vijay.cake;

/**
 * Created by vkbalakr on 6/22/17.
 */
public class HighestEggDrop {

    public static int determineMaxHtToDropEggFrom(int maxDropHt, int maxFloors) {

        int slowIdx = 0,fastIdx=0, dropCnt = 0;

        while (slowIdx < maxDropHt && fastIdx < maxDropHt) {
            slowIdx = fastIdx + 2;
            fastIdx = slowIdx + 2;
            if (!checkIfEggBreaks(slowIdx, maxDropHt)) {
                ++dropCnt;
               if (!checkIfEggBreaks(fastIdx, maxDropHt)) {
                   ++dropCnt;
               } else {
                   ++dropCnt;
                   break;
               }

            } else {
                //egg breaks
                ++dropCnt;
                break;
            }
        }
        return dropCnt;
    }

    public static boolean checkIfEggBreaks(int idx, int maxDropHt) {
        if (idx >= maxDropHt) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int maxDropHt = 99;
        int maxFloors = 100;
        System.out.println(determineMaxHtToDropEggFrom(maxDropHt, maxFloors));
    }
}
