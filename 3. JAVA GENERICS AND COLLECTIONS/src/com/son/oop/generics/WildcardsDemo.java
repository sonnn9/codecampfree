package com.son.oop.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Wildcards demo showing the PECS rule:
 *
 *  - Producer  => "? extends T"  (you READ/consume values safely; writing/add is not allowed)
 *  - Consumer  => "? super T"    (you WRITE/produce T values safely; reads come out as Object)
 *
 * Why?
 *  - "? extends Number" can be a List<Integer>, List<Double>, List<BigDecimal>, ...
 *    The only operation type-safe across all of them is reading as Number.
 *  - "? super Integer" can be a List<Integer>, List<Number>, or List<Object>.
 *    You can safely add Integer into any of those (because Integer is-a Number is-a Object).
 */
public class WildcardsDemo {

    /**
     * Sums any list whose element type is a subtype of Number.
     * This uses "? extends Number" because the list is a PRODUCER of values for us to READ.
     *
     * NOTE:
     *  - You cannot add to 'numbers' here (except 'null') because the exact element type is unknown.
     *  - Using doubleValue() may lose precision for BigDecimal/BigInteger, which is acceptable for a demo.
     *    Use BigDecimal arithmetic in real finance code.
     *
     * Time complexity: O(n)
     */
    static double sumOf(List<? extends Number> numbers) {
        double total = 0.0;
        for (Number n : numbers) total += n.doubleValue();
        return total;
    }

    /**
     * Adds 1..3 into a destination list that can ACCEPT Integers.
     * This uses "? super Integer" because the list is a CONSUMER that we WRITE into.
     *
     * Accepts:
     *  - List<Integer>
     *  - List<Number>
     *  - List<Object>
     *
     * READS:
     *  - Reading back from List<? super Integer> yields Object (not Integer) without a cast.
     */
    static void addInts(List<? super Integer> dst) {
        for (int i = 1; i <= 3; i++) dst.add(i);
        // Example (reads come out as Object):
        // Object first = dst.get(0);          // OK
        // Integer i0 = dst.get(0);            // ❌ Compile error (needs cast)
        // Integer i0 = (Integer) dst.get(0);  // ✅ With explicit cast
    }

    public static void main(String[] args) {
        // -------- CONSUMER (super) demo --------
        List<Integer> ints = new ArrayList<>();
        addInts(ints); // "? super Integer" accepts List<Integer> / List<Number> / List<Object>

        // These also compile:
        // List<Number> nums = new ArrayList<>();
        // addInts(nums); // OK
        // List<Object> objs = new ArrayList<>();
        // addInts(objs); // OK

        // -------- PRODUCER (extends) demo --------
        List<Double> doubles = List.of(2.5, 3.5); // immutable list is fine for reading/summing

        System.out.println("Sum of all ints:    " + sumOf(ints));
        System.out.println("Sum of all doubles: " + sumOf(doubles));

        // Key takeaways:
        // - Use "? extends T" when your method only needs to READ values as T (producer).
        // - Use "? super T"   when your method needs to WRITE values of type T (consumer).
        // - This is the PECS rule: Producer Extends, Consumer Super.
    }
}
