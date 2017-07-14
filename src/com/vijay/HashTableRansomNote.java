package com.vijay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by vkbalakr on 6/15/17.
 */
public class HashTableRansomNote {
    Map<String, Integer> magazineMap;
    Map<String, Integer> noteMap;

    public HashTableRansomNote(String magazine, String note) {
        //break magazine into words in Map(word, count)
        magazineMap = new HashMap<String, Integer>();
        populateMapWithWordCount(magazineMap, magazine);
        noteMap = new HashMap<String, Integer>();
        populateMapWithWordCount(noteMap, note);

    }

    public Map<String, Integer> populateMapWithWordCount(Map<String, Integer> wordMap, String wordsInaLine) {
        String[] magArr = wordsInaLine.split(" ");
        for (String magWord: magArr) {
            Integer magWordCount = wordMap.get(magWord);
            if (magWordCount == null) {
                wordMap.put(magWord, 1);
            } else {
                wordMap.put(magWord, magWordCount + 1);
            }
        }
        return wordMap;
    }

    public boolean solve() {
        //iterate thru noteMap and find matches for word and count in magazine
        Set<String> noteKeySet = noteMap.keySet();
        Iterator<String> keyIter = noteKeySet.iterator();
        boolean result = true;
        while (keyIter.hasNext()) {
            String noteKey = keyIter.next();
            Integer magWordCount = magazineMap.get(noteKey);
            if (magWordCount != null && magWordCount >= noteMap.get(noteKey)) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     6 4
     give me one grand today night
     give one grand today
     Yes
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        // Eat whitespace to beginning of next line
        scanner.nextLine();

        HashTableRansomNote s = new HashTableRansomNote(scanner.nextLine(), scanner.nextLine());
        scanner.close();

        boolean answer = s.solve();
        if(answer)
            System.out.println("Yes");
        else System.out.println("No");

    }
}
