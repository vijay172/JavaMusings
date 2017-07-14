package com.vijay;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vkbalakr on 7/6/17.
 */
public class CloneLinkedList {
    public static void main(String[] args) {
        // Pushing data in the linked list.
        LinkedList1 list = new LinkedList1(new Node(5));
        list.push(4);
        list.push(3);
        list.push(2);
        list.push(1);

        // Setting up random references.
        list.head.random = list.head.next.next;
        list.head.next.random =
                list.head.next.next.next;
        list.head.next.next.random =
                list.head.next.next.next.next;
        list.head.next.next.next.random =
                list.head.next.next.next.next.next;
        list.head.next.next.next.next.random =
                list.head.next;

        // Making a clone of the original linked list.
        LinkedList1 clone = list.clone();

        // Print the original and cloned linked list.
        System.out.println("Original linked list");
        list.print();
        System.out.println("\nCloned linked list");
        clone.print();
    }
}

class Node {
    int value;
    Node next, random = null;

    Node(int value) {
        this.value = value;
    }
}

class LinkedList1 {

    Node head;

    public LinkedList1(Node head) {
        this.head = head;
    }

    public void push(int data) {

        Node node = new Node(data);
        node.next = head;
        this.head = node;
    }

    void print() {
        Node temp = head;

        while (temp != null) {
            Node random = temp.random;
            System.out.println("value:" + temp.value + " random:" + random.value);
            temp = temp.next;
        }
    }

    public LinkedList1 clone() {
        Node origCurr = this.head;
        Node clonedCurr = null;

        Map<Node, Node> map = new HashMap<>();
        //copy original data to cloned and store in Map
        while (origCurr != null) {
            clonedCurr = new Node(origCurr.value);
            map.put(origCurr, clonedCurr);
            origCurr = origCurr.next;
        }

        origCurr = this.head;
        while (origCurr != null) {
            clonedCurr = map.get(origCurr);
            clonedCurr.random = map.get(origCurr.random);//tricky
            clonedCurr.next = map.get(origCurr.next);//tricky
            origCurr = origCurr.next;
        }
        return new LinkedList1(map.get(this.head));
    }

    public LinkedList1 cloneO1Space()
    {
       //next copy adj
        Node curr = head;
        Node temp,copy = null;
        //insert copy in between nodes
        while (curr != null) {
            temp = curr.next;//2
            curr.next = new Node(curr.value);//copy of 1
            curr.next.next = temp;//point to 2
            curr = temp;//2
        }
        //clone randoms
        curr = head;
        while (curr != null) {
            curr.next.random = curr.random.next;//copy of random is in random's next
            curr = curr.next != null ? curr.next.next : curr.next;
        }
        curr = head;
        copy = curr.next;
        temp = copy;
        //chg next pointers to every alternate node
        while (curr != null && copy != null) {
            curr.next = curr.next != null ? curr.next.next : curr.next;
            copy.next = copy.next != null ? copy.next.next : copy.next;
            curr = curr.next;
            copy = copy.next;
        }
        return new LinkedList1(temp);
    }
}



