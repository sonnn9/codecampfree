package com.son.oop.generics;
// Declares the package. Organizes code and avoids name collisions.

public class Pair<K,V> {
    //            ^ ^
    // Two generic type parameters:
    // K = type of the "key"
    // V = type of the "value"
    // They make the class type-safe and reusable (e.g., Pair<String, Integer>).

    private final K key;
    private final V value;
    // Two fields: both are 'final' → this class is immutable once constructed.

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    // Constructor: requires a key and a value to create a complete Pair.

    public K key() {
        return key;
    }
    // Accessor (getter) for the key. Method is named like a "record" accessor.

    public V value() {
        return value;
    }
    // Accessor (getter) for the value.
}
