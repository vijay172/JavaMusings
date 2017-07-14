package com.vijay.cake;

import java.util.Random;

/**
 * Created by vkbalakr on 6/23/17.
 */
public class BitlyUrl {

    private static final int NUM_CHARS_IN_SLUG = 7;
    private static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private Random rand = new Random();

    public String generateRandomSlug() {

        char[] result = new char[NUM_CHARS_IN_SLUG];

        for (int i=0; i < NUM_CHARS_IN_SLUG; i++) {
            int randomIdx = rand.nextInt(NUM_CHARS_IN_SLUG -1);
            result[i] = ALPHABET.charAt(randomIdx);
        }

        return new String(result);
    }

    public static void main(String[] args) {


    }
}
