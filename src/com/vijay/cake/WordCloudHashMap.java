package com.vijay.cake;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vkbalakr on 6/21/17.
 */
public class WordCloudHashMap {
    static Map<String, Integer> wordsToCounts = new HashMap<>();
    static Map<String,String> wordPairs = new HashMap<>();

    private static void populateWordToCounts(String sentence) {

        int currWordLen = 0; //reset at end of each word
        int runningWordStartIdx = 0; //where are we in the string - keeps increasing
        String prevWord = "";
        //iterate over each char in string
        for (int i = 0; i < sentence.length(); i++) {

            char ch = sentence.charAt(i);

            //reached end of sentence and add last char to word and to hashmap
            if (i == sentence.length() - 1) {//another delimiter for word
                if (Character.isLetter(ch)) {
                    currWordLen++;
                }
                if (currWordLen > 0) {
                    String currWord = sentence.substring(runningWordStartIdx, runningWordStartIdx + currWordLen);
                    addWordToHashMap(currWord, prevWord);
                    prevWord = currWord;
                }
            }
            //hit a space or em-dash, we are at end of a word
            else if ((ch == ' ') || (ch == '\u2014')) {
                if (currWordLen > 0) {
                    String currWord = sentence.substring(runningWordStartIdx, runningWordStartIdx + currWordLen);
                    addWordToHashMap(currWord, prevWord);
                    currWordLen = 0;
                }
            } else if (ch == '.') { //check for ..
                if (i < sentence.length() - 1 && sentence.charAt(i + 1) == '.') {
                    if (currWordLen > 0) {
                        String currWord = sentence.substring(runningWordStartIdx, runningWordStartIdx + currWordLen);
                        addWordToHashMap(currWord, prevWord);
                        currWordLen = 0;
                        prevWord = currWord;
                    }
                }
            } else if (Character.isLetter(ch) || (ch == '\'')) {
                if (currWordLen == 0) {
                    runningWordStartIdx = i;
                }
                currWordLen++;
            } else if (ch == '-') { //check prev and next chars exist
                if (i > 0 && Character.isLetter(i - 1) && Character.isLetter(i + 1)) {
                    if (currWordLen == 0) {
                        runningWordStartIdx = i;
                    }
                    currWordLen++;
                } else {
                    if (currWordLen > 0) {
                        String currWord = sentence.substring(runningWordStartIdx, runningWordStartIdx + currWordLen);

                        addWordToHashMap(currWord, prevWord);
                        currWordLen = 0;
                        prevWord = currWord;
                    }
                }
            }

        }
    }



    private static void addWordToHashMap(String word, String prevWord) {

        if (wordsToCounts.containsKey(word)) {
            if (wordPairs.containsKey(word)) {
                Integer prevWordCount = wordsToCounts.get(prevWord);
                String prevWord1 = wordPairs.get(word);
                if (prevWord.equals(prevWord1)) {
                    String pairKey = prevWord1 + word; //Fire truck
                    Integer pairKeyCount = wordsToCounts.get(pairKey);
                    if (pairKeyCount == null) {
                        wordsToCounts.put(pairKey, 1);
                    } else {
                        wordsToCounts.put(pairKey, pairKeyCount + 1);
                    }
                    wordsToCounts.put(prevWord, prevWordCount - 1);
                }
            } else {
                wordsToCounts.put(word, wordsToCounts.get(word) + 1);
            }
        } else if (wordsToCounts.containsKey(word.toLowerCase())) {//word is uppercase -convert to lowercase
            wordsToCounts.put(word.toLowerCase(), wordsToCounts.get(word.toLowerCase()) + 1);
        } else if (wordsToCounts.containsKey(capitalize(word))) {//Yellow
            //word is lowercase
            int upperCount = wordsToCounts.get(capitalize(word));
            wordsToCounts.put(word, upperCount + 1);
            wordsToCounts.remove(capitalize(word));
        } else {
            //not found
            wordsToCounts.put(word, 1);
        }

    }

    public static Map<String, Integer> getWordToCounts() {
        return Collections.unmodifiableMap(wordsToCounts);
    }

    private static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /*public static Map<String, Integer> createMap(String[] sentences) {
        Map<String, Integer> result = new HashMap<>();
        Set<String> filteredWords = new HashSet<>();
        filteredWords.add("after");
        filteredWords.add("dana");
        for (String sentence : sentences) {

            List<String> wordList = splitStr(sentence);//iterate thru all characters O(n)
            System.out.println("wordList:" + wordList);
            for (String word : wordList) {//iterate thru all words
                String lowerCaseWord = word.toLowerCase();
                if (!filteredWords.contains(lowerCaseWord)) {
                    //TODO:trim punctuation :.,;
                    Integer wordCount = result.get(lowerCaseWord);

                    if (wordCount == null) {
                        //is it uppercase
                        *//*int count = result.get(capitalize(word));
                        result.put(lowerCaseWord, count +1);
                        result.remove(capitalize(word));*//*
                        result.put(lowerCaseWord, 1);
                    } else {
                        result.put(lowerCaseWord, wordCount + 1);
                    }
                }

            }
        }
        return result;
    }

    public static List<String> splitStr(String sentence) {
        List<String> wordList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char ch : sentence.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {//TODO:punctuation
                sb.append(ch);
            } else {
                wordList.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        sb = null;
        return wordList;
    }*/

    public static void main(String[] args) {
        wordPairs.put("truck","Fire");
        wordPairs.put("Cake","Interview");

        int len = 4;
        String[] test = new String[len];
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                test[i] = "After beating the eggs, Dana read the next step: Fire truck";
                populateWordToCounts(test[i]);
            }
            else if (i == 1) {
            test[i] = "Add milk and eggs, then add flour and sugar. Interview Cake";
            populateWordToCounts(test[i]);
        } else if (i == 2) {
            test[i] = "We came, we saw, we conquered...then we ate Bill's (Mille-Feuille) cake.";
            populateWordToCounts(test[i]);
        } else if (i == 3) {
            test[i] = "The bill came to five dollars.";
            populateWordToCounts(test[i]);
        }
    }

    //System.out.println(createMap(test));
        System.out.println(getWordToCounts());
}
}
