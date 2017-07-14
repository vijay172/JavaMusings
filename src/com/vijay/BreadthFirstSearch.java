package com.vijay;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by vkbalakr on 7/2/17.
 */
public class BreadthFirstSearch {
    static LinkedList<Node> queue = new LinkedList<>();//PriorityQueue doesn't work as it orders 2 before 7
    static HashSet<Node> visited = new HashSet<>();

    static class Node implements Comparable {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
        public int compareTo(Object o1) {
            Node n1 = (Node)o1;
            return this.value - n1.value;
        }

    }

    public static boolean bfsSearch(Node head, int nbr) {
        Node curr = head;
        //For LL to act as Queue FIFO, addLast and removeFirst
        queue.addLast(curr);
        while (!queue.isEmpty()) {
            Node currNode = queue.removeFirst();
            if (currNode.value == nbr) {
                return true;
            }
            System.out.println(currNode.value + " ");
            visited.add(currNode);
            if (currNode.left != null) {
                if (!visited.contains(currNode.left)) {
                    queue.addLast(currNode.left);
                }
            }
            if (currNode.right != null) {
                if (!visited.contains(currNode.right)) {
                    queue.addLast(currNode.right);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] intArr = {1,2,3,4,5,6,7,9,11};
        Node node5 = new Node(5);
        Node node2 = new Node(2);
        Node node7 = new Node(7);
        Node node1 = new Node(1);
        Node node3 = new Node(3);
        Node node6 = new Node(6);
        Node node8 = new Node(8);
        node5.left = node2;
        node5.right = node7;
        node2.left = node1;
        node2.right = node3;
        node7.left = node6;
        node7.right = node8;
        System.out.println(bfsSearch(node5, 8));
    }
}
