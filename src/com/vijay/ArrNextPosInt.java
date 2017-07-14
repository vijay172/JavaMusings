package com.vijay;

import java.util.ArrayList;

/**
 * Created by vkbalakr on 7/9/17.
 */
public class ArrNextPosInt {

    public static void main(String[] args) {
        // run your function through some test cases here
        // remember: debugging is half the battle!
        //int[] testInput = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] testInput = new int[]{1,2,3,4,6};
        ArrayList<Integer> lst = new ArrayList<>();
        int prev = testInput[0];
        if (prev > 1) {
            System.out.println(1);
        }
        boolean nextPosNbrFound = false;
        int len = testInput.length;
        for (int i=1; i < len; i++) {
            int curr = testInput[i];
            if (curr - prev > 1) {
                //prevSum + curr
                int prevSum = lst.get(0);
                int nextPosNbr = prevSum + curr + 1;//so 10 + 6 + 1 = 17
                System.out.println("nextPosNbr:"+nextPosNbr);
                nextPosNbrFound = true;
                break;
            } else {
                //store sum
                //sum of curr consecutive ints
                int currSum = curr * (curr +1) / 2;
                if (!lst.isEmpty()) {
                    lst.remove(0);
                }
                lst.add(currSum);
                prev = curr;
            }
        }
        if (!nextPosNbrFound) {
            int nextInt = lst.get(0) +  1;
            System.out.println("nextInt:"+nextInt);
        }

        //System.out.println(testInput[minStart]);

    }
}
