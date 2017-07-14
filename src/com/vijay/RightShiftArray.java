package com.vijay;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by vkbalakr on 6/15/17.
 */
public class RightShiftArray {

    /*
    O(1) space, O(n) time
     */
    public static int[] rotate(int[] a, int k) {

        if (k > a.length) {
            k = k % a.length;
        }
        //length of 1st part
        int a1 = a.length - k;

        reverse(a, 0, a1-1);
        reverse(a, a1, a.length - 1);
        reverse(a,0, a.length - 1);
        return a;
    }

    public static void reverse(int[] arr, int start, int end) {
        if (arr == null || arr.length == 1) return;
        while ( start < end) {
            int tmp = arr[start];
            arr[start]=arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }



    /*
    O(n) time and space
     */
    public static int[] arrayRightRotation(int[] a, int n, int k) {

        int[] op = new int[n];

        //i+k-n
        for (int i = 0; i < n; i++) {
            int pos = i + k;
            if (pos >= n) {
                pos = pos - n;
            }
            op[pos] = a[i];
        }
        System.arraycopy(op, 0,a,0, op.length);
        return op;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        /*int[] output = new int[n];
        output = arrayRightRotation(a,n,k);

        for (int i = 0; i < n; i++) {
            System.out.print(output[i] + " ");;
        }
        System.out.println();*/
        System.out.println(Arrays.toString(rotate(a, k)));
    }
}
