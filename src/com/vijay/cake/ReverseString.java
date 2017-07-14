package com.vijay.cake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by vkbalakr on 6/20/17.
 */
public class ReverseString {

    public static String reverseString(String test) {
        char[] testChArr = test.toCharArray();
        int len = testChArr.length;
        int iterableLen = 0;
        if (len % 2 == 0) {
            iterableLen = len / 2;
        } else {
            iterableLen = (len / 2) + 1;
        }
        for (int i=0; i < iterableLen; i++) {
            char tmp =testChArr[len-1-i];
            testChArr[len-1-i] = testChArr[i];
            testChArr[i]=tmp;
            System.out.println("tmp:"+tmp);
        }
        System.out.println("testChArr:"+testChArr);
        /*StringBuilder sb = new StringBuilder();
        for (char ch: testChArr) {
          sb.append(ch);
        }*/
        return new String(testChArr);
    }

    //O(n) time, O(1) space
    public static String reverseStringCake(String test) {
        char[] testChArr = test.toCharArray();
        int startIndex = 0;
        int endIndex = testChArr.length - 1;
        while (startIndex < endIndex) {
            //swap
            char tmp = testChArr[startIndex];
            testChArr[startIndex] = testChArr[endIndex];
            testChArr[endIndex] = tmp;
            //move pointer fwd
            startIndex++;
            endIndex--;
        }
        return new String(testChArr);
    }


    public static class CustomerDrink {
        String customer;
        Set<String> drinks;
        CustomerDrink(String customer, Set<String> drinks) {
            this.customer = customer;
            this.drinks = drinks;
        }

    }

    public static class DrinkToCustomer {
        String drink;
        Set<String> customers;

        DrinkToCustomer(String drink, Set<String> customers) {
            this.drink = drink;
            this.customers = customers;
        }

        public boolean equals(Object o) {

            if (o == null) return false;
            if (this == o) return true;
            DrinkToCustomer dc = (DrinkToCustomer)o;
            if (this.drink.equals(dc.drink)) {
                return true;
            }
            return false;
        }


        @Override
        public int hashCode() {
            return drink.hashCode();
        }
    }

    public static void main(String[] args) {
        ArrayList<CustomerDrink> customerDrinks = new ArrayList<>();
        customerDrinks.add(new CustomerDrink("Cust1", new HashSet<>(Arrays.asList("n3", "n7","n5","n2","n9"))));
        customerDrinks.add(new CustomerDrink("Cust2", new HashSet<>(Arrays.asList("n5"))));
        customerDrinks.add(new CustomerDrink("Cust3", new HashSet<>(Arrays.asList("n3", "n2"))));
        customerDrinks.add(new CustomerDrink("Cust4", new HashSet<>(Arrays.asList("n4"))));
        customerDrinks.add(new CustomerDrink("Cust5", new HashSet<>(Arrays.asList("n3", "n4","n3","n5","n7","n4"))));
        int totalCustomerSize = 5;//TODO:

        Set<String> allCustomers = new HashSet<>();
        //create a drink to customers collection
        Map<String, DrinkToCustomer> drinkToCustomers = new HashMap<>();
        for (CustomerDrink cd : customerDrinks) {
            for (String drink: cd.drinks) {

                HashSet<String> customers = new HashSet<String>();
                customers.add(cd.customer);
                DrinkToCustomer dc = new DrinkToCustomer(drink, customers);
                if (drinkToCustomers.containsKey(drink)) {//
                   DrinkToCustomer drinkToCustomer = drinkToCustomers.get(drink);
                   drinkToCustomer.customers.add(cd.customer);
                } else {
                    drinkToCustomers.put(drink, dc);
                }
            }
        }
        //sort by cust size desc
        List<Map.Entry<String, DrinkToCustomer>> lst = new LinkedList<Map.Entry<String, DrinkToCustomer>> (drinkToCustomers.entrySet());
        Collections.sort(lst, new Comparator<Map.Entry<String, DrinkToCustomer>>() {
            @Override
            public int compare(Map.Entry<String, DrinkToCustomer> o1, Map.Entry<String, DrinkToCustomer> o2) {
                return o2.getValue().customers.size() - o1.getValue().customers.size();
            }
        });

        //greedily get all customers upto customer size
        Set<String> drinks = new HashSet<>();
        for ( Map.Entry<String, DrinkToCustomer> entry: lst) {
            DrinkToCustomer dc = entry.getValue();
            String drink = entry.getKey();
            if (allCustomers.size() >= totalCustomerSize) {
                System.out.println(drinks);
                break;
            } else {
                for (String customer: dc.customers) {
                    if (!allCustomers.contains(customer) ) {
                        allCustomers.add(customer);
                        drinks.add(drink);
                    }
                }

            }

        }

        //System.out.println(reverseStringCake("drier"));
    }
}
