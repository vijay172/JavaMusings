package com.vijay.cake;

/**
 * Created by vkbalakr on 6/28/17.
 */
public class RotationPointArray {

    public static int findRotationPoint(String[] words) {
        if (words.length <= 1) {
            throw new IllegalArgumentException("Words need to be longer than 1 word");
        }
        int firstIdx = 0, lastIdx = words.length - 1;
        String first = words[firstIdx];
        //binary search for sorted array
        while (firstIdx < lastIdx) {

            int midIdx = firstIdx + (lastIdx - firstIdx) / 2;
            String mid = words[midIdx];
            System.out.println(String.format("firstIdx:%d, first:%s, midIdx:%d, mid:%s, lastIdx:%d", firstIdx, first, midIdx, mid, lastIdx));
            if (mid.compareTo(first) >= 0) {//mid cud be 1st word
                firstIdx = midIdx;
            } else {
                lastIdx = midIdx;
            }
            //tricky bit about flipping
            if (firstIdx + 1 == lastIdx) {//firstIdx and lastIdx have converged
                //where firstIdx and lastIdx are about to get flipped-
                //so lastIdx is alphabetically 1st
                break;
            }

        }
        return lastIdx;
    }
    public static void main(String[] args) {
        String[] words = new String[]{
                "ptolemaic",
                "retrograde",
                "supplant",
                "undulate",
                "xenoepist",
                //"zebra",
                "asymptote", // <-- rotates here!
                "babka",
                "banoffee",
                "engender",
                "karpatka",
                "othellolagkage",

        };
        System.out.println(findRotationPoint(words));
    }
}
