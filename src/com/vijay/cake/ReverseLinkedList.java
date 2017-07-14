package com.vijay.cake;

import java.util.Stack;

/**
 * Created by vkbalakr on 6/20/17.
 */
public class ReverseLinkedList {
    public static class Node {
        private int value;
        private Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    public Node reverseList(Node head) {
        Node curr = head;
        Node prev = null;

        while (curr != null) {

            Node nxt = curr.next;//tmp var
            curr.next = prev; //null initially

            //move fwd
            prev = curr;
            curr = nxt;

        }
        return prev;
    }

    public Node revListOutOfPlace(Node head) {
        Node current = head;
        Stack<Node> stack = new Stack();
        while(current != null) {
            stack.push(current);
            current = current.next;
        }
        System.out.println("populated stack");
        Node curr = null;
        Node prev = null;
        Node revHead = stack.peek();
        while (!stack.isEmpty()) {
            curr = stack.pop();//3  2  1
            if (!stack.isEmpty()) {
                prev = stack.peek();//2 1  null/nil
            } else {
                prev = null;
            }
            curr.next = prev;//3 -> 2 -> 1 -> null
        }
        return revHead;
    }

    public static void main(String[] args) {
        ReverseLinkedList rll = new ReverseLinkedList();
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        a.next = b;
        b.next = c;
        c.next = null;

        Node head = rll.revListOutOfPlace(a);
        System.out.println(head.value);
    }
}
