package com.vijay.cake;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by vkbalakr on 6/20/17.
 */
public class MatchParenethesisStack {

    public static boolean matchParenthesis(String test) {

        Set<String> set = new HashSet<>();
        set.add("()");
        set.add("{}");
        set.add("[]");
        Stack<Character> stack = new Stack<>();
        for (char ch: test.toCharArray()) {
            if (ch == '{' || ch == '(' || ch == '[') {
                stack.push(ch);
            } else if (ch == '}' || ch == ')' || ch == ']') {
                //stack cud be empty here
                if (stack.isEmpty()) {
                    return false;
                }
                String combinedStr = "" + stack.peek() + ch;
                if (set.contains(combinedStr)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean matchParenthesisMap(String test) {
        //use Map instead of set
        Map<Character,Character> openersToClosers = new HashMap<>();
        openersToClosers.put('{','}');
        openersToClosers.put('[',']');
        openersToClosers.put('(',')');

        Set<Character> openers = openersToClosers.keySet();
        Set<Character> closers = new HashSet<Character>(openersToClosers.values());
        Stack<Character> stack = new Stack<>();
        for (char ch: test.toCharArray()) {
            if (openers.contains(ch)) {
                stack.push(ch);
            } else if (closers.contains(ch)) {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character opener = stack.pop();
                    if (openersToClosers.get(opener) != ch) {//match ch
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }


        public static void main(String[] args) {
        String test1 = "{[]()}";
        String test2 = "{ [ ( ] ) }";
        String test3 = "{ [ }";
        String test4 = "{ [ ( ] ) }";
        System.out.println(matchParenthesisMap(test1));
        System.out.println(matchParenthesisMap(test2));
        System.out.println(matchParenthesisMap(test3));
        System.out.println(matchParenthesisMap(test4));
    }
}
