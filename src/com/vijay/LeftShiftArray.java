package com.vijay;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LeftShiftArray {

    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        if (k > n) {
            k = k % n;
        }
        int[] op = new int[n];

        for (int i = 0; i < n; i++) {
            int pos = i - k;
            if (pos < 0) {
                pos = pos + n;
            }
            //System.out.println("i:"+i+"pos:"+pos+" a[pos]:"+a[pos]);
            op[pos] = a[i];
        }
        return op;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        int[] output = new int[n];
        output = arrayLeftRotation(a, n, k);
        for(int i = 0; i < n; i++)
            System.out.print(output[i] + " ");

        System.out.println();

    }
}
