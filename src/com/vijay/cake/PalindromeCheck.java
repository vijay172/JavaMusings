package com.vijay.cake;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by vkbalakr on 6/20/17.
 */
public class PalindromeCheck {

    public static boolean palindromeMatch(String test) {
       Set<Character> unpairedChars = new HashSet<>();
       for (char ch: test.toCharArray()) {
           //all i need at the end is the nbr of odd occurrences of chars
           if (unpairedChars.contains(ch)) {
               unpairedChars.remove(ch);
           } else {
               unpairedChars.add(ch);
           }
       }
       return unpairedChars.size() <= 1;
    }

    public static boolean palindromeCheck(String test) {
        //generate permutations of str - skip this
        /*check palindrome
          each character shud be even times
          only 1 character shud be odd times
         */
        Map<Character, Integer> charCntMap = new HashMap<>();
        for (char ch: test.toCharArray()) { //O(n)

            if (charCntMap.containsKey(ch)) {
                //System.out.println("key matched:" + ch);
                charCntMap.put(ch, charCntMap.get(ch) + 1);
            } else {
                //System.out.println("key not matched:" + ch);
                charCntMap.put(ch, 1);
            }
        }
        //count occurrences
        //Set<Integer> occurrences = new HashSet<>(charCntMap.values());
        int oddCnt = 0;
        for (Integer occurrence: charCntMap.values()) {
            //System.out.println("occurrence:" + occurrence);
            if (occurrence % 2 != 0) {
               ++oddCnt;
               if (oddCnt > 1) {
                   return false;
               }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] test = new String[4];
        test[0] = "civic";
        test[1] = "ivicc";
        test[2] = "civil";
        test[3] = "livci";
        for (int i=0;i<4; i++) {
            //System.out.println(palindromeCheck(test[i]));
            System.out.println(palindromeMatch(test[i]));
        }

    }
}
