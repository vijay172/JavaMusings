package com.vijay;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by vkbalakr on 6/17/17.
 */
public class QueueWith2Stacks {

    public static class MyQueue<T> {
        Stack<T> stackNewestOnTop = new Stack<T>();
        Stack<T> stackOldestOnTop = new Stack<T>();

        public int length() {
            return stackOldestOnTop.size();
        }

        public void enqueue(T value) { // Push onto newest stack
            //if (stackNewestOnTop.isEmpty()) {
            stackNewestOnTop.push(value);
        /*
        } else {
                while (!stackNewestOnTop.isEmpty()) {
                    stackOldestOnTop.push(stackNewestOnTop.pop());//dump older items to old stack
                }
                stackNewestOnTop.push(value);
            }*/
        }

        public T peek() {
            if (stackOldestOnTop.isEmpty()) {
                while (!stackNewestOnTop.isEmpty()) {
                    stackOldestOnTop.push(stackNewestOnTop.pop());//so newest will be at bottom
                }
            }
            return stackOldestOnTop.peek();
        }

        public T dequeue() {
            if (stackOldestOnTop.isEmpty()) {
                while (!stackNewestOnTop.isEmpty()) {
                    stackOldestOnTop.push(stackNewestOnTop.pop());
                }
            }
            return stackOldestOnTop.pop();
            /*
            while (!stackOldestOnTop.isEmpty()) {
                stackNewestOnTop.push(stackOldestOnTop.pop());
            }
            return stackNewestOnTop.pop();
            */
        }
    }

/* Input
10
1 42
2
1 14
3
1 28
3
1 60
1 78
2
2
Output:
14
14
 */

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        System.out.println("queue length:" + queue.length());
        scan.close();
    }

}
