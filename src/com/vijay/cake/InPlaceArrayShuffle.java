package com.vijay.cake;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by vkbalakr on 6/21/17.
 */
public class InPlaceArrayShuffle {

    public static int getRandom(int floor, int ceiling) {
        return ThreadLocalRandom.current().nextInt(floor, ceiling);
    }
    static Random rand = new Random();


    public static void shuffle(int[] inputArr) {
        int arrLen = inputArr.length - 1;
        for (int i=0; i <= arrLen; i++) {
            int tmp = inputArr[i];
            int dest = getRandom(i, arrLen + 1); //dest might appear in getRandom again
            inputArr[i] = inputArr[dest];
            inputArr[dest] = tmp;
        }
        //return inputArr;
    }

    /*
    Fisher yates shuffle
     */
    public static int getNewRandom(int floor, int ceiling) {
        return rand.nextInt((ceiling - floor) + 1) + floor;//start after curr array????
    }

    public static void knuthShuffle(int[] inputArr) {
        if (inputArr.length <= 1) {
            return;
        }

        for (int idxWeAreChoosingFor = 0; idxWeAreChoosingFor < inputArr.length - 1; idxWeAreChoosingFor++) {

            int randInt = getNewRandom(idxWeAreChoosingFor, inputArr.length - 1);//each one must be after the last selected one
            System.out.println("randInt:"+randInt + " idxWeAreChoosingFor:"+idxWeAreChoosingFor);
            if (randInt != idxWeAreChoosingFor) {
                int tmp = inputArr[idxWeAreChoosingFor];
                inputArr[idxWeAreChoosingFor] = inputArr[randInt];
                inputArr[randInt] = tmp;
            }

        }

    }

    public static void main(String[] args) {
        int[] input = new int[]{1,3,5,7,2};
        knuthShuffle(input);
        for (int in: input) {
            System.out.print(in+ " ");
        }

    }
}
