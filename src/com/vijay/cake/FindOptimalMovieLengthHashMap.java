package com.vijay.cake;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by vkbalakr on 6/28/17.
 */
public class FindOptimalMovieLengthHashMap {

    public static boolean findOptimalMovieLengths(int[] movieLengths, int flightLength) {
        Map<Integer, Integer> movieMap = new HashMap<>();
        int idx = 0;
        for (int movieLength: movieLengths) {
            movieMap.put(movieLength, idx++);
        }
        for (int movieLength: movieLengths) {
            int movieIdx = movieMap.get(movieLength);
            movieMap.remove(movieLength);
            if (movieMap.containsKey(flightLength - movieLength)) {
                return true;
            }
        }
        return false;
    }

    public static boolean findOptimalMovieLengths1(int[] movieLengths, int flightLength) {
        Set<Integer> movieSet = new HashSet<>();
        for (int movieLength: movieLengths) {
            if (!movieSet.contains(flightLength - movieLength)) {
                movieSet.add(movieLength);
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // run your function through some test cases here
        // remember: debugging is half the battle!
        int[] movieLengths = new int[]{22,19,15,22,20};
        int flightLength = 40;
        System.out.println(findOptimalMovieLengths1(movieLengths, flightLength));
    }
}
