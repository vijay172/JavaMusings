package com.vijay.cake;

/**
 * Created by vkbalakr on 6/22/17.
 */
public class FindDuplicateSpace {

    private static int[] inputArr = new int[]{1,2,3,4,5,6,7,8,1,9,10};

    public static int findDuplicates(int[] input) {

        int[] newArr = new int[input.length];
        for (int nbr: input) {
            int count = newArr[nbr];

            if (++count > 1) {
                return nbr;
            } else {
                newArr[nbr] = count;
            }
            System.out.println("count:"+count+" nbr:"+nbr);
        }
        return 0;
    }

    public static void main(String[] args) {

        System.out.println(findDuplicates(inputArr));
    }
}
