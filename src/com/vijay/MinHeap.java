package com.vijay;

/**
 * Created by vkbalakr on 7/9/17.
 */
public class MinHeap<E extends Comparable<E>> {

    Object S[];
    int last, capacity;

    public int size() {
        return last;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public MinHeap() {
        S = new Object[11];
        last = 0; capacity = 7;
    }

    public MinHeap(int capacity) {
        S = new Object[capacity + 1];
        last = 0;
        this.capacity = capacity;
    }

    public E min() throws Exception{
        if (isEmpty()) {
            throw new Exception("empty heap.");
        }
        else {
            return (E) S[1];
        }
    }

    private int compare(Object x, Object y) {
        return ((E)x).compareTo((E)y);
    }

    public E removeMin() throws Exception {
        if (isEmpty())
            throw new Exception("empty heap");
        E min = min();
        S[1] = S[last];//Complete binary tree
        last--;
        downHeapBubble();
        return min;
    }

    public void downHeapBubble() {
        int index =1;
        while (true) {
            int child = index * 2;
            if (child > size()) {
                break;
            }
            if (child + 1 <= size()) {
                child = findMin(child, child +1);
            }
            if (compare(S[index],S[child]) <= 0)
                break;
            swap(index,child);
            index = child;
        }

    }

    private int findMin(int lchild, int rchild) {
        if (compare(S[lchild], S[rchild]) <= 0) {
            return  lchild;
        }
        else {
            return rchild;
        }
    }



    public void insert(E e) throws Exception {
        if (size() == capacity) {
            throw new Exception("exceeds capacity");
        }
        last++;
        S[last]=e;//add at end
        upHeapBubble();
    }

    private void upHeapBubble() {
        //compare with parent
        int index = size();
        while (index > 1) {
            int parent = index / 2;
            if (compare(S[index], S[parent]) >= 0)
                break;//
            swap(index,parent);
            index = parent;//move up
        }
    }

    private void swap(int i, int j) {
        Object tmp = S[j];
        S[j] = S[i];
        S[i] =  tmp;
    }

    public static void main(String[] args) throws Exception {
        MinHeap<Integer> q = new MinHeap<>();
        q.insert(12);
        q.insert(4);
        q.insert(5);
        q.insert(3);
        q.insert(8);
        q.insert(7);
        System.out.println(q.removeMin());
        System.out.println(q.removeMin());
        System.out.println(q.removeMin());
    }
}
