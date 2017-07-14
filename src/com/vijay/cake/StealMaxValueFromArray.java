package com.vijay.cake;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by vkbalakr on 6/29/17.
 */
public class StealMaxValueFromArray {

    static class CakeType {
        int weight;
        int value;
        public CakeType(int weight, int value) {
            this.weight = weight;
            this.value  = value;
        }
    }
    public static int maxDuffelBagValue (CakeType[] cakeTypes, int capacity) {
        Arrays.sort(cakeTypes, new Comparator<CakeType>() {
            public int compare(CakeType ct1, CakeType ct2) {
                if (ct2.weight > 0 && ct1.weight > 0) {
                    return (ct2.value/ct2.weight) - (ct1.value/ct1.weight);
                } else {
                    return 0;
                }
            }
        });
        int maxTotalValue = 0;
        for (int cakeTypeIdx = 0; cakeTypeIdx < cakeTypes.length; cakeTypeIdx++) {
            int maxNbrToLoot = 0;
            if (cakeTypes[cakeTypeIdx].weight > 0) {
                maxNbrToLoot = capacity / cakeTypes[cakeTypeIdx].weight;
                capacity = capacity % cakeTypes[cakeTypeIdx].weight;
            } else if (cakeTypes[cakeTypeIdx].weight == 0) {
                maxNbrToLoot = capacity;
            }
            if (maxNbrToLoot > 0 && cakeTypes[cakeTypeIdx].value > 0) {
                maxTotalValue += maxNbrToLoot * cakeTypes[cakeTypeIdx].value;
            }
        }
        return maxTotalValue;
    }

    public static long maxDuffelBagValueBottomUp(CakeType[] cakeTypes, int weightCapacity) {
        //want to go thru all the weights from 0 on to weightCapacity
        long[] maxValuesForEachCapacity = new long[weightCapacity + 1];//start with 0

        for (int currentCapacity = 0; currentCapacity <= weightCapacity; currentCapacity++) {

            long currMaxValue = 0L;
            //go thru each cake to figure out if it fits in the currCapacity upto max
            for (CakeType cakeType: cakeTypes) {
                if (cakeType.weight <= currentCapacity) {
                    int remainderCapacity = currentCapacity - cakeType.weight;
                    //insert cake in and find value for remainderCapacity whic ahs already been calculated before as it is < cake weight
                    long maxValueForWeight = cakeType.value + maxValuesForEachCapacity[remainderCapacity];
                    currMaxValue = Math.max(maxValueForWeight, currMaxValue);
                }
            }
            maxValuesForEachCapacity[currentCapacity] = currMaxValue;//gone thru all cake types for currentCapacity
        }
        return maxValuesForEachCapacity[weightCapacity];//at the end
    }

    static class InfinityException extends RuntimeException {
        public InfinityException() {
            super("Max value is infinity!");
        }
    }

    public static long maxDuffelBagValueWithExplanation(CakeType[] cakeTypes, int weightCapacity) {

        // we make an array to hold the maximum possible value at every
        // duffel bag weight capacity from 0 to weightCapacity
        // starting each index with value 0
        long[] maxValuesAtCapacities = new long[weightCapacity + 1];

        for (int currentCapacity = 0; currentCapacity <= weightCapacity; currentCapacity++) {

            // set a variable to hold the max monetary value so far for currentCapacity
            long currentMaxValue = 0;

            for (CakeType cakeType : cakeTypes) {

                // if a cake weighs 0 and has a positive value the value of our duffel bag is infinite!
                if (cakeType.weight == 0 && cakeType.value != 0) {
                    throw new InfinityException();
                }

                // if the current cake weighs as much or less than the current weight capacity
                // it's possible taking the cake would give get a better value
                if (cakeType.weight <= currentCapacity) {

                    // so we check: should we use the cake or not?
                    // if we use the cake, the most kilograms we can include in addition to the cake
                    // we're adding is the current capacity minus the cake's weight. we find the max
                    // value at that integer capacity in our array maxValuesAtCapacities
                    long maxValueUsingCake = cakeType.value + maxValuesAtCapacities[currentCapacity - cakeType.weight];

                    // now we see if it's worth taking the cake. how does the
                    // value with the cake compare to the currentMaxValue?
                    currentMaxValue = Math.max(maxValueUsingCake, currentMaxValue);
                }
            }

            // add each capacity's max value to our array so we can use them
            // when calculating all the remaining capacities
            maxValuesAtCapacities[currentCapacity] = currentMaxValue;
        }

        return maxValuesAtCapacities[weightCapacity];
    }

    public static long product1ToN(int n) {

        long result = 1;

        for(int i=1; i <= n; i++) {
            result *= i;
        }
        return result;
    }


    public static long highestValueToStealBottomUp(CakeType[] cakeTypes, int capacity) {
        long[] maxValue = new long[capacity + 1];   //0..capacity
        maxValue[0] = 0; //base case

        for (CakeType cakeType: cakeTypes) {
            long currMaxValue = 0;//for each cakeType
            if (cakeType.weight == 0 && cakeType.value != 0) {
                throw new InfinityException();
            }

            for (int higherCapacity = cakeType.weight; higherCapacity <= capacity; higherCapacity++) {
               int higherCapacityRem = higherCapacity - cakeType.weight;
               currMaxValue = cakeType.value + maxValue[higherCapacityRem];
               maxValue[higherCapacity] = Math.max(maxValue[higherCapacity], currMaxValue);

            }
        }
        return maxValue[capacity];
    }
    public static void main(String[] args) {
        CakeType[] cakeTypes = new CakeType[] {
                new CakeType(7, 160),
                new CakeType(3, 90),
                new CakeType(2, 15),
        };

        int capacity = 20;
        //System.out.println(maxDuffelBagValue(cakeTypes, capacity));
        //System.out.println(maxDuffelBagValueBottomUp(cakeTypes, capacity));
        System.out.println(highestValueToStealBottomUp(cakeTypes, capacity));
        //System.out.println(product1ToN(5));
    }
}
