package com.vijay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by vkbalakr on 7/5/17.
 */
public class CustomerDrinks {
    public static class CustomerDrink {
        String customer;
        Set<String> drinks;

        CustomerDrink(String customer, Set drinks) {
            this.customer = customer;
            this.drinks = drinks;
        }
    }

    public static class DrinkToCustomer {
        String drink;
        Set customers;

        DrinkToCustomer(String drink, Set customers) {
            this.drink = drink;
            this.customers = customers;
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (this == o) return true;
            DrinkToCustomer dc = (DrinkToCustomer) o;
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
        customerDrinks.add(new CustomerDrink("Cust1", new HashSet<>(Arrays.asList("n3", "n7", "n5", "n2", "n9"))));
        customerDrinks.add(new CustomerDrink("Cust2", new HashSet<>(Arrays.asList("n5"))));
        customerDrinks.add(new CustomerDrink("Cust3", new HashSet<>(Arrays.asList("n3", "n2"))));
        customerDrinks.add(new CustomerDrink("Cust4", new HashSet<>(Arrays.asList("n4"))));
        customerDrinks.add(new CustomerDrink("Cust5", new HashSet<>(Arrays.asList("n3", "n4", "n3", "n5", "n7", "n4"))));
        int totalCustomerSize = 5;//TODO:
        Set allCustomers = new HashSet<>();
        // create a drink to customers collection
        Map<String, DrinkToCustomer> drinkToCustomers = new HashMap<>();

        drinkToCustomers.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(new Comparator<DrinkToCustomer>() {
                    @Override
                    public int compare(DrinkToCustomer o1, DrinkToCustomer o2) {
                        return o2.customers.size() - o1.customers.size();
                    }
                }))
                .forEach(entry -> System.out.println(entry));
        /*for (CustomerDrink cd : customerDrinks) {
            for (String drink : cd.drinks) {
                HashSet customers = new HashSet();
                customers.add(cd.customer);
                DrinkToCustomer dc = new DrinkToCustomer(drink, customers);
                if (drinkToCustomers.containsKey(drink)) {//
                    DrinkToCustomer drinkToCustomer = drinkToCustomers.get(drink);
                    drinkToCustomer.customers.add(cd.customer); }
                else
                    { drinkToCustomers.put(drink, dc); } } }
                    //sort by cust size desc - ugly
                    LinkedList<Set<Map.Entry<String, DrinkToCustomer>>> lst = new LinkedList<Set<Map.Entry<String, DrinkToCustomer>>> (drinkToCustomers.entrySet());
                    Collections.sort(lst, new Comparator<String, DrinkToCustomer>() {
                        @Override
                        public int compare(Map.Entry<String, DrinkToCustomer> o1, Map.Entry<String, DrinkToCustomer> o2) {
                            return o2.getValue().customers.size() - o1.getValue().customers.size();
                        }
                    }); //greedily get all customers upto customer size
                    Set drinks = new HashSet<>();
                    for ( Map.Entry entry: lst) {
                        DrinkToCustomer dc = entry.getValue();
                        String drink = entry.getKey();
                        if (allCustomers.size() >= totalCustomerSize)
                        { System.out.println(drinks); break; }
                        else { for (String customer: dc.customers) { if (!allCustomers.contains(customer) ) { allCustomers.add(customer); drinks.add(drink); } } } }
                }*/
    }
}