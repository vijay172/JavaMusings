package com.vijay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vkbalakr on 7/11/17.
 */
public class ReverseLinkedList {

    public class LinkedList<T> {
        private T element;
        private LinkedList<T> next;

        public LinkedList(T element, LinkedList<T> next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public LinkedList<T> getNext() {
            return next;
        }
    }

    public static <T> LinkedList<T> reverse(final LinkedList<T> original) {

        LinkedList next = original.next;
        original.next = null;//reversed last elemnt

        LinkedList othersReversed = reverse(next);

        next.next = original;//why at the end
        return othersReversed;

    }

   /* @Test
    public void reverseLinkedList() {
        final LinkedList<String> three = new LinkedList<>("3", null);
        final LinkedList<String> two = new LinkedList<>("2", three);
        final LinkedList<String> one = new LinkedList<>("1", two);

        final LinkedList<String> reversed = LinkedList.reverse(one);

        assertEquals("3", reversed.getElement());
        assertEquals("2", reversed.getNext().getElement());
        assertEquals("1", reversed.getNext().getNext().getElement());
    }
*/

   public interface GOP<A, B> {
       public B performOperation(A a);
   }

   public static class StringGOP implements GOP<String, Integer> {
       public Integer performOperation(String s) {
           return s.length();
       }
   }

   public static <A,B> List<B> mapList(final List<A> lst, final GOP<A,B> op) {
       ArrayList<B> lstNew = new ArrayList<>(lst.size());
       for (A a: lst) {
           lstNew.add(op.performOperation(a));
       }
       return lstNew;
   }
    public static void main(String[] args) {
        /* final LinkedList<String> three = new LinkedList<>("3", null);
        final LinkedList<String> two = new LinkedList<>("2", three);
        final LinkedList<String> one = new LinkedList<>("1", two);

       final LinkedList<String> reversed = LinkedList.reverse(one);

        assertEquals("3", reversed.getElement());
        assertEquals("2", reversed.getNext().getElement());
        assertEquals("1", reversed.getNext().getNext().getElement());*/

        /*List<Integer> nbrLst = new ArrayList<>();
        nbrLst.add(1);
        nbrLst.add(2);
        nbrLst.add(3);
        nbrLst.add(4);
        Integer[] nbr1 = (Integer[])nbrLst.stream().map((nbr) -> nbr * 2).toArray();
        System.out.println(nbr1);*/

        List<String> strLst = Arrays.asList("ace","band","try1");
        List<Integer> lstInts = mapList(strLst, new StringGOP());
        System.out.println(lstInts);
    }


}
