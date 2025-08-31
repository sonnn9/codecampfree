package com.son.oop.generics;

import java.util.List;

/**
 * GenericUtils — a small, non-instantiable utility class with generic helpers for lists.
 *
 * Design:
 * - 'final' + private constructor => the class cannot be subclassed or instantiated.
 * - Static generic methods work with any element type, determined at the call site.
 *
 * Notes:
 * - Both methods return null when the input list is null or empty (simple for demos).
 *   In production code, consider returning Optional<T> to make "no value" explicit.
 */
public final class GenericUtils {

    /**
     * Private constructor prevents 'new GenericUtils()'.
     * This is the standard "utility class" pattern.
     */
    private GenericUtils() {}

    /**
     * Returns the first element of the given list, or null if the list is null or empty.
     *
     * <T> — Type parameter:
     *   The caller decides the element type. For List<String>, this returns a String.
     *   For List<Student>, it returns a Student, etc.
     *
     * Behavior:
     *   - If list == null or list.isEmpty(), we return null (nothing to return).
     *   - Otherwise, we return list.get(0) (constant-time access on ArrayList).
     *
     * Complexity: O(1)
     *
     * Caveat:
     *   - If the first element itself is null, this method returns null as well.
     */
    public static <T> T first(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        return list.get(0);
    }

    /**
     * Returns the minimum element by natural ordering, or null if the list is null or empty.
     *
     * <T extends Comparable<T>> — Bounded type parameter:
     *   - Ensures each element T knows how to compare itself to another T via compareTo.
     *   - This lets us call cur.compareTo(best) safely at compile-time.
     *
     * Algorithm:
     *   - Start with best = first element.
     *   - Scan the rest; if cur < best, update best.
     *   - Return best at the end.
     *
     * Complexity: O(n) time, O(1) extra space.
     *
     * Caveats:
     *   - If any element is null, calling compareTo on it will throw NullPointerException.
     *     If nulls are possible, you should either skip them or define how to order nulls.
     *   - For custom domain classes, ensure Comparable<T> is implemented consistently with equals.
     */
    public static <T extends Comparable<T>> T min(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        T best = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            T cur = list.get(i);
            if (cur.compareTo(best) < 0) best = cur;
        }
        return best;
    }
}

/*
USAGE EXAMPLES:

List<Integer> nums = List.of(7, 3, 5);
Integer a = GenericUtils.first(nums); // -> 7
Integer m = GenericUtils.min(nums);   // -> 3

List<String> names = List.of("Linh", "An", "Binh");
String f = GenericUtils.first(names); // -> "Linh"
String s = GenericUtils.min(names);   // -> "An" (lexicographic)

OPTIONAL IMPROVEMENTS (if you need more flexibility):

1) Comparator-based overload (supports non-Comparable types and custom orderings):
   public static <T> T min(List<T> list, Comparator<? super T> cmp) { ... }

2) Optional-returning variants (avoid returning null and force the caller to handle empties):
   public static <T> Optional<T> firstOpt(List<T> list) { ... }
   public static <T extends Comparable<? super T>> Optional<T> minOpt(List<T> list) { ... }

3) Slightly more general Comparable bound (common best practice):
   // use Comparable<? super T> instead of Comparable<T> in some APIs.

4) Null-tolerant min (if lists may contain nulls):
   // define how to treat nulls (e.g., treat null as "largest" or "smallest") or filter them out first.
*/
