package com.vijay.cake;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by vkbalakr on 6/20/17.
 */
public class PermutationStringRecursion {
    /*
    get all permutations of N-1 chars. Then insert last ch to N-1 permutations length strings and get all
    permutations of length N. Complexity O(N!) as there are N! possible combinations for a length N.
     */

    public static Set<String> getPermutations(String theString) {

        //base case
        if(theString.length() <= 1) {
            System.out.println("Inside base case:"+theString);
            return new HashSet<String>(Arrays.asList(theString));
        }
        //cat s break down string into 2 parts
        String allCharsExcLast = theString.substring(0, theString.length() - 1);
        //System.out.println(" allCharsExcLast:"+allCharsExcLast + " lastCh:" + lastCh);
        /*
          c a generates ac ca for use below

          ca t generates tca cta cat for use below
          ac t generates tac atc act for use below

          These 6 permutations above are used with s to generate all permutations
          cat s generates scat csat cast cats
          atc s generates satc astc atsc atcs
          tac s generates stac tsac tasc tacs
          tca s generates stca tsca tcsa tcas
          act s generates sact asct acst acts
          cta s generates scta csta ctsa ctas
         */
        Set<String> permOfAllCharsExcLast = getPermutations(allCharsExcLast);//ca t
        char lastCh = theString.charAt(theString.length() - 1);//only last ch

        System.out.println("permOfAllCharsExcLast:"+permOfAllCharsExcLast+" lastCh:"+lastCh);//[c]   [ca ac]  [cat, atc, tac, tca, act, cta]
        //add lastChar to all permutations of allCharsExcLast
        Set<String> permutations = new HashSet<>();
        for (String permExcLast: permOfAllCharsExcLast) { //also LIFO
            // -> c a generates permutations permExcLast [ca,ac]  -> ca t generates tca,cta,cat,..., ac t generates tac,atc,act --> tca  s generates stca,tsca,tcsa,tcas  & so on
            System.out.println(" permExcLast:"+permExcLast + " lastCh:"+lastCh + " allCharsExcLast.length():"+allCharsExcLast.length());
            //add s (lastChar) to every position of cat (allCharsExcLast) i.e scat, csat, cast, cats
            for (int pos = 0; pos <= permExcLast.length(); pos++) { //<=
                String perm = permExcLast.substring(0, pos) + lastCh + permExcLast.substring(pos);//tricky bit to use permExcLast instead of allCharsExcLast
                System.out.println("perm:"+perm);
                permutations.add(perm);
            }
        }
        return permutations;
    }

    public static Set<String> getPermutations1(String inputString) {

        // base case
        if (inputString.length() <= 1) {
            return new HashSet<String>(Arrays.asList(inputString));
        }

        String allCharsExceptLast = inputString.substring(0, inputString.length() - 1);
        char lastChar = inputString.charAt(inputString.length() - 1);

        // recursive call: get all possible permutations for all chars except last
        Set<String> permutationsOfAllCharsExceptLast = getPermutations1(allCharsExceptLast);

        // put the last char in all possible positions for each of the above permutations
        Set<String> permutations = new HashSet<String>();
        for (String permutationOfAllCharsExceptLast : permutationsOfAllCharsExceptLast) {
            for (int position = 0; position <= allCharsExceptLast.length(); position++) {
                String permutation = permutationOfAllCharsExceptLast.substring(0, position) + lastChar
                        + permutationOfAllCharsExceptLast.substring(position);
                permutations.add(permutation);
            }
        }
        System.out.println("permutations:"+permutations);
        return permutations;
    }



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String testStr = scan.next();
        Set<String> strSets = getPermutations(testStr);
        System.out.println(strSets);
    }
}
