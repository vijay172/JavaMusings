package com.vijay;

/**
 * Created by vkbalakr on 6/26/17.
 */
public class Fibonacci {


    public  int fibRecursive(int n) {

        if (n == 0) return 0;
        if (n == 1) return 1;

        return fibRecursive(n-1) + fibRecursive(n-2);
    }

    public int fibRecMemo(int n) {
        return fibRecMemo(n, new int[n +1]);
    }

    public int fibRecMemo(int n, int[] memo) {

        if ((n==0) || (n==1)) return n;
        if (memo[n] == 0) {
            memo[n] = fibRecMemo(n-1, memo) + fibRecMemo(n-2, memo);
        }
        return memo[n];
    }

    public int fibBottomUp(int n) {
        if ((n==0) || (n==1)) return n;

        int[] memo = new int[n];
        memo[0]=0;memo[1]=1;

        for (int i=2; i< n; i++) {
            memo[i] =memo[i-1] + memo[i-2];
        }
        return memo[n-1] + memo[n-2];
    }

    public int fibIterativeSimpleWithNoMemo(int n) {

        int curr =0;
        int next = 1;
        for (int i = 0; i < n; i++) {
            int tmp = curr + next;
            curr = next;
            next = tmp;
        }
        return curr;
    }

    public static int fibIter(int n) {
        if (n == 0 || n == 1) return n;

        int curr=0;
        int prevPrev=0,prev=1;//memo items 1st & 2nd items
        for (int i=1; i < n; i++) {
            //iteration 1: 2nd fibonacci fib(2)= 0 + 1 = 1
            //iteration 2: 3rd fib 1 + 1 = 2
            //iteration 3: 4th fib 1 + 2 = 3
            //iteration n: n-1 iterations
            curr = prevPrev + prev;
            prevPrev = prev;
            prev = curr;
        }
        return curr;

    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        //System.out.println(fib.fibRecursive(6));
        //System.out.println(fib.fibRecMemo(6));//8
        //System.out.println(fib.fibBottomUp(6));//8
        System.out.println(fib.fibIterativeSimpleWithNoMemo(6));//8
    }
}
