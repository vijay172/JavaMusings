package com.vijay.cake;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by vkbalakr on 6/22/17.
 */
public class RiffleShuffle {

    public static int riffleCount(int remaining, int countRiffles) {
        System.out.println("Inside riffleCount with remaining:"+remaining+" countRiffles:"+countRiffles);
        while (remaining > 0) {
            remaining = remaining - getRandom(1, remaining + 1);
            countRiffles += 1;
        }
        System.out.println("countRiffles:"+countRiffles);
        return countRiffles;
    }

    public static int getRandom(int start, int end) {
        int random = ThreadLocalRandom.current().nextInt(start, end);
        System.out.println("random:"+random);
        return random;
    }

    public static int riffleCount(int half) {
        return riffleCount(half, 0);
    }

    public static boolean riffle(int cards) {

        int half = cards / 2;//expect even cards
        //do it twice in parallel for 2 halves
        int count = 0;
        for (int i=0; i < 2; i++) {
            System.out.println("inside riffle:"+i);
            count += riffleCount(half);
        }
        return (count == 2);
    }



    public static int riffleRecursive(int remaining, int count) {
        if (remaining <= 0) return count;
        int random = getRandom(0, remaining + 1);
        remaining -= random;
        System.out.println("remaining:"+remaining);
        return riffleRecursive(remaining, ++count);

    }

    public static boolean rif(int cards) {

        int half = cards / 2;
        int count = 0;
        for (int i=0; i < 2; i++) {
            System.out.println("starting:"+i);
            count += riffleRecursive(half, 0);
        }
        System.out.println("count:"+count);
        return (count == 2);
    }

    private static int[] removeTopCard(int[] cards) {
        return Arrays.copyOfRange(cards, 1, cards.length);
    }

    public static boolean isSingleRiffle(int[] half1, int[] half2, int[] deck) {

        //base
        if (deck.length == 0) return true;

        //if card in deck matches top card in half1, remove top card from half1 & deck
        if (half1.length > 0 && half1[0] == deck[0]) {
            return isSingleRiffle(removeTopCard(half1), half2, removeTopCard(deck));
        } else if (half2.length > 0 && half2[0] == deck[0]) {
            return isSingleRiffle(half1, removeTopCard(half2), removeTopCard(deck));
        } else { return false; }

    }

    public static boolean isSingleRiffle1(int[] half1, int[] half2, int[] deck) {
        return isSingleRiffle1(half1, half2, deck, 0, 0, 0);
    }

    //keep track of idx instead of slicing the array itself.  O(n) time going thru array.
    //Still O(n) space in call stack becoz of recursion.
    public static boolean isSingleRiffle1(int[] half1, int[] half2, int[] deck, int h1Idx, int h2Idx, int deckIdx) {

        if (h1Idx < half1.length  && half1[h1Idx] == deck[deckIdx]) {
            h1Idx++;
        } else if (h2Idx < half2.length && half2[h2Idx] == deck[deckIdx]) {
            h2Idx++;
        } else {
            return false;
        }
        deckIdx++;
        return isSingleRiffle1(half1, half2, deck, h1Idx, h2Idx, deckIdx);
    }

    public static boolean isSingleRiffleNonRecursive(int[] half1, int[] half2, int[] deck, int h1Idx, int h2Idx, int deckIdx) {

        while (h1Idx < half1.length || h2Idx < half2.length) {
            if (half1[h1Idx] == deck[deckIdx]) {
                h1Idx++;
            } else if (half2[h2Idx] == deck[deckIdx]) {
                h2Idx++;
            } else {
                return false;
            }
            deckIdx++;
        }
        return true;
    }

    public static boolean isSingleRiffleSpaceReduced(int[] half1, int[] half2, int[] deck) {

        int h1Idx = 0;
        int h2Idx = 0;

        int h1MaxIdx = half1.length - 1;
        int h2MaxIdx = half2.length - 1;
        for (int card: deck) {

            if (h1Idx < h1MaxIdx && half1[h1Idx] == card) {
                h1Idx++;
            } else if (h1Idx < h2MaxIdx && half2[h2Idx] ==  card) {
                h2Idx++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int cards = 52;
        if (cards % 2 != 0) {
           throw new IllegalArgumentException("not even cards");
        }
//        System.out.println(riffle(cards));
        //System.out.println(rif(cards));
        System.out.println(rif(cards));

    }
}
