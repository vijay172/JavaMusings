package com.vijay;

/**
 * Created by vkbalakr on 6/26/17.
 */
public class BinarySearchArr {


    public static boolean binarySearchRecursive(int[] arr, int intToSearch) {
        return binarySearchRecursive(arr, intToSearch, 0, arr.length -1);
    }

    private static boolean binarySearchRecursive(int[] arr, int intToSearch, int left, int right) {

        if (left > right) {//only >
            return false;
        }

        int mid = left + ((right -left) / 2);
        if (arr[mid] == intToSearch) {
            return true;
        } else if (intToSearch < arr[mid]) {
            return binarySearchRecursive(arr, intToSearch, left, mid -1);
        } else  {
            return binarySearchRecursive(arr, intToSearch, mid +1, right);
        }
    }

    public static boolean binarySearchIterative(int[] arr, int x) {

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);//needs to be inside loop

            if (arr[mid] == x) {
                return true;
            } else if (x < arr[mid]) {
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(binarySearchRecursive(new int[]{1,2,3,4,5,6}, 6));
    }
}
