package com.vijay.cake;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by vkbalakr on 6/28/17.
 */
public class TrieVisitedUrl {

    //Trie uses TrieNode
    static class TrieNode {
        Map<Character, TrieNode> nodeChildren;//each node's children

        TrieNode() {
            this.nodeChildren = new HashMap<Character, TrieNode>();
        }
        //get,add, has
        public TrieNode getChildNode(Character ch) {
            return this.nodeChildren.get(ch);
        }
        public void addChildNode(Character ch) {
            this.nodeChildren.put(ch, new TrieNode());
        }
        public boolean hasChildNode(Character ch) {
            return this.nodeChildren.containsKey(ch);
        }
    }

    public static class Trie {
        TrieNode rootNode;

        char endOfWordMarker = '\0';

        public Trie() {
            this.rootNode = new TrieNode();
        }
        public boolean checkPresentAndAdd(String word) {
            TrieNode curr = rootNode;
            boolean isNewWord = false;
            for (int i=0; i < word.length();i++) {
                char ch = word.charAt(i);
                if (!curr.hasChildNode(ch)) {
                    isNewWord = true;
                    curr.addChildNode(ch);
                }
                curr = curr.getChildNode(ch);//at 1st char
            }
            //after going thru word- see if end of wordmarker needed for newword
            if (!curr.hasChildNode(endOfWordMarker)) {
                isNewWord = true;
                curr.addChildNode(endOfWordMarker);
            }
            return isNewWord;
        }
        //display contacts as each additional char is typed
        public TrieNode displayContacts(String str) {
            TrieNode prevNode = rootNode;
            String prefix="";
            for (int i=0;i< str.length();i++) {
                char ch = str.charAt(i);
                prefix += ch;
                if (!prevNode.hasChildNode(ch)) {
                    i++;
                    break;
                }
                TrieNode currNode = prevNode.getChildNode(ch);
                displayContactsUtil(currNode, prefix);
                prevNode = currNode;
            }

            return prevNode;
        }

        public void displayContactsUtil(TrieNode curr, String prefix) {//autosuggest at some prefix point in String
            if (curr != null) {

                if (curr.hasChildNode(endOfWordMarker)) {
                    System.out.println("prefix:" + prefix);
                }
                //DFS on nodes
                for (char i='a';i <= 'z';i++) {
                    TrieNode nextNode = curr.getChildNode(i);
                    displayContactsUtil(nextNode, prefix + i);
                }
            }

        }
    }

    public static void main(String[] args) {
        // run your function through some test cases here
        // remember: debugging is half the battle!
        String testInput = "dog.com";
        System.out.println(new Trie().checkPresentAndAdd(testInput));
    }
}
