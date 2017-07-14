package com.vijay;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by vkbalakr on 7/10/17.
 */
public class BinaryTreeExamples {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    //post order traversal to mirror tree
    public static void mirrorTree(Node head) {
        if (head == null) return;

        mirrorTree(head.left);
        mirrorTree(head.right);
        //swap
        Node temp = head.left;
        head.left = head.right;
        head.right = temp;
        System.out.println(head.data);
    }

    //Post order traversal to find height of sub trees 1st
    public static int height(Node t) {
        if (t == null) return -1;
        return 1 + Math.max(height(t.left), height(t.right));//1 is for root node
    }

    //Pre order traversal to label each node with its depth - root processed before children
    public static Node labelNodes(Node t) {
        if (t == null) return null;
        t.data = height(t);
        labelNodes(t.left);
        labelNodes(t.right);
        return t;
    }



    //no of leaves
    public static int leaves(Node t) {
        if (t == null) {
            return 0;
        }


        if (t.left == null && t.right == null) {
            return 1;//crucial
        }
        /*int leftLeaves =  leaves(t.left, count);
        System.out.println("leftLeaves:" + leftLeaves );
        int rightLeaves =  leaves(t.right, count);
        System.out.println("leftLeaves:" + leftLeaves + " rleaves:" + rightLeaves);
        return count;*/
        return leaves(t.left) + leaves(t.right);
    }

    public static void minValue(Node head) {

        minValue(head, 4);
    }

