package com.vijay.cake;

import java.util.Stack;

/**
 * Created by vkbalakr on 6/27/17.
 */
public class Find2ndLargestBST {


    public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode insertLeft(int leftValue) {
            this.left = new BinaryTreeNode(leftValue);
            return this.left;
        }

        public BinaryTreeNode insertRight(int rightValue) {
            this.right = new BinaryTreeNode(rightValue);
            return this.right;
        }
    }

    public static BinaryTreeNode find2ndLargestElement(BinaryTreeNode root) {
        Stack<BinaryTreeNode> nodes = new Stack<>();
        Stack<BinaryTreeNode> prevNodes = new Stack<>();
        nodes.push(root);

        while (!nodes.isEmpty()) {//DFS
            BinaryTreeNode node = nodes.pop();
            if (node.right == null && node.left == null) {
                //leaf
                if (!prevNodes.isEmpty()) {
                    prevNodes.pop();
                } else {
                    return null;
                }
            }
            if (node.right == null && node.left != null) { //follow along leftmost path at the end
                //get the largest element in the left tree
                BinaryTreeNode nodeNext = node.left;
                while(nodeNext.right != null) {//it is the largest element in the left tree
                    nodeNext = nodeNext.right;
                }
                if (!prevNodes.isEmpty()) {
                    prevNodes.pop();
                }
                nodes.push(nodeNext);
                prevNodes.push(nodeNext);
            }
            if (node.right != null) { //follow along rightmost path
                if (!prevNodes.isEmpty()) {
                    prevNodes.pop();
                }
                nodes.push(node.right);
                prevNodes.push(node);
            }

        }
        return null;
    }

    private static int findLargest(BinaryTreeNode rootNode) {
        if (rootNode == null) {
            throw new IllegalArgumentException("Tree must have at least 1 node");
        }
        if (rootNode.right != null)  {
            return findLargest(rootNode.right);
        }
        return rootNode.value;
    }

    private static int findLargest1(BinaryTreeNode rootNode) {
        BinaryTreeNode curr = rootNode;

        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.value;
    }

    private static int findLargest2(BinaryTreeNode rootNode) {
        BinaryTreeNode curr = rootNode;
        BinaryTreeNode largest = null;
        while (curr != null) {
            if (curr.right == null) {
                largest = curr;
            }
            curr = curr.right;
        }
        return largest.value;
    }

    public static int findSecondLargest(BinaryTreeNode rootNode) { //O(h) space and time
        if (rootNode == null || (rootNode.left == null
                && rootNode.right == null)) {
            throw new IllegalArgumentException("Tree must have at least 2 nodes");
        }

        // case: we're currently at largest, and largest has a left subtree,
        // so 2nd largest is largest in said subtree
        if (rootNode.left != null && rootNode.right == null) {
            return findLargest(rootNode.left);
        }

        // case: we're at parent of largest, and largest has no left subtree,
        // so 2nd largest must be current node
        if (rootNode.right != null //COOL
                && rootNode.right.left == null
                && rootNode.right.right == null) {
            return rootNode.value;
        }

        // otherwise: step right
        return findSecondLargest(rootNode.right);
    }

    public static int find2ndLargest(BinaryTreeNode rootNode) {

        if (rootNode == null || rootNode.left == null && rootNode.right == null) {
            throw new IllegalArgumentException("Tree must have 2 nodes");
        }

        BinaryTreeNode curr = rootNode;

        while (true) {

            //rightmost element has a left sub-tree- the left sub-tree has the rightmost element as the largest element in the sub-tree
            //that will be 2nd largest elemnt
            if (curr.left != null && curr.right == null) {
                return findLargest1(curr.left);
            }


            if (curr.right != null && curr.right.left == null && curr.right.right == null) { //am on 2nd largest element
                return curr.value;
            }

            curr = curr.right;
        }
    }

    public static void main(String[] args) {
        // run your function through some test cases here
        // remember: debugging is half the battle!
        BinaryTreeNode node50 = new BinaryTreeNode(50);
        BinaryTreeNode node70 = new BinaryTreeNode(70);
        BinaryTreeNode node30 = new BinaryTreeNode(30);
        BinaryTreeNode node90 = new BinaryTreeNode(90);
        BinaryTreeNode node80 = new BinaryTreeNode(80);
        BinaryTreeNode node85 = new BinaryTreeNode(85);
        BinaryTreeNode node100 = new BinaryTreeNode(100);
        BinaryTreeNode node95 = new BinaryTreeNode(95);
        BinaryTreeNode node98 = new BinaryTreeNode(98);
        /*BinaryTreeNode node20 = new BinaryTreeNode(50);
        BinaryTreeNode node50 = new BinaryTreeNode(50);
        BinaryTreeNode node50 = new BinaryTreeNode(50);*/
        node50.insertRight(node70.value).insertRight(node90.value).insertRight(node100.value).insertLeft(node95.value).insertRight(node98.value);
        //node100.insertLeft(node95.value).insertRight(node98.value);
        node70.insertLeft(node80.value);
        node90.insertLeft(node85.value);
        node50.insertLeft(node30.value);

        System.out.println(find2ndLargestElement(node50).value);
    }
}