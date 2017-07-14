package com.vijay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by vkbalakr on 7/10/17.
 */
public class PrintAnagrams {

    public static void main(String[] args) {
        ArrayList<String> lstOfWords = new ArrayList<>();
        lstOfWords.add("cinema");
        lstOfWords.add("mugshot");
        lstOfWords.add("iceman");
        HashMap<String, ArrayList<String>> sortedWordToActualWordMap = new HashMap<>();
        for (String word: lstOfWords) {
            //sort word
            char[] wordToCharArr = word.toCharArray();
            Arrays.sort(wordToCharArr);
            String wordToCharArrStr = new String(wordToCharArr);
            ArrayList<String> wordLst = sortedWordToActualWordMap.get(wordToCharArrStr);
            if (wordLst == null)
                wordLst = new ArrayList<>();
            wordLst.add(word);
            sortedWordToActualWordMap.put(wordToCharArrStr, wordLst);

        }
        //HashMap<String, ArrayList<String>> sortedWordToActualWordMap = new HashMap<>();
        sortedWordToActualWordMap.forEach((k,v) -> {
            if (v.size() > 1) {
                System.out.println("forEach example:"+v);
            }
        }
        );

        /*for (Map.Entry<String, ArrayList<String>> entry1 : sortedWordToActualWordMap.entrySet()) {
            ArrayList<String> valueLst = entry1.getValue();
            if (valueLst.size() > 1) {
                System.out.println(valueLst);
            }
        }*/

    }


}


