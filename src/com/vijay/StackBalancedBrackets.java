package com.vijay;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * Created by vkbalakr on 6/16/17.
 * Sample input:
 3
 {[()]}
 {[(])}
 {{[[(())]]}}
 *
 */
public class StackBalancedBrackets {
    public static boolean isBalanced(String expression) {
        if (expression == null || (expression != null && expression.length() == 0)) {
            return false;
        }
        Set<String> set = new HashSet<>();
        set.add("[]");
        set.add("{}");
        set.add("()");
        Stack<Character> stack = new Stack<>();
        char[] exprArr = expression.toCharArray();
        for (char currch : exprArr) {
            if (!stack.isEmpty()) {
                Character prevch = stack.peek();
                String combinedStr = "" + prevch + currch;
                if (set.contains(combinedStr)) {
                    stack.pop();
                } else {
                    stack.push(currch);
                }
            } else {
                stack.push(currch);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
        }
    }
}
