package com.vijay;


/**
 * Created by vkbalakr on 7/6/17.
 */
public class LongestConsecutiveSequenceBinaryTree {

    public static int longestConsecutiveSeq(BinaryNode root) {
        if (root == null) return 0;

        int res = 0;

        res = longestConsecutiveSeqRec(root, root.data, 0, res);
        return res;
    }

    /**
     * We need information on the parentNode data and so we pass it as *expected* value as parent.data + 1
     * @param root
     * @param expected
     * @param cnt
     * @param maxSeq
     * @return
     */
    public static int longestConsecutiveSeqRec(BinaryNode root, int expected, int cnt, int maxSeq) {
        BinaryNode curr = root;
        System.out.println(String.format("before root:%s, expected:%d, cnt:%d,maxSeq:%d ",root != null ? root.data: "null", expected, cnt, maxSeq));
        if (curr == null) {
            return maxSeq;
        }

        if (curr.data == expected) {//curr node's data is 1 more than parent and so is in seq
            //+1 check
            cnt++;
        } else {
            cnt = 1;
        }
        maxSeq = Math.max(cnt, maxSeq);
        System.out.println(String.format("after root:%s, expected:%d, cnt:%d,maxSeq:%d ",root != null ? root.data: "null", expected, cnt, maxSeq));
        return Math.max(longestConsecutiveSeqRec(curr.left, curr.data + 1, cnt, maxSeq),
        longestConsecutiveSeqRec(curr.right, curr.data + 1, cnt, maxSeq ));

    }

    public static int sumOfLeftLeaves(BinaryNode root) {

        return sumOfLeftLeaves(root, 0);
    }
    static class Sum {
        int sum=0;
    }

    public static int sumOfLeftLeaves1(BinaryNode root) {
        Sum sum = new Sum();
        sumOfLeftLeaves1(root, sum);
        return sum.sum;
    }
    public static void sumOfLeftLeaves1(BinaryNode root, Sum sum) {
        BinaryNode curr = root;
        if (curr == null) return;
        if (curr.left != null && curr.left.left == null && curr.left.right ==  null ) {
            sum.sum += curr.left.data;
        }
        sumOfLeftLeaves1(curr.left, sum);
        sumOfLeftLeaves1(curr.right, sum);
    }

    public static int sumOfLeftLeaves(BinaryNode root, int sum) {
        BinaryNode curr = root;
        if (curr == null) return sum;
        if (curr.left != null && curr.left.left == null && curr.left.right ==  null ) {
            sum += curr.left.data;
        }
        return sumOfLeftLeaves(curr.left, 0) + sumOfLeftLeaves(curr.right, sum);
    }

    static boolean isLeaf(BinaryNode node) {
        System.out.println("isLeaf node: "+ (node != null ? node.data: null));
        if (node == null) return false;
        if (node.left == null && node.right == null) return true;
        return false;
    }

    public static int sumOfLeftLeaves2(BinaryNode root) {
        BinaryNode curr = root;
        System.out.println("sumOfLeftLeaves2 curr at top "+ (curr != null ? curr.data: null));
        int sum=0;
        if (curr != null) {

            if (isLeaf(curr.left)) {
                System.out.println("curr.left for sum "+ (curr.left != null ? curr.left.data: null));
                sum += curr.left.data;
            } else {
                sum += sumOfLeftLeaves2(curr.left);
            }
            System.out.println("curr.right at bottom for right side "+curr.data);
            sum += sumOfLeftLeaves2(curr.right);//still at root for curr here
        }
        return sum;
    }

    public static void main(String[] args) {
        /*BinaryNode node6 = new BinaryNode(6);
        BinaryNode node9 = new BinaryNode(9);
        BinaryNode node7 = new BinaryNode(7);
        BinaryNode node10 = new BinaryNode(10);
        BinaryNode node11 = new BinaryNode(11);
        node6.right = node9;
        node9.left = node7;
        node9.right = node10;
        node10.right = node11;
        System.out.println(longestConsecutiveSeq(node6));*/

        BinaryNode tree = new BinaryNode(20);
        tree.left = new BinaryNode(9);
        tree.right = new BinaryNode(49);
        tree.left.right = new BinaryNode(12);
        tree.left.left = new BinaryNode(5);
        tree.right.left = new BinaryNode(23);
        tree.right.right = new BinaryNode(52);
        tree.left.right.right = new BinaryNode(15);
        tree.right.right.left = new BinaryNode(50);
        System.out.println(sumOfLeftLeaves2(tree));
    }
}

class BinaryNode {
    int data;
    BinaryNode left;
    BinaryNode right;
    BinaryNode(int data) {
        this.data = data;
    }
}
