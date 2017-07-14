package com.vijay;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Created by vkbalakr on 6/15/17.
 */
public class Anagram {

    public static Map<Character, Integer> buildMapForCharCount(String str) {
        Map<Character, Integer> mapForCharCnt = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            Integer cnt = mapForCharCnt.get(ch);
            if (cnt != null && cnt.intValue() > 0) {
                mapForCharCnt.put(ch, cnt.intValue() + 1);
            } else {
                mapForCharCnt.put(ch, 1);
            }
        }
        return mapForCharCnt;
    }

    public static int countNotMatched(Map<Character, Integer> map1, String second) {
        int delCount = 0;
        //match in 2nd string
        for (int i = 0; i < second.length(); i++) {
            char ch = second.charAt(i);
            Integer matchCnt = map1.get(ch);
            if (matchCnt != null) {
                int chCnt = map1.get(ch) - 1;
                if (chCnt == 0) {
                    map1.remove(ch);
                } else {
                    map1.put(ch, chCnt);
                }
            } else {
                delCount++;
            }

        }
        //find non matches remaining in map
        for(Map.Entry<Character,Integer> entry: map1.entrySet()) {
            delCount += entry.getValue();

        }

        return delCount;
    }

    public static int numberNeeded(String first, String second) {
        //
        int delCount = 0;
        if (first.length() >= second.length()) {
            Map<Character, Integer> map1 = buildMapForCharCount(first);
            delCount = countNotMatched(map1, second);
        } else {
            Map<Character, Integer> map1 = buildMapForCharCount(second);
            delCount = countNotMatched(map1, first);
        }

        return delCount;
        /*StringBuilder firstsb = new StringBuilder();
        StringBuilder secondsb = new StringBuilder();
        char[] charArr = first.toCharArray();
        int delCount = 0;
        for (int i=0; i < charArr.length; i++) {
            if (second.indexOf(charArr[i]) > -1) {
                firstsb.append(charArr[i]);

            } else {
                //System.out.println("not found in second:"+charArr[i] + " delCount:" + delCount);
                ++delCount;
            }
        }
        char[] charArr2 = second.toCharArray();
        for (int i=0; i < charArr2.length; i++) {
            if (first.indexOf(charArr2[i]) > -1) {
                secondsb.append(charArr2[i]);
            } else {

                //System.out.println("not found in first:" + charArr2[i] + " delCount:" + delCount);
                ++delCount;
            }
        }
        //System.out.println("firstsb.toString():" + firstsb.toString() + " secondsb.toString():" + secondsb.toString());
        if (firstsb.length() == secondsb.length() && firstsb.toString().equals(secondsb.toString())) {
            return delCount;
        } else {
            return 0;
        }*/
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
