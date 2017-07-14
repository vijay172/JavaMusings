package com.vijay.vinu;

/**
 * Created by vkbalakr on 7/3/17.
 */
public class Power {
    /*
before x:5.0 n:2
before x:5.0 n:1
after x:5.0v:1.0 n:1 <== how did v get 1.0 in the 1st place ??
inside mod odd x:5.0v:1.0 n:1
after x:5.0v:5.0 n:2
inside mod even x:5.0v:5.0 n:2
     */

    public static double power(double x, int n) {
        if (n == 0) return 1;
        System.out.println("before x:"+x+" n:"+n);

        double v = power(x, n/2);
        System.out.println("after x:"+x+"v:"+v+" n:"+n);

        if (n % 2 == 0) {
            System.out.println("inside mod even x:"+x+"v:"+v+" n:"+n);
            return v * v;
        } else {
            System.out.println("inside mod odd x:"+x+"v:"+v+" n:"+n);
            return v * v * x;
        }

    }

    public static double pow(double x, int n) {
        if (n < 0) {
            return 1 / power(x, -n);
        } else {
            return power(x,n);
        }
    }
    public static void main(String[] args) {
        System.out.println(pow(5,2));
    }
}
