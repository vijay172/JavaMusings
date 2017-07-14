package com.vijay.cake;

import java.util.HashMap;

/**
 * Created by vkbalakr on 6/20/17.
 */
public class KthNodeFromLastLinkedList {

    public static class Node {
        private String value;
        private Node next;
        public Node(String value) {
            this.value = value;
        }
    }

    public static Node kthToLastNode(int kthToLast, Node head) {
        HashMap<Integer, Node> mapIntToNode = new HashMap<>();
        Node curr = head;
        int i=0;
        while (curr != null) {
            mapIntToNode.put(i++, curr);

            curr = curr.next;
        }
        //constraint check
        int idx = i - kthToLast ;//n - k +1
        return mapIntToNode.get(idx); //stores from 0
    }

    public static Node kthTolastNodeStick(int kthToLast, Node head) {

        Node leftNode = head;
        Node rightNode = head;

        for (int i=0; i <kthToLast -1; i++) {

            if (rightNode.next == null) {
                throw new IllegalArgumentException("k is > length of list");
            }
            rightNode = rightNode.next;
        }

        //move lt and rt nodes until rightNode is at end
        while (rightNode.next != null) {
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }
        return leftNode;
    }


    public static void main(String[] args) {

        Node a1 = new Node("Angels Food");
        Node b2 = new Node("Bundt");
        Node c3 = new Node("Cheese");
        Node d4 = new Node("Indian Food");
        Node e5 = new Node("Chinese Food");

        a1.next = b2;
        b2.next = c3;
        c3.next = d4;
        d4.next = e5;
        KthNodeFromLastLinkedList ll = new KthNodeFromLastLinkedList();
        Node result = ll.kthToLastNode(2, a1);
        System.out.println("result:"+result.value);
    }
}
