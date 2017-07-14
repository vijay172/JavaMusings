package com.vijay;

/**
 * Created by vkbalakr on 6/17/17.
 */
public class BinarySearchTree {
    static class Node {
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
        }
    }

    boolean checkBST(Node root) {
        return traverseInOrder(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean traverseInOrder(Node root, int min, int max ) {
        if (root == null) return true;//base condition is BST
        if (root.data < min || root.data > max) return false;//must have return false & true
        //check min < left <= root.data -1   root.data + 1 <= right < max
        return traverseInOrder(root.left, min, root.data - 1) && traverseInOrder(root.right, root.data + 1, max);
    }

    public static int maxDepth(Node root) {

        Node curr = root;

        if (curr== null) {
            return 0;
        } else {
            return 1 + Math.max(maxDepth(curr.left), maxDepth(curr.right));
        }


    }

    public static int diameter(Node root) {

        Node node = root;

        if (node == null) {
            return 0;
        }

        int lDiameter = diameter(node.left);
        int rDiameter = diameter(node.right);

        int lHeight = maxDepth(node.left);
        int rHeight = maxDepth(node.right);

        return Math.max(lHeight + rHeight + 1, Math.max(lDiameter, rDiameter));
    }

    public static void storeInOrder(Node head, int[] a1, int idx) {

        Node curr = head;
        if(curr != null) {
            storeInOrder(curr.left, a1, idx);
            System.out.println(curr.data);
            a1[idx++] = curr.data;
            storeInOrder(curr.right, a1, idx);
        }
    }

    /** merged 2 balanced BST */
    public static Node mergeBSTTrees(Node n1, Node n2, int n1Len, int n2Len) {
        int[] a1 = new int[n1Len];
        int i=0;
        storeInOrder(n1, a1, i);//store 1st bst in array a1

        int[] a2 = new int[n2Len];
        int j=0;
        storeInOrder(n2, a2, j);////store 2nd bst in array a2
        //merge 2 sorted array into one
        int[] mergedArr = merge(a1, a2);
        return sortedArrToBST(mergedArr, 0, n1Len + n2Len);
    }

    public static Node sortedArrToBST(int[] mergedArr, int start, int end) {
        if (start > end) {//base
            return null;//leaf
        }
        int mid = start + (end - start) / 2;
        Node root = new Node(mergedArr[mid]);

        root.left = sortedArrToBST(mergedArr, start, mid-1);
        root.right = sortedArrToBST(mergedArr, mid+1, end);
        return root;
    }

    public static int[] merge(int[] a1, int[] a2) {//O(n)
        int left =0, right =0;
        int[] mergedArr = new int[a1.length + a2.length];
        int count = 0;
        while (left < a1.length && right < a2.length) {
            if (a1[left] < a2[right]) {
                mergedArr[count++]= a1[left++];

            } else  {
                mergedArr[count++] = a2[right++];
            }
        }

        while (left < a1.length) {
            mergedArr[count++] = a1[left++];
        }
        while (right < a2.length) {
            mergedArr[count++]=a2[right++];
        }
        return mergedArr;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        head.left = node3;
        head.right = node7;
        node3.left = node2;
        node3.right = node4;

        node4.left = null;
        node4.right = node5;

        node7.left = node6;
        node7.right = node8;
        node6.left=null;
        node6.right=null;
        node8.left = null;
        node8.right = node9;
        node9.left = null;
        node9.right = null;


        //System.out.println(maxDepth(head));
        System.out.println(diameter(head));
    }
}
