package com.vijay.vinu;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vkbalakr on 7/4/17.
 */
public class LRUCache {
    class Node {
        int key;
        int value;
        Node pre;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, Node> map = new LinkedHashMap<>();
    int capacity;
    Node head = null;
    Node tail =  null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {

        if (map.containsKey(key)) {
            Node curr = map.get(key);
            //make it latest becomes front of LL
            remove(curr);//no capacity overfill becoz we remove before adding it back
            setHead(curr);
            return curr.value;
        } else {
            return -1;
        }
    }

    public void remove(Node curr) {
        //handle both pre and next
        if (curr.pre != null) {
            curr.pre.next = curr.next;
        } else {
            head = curr.next;//it is the head
        }
        if (curr.next != null) {
            curr.next.pre = curr.pre;
        } else {
            tail = curr.pre;//it is the tail
        }


    }
    public void setHead(Node curr) {
        curr.next = head;
        curr.pre = null;
        if (head != null)
            head.pre = curr;//curr <--> head
        head = curr;
        if (tail == null)
            tail = head;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            old.value = value;
            //make it latest
            remove(old);
            setHead(old);
        } else {
           Node created = new Node(key, value);
           if (map.size() >=  capacity) {//reached capacity
               //remove last entry
               map.remove(tail.key);
               remove(tail);
               setHead(created);
           } else {
               setHead(created);
           }
            map.put(key, created);
        }
    }


}
