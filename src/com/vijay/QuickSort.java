package com.vijay;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by vkbalakr on 7/2/17.
 */
public class QuickSort {

    Random rand = new Random();

    public static void quicksortRecursive(int[] arr) {

        quickSortRecursive(arr, 0, arr.length - 1);

    }

    public static void quickSortRecursive(int[] arr, int left, int right) {
        //base case 0 empty 1 element
        if (arr != null && arr.length < 2) {
         	return;
        }
        //2 element case swap
        if (arr[left] > arr[right]) {
            int temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
        }

        int pivot = left + (right - left) / 2;

        if (left < pivot -1) {
            quickSortRecursive(arr, left, pivot - 1);
        }
        if(pivot < right) {
            quickSortRecursive(arr, pivot, right);
        }


    }

    public static void quicksort(int[] arr) {

        quicksort(arr, 0, arr.length - 1);
    }

    public static void quicksort(int[] arr, int low, int hi) {
        int pivot = partition(arr, low, hi);
        if (low < pivot - 1) {//sort left half
            quicksort(arr, low, pivot -1);
        }
        if (pivot < hi ) {//sort right half
            quicksort(arr, pivot, hi);
        }
    }

    public static int partition(int[] arr, int low, int hi) {
        int mid = low + (hi - low) / 2;//pick pivot point before while loop
        while (low <= hi) {
            while (arr[low] < arr[mid]) low++;
            while (arr[hi] > arr[mid]) hi--;
            if (low <= hi) {
                //swap
                int tmp = arr[low];
                arr[low] = arr[hi];
                arr[hi]=tmp;
                low++;
                hi--;
            }
        }
        return low;
    }


    /*public static void quicksort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            quicksort(arr, left, index - 1);
        }
        if (index < right) {
            quicksort(arr, index, right);
        }

    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + ((right - left) / 2)) ];

        while (left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;
            if (left <= right) {
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
                left++;
                right--;
            }
        }
        return left;//???
    }*/


    public static void main(String[] args) {
        int[] arr = new int[]{4,1,2,7,10,3};
        quicksort(arr, 0, arr.length - 1 );
        //quicksortRecursive(arr);
        System.out.println(Arrays.toString(arr));
    }
}
