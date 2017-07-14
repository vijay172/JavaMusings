package com.vijay;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vkbalakr on 6/15/17.
 */
public class LinkedList<E> {

    private class Node<E> {
        private E data;
        private Node<E> next;
        private boolean visited = false;
        private Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node<E> head;
    int size = 0;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    public LinkedList(E data) {
        this.head = new Node<E>(data);
        this.size = 1;
    }

    //append
    public void append(E data){
        Node<E> tmp = head;
        while(tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = new Node<E>(data);
        size++;
    }
    //prepend
    public void prepend(E data) {
        Node<E> tmp = head;
        Node<E> nxtHead = new Node<E>(data);
        head = nxtHead;
        head.next = tmp;
        size++;
    }

    //insert
    public void insert(E data, int idx) {
        if (idx < 0 || idx > size) {
            try {
                throw new IndexOutOfBoundsException("Error with idx:"+idx + " or size:"+size);
            } catch (Exception e) {
                return;
            }
        }

        if (idx == 0) {
            Node<E> nxtHead = new Node<E>(data);
            Node<E> tmp = head;
            head = nxtHead;
            head.next = tmp;
        } else {
            int count = 1;

            Node<E> tmp = head;
            while (tmp.next != null && count < idx) {
                tmp = tmp.next;
                count++;
            }
            Node<E> newNode = new Node<E>(data);
            newNode.next = tmp.next;
            tmp.next = newNode;
        }
        size++;
    }

    //delete
    public void delete(int idx) {
        if (idx < 0 || idx > size) {
            try {
                throw new IndexOutOfBoundsException("Error with idx:"+idx + " or size:"+size);
            } catch (Exception e) {
                return;
            }
        }
        if (idx == 0) {
            Node<E> tmp = head;
            head = tmp.next;
            tmp = null;
        } else {
            int count = 1;
            Node<E> tmp = head;
            while (tmp.next != null && count < idx) {
                tmp = tmp.next;
                count++;
            }
            tmp.next = tmp.next.next;
        }
        size--;
    }
    public Node deleteNode(Node<Integer> head, int d) {

        Node<Integer> n = head;
        if (n.data == d) {
            head = head.next;
            //return head;
        }

        while (n.next != null) {
            if (n.next.data == d) {
                n.next = n.next.next;
                //return head;
            }
            n =n.next;
        }
        return head;
    }

    public void appendToTail(int d) {

        Node end = new Node(d);
        Node n = head;

        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    public Node<E> get(int idx) {
        if (idx < 0 || idx >= size) {
            try {
                throw new IndexOutOfBoundsException("Error with idx:"+idx + " or size:"+size);
            } catch (Exception e) {
                return null;
            }
        }
        if (idx == 0) {
            return head;
        } else {
            int count = 1;
            Node<E> tmp = head;
            while (tmp.next != null && count < idx) {
                tmp = tmp.next;
                count++;
            }
            if (count == idx) {
                return tmp.next;
            } else {
                return null;
            }
        }
    }

    public void print() {
        Node<E>  tmp = head;
        while(tmp!= null) {
            System.out.print(tmp.data + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        /*LinkedList<Integer> ll = new LinkedList<>(3);
        ll.print();

        ll.append(5);
        ll.print();

        ll.insert(7, 1);
        ll.print();

        ll.prepend(2);
        ll.print();

        System.out.println(ll.get(4) != null ? ll.get(4).data : null);

        ll.delete(0);
        ll.print();

        ll.delete(1);
        ll.print();*/

        LinkedList<String> ll = new LinkedList<>("a1");
        ll.append("a2");
        ll.append("a3");
        ll.append("a4");
        ll.append("b1");
        ll.append("b2");
        ll.append("b3");
        ll.append("b4");
        ll.weave();
        ll.print();
    }

    boolean hasCycle(Node<E> head) {
        if (head == null || head.next == null ) {
            return false;
        }
        Set<Node> visitedNodes = new HashSet<Node>();
        Node curr = head;//1,2

        while (curr.next != null) {
            Node nextNode = curr.next;//2,3
            if (nextNode.next != null && ((nextNode.next == curr) || (visitedNodes.contains(nextNode.next))) ) {//3 == 1, 2 == 2
                return true;
            } else {
                curr = nextNode; //2
                visitedNodes.add(curr);
            }
        }
        return false;
    /*
    if (head == null) return false;

    Node slow = head;
    Node fast = head.next;
    while (slow != fast) {
        if (fast == null || fast.next == null) return false;

        slow = slow.next;
        fast = fast.next.next;
    }

    return true;
    */
    }

    public void weave() {
        Node slow = head;
        Node fast = head;
        Node midpoint = head;

        while(fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null || fast.next == null) {
            midpoint = slow;//set a mid pointer with slow
        }
        System.out.println("breezed thru fast-midpoint:"+midpoint.data);
        fast = head;//reset head
        slow = midpoint;
        while (slow != null || slow.next != null) {
            System.out.println("in weaving with slow:"+slow.data+" fast:"+fast.data);
            if (slow == null || slow.next == null) {
                break;
            }
            Node slowNext = slow.next;
            Node fastNext = fast.next;
            if (slow.next == null) {
                fast.next = slow;//insert slow's next after a fast element to weave it in
                slow.next = null;
                fastNext = null;
            } else {
                System.out.println("slow:"+slow.data+" fast:"+fast.data);
                Node tmp = fast.next;
                fast.next = slow;
                slow.next = tmp;
            }
            slow = slowNext;
            fast = fastNext;
        }
        System.out.println("reached here:");
    }

}
