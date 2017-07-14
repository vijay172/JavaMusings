package com.vijay.cake;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by vkbalakr on 6/20/17.
 */
public class MatchParenthesis {

    public static int matchParenthesis(String test, int begin) {

        Set<String> parenthesis = new HashSet<>();
        parenthesis.add("()");
        parenthesis.add("{}");
        parenthesis.add("[]");
        char[] testArr = test.toCharArray();
        Stack<Store> stack = new Stack<>();
        for(int i=begin; i < testArr.length; i++) {
            char ch = testArr[i];
            if (ch == '(' || ch == ')' || ch == '{' || ch == '}') { //TODO:ugly
                if (ch == ')' || ch == '}') { //look in stack for matching parentheses/tuple
                    if (!stack.isEmpty()) {
                        String combinedStr = "" + stack.peek().ch + ch;
                        if (parenthesis.contains(combinedStr)) {
                            if (stack.peek().pos != begin) {
                                stack.pop();
                            } else {
                                //match found
                                return i;
                            }
                        }
                    }
                } else if (ch == '(' || ch == '{') {
                    stack.push(new Store(ch, i));
                }
            }
        }
        return 0;
    }

    public static int matchPSimple(String sentence, int begin) {
        int openParenCnt = 0;
        for (int i=begin; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (c == '(') {
                openParenCnt++;
            } else if (c == ')') {
                openParenCnt--;
            }
            if (openParenCnt == 0) {
                return i;
            }
        }
        throw new IllegalArgumentException("No matching parenthesis");
    }


    //public static Set<Character> openers = new HashSet<>(){'('};
    //public static Set<Character> closers = new HashSet<>(')');
    public static int matchParen1(String sentence, int begin) {
        Map<Character,Character> openersToClosers = new HashMap<>();
        openersToClosers.put('{','}');
        openersToClosers.put('[',']');
        openersToClosers.put('(',')');
        Set<Character> openers = openersToClosers.keySet();
        Set<Character> closers = new HashSet<>(openersToClosers.values());
        int lastMatchedCharIdx = begin;
        Stack<Character> stackChars = new Stack<>();
        char[] sentenceCharArr = sentence.toCharArray();
        for (int charIdx = begin; charIdx < sentenceCharArr.length; charIdx++) {
            char curCh = sentenceCharArr[charIdx];
            //System.out.println(String.format(" start curCh:%s charIdx:%d",curCh,charIdx));
            if (openers.contains(curCh)) {
                //System.out.println("opener curCh charIdx:"+charIdx);
                stackChars.push(curCh);
            } else if (closers.contains(curCh)) {
                if (stackChars.isEmpty()) {
                    //System.out.println("Stack is empty");
                    return 0;
                }
                char peekedCh = stackChars.peek();
                //System.out.println("peekedCh:"+peekedCh);
                if (openers.contains(peekedCh)) {
                    if (openersToClosers.get(peekedCh) == curCh) {
                        stackChars.pop();//match found
                        lastMatchedCharIdx = charIdx;
                        System.out.println("lastMatchedCharIdx:"+lastMatchedCharIdx);
                    }
                } else {
                    //System.out.println(String.format("curCh:%s, charIdx:%d",curCh, charIdx));
                    return 0;
                }
            }
        }
        return lastMatchedCharIdx;
    }

    public static class Store {
        public int pos;//TODO:getters
        public char ch;
        public Store(char ch, int pos) {
            this.ch = ch;
            this.pos = pos;
        }
    }
    public static void main(String[] args) {
        String test = "Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get confusing.";

        //System.out.println(matchParenthesis(test, 10));
        System.out.println(matchParen1(test, 10));
        //System.out.println(matchPSimple(test, 10));

    }
}
