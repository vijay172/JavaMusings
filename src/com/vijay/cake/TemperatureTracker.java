package com.vijay.cake;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by vkbalakr on 6/27/17.
 */
public class TemperatureTracker {

    private int maxTemp;
    private int minTemp;
    private int countTemps;
    private int totalTemp;
    private double mean;

    private int mode;
    private int[] occurrences = new int[111];//0..110
    private int maxOccurrence;

    private Map<Integer, Integer> tempMap = new HashMap<>();
    private Set<Integer> modeTemps = new HashSet<>();

    public class ModeTemperature {
        private int temp;
        private int count;
        public ModeTemperature(int temp, int count) {
            this.temp = temp;
            this.count = count;
        }

    }

    public void insert(Integer temp) {
        maxTemp = Math.max(maxTemp, temp);
        minTemp = Math.min(minTemp, temp);
        totalTemp += temp;
        countTemps++;
        mean = (double) totalTemp / countTemps;

        occurrences[temp]++;
        if (occurrences[temp] > maxOccurrence) {
            maxOccurrence = occurrences[temp];
            mode = temp;
        }

        /*Integer tempCount = tempMap.get(temp);
        if (tempCount == null)  {
            tempMap.put(temp, 1);
        } else {
            tempMap.put(temp, tempCount + 1);
        }
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry: tempMap.entrySet()) {
            maxCount = Math.max(maxCount, entry.getValue());
        }
        //get temps for maxCount from tempMap
        for (Map.Entry<Integer, Integer> entry : tempMap.entrySet()) {
            if (entry.getValue()== maxCount) {
                //modeTemps.add(new ModeTemperature(entry.getKey(), maxCount));
                modeTemps.add(entry.getKey());
            }
        }*/
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMinTemp()
    {
        return minTemp;
    }

    public double getMean() {
        return mean;

    }

    public Integer getMode() {
        return mode;
    }

    public static void main(String[] args) {

    }
}
