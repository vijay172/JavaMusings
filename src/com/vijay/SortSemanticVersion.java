package com.vijay;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by vkbalakr on 6/29/17.
 */
public class SortSemanticVersion {

    public static void main(String[] args) {
        String[] versions = new String[]{"1.0.0","1.1.0","1.1.0-beta2","2.0.0-beta1","2.0.0"};
        Arrays.sort(versions, new Comparator<String>() {
            public int compare(String s1, String s2) {

                if (s1.substring(0,5).equals(s2.substring(0,5)) && s1.length() > s2.length()) {
                    System.out.println(String.format("s1:%s,s2:%s",s1,s2));
                    s2 = s2 +"-a";
                    return (s2 + "-a").compareTo(s1);
                }
                return s2.compareTo(s1);
            }
        });

        System.out.println(Arrays.toString(versions));
    }
}
