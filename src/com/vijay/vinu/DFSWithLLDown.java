package com.vijay.vinu;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by vkbalakr on 7/1/17.
 */
public class DFSWithLLDown {
    public static class Node {
        private int value;
        private Node next;
        private Node down;
        Node(int value) {
            this.value = value;
        }
    }

    public static void traverseLL(Node head) {
        Node root = head;
        Stack<Node> stackOfNbrs = new Stack<>();
        Set<Node> visited = new HashSet<>();
        stackOfNbrs.push(root);
        while (!stackOfNbrs.isEmpty()) {
            Node curr = stackOfNbrs.pop();
            if (visited.contains(curr)) {
                continue;
            } else {
                visited.add(curr);
            }
            System.out.print(curr.value + " ");
            if (curr.next != null) {
                stackOfNbrs.push(curr.next);
            }
            if (curr.down != null) {
                stackOfNbrs.push(curr.down);
            }
        }
    }
    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        one.next = two;
        two.next = three;
        three.next = four;
        two.down = five;
        five.next = six;
        five.down = seven;
        seven.next = eight;
        traverseLL(one);
    }
}
