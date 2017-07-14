package com.vijay;

import java.util.Scanner;

/**
 * Created by vkbalakr on 6/19/17.
 */
public class TrieContactArr {

    public static class Node {
        private static int NUMBER_OF_CHARS = 26;
        Node[] children = new Node[NUMBER_OF_CHARS];
        public int size = 0;

        private static int getCharIdx(char c) {
            return c - 'a';
        }

        private Node getNode(char ch) {
            return children[getCharIdx(ch)];
        }

        private void setNode(char ch, Node node) {
            children[getCharIdx(ch)] = node;
        }

        private void add(String s) {
            add(s,0);
        }

        private void add(String s, int idx) {
            size++; //why ? how many children it has
            //TODO: base
            if (idx == s.length()) {
                return;
            }
            char c = s.charAt(idx);
            Node child = getNode(c);//h
            if (child == null) {
                child = new Node();
                setNode(c, child);
            }
            child.add(s, idx + 1);//add to child from here
        }

        private int findCount(String s, int idx) {
            //TODO:base
            if (idx == s.length()) {
                return size;
            }
            char c = s.charAt(idx);
            Node child = getNode(c);
            if (child == null) {return 0;}
            return child.findCount(s, idx + 1);//find children


        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        TrieContactArr.Node tri = new TrieContactArr.Node();
        for(int i=0; i < n; i++) {
            String op = in.next();
            String contact = in.next();
            if (op.equals("add")) {
                tri.add(contact);
            } else if (op.equals("find")){
                System.out.println(tri.findCount(contact, 0));
            }
        }

    }
}
