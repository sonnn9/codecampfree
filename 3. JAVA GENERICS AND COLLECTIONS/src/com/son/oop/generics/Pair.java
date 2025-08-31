package com.son.oop.generics;

/**
 * Pair<K,V> — a tiny, immutable tuple of two values.
 *
 * What K and V mean:
 * - K is the type of the "key" component.
 * - V is the type of the "value" component.
 * You choose concrete types at the call site (e.g., Pair<String, Integer>).
 *
 * Why generics here?
 * - Compile-time type safety: a Pair<String,Integer> cannot accidentally hold (String, String).
 * - Reusability: one class works for any two types without casting.
 *
 * Immutability:
 * - Both fields are final and there are no setters, so once constructed the Pair cannot change.
 * - This makes instances thread-safe for read sharing.
 *
 * Null handling:
 * - This implementation allows null for key and/or value. If you want to forbid nulls,
 *   validate in the constructor (see optional refinements below).
 *
 * Type erasure note:
 * - At runtime the JVM erases the generic parameters, so you cannot check
 *   `instanceof Pair<String, Integer>`; only `instanceof Pair<?,?>`.
 */
public class Pair<K,V> { // K, V are type parameters decided by the caller

    /** The "key" component (may be null unless you forbid it). */
    private final K key;

    /** The "value" component (may be null unless you forbid it). */
    private final V value;

    /**
     * Creates a new Pair consisting of the given key and value.
     * @param key   the key component (can be null in this design)
     * @param value the value component (can be null in this design)
     *
     * No validation is performed here for brevity. For stricter invariants,
     * add checks (e.g., Objects.requireNonNull) in real-world code.
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key component.
     * @return the key (possibly null)
     *
     * Method name mirrors Java records' style (key()) for concision.
     */
    public K key() { return key; }

    /**
     * Returns the value component.
     * @return the value (possibly null)
     */
    public V value() { return value; }
}

/*
USAGE EXAMPLES:

Pair<String, Integer> age = new Pair<>("age", 18);
String k = age.key();        // "age"
Integer v = age.value();     // 18

Pair<Integer, String> idName = new Pair<>(101, "An");
// idName.key()   -> 101
// idName.value() -> "An"

COMMON PITFALLS / NOTES:

1) Equality and hashing:
   - This class does not override equals() / hashCode() / toString().
   - Two Pairs with the same contents are not "equal" by default.
   - If you plan to store Pair in HashSet/HashMap or compare by contents, override these methods.

2) Null tolerance:
   - If null is not acceptable for key/value in your domain, add constructor validation:
       this.key = java.util.Objects.requireNonNull(key, "key");
       this.value = java.util.Objects.requireNonNull(value, "value");

3) Type erasure:
   - You cannot do: if (pair instanceof Pair<String,Integer>) { ... }
   - You can only check: if (pair instanceof Pair<?,?>) { ... }

OPTIONAL REFINEMENTS (if needed):

// a) Value-based equality / hashing / string representation:
@Override public boolean equals(Object o) {
  if (this == o) return true;
  if (!(o instanceof Pair<?,?> p)) return false;
  return java.util.Objects.equals(key, p.key) &&
         java.util.Objects.equals(value, p.value);
}
@Override public int hashCode() { return java.util.Objects.hash(key, value); }
@Override public String toString() { return "Pair[" + key + ", " + value + "]"; }

// b) Static factory for readability:
public static <K,V> Pair<K,V> of(K k, V v) { return new Pair<>(k, v); }

// c) Java 16+: concise record alternative (auto-generates accessors/equals/hashCode/toString):
public record Pair<K,V>(K key, V value) { }

// d) Standard library alternatives when a Map.Entry fits better:
java.util.Map.Entry<K,V> e = java.util.Map.entry(k, v);      // immutable entry (Java 9+)
java.util.AbstractMap.SimpleEntry<K,V> mutable = new java.util.AbstractMap.SimpleEntry<>(k, v);
*/
