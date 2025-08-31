package com.son.oop.collections;

import java.util.*;

/**
 * CollectionsDemo
 *
 * This example shows:
 *  1) How to sort with a Comparator (multi-level: age ↑, then name ↑).
 *  2) How to do a binary search with the SAME Comparator used for sorting.
 *  3) How to use Optional when searching (avoid manual null checks).
 *  4) How to group items into a Map<K, List<V>> with computeIfAbsent.
 *
 * Key ideas:
 *  - Comparator vs Comparable: we use Comparator here to define ordering without
 *    modifying Person (Person does NOT implement Comparable).
 *  - Binary search requires the list to be sorted with the exact same Comparator,
 *    otherwise the result is undefined.
 *  - LinkedHashMap preserves insertion order of keys, making printed reports stable.
 */
public class CollectionsDemo {

    /**
     * Simple data class for the demo.
     * Fields are final → instances are immutable after construction.
     * We define no equals/hashCode on purpose (not needed for this demo),
     * but if you use Person as a key in HashMap/HashSet, you should implement them.
     */
    static class Person {
        final int id;
        final String name;
        final int age;

        Person(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    public static void main(String[] args) {
        // Create a mutable ArrayList from an immutable List.of(...) literal.
        // We want mutability so we can sort the list in-place.
        List<Person> people = new ArrayList<>(List.of(
                new Person(1, "Linh", 23),
                new Person(2, "Hoa", 19),
                new Person(3, "An", 23),
                new Person(4, "Binh", 28)
        ));

        // --- SORTING ---
        // Sort by age ascending, then by name ascending.
        // Comparator.comparingInt extracts an int key (p.age).
        // thenComparing chains a second key extractor (p.name).
        // Time complexity: O(n log n).
        people.sort(
                Comparator.comparingInt((Person p) -> p.age)
                        .thenComparing(p -> p.name)
        );
        System.out.println("Sort: " + people);
        // Example output: [Hoa(19), An(23), Linh(23), Binh(28)]

        // --- BINARY SEARCH ---
        // Requirement: the list MUST already be sorted with the SAME comparator,
        // otherwise binarySearch result is undefined.
        //
        // We search for "some Person with age=23 and name='An'".
        // Note: The 'id' field is irrelevant for the comparator, so we can put any id here.
        // If there are multiple matches with (age=23,name='An'), binarySearch returns
        // the index of one matching element, not guaranteed which one.
        int idx = Collections.binarySearch(
                people,
                new Person(-1, "An", 23),
                Comparator.comparingInt((Person p) -> p.age)
                        .thenComparing(p -> p.name)
        );
        System.out.println("Binary search 'An,23' => index " + idx);
        // If 'idx' >= 0, the element exists at that index.
        // If 'idx' < 0, the element was not found; (-idx - 1) is the insertion point.

        // --- OPTIONAL FIND ---
        // Find by id using streams.
        // findFirst returns Optional<Person> — we can map it to string or provide a default.
        Optional<Person> found = people.stream()
                .filter(p -> p.id == 4)
                .findFirst();

        System.out.println(
                "Find id=4: " + found.map(Object::toString).orElse("không thấy")
        );
        // If found, prints the Person's toString(); otherwise prints "không thấy".
        // Using Optional encourages explicit handling of the "absent" case.

        // --- GROUPING ---
        // Group people by age using a Map<Integer, List<Person>>.
        // We choose LinkedHashMap to preserve the order in which keys appear (insertion order),
        // so printing the map yields a stable order across runs after the sort above.
        Map<Integer, List<Person>> byAge = new LinkedHashMap<>();

        // computeIfAbsent: if the key is missing, create a new ArrayList and put it, then return it.
        // This avoids manual "if (!map.containsKey(age)) put(new ArrayList<>())" boilerplate.
        for (Person p : people) {
            byAge.computeIfAbsent(p.age, k -> new ArrayList<>()).add(p);
        }
        System.out.println("Group by age: " + byAge);
        // Example: {19=[Hoa(19)], 23=[An(23), Linh(23)], 28=[Binh(28)]}

        // Notes:
        // - If you instead used HashMap, the key order when printing could look "random".
        // - For sorted keys automatically, use TreeMap<Integer, List<Person>> (O(log n) operations).
    }
}
