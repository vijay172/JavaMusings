package com.vijay.cake;

import java.util.Stack;

/**
 * Created by vkbalakr on 6/19/17.
 */
public class MaxStack {
    private Stack<Integer> normalStack = new Stack<>();
    private Stack<Integer> maxStack = new Stack<>();

    public int push(Integer i) {
        normalStack.push(i);
        if (maxStack.isEmpty() || i >= maxStack.peek()) {//why =  for popping
            maxStack.push(i);
        }
        return i;
    }

    public int pop() {
        int item = normalStack.pop();
        if (item == maxStack.peek()) {
            maxStack.pop();
        }
        return item;
    }

    public int getMax() {
        return maxStack.peek();
    }

    public static void main(String[] args) {
        MaxStack max = new MaxStack();
        /*max.push(1);
        max.push(2);
        max.push(3);
        max.push(4);
        System.out.println(max.getMax());*/
        for (int i=1;i < 5; i++) {
            max.push(i);
            System.out.println(max.getMax());
        }
    }


}
