package com.vijay.cake;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by vkbalakr on 6/27/17.
 */
public class MedianHeapTempTracker {

    //2. order reqmt
    //max heap has lower half of elements in desc order
    private Queue<Integer> low = new PriorityQueue<Integer>(Comparator.reverseOrder());//desc - pull max from top
    //minheap has larger half of elements
    private Queue<Integer> hi = new PriorityQueue<>();//asc  - pull min from top

    //Insert - O(log N),
    public void add(Integer temp) {

        //1. size reqmt
        //where to add
        Queue<Integer> target = low.size() <= hi.size() ? low : hi;
        target.add(temp);
        balance();
    }

    //switch top of max heap and min heap
    private void balance() {

        Integer lowTopMax = low.poll();
        Integer hiTopMin = hi.poll();
        while (!low.isEmpty() && !hi.isEmpty() && lowTopMax > hiTopMin) {
            //switch
            low.add(hiTopMin);
            hi.add(lowTopMax);
        }

    }

    //Median - O(1) from root of heaps
    public double median() {
        if (low.isEmpty() && hi.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return low.size() == hi.size() ? (low.peek() + hi.peek())/ 2 : low.peek();
    }


    public static void main(String[] args) {

    }
}
