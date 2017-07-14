package com.vijay.cake;

/**
 * Created by vkbalakr on 6/20/17.
 */
public class ReverseWords {


    public static String reverseWords(String test) {

        String[] testStrArr = test.split(" ");
        int startIdx = 0;
        int endIdx = testStrArr.length - 1;

        while (startIdx < endIdx) {
            String tmp = testStrArr[startIdx];
            testStrArr[startIdx] = testStrArr[endIdx];
            testStrArr[endIdx] = tmp;

            startIdx++;
            endIdx--;
        }
        StringBuilder sb = new StringBuilder();
        for (String st : testStrArr) {
            sb.append(st).append(" ");
        }
        return sb.toString().trim();
    }

    public static String reverseSentence(String message) {
        //reverse whole message

        char[] messageChars = message.toCharArray();
        int startIdx = 0;
        int endIdx = messageChars.length-1;
        reverseChars(messageChars, startIdx, endIdx);
        int currWordStartIdx = 0;
        //reverse each word in message on finding space delimiter
        for (int i =0; i <= messageChars.length; i++) {

            //find delimiter
            if (i == messageChars.length || messageChars[i] == ' ' ) {//this order matters
                //reverse word found
                reverseChars(messageChars, currWordStartIdx, i-1);
                currWordStartIdx = i +1;
            }

        }
        return new String(messageChars);

    }

    public static void reverseChars(char[] messageCharsArr, int startIdx, int endIdx) {

        while (startIdx < endIdx) {
            //swap
            char tmp = messageCharsArr[startIdx];
            messageCharsArr[startIdx] = messageCharsArr[endIdx];
            messageCharsArr[endIdx] = tmp;
            startIdx++;
            endIdx--;
        }

    }

    public static void main(String[] args) {
        //String message = "find you will pain only go you recordings security the into if";
        String message = "  degree  cats ";

        System.out.println(reverseWords(message));
        //System.out.println(reverseSentence(message));
    }
}
