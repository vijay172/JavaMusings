package com.vijay;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vkbalakr on 6/24/17.
 */
public class PalindromePermutationCheck {

    public static boolean checkIfStringIsPermutationOfPalindrome(String input) {
        //TODO: pre constraints checks on input
        if (input == null || input.length() <= 1) {
            return false;
        }
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char ch: input.toCharArray()) {
            if (ch == ' ') {
                continue;
            }
            Integer chCount = charCountMap.get(ch);
            if ( chCount == null ) {
                charCountMap.put(ch, 1);
            } else {
                charCountMap.remove(ch);
            }
        }
        Collection<Integer> countSize = charCountMap.values();
        if(countSize != null && countSize.size() == 1) {
            return true;
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        //String input = "Tact Coa";
        String input = "gomin bao";
        System.out.println(checkIfStringIsPermutationOfPalindrome(input.toLowerCase()));
    }
}
