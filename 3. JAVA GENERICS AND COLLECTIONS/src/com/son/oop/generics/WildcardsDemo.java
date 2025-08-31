package com.son.oop.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * WildcardsDemo — demonstrates PECS and why we use "? extends" vs "? super".
 *
 * PECS rule:
 *   - Producer Extends: use "? extends T" when the list PRODUCES T-values for you to READ.
 *     You can iterate and read elements as T (or a supertype), but you cannot safely add elements.
 *   - Consumer Super:   use "? super T"   when the list CONSUMES T-values that you WRITE.
 *     You can safely add T (and its subclasses), but reads come out as Object without a cast.
 *
 * Invariance reminder:
 *   List<Integer> is NOT a subtype of List<Number>. Use wildcards to make APIs flexible.
 */
public class WildcardsDemo {

    /**
     * Sums any list whose element type is a subtype of Number.
     *
     * We declare the parameter as List<? extends Number> because:
     *  - We only need to READ values (treating each as a Number).
     *  - The actual list could be List<Integer>, List<Double>, List<BigDecimal>, etc.
     *  - With "? extends", adding elements is not allowed (except null) since the exact subtype is unknown.
     *
     * Precision note:
     *  - Using doubleValue() may lose precision for BigDecimal/BigInteger.
     *    That’s OK for a demo; for money/precise decimals, use BigDecimal arithmetic.
     *
     * Time complexity: O(n)
     */
    static double sumOf(List<? extends Number> numbers) {
        double total = 0.0;
        for (Number n : numbers) total += n.doubleValue(); // read as Number (safe)
        // numbers.add(1); // ❌ not allowed: exact element type is unknown (could be Double list)
        return total;
    }

    /**
     * Adds integers 1..3 into a destination list that can accept Integer values.
     *
     * We declare the parameter as List<? super Integer> because:
     *  - We need to WRITE Integer values into the list.
     *  - The destination could be List<Integer>, List<Number>, or List<Object>.
     *    All of these can accept an Integer (since Integer is-a Number is-a Object).
     *
     * Reads:
     *  - Reading from List<? super Integer> gives elements typed as Object (not Integer)
     *    because the list might actually be a List<Object> or List<Number>.
     */
    static void addInts(List<? super Integer> dst) {
        for (int i = 1; i <= 3; i++) dst.add(i); // safe to add Integer
        // Example: reads come out as Object (no type guarantee at compile time)
        // Object first = dst.get(0);     // OK
        // Integer firstInt = dst.get(0); // ❌ compile-time error without a cast
        // Integer firstInt = (Integer) dst.get(0); // ✅ works with explicit cast
    }

    public static void main(String[] args) {
        // ----- CONSUMER (super) demo -----
        // We can pass List<Integer> to addInts, because "? super Integer" allows
        // List<Integer>, List<Number>, or List<Object>.
        List<Integer> ints = new ArrayList<>();
        addInts(ints);

        // These would also compile:
        // List<Number> nums = new ArrayList<>();
        // addInts(nums); // OK
        // List<Object> objs = new ArrayList<>();
        // addInts(objs); // OK

        // ----- PRODUCER (extends) demo -----
        // List.of(...) returns an immutable list; that's fine since sumOf only READS.
        List<Double> doubles = List.of(2.5, 3.5);

        System.out.println("Sum ints = " + sumOf(ints));       // sums 1+2+3
        System.out.println("Sum doubles = " + sumOf(doubles)); // sums 2.5+3.5
    }
}
