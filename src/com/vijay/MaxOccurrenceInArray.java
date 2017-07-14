package com.vijay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 * Created by vkbalakr on 7/6/17.
 */
public class MaxOccurrenceInArray {
    static class Tuple {
        int key;
        int occurrence;
        Tuple(int key, int occurrence) {
            this.key = key;
            this.occurrence = occurrence;
        }
        public String toString() {
            return "key=" + key + " occurrence=" + occurrence;
        }
    }

    public static List<Integer> getMaxNbrs(int[] inputArr, int k) {
        HashMap<Integer, Tuple> mapOfNbrs = new HashMap<>();
        for (int i=0; i < inputArr.length; i++) {
            int nbr = inputArr[i];
            Tuple tupleNbrKey = mapOfNbrs.get(nbr);
            if (tupleNbrKey == null) {
                mapOfNbrs.put(nbr, new Tuple(nbr,1));
            } else {
                mapOfNbrs.put(nbr, new Tuple(nbr,tupleNbrKey.occurrence + 1));
            }
        }
        //sort the Tuple based on occurrence
        Collection<Tuple> tuples = mapOfNbrs.values();
        System.out.println(tuples);
        ArrayList tupleLst = new ArrayList(tuples);//chg to ArrayList to sort for values
        Collections.sort(tupleLst, new Comparator<Tuple>() {
            public int compare(Tuple t1, Tuple t2) {
                if (t1.occurrence == t2.occurrence) {
                    return t2.key - t1.key;
                }
                return t2.occurrence - t1.occurrence;
            }
        });
        System.out.println("tupleLst:"+ Arrays.toString(tupleLst.toArray()));//chg to Arrays from List to print
        LinkedList<Integer> intSet = new LinkedList<>();//chg to LL to print in order
        Iterator<Tuple> iter = tupleLst.iterator();
        int occ = 0;
        while (iter.hasNext()) {
            Tuple tup = (Tuple)iter.next();
            intSet.add(tup.key);
            if (occ++ > k) {
                break;
            } else {
                occ++;
            }
        }
        return intSet;

    }

    public static void main(String[] args) {
        // run your function through some test cases here
        // remember: debugging is half the battle!
        int[] testInput = {3, 1, 4, 4, 5, 2, 6, 1};
        System.out.println(getMaxNbrs(testInput, 2));
    }
}
