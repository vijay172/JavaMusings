package com.vijay;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by vkbalakr on 6/26/17.
 */
public class DepthFirstSearch {

    public static class Node {
        private int id;
        java.util.LinkedList<Node> adjacent = new LinkedList<>();
        private Node next;
        public Node(int id) {
            this.id = id;
        }
    }

    Node head;

    private Node getNode(int id) {
        Node curr = head;
        while (curr != null) {
            if (curr.id == id) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }
    private void addEdge(Node src, Node dest) {
        //TODO:
    }

    public boolean hasPathDFS(int src, int dest) {

        Node source = getNode(src);
        Node dst = getNode(dest);
        HashSet<Integer> visited = new HashSet<>();

        return hasPathDFS(source, dst, visited);
    }

    private boolean hasPathDFS(Node src, Node dest, HashSet<Integer> visited) {

        if (visited.contains(src.id)) {//ids stored in visited
            return false;
        }
        visited.add(src.id);
        if (src == dest) {
            return true;//recursive methods shud return both true and false.
        }
        for (Node child: src.adjacent) {//depth 1st - nested children of source
            if ( hasPathDFS(child, dest, visited)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPathBFS(Node src, Node dest) {
        LinkedList<Node> nextToVisit = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        nextToVisit.add(src);
        while (!nextToVisit.isEmpty()) {
            Node node = nextToVisit.remove();
            if (node == dest) {
                return true;
            }
            if (visited.contains(node.id)) {
                continue;//TODO: why ???
            }
            visited.add(node.id);
            for (Node child: node.adjacent) {//breadth 1st
                nextToVisit.add(child);
            }
        }
        return false;
    }
}
