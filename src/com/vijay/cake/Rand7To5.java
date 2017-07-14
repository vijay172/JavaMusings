package com.vijay.cake;

import java.util.Random;

/**
 * Created by vkbalakr on 6/22/17.
 */
public class Rand7To5 {

    public static Random rand = new Random();
    public static int getRandom(int floor, int ceiling) {
        return rand.nextInt((ceiling - floor) + 1) + floor;
    }

    public static int rand5() {
        int ceiling = rand7() % 5 + 1;
        return getRandom(1, ceiling);
    }

    public static int rand7() {
        return getRandom(1, 7);
    }

    public static int rand5New() {
        return getRandom(1, 5);
    }

    public static int rand5To7() {
        int result = rand5New() + rand5New();//trick
        if (result > 7) {
            result = result % 7 + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i=0; i< 25; i++) {
            System.out.print(rand5To7() + " ");
        }
    }
}
