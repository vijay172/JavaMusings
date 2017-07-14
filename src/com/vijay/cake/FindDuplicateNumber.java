package com.vijay.cake;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by vkbalakr on 6/21/17.
 */
public class FindDuplicateNumber {

    public static int findDuplicateNbr(int[] nbrArr) {

        Map<Integer, Integer> countOfNbrs = new HashMap<>();
        Set<Integer> dupNbrSet = new HashSet<>();
        for (int nbr: nbrArr) {
            Integer count = countOfNbrs.get(nbr);
            if (count == null) {
                countOfNbrs.put(nbr, 1);
            } else {
                dupNbrSet.add(nbr);//dup found
                break;
            }

        }
        return dupNbrSet.iterator().next();//TODO:
    }

    public static float findDup(int[] nbrArr, int n) {

        //int n = nbrArr.length - 2;
        float sum =  (n - 1) * n / 2;
        float newSum = 0;
        for (int nbr: nbrArr) {
            newSum += nbr;
        }
        System.out.println("newSum:"+newSum + " sum:"+  sum);
        return newSum - sum;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] testArr = new int[n+1];
        for (int i=0; i < n+1; i++) {
            testArr[i] = i;
            if (i==68) {
                testArr[++i] = 68;
            }
        }
        //System.out.println(findDuplicateNbr(testArr));
        System.out.println(findDup(testArr, n));
    }
}
