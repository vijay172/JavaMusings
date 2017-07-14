package com.vijay.cake;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by vkbalakr on 6/27/17.
 */
public class BinaryTreeNodeBalanced {

    private static class NodeDepth {

        private BinaryTreeNode node;
        private int depth;

        NodeDepth(BinaryTreeNode leaf, int depth) {
            this.node = leaf;
            this.depth = depth;
        }
    }

    public static boolean isBalanced(BinaryTreeNode treeRoot) {

        if (treeRoot == null) {
            return true;
        }

        List<Integer> depths = new ArrayList<>(3);//short circuit as soon as it is 3
        Stack<NodeDepth> nodes = new Stack<>();//DFS search

        nodes.add(new NodeDepth(treeRoot, 0));

        while (!nodes.isEmpty()) {
            NodeDepth nodePair = nodes.pop();
            BinaryTreeNode node = nodePair.node;
            int depth = nodePair.depth;

            if (node.left == null && node.right == null)//at leaf
            {
                if (!depths.contains(depth)) {

                    depths.add(depth);

                    if ((depths.size() > 2) || ( depths.size() == 2 && Math.abs(depths.get(0) - depths.get(1)) > 1) ) {
                        return false;
                    }
                }

            }
            else {
                if (node.left != null) {
                    nodes.add(new NodeDepth(node.left, depth + 1));
                }
                if (node.right != null) {
                    nodes.add(new NodeDepth(node.right, depth + 1));
                }
            }

        }
        return false;
    }

    public static class BinaryTreeNode {

        //left, right, value
        private int value;
        private BinaryTreeNode left;
        private BinaryTreeNode right;
        private List<BinaryTreeNode> adjacent = new LinkedList<>();

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode insertLeft(BinaryTreeNode left) {
            this.left = left;
            return this.left;
        }

        public BinaryTreeNode insertRight(BinaryTreeNode right) {
            this.right = right;
            return this.right;
        }

        public boolean depthFirstSearch(BinaryTreeNode root, BinaryTreeNode leaf) {
            Set<Integer> visited = new HashSet<>();

            return depthFirstSearch(root, leaf, visited);
        }

        private boolean depthFirstSearch(BinaryTreeNode root, BinaryTreeNode leaf, Set<Integer> visited) {
            BinaryTreeNode curr = root;

            if (visited.contains(curr.value)) {
                return false;
            }
            visited.add(curr.value);
            if (root == leaf) {
                return true;
            }
            for (BinaryTreeNode child: curr.adjacent) {
                if (depthFirstSearch(child, leaf, visited)) {
                    return true;
                }
            }
            return false;
        }


    }
}
