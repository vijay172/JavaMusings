package com.vijay.cake;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vkbalakr on 6/20/17.
 */
public class LinkedListCycleDetection {

    public static class Node {
        private int value;
        private Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    public boolean detectCycle(Node root) {
        Node curr = root;
        Node head = root;
        boolean cycleFound = false;
        Set<Node> visitedNodes = new HashSet<>();
        while (curr.next != null && curr.next != head) {
            Node nxtNode = curr.next;
            if (nxtNode.next == curr || (visitedNodes.contains(nxtNode.next) && nxtNode.next != head)) {
            //if ( (nxtNode.next != head && visitedNodes.contains(nxtNode.next) )) {
                cycleFound = true;
                break;
            } else {
                visitedNodes.add(curr);
                curr = nxtNode;
            }
        }
        return cycleFound;
    }

    public boolean detectCycle1(Node root) {

        //slow fast runners
        Node slow = root;
        Node fast = root;
        //until end of list
        while (fast != null & fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {//fast catches up with slow or laps slow
                return true;
            }
        }
        return false;//fast hits end of list

    }

    public static Node find1stNodeInaCycle(Node root, int length) {
        //get inside a cycle by walking length of linked list.1 more step should take us to head inside the cycle.
        Node curr = root;
        for(int i=0; i < length; i++) {
            curr = curr.next;
        }
        Node positionIncycle = curr;

        //find cycle's length by remembering a position incide a cycle and walking till we hit it again.- gets us stick length
        Node rememberedPositionInCycle = positionIncycle;
        Node currPositionInCycle = curr.next;//walked 1 step to get to head again
        int cycleStepCnt = 1;
        while (rememberedPositionInCycle != currPositionInCycle) {
            currPositionInCycle = currPositionInCycle.next;
            cycleStepCnt += 1;
        }

        //find 1st node in cycle by walking the stick starting from 2 pointers - head and head + stickLen(cycle length)
        //until head and head + stickLen meet which they do at the beginning of the cycle.
        Node head = root;
        Node stickHead = root;
        for (int i=0; i < cycleStepCnt; i++) {
            stickHead = stickHead.next;
        }

        while (head != stickHead) {
            head = head.next;
            stickHead = stickHead.next;
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedListCycleDetection ll = new LinkedListCycleDetection();
        Node a1 = new Node(1);
        Node b2 = new Node(2);
        Node c3 = new Node(3);
        Node d3 = new Node(4);

        a1.next = b2;
        b2.next = c3;
        c3.next = d3;
        d3.next = b2;
        //System.out.println("Cycle detected:"+ll.detectCycle1(a1));
        System.out.println("1st node in Cycle:"+ll.find1stNodeInaCycle(a1, 4).value);//how to find length of LL ??
    }
}