    //In order traversal to get kth smallest element ??????
    public static int minValue(Node node, int k) {

        Stack<Node> stack = new Stack<>();
        Node curr = node;
        int tmp = k;

        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {//goes thru left 1st
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                tmp--;
                if (tmp == 0) {
                    System.out.println(curr.data);
                    return curr.data;
                }
                curr = curr.right;
            }
        }
        return -1;
    }

    public static int kthSmallest(Node pivot, int k) {
        if (pivot == null) return k;//???
        //in order
        k = kthSmallest(pivot.left, k);
        --k;
        if (k==0) {
            System.out.println(pivot.data);
        }
        k = kthSmallest(pivot.right, k);
        return k;
    }

    public static void findKMin(Node head, int k) {
       //inorder
        if (head == null || k < 0) return;
        findKMin(head.left, k);
       --k;
       if (k==0) {
           System.out.println(head.data);
           return;
       }
       findKMin(head.right, k);
    }

    public static boolean checkBST(Node root) {
        return checkBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean checkBSTHelper(Node root, int min, int max) {

        //base -if we go this far, it is a BST
        if (root == null) return true;
        //boolean me = ( root.data > min && root.data < max);//each node needs to check if it is between min & max
        if ( root.data < min || root.data > max) {
            return false;
        }

        //reduction step
        boolean left = true;
        boolean right = true;
        if (root.left != null) {
            left = checkBSTHelper(root.left, min, root.data);
        }
        if (root.right != null) {
            right = checkBSTHelper(root.right, root.data, max);
        }

        return left && right;
    }
    /*
    Search for node holding highest value less than this node's value
     */
    public static int findNextLowerValue(Node tree) {//from left hand child
    //tree at left child node
        if (tree.right == null) {//if childs right link is null, curr tree node has next highest value as left will be lesser
            return tree.data;//data at child's node
        } else {
            return findNextLowerValue(tree.right);
        }
    }

    public static Node deleteNode(Node tree, int item) {
        if (tree == null) {
            System.out.println("tree not present");
        } else if (item < tree.data) {
            tree.left = deleteNode(tree.left, item);//link to left link of parent
        } else if (item > tree.data) {
            tree.right = deleteNode(tree.right, item);
        } else {//matches
            //leaf match
            if (tree.left == null && tree.right == null) {
                tree = null;
            }//single child node -link parent to child node of the ndoe to be deleted
            else if (tree.left == null) { //tree is the child node
                tree = tree.right;
            } else if (tree.right == null) {
                tree = tree.left;
            } else {//case 2 children-replace by next lower value
                int replacementValue = findNextLowerValue(tree.left);
                tree.data = replacementValue;
                tree.left = deleteNode(tree.left, replacementValue);//recursive
            }
        }
        return tree;
    }

    /*
    The time complexity of this solution is O(N), which is the number of nodes in the tree, so it’s optimal.
    Because we should visit each node at least once. The space complexity depends on maximum size of the queue at
    any point, which is the most number of nodes at one level. The worst case occurs when the tree is a complete
    binary tree, which means each level is completely filled with maximum number of nodes possible. In this case,
    the most number of nodes appear at the last level, which is (N+1)/2
    where N is the total number of nodes. So the space complexity is also O(N). Which is also optimal while using a queue.
     */
    public static void printTreeInLevelOrder(Node head) {
        //BFS Queue O(N) time O(N) space
        Queue<Node> q = new ArrayDeque<>();
        q.add(head);
        int currCount = 1, nxtCnt = 0;
        HashSet<Node> visited = new HashSet<>();
        while (!q.isEmpty()) {
            Node curr = q.poll();
            currCount--;
            if (visited.contains(curr)) {
                continue;
            }
            visited.add(curr);
            System.out.println(curr.data);//pre-order
            if (curr.left != null) {
                q.add(curr.left);
                nxtCnt++;
            }
            if (curr.right != null) {
                q.add(curr.right);
                nxtCnt++;
            }
            if (currCount == 0) {
                System.out.println();
                currCount = nxtCnt;
                nxtCnt = 0;
            }
        }

    }

    public static void printRev(Node head)
    {
        Stack<Node> s = new Stack();
        int d = Integer.MIN_VALUE;
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(head);
        q.addLast(new Node(d));
        while(!q.isEmpty())
        {
            Node o = q.removeFirst();//4 d
            System.out.println("o.data:"+o.data);
            if(d == o.data)
            {
                if (q.isEmpty())
                    break;
                q.addLast(new Node(d));//d 3 1
                s.push(new Node(d));//d 4
                System.out.println("delim stack data:"+o.data);
            }
            else
            {
                s.push(o);//1 4
                System.out.println("stack data:"+o.data);
                if(o.left != null)
                    q.addLast(o.left);//1 d
                if(o.right != null)
                    q.addLast(o.right);//3 1 d
            }
        }
        while(!s.empty())
        {
            Node o = s.pop();
            if(d==o.data)
            {
                System.out.println();
            }
            else
            {
                System.out.print(o.data + " ");
            }
        }
    }

    //O(1) NICE
    public static void printTreeBottomUpBFS(Node head) {
        Queue<Node> q = new ArrayDeque<>();
        Stack<Node> stack = new Stack<>();
        q.add(head);
        while (!q.isEmpty()) {
            Node curr = q.peek()    ;
            q.remove();
            stack.push(curr);
            if (curr.right != null) {//crucial to use right 1st to do it in reverse from stack
                q.add(curr.right);
            }
            if (curr.left != null) {
                q.add(curr.left);
            }
        }
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.data+ " ");
        }

    }

    public static void printRevTree(Node head) {
        int height = height1(head);
        //System.out.println("ht:"+height);
        for (int i = height; i >= 1; i--) {//ends at 1
            printGivenLevel(head, i);
        }
    }

    public static void printGivenLevel(Node node, int level) {
        //System.out.println(String.format("level:%d, node.data:%d ",level, node != null?node.data:null));
        if (node == null) return;
        if (level == 1) {
            System.out.print(node.data + " ");
        } else if (level > 1) {
            //System.out.println("level >1: "+node.data);
            printGivenLevel(node.left, level -1);
            printGivenLevel(node.right, level -1);
        }
    }
    public static void printRevTreeBottomUpLeftRight(Node head) {
        int ht = height1(head);

        for (int i = ht; i >= 1; i--) {
            printGivenLevelBottomUpWithLeftRight(head.left, head.right, i);
        }
    }

    public static void printGivenLevelBottomUpWithLeftRight(Node left, Node right, int level) {
        if (left == null || right == null) return;
        if (level < 1) return;
        if (level > 1) {
            printGivenLevelBottomUpWithLeftRight(left.left,right.right, level - 1);
            printGivenLevelBottomUpWithLeftRight(left.right,right.left, level - 1);
        }
        if (level == 1) {
            System.out.print(" " + left.data + " " + right.data);
        }

    }

    public static int height1(Node head) {
        if (head == null) return 0;
        Node curr = head;
        int lheight = height1(curr.left);
        int rheight = height1(curr.right);
        int height = Math.max(lheight, rheight);
        return 1 + height ;//root included
    }

    public static void printSpiral(Node head) {

        int ht = height1(head);
        boolean ltr = false;
        for (int i = 1; i <= ht; i++) {//crucial  starts from 1, not 0
            printGivenLevelSpiral(head, i, ltr);
            ltr = !ltr;
        }
    }

    public static void printGivenLevelSpiral(Node node, int level, boolean ltr) {
        if (node == null ) return;
        if (level == 1) //<== print here
            System.out.print(node.data + " ");
        else if (level > 1) {
            if (ltr) {
                printGivenLevelSpiral(node.left, level -1, ltr);
                printGivenLevelSpiral(node.right, level -1, ltr);
            } else {
                printGivenLevelSpiral(node.right, level -1, ltr);
                printGivenLevelSpiral(node.left, level -1, ltr);
            }
        }
    }

    public static void printSpecificLevelOrder(Node head) {
        if (head == null) return;
        System.out.print(head.data + " ");
        Node node = head;
        if (node.left != null) {//binary tree-no need to check right if left exists
            System.out.print(node.left.data +" " + node.right.data + " ");
        }
        Node first = node.left;
        Node second = node.right;
        Queue<Node> q = new LinkedList<>();
        q.add(first);
        q.add(second);
        while (!q.isEmpty()) {
            first = q.poll();
            second = q.poll();

            if (first.left != null) {//handle grand children
                System.out.print(" " + first.left.data + " " +  second.right.data);
                System.out.print(" " + first.right.data + " " +  second.left.data);
            }
            //grand children
            if (first.left.left != null) {
                q.add(first.left);
                q.add(second.right);
                q.add(first.right);
                q.add(second.left);
            }
        }
    }

    public static void reverseAlternateAndPrint(Node head) {
        reverseAlternate(head);
        printInOrder(head);
    }
    public static void reverseAlternate(Node node) {
        preorder(node.left, node.right, 0);

    }

    public static void preorder(Node left, Node right, int level) {
        if (left == null && right == null) return;
        if (level % 2 == 0) {
            //swap
            int tmp = left.data;
            left.data = right.data;
            right.data = tmp;
        }
        // Recur for left and right subtrees (Note : left of root1
        // is passed and right of root2 in first call and opposite
        // in second call.
        preorder(left.left, right.right, level + 1);
        preorder(left.right, right.left, level + 1);//tricky
    }

    public static void printInOrder(Node node) {
        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node5 = new Node(5);
        Node node1 = new Node(1);
        head.right = node5;
        head.left = node2;
        node2.left = node1;
        node2.right = node3;
        //mirrorTree(head);
        //System.out.println(minValue(head,0, 3));
        //System.out.println(height(head));
        //System.out.println(leaves(head));
        //System.out.println(labelNodes(head).data);
        //System.out.println(checkBST(head));
        //printTreeInLevelOrder(head);
        //printRev(head);
        //printRevTree(head);
        //printTreeBottomUpBFS(head);


        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);

        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.right.left = new Node(6);
        tree.right.right = new Node(7);

        tree.left.left.left = new Node(8);
        tree.left.left.right = new Node(9);
        tree.left.right.left = new Node(10);
        tree.left.right.right = new Node(11);
        tree.right.left.left = new Node(12);
        tree.right.left.right = new Node(13);
        tree.right.right.left = new Node(14);
        tree.right.right.right = new Node(15);

        tree.left.left.left.left = new Node(16);
        tree.left.left.left.right = new Node(17);
        tree.left.left.right.left = new Node(18);
        tree.left.left.right.right = new Node(19);
        tree.left.right.left.left = new Node(20);
        tree.left.right.left.right = new Node(21);
        tree.left.right.right.left = new Node(22);
        tree.left.right.right.right = new Node(23);
        tree.right.left.left.left = new Node(24);
        tree.right.left.left.right = new Node(25);
        tree.right.left.right.left = new Node(26);
        tree.right.left.right.right = new Node(27);
        tree.right.right.left.left = new Node(28);
        tree.right.right.left.right = new Node(29);
        tree.right.right.right.left = new Node(30);
        tree.right.right.right.right = new Node(31);

        //System.out.println("Specific Level Order traversal of binary"
                //+"tree is ");
        //printSpecificLevelOrder(tree);
        //printRevTreeBottomUpLeftRight(tree);
        //System.out.println(leaves(head));
        //minValue(head);
        //findKMin(head, 3);
        kthSmallest(head, 4);
    }
}
