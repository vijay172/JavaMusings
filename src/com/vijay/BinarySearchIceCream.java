package com.vijay;

import java.util.Arrays;
import java.util.Scanner;

class IceCream implements Comparable{
    int flavor;
    int index;

    public IceCream(int flavor, int index) {
        this.flavor = flavor;
        this.index = index;
    }

    @Override
    public int compareTo(Object o) {
        return this.flavor - ((IceCream)o).flavor;
    }

    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof IceCream)) {
            return false;
        }

        return (this.flavor ==((IceCream)o).flavor);
    }

}

/**
 * Created by vkbalakr on 6/27/17.
 */
public class BinarySearchIceCream {

    public static int binarySearch(int first, int last, IceCream[] arr, int search) {
        //return icecream.index
        System.out.println(String.format("initial first:%d, last:%d",first,last));
        while (first <= last) {
            int mid = first + ((last - first)/2);
            //System.out.println(String.format("mid:%d,arr[mid].flavor:%d, arr[mid].index:%d",mid,arr[mid].flavor,arr[mid].index));
            if (search == arr[mid].flavor) {
                //System.out.println(String.format("matched arr[mid].flavor:%d, arr[mid].index:%d",arr[mid].flavor,arr[mid].index));
                return arr[mid].index;
            } else if (search < arr[mid].flavor) {
                last = mid -1;
            } else {
                first = mid + 1;
            }
            //System.out.println(String.format("first:%d, last:%d",first,last));
        }
        return -1;//not found
    }

    /**
     *
     Input
     2
     4
     5
     1 4 5 3 2
     4
     4
     2 2 4 3
     * @param args
     */

    public static void main(String[] args) {

        int t;
        int n, m;

        Scanner in = new Scanner(System.in);
        t = in.nextInt();
        for(int test = 0; test < t; test++) {

            m = in.nextInt();
            n = in.nextInt();
            IceCream[] arr = new IceCream[n];

            for (int i = 0; i < n; i++)
                arr[i] = new IceCream(in.nextInt(), i + 1);

            Arrays.sort(arr);
            int firstIndex = 100000, secondIndex = 100000;
            for(int i = 0; i < n - 1 ; i++) {
                int search = m - arr[i].flavor;
                if(search >= arr[i].flavor) {
                    int index = binarySearch( i + 1, n - 1, arr, search);
                    if( index != -1 ) {
                        System.out.println( Math.min(arr[i].index, index) + " " + Math.max(arr[i].index, index));
                        break;

                    }
                }
            }

        }

    }
}
