package com.son.oop.generics;

import java.util.List;

/**
 * A non-instantiable utility class containing generic helpers for lists.
 *
 * <p>Design choices:
 * - class is final + private constructor → purely static utilities
 * - returns null for null/empty inputs to keep call sites simple in demos
 *   (in real apps, consider Optional<T> to force explicit "no value" handling)
 */
public final class GenericUtils {
    /** Prevent instantiation (utility class pattern). */
    private GenericUtils() {}

    /**
     * Returns the first element of the given list, or null if the list is null or empty.
     *
     * <p>Type parameter:
     * <T> is inferred from the provided list. Works for any List<T> (String, Integer, Student, ...).
     *
     * @param list the input list (may be null)
     * @param <T>  the element type of the list
     * @return the first element, or null if list is null/empty
     *
     * <p>Time complexity: O(1)</p>
     */
    public static <T> T first(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        return list.get(0);
    }

    /**
     * Returns the minimum element by natural ordering, or null if list is null/empty.
     *
     * <p>Type bound:
     * &lt;T extends Comparable&lt;T&gt;&gt; means "T must implement Comparable&lt;T&gt;".
     * This allows calling cur.compareTo(best).
     *
     * <p>Assumptions and caveats:
     * - If list contains null elements, compareTo will throw NullPointerException.
     *   (You can skip nulls or define a custom ordering if needed.)
     * - For custom classes, implement Comparable&lt;T&gt; consistently with equals.
     *
     * @param list the input list (may be null)
     * @param <T>  element type that is naturally comparable to itself
     * @return the smallest element by natural order, or null if list is null/empty
     *
     * <p>Time complexity: O(n); Extra space: O(1)</p>
     */
    public static <T extends Comparable<T>> T min(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        T best = list.get(0); // may be null: compareTo(null) will NPE later
        for (int i = 1; i < list.size(); i++) {
            T cur = list.get(i);
            // natural ordering: cur < best ?
            if (cur != null && (best == null || cur.compareTo(best) < 0)) best = cur;
            // Note: the extra null checks make this slightly more tolerant of nulls in the list.
            // If you want strict non-null behavior, remove the null branches and let NPE surface.
        }
        return best;
    }

    /* Optional extensions (not included to keep the API minimal):
     *
     * 1) Comparator-based overload to support arbitrary orderings and non-Comparable types:
     *    public static <T> T min(List<T> list, Comparator<? super T> cmp) { ... }
     *
     * 2) Optional-returning variants to avoid returning null:
     *    public static <T> Optional<T> firstOpt(List<T> list) { ... }
     *    public static <T extends Comparable<? super T>> Optional<T> minOpt(List<T> list) { ... }
     */
}
