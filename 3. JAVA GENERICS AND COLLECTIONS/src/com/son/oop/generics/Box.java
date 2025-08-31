package com.son.oop.generics;
// Names the package. Helps organize code and avoid class-name clashes.

public class Box<T> {
    // ^^^^^^^^
    // Generic type parameter `T`:
    // Think of `T` as a placeholder for "the type you will store".
    // At compile time you’ll bind `T` to a real type, e.g. Box<String> or Box<Integer>.
    // Benefit: compile-time type safety (fewer ClassCastException at runtime).

    private T value;
    // A single field that holds one value of type T.
    // This class is a "mutable container": the value can change over time.

    public Box(T value) {
        this.value = value;
    }
    // Constructor: requires an initial value.
    // Note: `value` can be null (there’s no null check). That’s allowed but be mindful when using it.

    public T getValue() {
        return value;
    }
    // Getter: returns whatever type T was bound to (e.g., String, Integer, Student, ...)

    public void setValue(T value) {
        this.value = value;
    }
    // Setter: lets you replace the stored value.
    // Because there is a public setter, the class is MUTABLE.
    // In concurrent code this is not thread-safe without extra synchronization.

    @Override
    public String toString() {
        return "Box (value=" + value + ")";
    }
    // Human-readable representation.
    // This delegates to value.toString(); if value is null, you'll see "Box (value=null)".
}
