package com.vijay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by vkbalakr on 7/12/17.
 */
public class ArrayExamples {

    public static Random rand = new Random();
    public static int partition(int[] arr, int left, int right) {
        //int mid = rand.nextInt(right - left) + left;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            while (arr[left] < arr[mid]) left++;
            while (arr[right] > arr[mid]) right--;
            if(left <= right) {
                //swap
                int tmp = arr[left];
                arr[left]=arr[right];
                arr[right]=tmp;
                left++; right--;
            }
        }
        return left;
    }

    //O(N)
    public static int findKthLargest(int[] arr, int left, int right, int k) {
        //base
        if (k > arr.length - 1 || k < 1) return -1;
        if (left == right ) return arr[left];

        //reduction
        while (true) {
            int pivotIdx = partition(arr, left, right);
            //Let’s say after partitioning the array the position of the pivot in the array is m.
            // If m is equal to k, then the pivot is exactly the kth element that we’re looking for, so we return the pivot value.
            int rank = pivotIdx - left + 1;//???
            if (rank == k) {
                return arr[pivotIdx];
            } else if (k < rank) {
                return findKthLargest(arr,left, pivotIdx -1, k);
            } else {
                //The value of k also gets adjusted if we’re searching the right subarray since we change the left index, so the
                // new value of k becomes k-rank, meaning we count out rank number of elements that we’re eliminating at the left portion of the array.
                return findKthLargest(arr,pivotIdx +1, right, k - rank);
            }
        }


    }

    /**
     * 0(log n)
     * @param arr
     * @param k
     * @return
     */
    public static int searchSortedArrayUnknownLength(int[] arr, int k) {
        //get close to idx as 0..1..2..4..8..16...2^n
        //narrowed upper bound down
        int idx = 0, exp=0;
        while (true) {
            try {
                if (arr[idx] == k) {
                    return idx;
                } else if (arr[idx] < k) {
                    if (idx == 0)
                        idx = 1;
                    idx = 2 * idx;
                    exp++;
                } else {
                    break;//within 2^exp-1 < k < idx
                }
            } catch(ArrayIndexOutOfBoundsException e) {//<== missed this
                break;
            }
        }
        //binary search with lower bound && approx upper bound
        int left = (idx / 2) + 1; // 2 ^exp - 1
        int right = idx - 1;
        while(left <= right) { //<=
            int mid = left + (right - left) / 2;
            if (arr[mid] == k) {
                return mid;
            } else if (arr[mid] < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static Integer findMissingEvenElement(Integer[] arr) {
        Set<Integer> setNbrs = new HashSet<Integer>(Arrays.asList(arr));
        List<Integer> arrLst = new ArrayList<>(Arrays.asList(arr));
        arrLst.addAll(setNbrs);
        int missingNbr = 0;
        for (int i = 0; i < arrLst.size(); i++) {
                missingNbr ^= arrLst.get(i);
        }
        return missingNbr;
    }

    /*
    XOR a nbr with itself(even) produces 0. XOR twice for odd occurrences and u get the nbr back.
    For odd occurrences, it will XOR even times producing 0.
     */
    public static Integer findMissingOddElement(Integer[] arr) {
        int missingNbr = 0;
        for (int i = 0; i < arr.length; i++) {
            missingNbr ^= arr[i];
        }
        return missingNbr;
    }

    public static int checkNextLargestPalindrome(int n) {
        for (int i=n; i < Integer.MAX_VALUE; i++) {
            if (checkPalindrome(i)) {
                return i;
            }
        }
        return 0;
    }

    public static boolean checkPalindrome(int n) {
        Set<Character> setCh = new HashSet<>();
        String str = Integer.toString(n);
        for (char ch:str.toCharArray()) {
            if (setCh.contains(ch)) {
                setCh.remove(ch);
            } else {
                setCh.add(ch);
            }
        }
        return (setCh.size() == 0 || setCh.size() == 1);

    }

    public static int getEquivIntForChar(char c) {
        return c - 'a';
    }

    public static int getDecimal(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()) {
            sb.append(getEquivIntForChar(c));
        }
        return Integer.parseInt(sb.toString());
    }

    public static void printLexicoSmallerStrings() {

        String[] s = {"Amazon","sun", "run"};
        Arrays.sort(s, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
               int s1Int = getDecimal(s1);
               int s2Int = getDecimal(s2);
               if (s1Int == s2Int) {
                   return s1.compareTo(s2);
               } else {
                   return s1Int - s2Int;
               }
            }
        });
    }

    public static void main(String[] args) {
        /*int[] arr = {3, 1, 2, 1, 4};//1,2,3,4,8,9,11
        System.out.println(findKthLargest(arr, 0, arr.length - 1, 3));*/
        /*int[] arr= {1,2,3,4,6,8,9,11};
        System.out.println(searchSortedArrayUnknownLength(arr, 8));*/
        /*Integer[] arr = {2, 1, 3, 1};
        System.out.println(findMissingEvenElement(arr));*/
        //System.out.println(checkNextLargestPalindrome(125));

        Integer[] arr = {1, 1, 3, 2,2};
        //System.out.println(findMissingEvenElement(arr));

        //printLexicoSmallerStrings();
    }
}
