package com.vijay;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vkbalakr on 6/25/17.
 */
public class Compare2StringsForMatch {

    public static boolean matchStrings(String input, String output) {
        //TODO: constraints check
        //store input in hashMap
        Map<Character, Integer> charToCountMap = new HashMap<>();
        for (char ch: input.toCharArray()) {
            Integer inputCnt = charToCountMap.get(ch);
            if ( inputCnt == null) {
                charToCountMap.put(ch, 1);
            } else {
                charToCountMap.put(ch, inputCnt + 1);
            }

        }
        //check each char in output for match in hashmap
        int outputCnt = 0;
        int inputLen = input.length();
        for (char outputCh: output.toCharArray()) {
            if (charToCountMap.containsKey(outputCh)) {
                outputCnt++;
            }
        }

        //increment count for match
        //if input length - count <= 1 , true
        if (inputLen - outputCnt <= 1) {
            return true;
        } else {
            return false;
        }

    }
    public static void main(String[] args) {
        String input = "pale";
        String output = "bale";
        System.out.println(matchStrings(input, output));
    }
}
