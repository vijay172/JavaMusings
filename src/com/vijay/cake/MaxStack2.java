package com.vijay.cake;

import java.util.Stack;

/**
 * Created by vkbalakr on 6/30/17.
 */
public class MaxStack2 {
    public static class MaxStack extends Stack {

        private Integer maxValue;

        MaxStack(Integer maxValue) {
            this.maxValue = maxValue;
        }

        public Integer push(Integer nbr) {
            super.push(nbr);

            this.maxValue = Math.max(((Integer)this.maxValue).intValue(), ((Integer)nbr));
            return nbr;
        }
        public Object peek() {
            return super.peek();
        }
        public Integer pop() {
            super.pop();
            this.maxValue = Math.max(maxValue.intValue(), ((Integer)super.peek()));
            return this.maxValue;
        }
        public Integer getMax() {
            return maxValue;
        }
    }

    public static void main(String[] args) {
        MaxStack2.MaxStack maxStack = new MaxStack2.MaxStack(Integer.MIN_VALUE);
        maxStack.push(12);
        maxStack.push(3);
        maxStack.push(7);
        maxStack.push(6);
        maxStack.push(9);

        System.out.println(maxStack.getMax());
    }
}
