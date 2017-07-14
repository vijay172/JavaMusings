package com.vijay;

/**
 * Created by vkbalakr on 6/24/17.
 */
public class ReplaceSpacesWithURLEncode {

    public static void main(String[] args) {

      String input = "     Mr John Smith    ";
      //String test = input.trim();//TODO:
      int length = input.length();
      char[] trimArr = input.toCharArray();
      int st = 0;
      while (st < length && trimArr[st] <= ' ') {
          st++;
      }
      while (trimArr[length-1] <= ' ') {
          length--;
      }
      String trimmedStr = input.substring(st,length);

      char[] inputCharArr = trimmedStr.toCharArray();
      StringBuilder sb = new StringBuilder();
      for (char ch : inputCharArr) {
          if (ch == ' ') {
              sb.append("%20");
          } else {
              sb.append(ch);
          }
      }
        System.out.println(sb.toString());
    }
}
