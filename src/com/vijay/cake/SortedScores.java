package com.vijay.cake;

/**
 * Created by vkbalakr on 6/21/17.
 */
public class SortedScores {

    public static int[] sortScores(int[] unsortedScores, int highestScore) {
        //store scores as idx with count as value in new array
        int[] scoresCount = new int[highestScore + 1];//need to store 0 too

        for (int score:unsortedScores) {
            scoresCount[score]++;//only scores values populated
        }
        //adds more space to save on time
        int[] sortedArr = new int[unsortedScores.length];
        int currSortedIdx = 0;
        //loop thru scoresCount from highest to lowest
        for (int score = highestScore; score >= 0; score--) { //starting with highestScore lets it be sorted desc
            int count = scoresCount[score];
            //System.out.println(" score:"+score+" count:"+count);
            //populate score count times in new sortedArr- zeros will not be populated here.
            for (int i=0; i < count; i++) {//i < count is critical
                System.out.println(" score:"+score+" count:"+count);
                sortedArr[currSortedIdx++] = score;
            }
        }
        return sortedArr;

    }

    public static void main(String[] args) {

        int[] scores = {37, 89, 41, 65, 91, 53, 65};
        final int HIGHEST_POSSIBLE_SCORE = 100;

        int[] sortedScores = sortScores(scores, HIGHEST_POSSIBLE_SCORE);
        System.out.println(sortedScores.length);
    }
}
