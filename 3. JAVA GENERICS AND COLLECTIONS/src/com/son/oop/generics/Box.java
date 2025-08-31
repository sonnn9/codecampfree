package com.son.oop.generics;

/**
 * Box<T> — a minimal, type-safe container for exactly one value.
 *
 * Why generics?
 * - The type parameter <T> is a placeholder for “the element type”.
 * - When you create Box<String>, the compiler enforces that only Strings go in and come out.
 * - This eliminates many ClassCastException bugs at runtime by catching them at compile-time.
 *
 * Notes on design:
 * - This class is MUTABLE (there is a setter). Mutability is convenient for demos,
 *   but shared mutable state requires care in multi-threaded contexts.
 * - The field can be null; if you want to forbid nulls, validate in constructor/setter.
 * - We don’t override equals/hashCode; two Box<T> with same value are not “equal” by default.
 *   Add those methods if you need value-based equality (e.g., to use Box<T> as a key in a Map).
 *
 * Generics & type erasure:
 * - At runtime, the JVM erases T, so you cannot do `instanceof Box<String>` (only `Box<?>`).
 * - You also cannot directly `new T()` or create `T[]` inside this class.
 */
public class Box<T> { // T is a type parameter supplied by the caller (e.g., Box<String>, Box<Integer>)

    /**
     * The stored value of type T. It may be null unless you enforce otherwise.
     * Because this field is not final, the class is mutable.
     */
    private T value;

    /**
     * Constructs a Box holding an initial value.
     * @param value initial value (may be null)
     *
     * Complexity: O(1)
     */
    public Box(T value) {
        this.value = value;
    }

    /**
     * Returns the current value stored in the box.
     * The returned static type matches T chosen by the caller (e.g., String for Box<String>).
     *
     * @return the value (possibly null)
     *
     * Complexity: O(1)
     */
    public T getValue() {
        return value;
    }

    /**
     * Replaces the stored value with a new one.
     * Because of generics, the compiler ensures the argument is a T (type-safe).
     *
     * @param value new value (possibly null)
     *
     * Thread-safety note:
     * - No synchronization is used here. If multiple threads can call setValue/getValue concurrently,
     *   you may need external synchronization or make the class immutable (remove setter, make field final).
     *
     * Complexity: O(1)
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Human-friendly representation for logs/debugging.
     * If value is null, you’ll see "Box (value=null)".
     */
    @Override
    public String toString() {
        return "Box (value=" + value + ")";
    }
}

/*
USAGE EXAMPLES:

Box<String> bs = new Box<>("hello");
String s = bs.getValue();   // "hello"
// bs.setValue(123);        // ❌ compile-time error: 123 is not a String

Box<Integer> bi = new Box<>(42);
bi.setValue(100);           // ok
System.out.println(bi);     // prints: Box (value=100)

OPTIONAL IMPROVEMENTS:

// 1) Immutable variant (safer for concurrency):
public final class Box<T> {
    private final T value;
    public Box(T value) { this.value = value; }
    public T getValue() { return value; }
    @Override public String toString() { return "Box (value=" + value + ")"; }
}

// 2) Factory method for readability:
public static <T> Box<T> of(T v) { return new Box<>(v); }

// 3) Null-safety:
public Box(T value) {
    this.value = java.util.Objects.requireNonNull(value, "value");
}
*/
