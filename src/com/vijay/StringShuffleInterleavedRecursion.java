package com.vijay;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vkbalakr on 7/12/17.
 */
public class StringShuffleInterleavedRecursion {

    public static class Tuple {
        String s1;
        String s2;
        Tuple(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }
    }

    public static boolean isShuffle(String s1, String s2, String s3, Map<Tuple, Boolean> map) {
        if (map.get(new Tuple(s1,s2)) != null) {//with cache, O(NM). Otherwise exponential complexity
            return false;
        }

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        //base case
        if (s1.length() == 0 || s2.length() == 0 || s3.length() == 0) {
            if ((s1 + s2).equals(s3)) {
                return true;
            } else {
                return false;
            }
        }

        if ((s1.charAt(0) != s3.charAt(0)) && (s2.charAt(0) != s3.charAt(0)) ) {
            return false;
        }
        //reduction case
        if ((s1.charAt(0) == s3.charAt(0)) && isShuffle(s1.substring(1), s2, s3.substring(1), map) ) {
            return true;
        }

        if ((s2.charAt(0) ==  s3.charAt(0)) && isShuffle(s1, s2.substring(1), s3.substring(1), map) ) {
            return true;
        }
        map.put(new Tuple(s1,s2), true);//these cannot produce a valid shuffle and we dont want to check it again
        return false;
    }

    public static void main(String[] args) {
        Map<Tuple, Boolean> map = new HashMap<>();
        System.out.println(isShuffle("abc","def","dabecf", map));
    }
}
