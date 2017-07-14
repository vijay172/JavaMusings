package com.vijay;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by vkbalakr on 7/9/17.
 */
public class MaxHeapWithQueue {

    PriorityQueue<Integer> low = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> hi = new PriorityQueue<>();

    public void add(int nbr) {
        //where to add
        Queue<Integer> target = low.size() <= hi.size() ? low : hi;
        target.add(nbr);
        balance();
    }

    private void balance() {
        while (!low.isEmpty() && !hi.isEmpty() && low.peek() > hi.peek()) {
            int lowMax  = low.poll();
            int hiMin = hi.poll();
            low.add(hiMin);
            hi.add(lowMax);
        }
    }

    public int getMin() throws Exception{
        int lowMin=0;
        if (!low.isEmpty()) {
            lowMin = low.poll();
            balance();
        } else {
            throw new Exception("Empty heap");
        }
        return lowMin;
    }

    public int getMax() throws Exception {
        int hiMax = 0;
        if (!hi.isEmpty()) {
            hiMax = hi.poll();
            balance();
        } else {
            throw new Exception("Empty heap");
        }
        return hiMax;

    }

    public static void main(String[] args) throws Exception{
        MaxHeapWithQueue q = new MaxHeapWithQueue();
        q.add(12);
        q.add(4);
        q.add(5);
        q.add(3);
        q.add(8);
        q.add(7);
        System.out.println(q.getMax());
        System.out.println(q.getMax());
        System.out.println(q.getMax());
    }
}
