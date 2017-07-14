package com.vijay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by vkbalakr on 6/30/17.
 */
public class MinAvgWaitingTimeHeap {
    static class Pizza {
        private int startTime;
        private int duration;
        Pizza(int startTime, int duration) {
            this.startTime = startTime;
            this.duration = duration;
        }
    }

    public static int findAverageWaitTime(List<Pizza> pizzaLst) {//sorted by startTime asc

        int lstSize = pizzaLst.size();
        PriorityQueue<Pizza> qByDurAsc = new PriorityQueue<>(lstSize, new Comparator<Pizza>() {
            public int compare(Pizza p1, Pizza p2) {
                return p1.duration - p2.duration;
            }
        });
        int t=0, pizzaPullTime = 0, waitTime=0,cntOfPizza=0;
        boolean ovenEmpty = true;
        Pizza pizza = null;
        //TODO: pre constraints checks
        while (true) {
            //pop pizza if pullPizzaTime
            if (t != 0 && t == pizzaPullTime) {
                ovenEmpty = true;

                pizza = null;
                if (pizzaLst.isEmpty() && qByDurAsc.isEmpty()) {
                    break;
                }
            }
            //get next pizza
            Pizza nextPizzaInLst = pizzaLst.size() == 0 ? null : pizzaLst.get(0);
            if (nextPizzaInLst != null && t == nextPizzaInLst.startTime) {
                qByDurAsc.add(nextPizzaInLst);
                pizzaLst.remove(0);
                //System.out.println("Add to Q startTime:" + t);
            }
            //add next pizza
            if (ovenEmpty) {
                pizza = qByDurAsc.poll();//pull min duration pizza 1st from Q
                pizzaPullTime = t + (pizza != null ? pizza.duration: 0);//when it can be pulled out
                waitTime += t + (pizza != null ? pizza.duration - pizza.startTime :0);
                //System.out.println("waitTime:" + waitTime);
                ovenEmpty = false;
                cntOfPizza++;
                //System.out.println(String.format("pizzaPullTime:%d,cntOfPizza:%d", pizzaPullTime, cntOfPizza));
            }


            t++;//next tick
        }
        return (waitTime / cntOfPizza);

    }

    /*
    Input
    3
0 3
1 9
2 6
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nbrOfCustomers = scan.nextInt();
        List<Pizza> pizzaLst = new ArrayList<>();
        for (int i=0; i < nbrOfCustomers; i++) {
            int startTime = scan.nextInt();
            int duration = scan.nextInt();

            pizzaLst.add(new Pizza(startTime, duration));
        }
        Collections.sort(pizzaLst, new Comparator<Pizza>() {
            public int compare(Pizza p1, Pizza p2) {
                return p1.startTime - p2.startTime;
            }
        });
        System.out.println(findAverageWaitTime(pizzaLst));
    }
}
