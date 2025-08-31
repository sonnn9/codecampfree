package com.son.oop.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * RawTypesDemo — illustrates why RAW TYPES are dangerous and how GENERICS prevent bugs.
 *
 * Raw type = using a generic class without its type parameter (e.g., List instead of List<String>).
 * Consequences:
 *  - The compiler cannot enforce element type safety.
 *  - You get "unchecked" warnings and potential ClassCastException at runtime.
 *
 * When (rarely) to use raw types:
 *  - Almost never in new code. They exist mainly for backward compatibility with pre-Java 5 APIs.
 *  - If you don't know the element type but only need to READ (no adds), prefer List<?> (wildcard) over raw List.
 */
public class RawTypesDemo {
    public static void main(String[] args) {

        // ---------------- RAW TYPE (anti-pattern) ----------------
        // 'List raw' is a raw type. The compiler has NO knowledge about element type,
        // so it allows mixing different kinds of objects in the same list.
        List raw = new ArrayList(); // ⚠️ raw type: NOT type-safe (will also raise a compiler warning)
        raw.add("hello");           // allowed
        raw.add(123);               // allowed (because the type is unknown to the compiler)
        // compiles fine, but sets up a runtime failure later

        try {
            // Enhanced-for over Object forces manual casts for specific use.
            // We attempt to CAST every element to String…
            for (Object o : raw) {
                String s = (String) o; // ⚠️ ClassCastException when o is 123 (an Integer)
                System.out.println(s.toUpperCase());
            }
        } catch (ClassCastException ex) {
            System.out.println("Broke because of raw types: " + ex);
            // You will see something like:
            // java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
        }

        // ---------------- GENERIC TYPE (best practice) ----------------
        // With generics, the compiler KNOWS this list holds Strings only.
        // It will prevent adding non-String elements at COMPILE TIME.
        List<String> safe = new ArrayList<>();
        safe.add("hello");
        // safe.add(123); // ❌ compile-time error: required String, found int/Integer

        // Now you don't need casts when reading; the enhanced-for gives you String directly.
        for (String s : safe) System.out.println(s.toUpperCase());

        // Notes:
        // - If you truly don't know the element type and only need to iterate/read,
        //   prefer List<?> instead of raw List. It is read-only w.r.t. element type (you cannot add).
        //   Example:
        //     void printAll(List<?> xs) { for (Object x : xs) System.out.println(x); }
        //
        // - Generics use type erasure at runtime, but the key advantage is COMPILE-TIME checking,
        //   which is exactly what prevents this demo’s ClassCastException in the generic case.
    }
}
