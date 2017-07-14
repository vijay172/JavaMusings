package com.vijay;

import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by vkbalakr on 6/18/17.
 */
public class MaxStack extends Stack{

    TreeSet<Integer> tree = new TreeSet<>();

    public void push(Integer i) {
        super.push(i);
        tree.add(i);

    }

    public Integer getMax() {
        return tree.pollLast();
    }

    public static void main(String[] args) {
        //Stack<Integer> stack = new Stack<>();

        MaxStack stack = new MaxStack();
        /*stack.push(new Integer(3));
        stack.push(5);
        stack.push(2);
        System.out.println(stack.getMax());*/

        for (int i=1;i < 5; i++) {
            stack.push(i);
            System.out.println(stack.getMax());
        }
        System.out.println(stack.size());
    }
}
