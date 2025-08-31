package com.son.oop.student;

import java.util.*;
import java.util.stream.Collectors;

/**
 * StudentRegistry
 *
 * Purpose:
 * - In-memory registry that stores Student objects, and provides basic operations:
 *   add, read-all (as an unmodifiable copy), find by id, filter by major,
 *   sort by GPA (desc) then name, group by major, and build a text report.
 *
 * Design notes:
 * - Internal storage is a mutable ArrayList (append-friendly).
 * - all() returns List.copyOf(data) → callers cannot mutate the registry from outside.
 * - findById returns Optional<Student> to express "may not exist".
 * - groupByMajor uses LinkedHashMap to preserve key insertion order in the report.
 *
 * Caveats:
 * - This is not thread-safe. If multiple threads access/modify the same registry,
 *   wrap with synchronization or use a concurrent design.
 * - filterByMajor assumes Student.getMajor() is non-null. If majors can be null,
 *   add a null check before equalsIgnoreCase.
 */
public class StudentRegistry {

    /** Internal storage (append order). */
    private final List<Student> data = new ArrayList<>();

    /**
     * Adds a student to the registry.
     * Complexity: amortized O(1).
     */
    public void add(Student s) { data.add(s); }

    /**
     * Returns an unmodifiable snapshot (shallow copy) of all students.
     * Callers cannot add/remove elements to this returned list.
     * Complexity: O(n) to copy references.
     */
    public List<Student> all() { return List.copyOf(data); }

    /**
     * Finds a student by id.
     * Linear scan: O(n).
     *
     * Returns:
     * - Optional.of(student) if found
     * - Optional.empty() if not found
     */
    public Optional<Student> findById(int id) {
        for (Student s : data) if (s.getId() == id) return Optional.of(s);
        return Optional.empty();
    }

    /**
     * Returns a list of students whose major equals the given major (case-insensitive).
     * Complexity: O(n).
     *
     * Note:
     * - Assumes s.getMajor() != null. If nulls are possible, guard with a null check:
     *   if (s.getMajor() != null && s.getMajor().equalsIgnoreCase(major)) ...
     * - Returns a new mutable list containing the matches.
     */
    public List<Student> filterByMajor(String major) {
        List<Student> out = new ArrayList<>();
        for (Student s : data) if (s.getMajor().equalsIgnoreCase(major)) out.add(s);
        return out;
    }

    /**
     * Returns a new list sorted by GPA descending, then by name ascending.
     * Steps:
     * - Make a defensive copy so the registry's ordering isn't mutated.
     * - Sort with a Comparator: comparingDouble(...).reversed().thenComparing(...)
     *
     * Complexity: O(n log n).
     */
    public List<Student> sortByGpaDescThenName() {
        List<Student> copy = new ArrayList<>(data);
        copy.sort(Comparator.comparingDouble(Student::getGpa).reversed()
                .thenComparing(Student::getName));
        return copy;
    }

    /**
     * Groups students by major into a Map<major, List<Student>>.
     *
     * Choice of map:
     * - LinkedHashMap preserves insertion order of keys (majors) based on the
     *   first time each major appears while iterating 'data'.
     * - If you want keys alphabetically sorted, consider TreeMap instead.
     *
     * Complexity: O(n) to iterate and append to lists (amortized).
     */
    public Map<String, List<Student>> groupByMajor() {
        Map<String, List<Student>> map = new LinkedHashMap<>();
        for (Student s : data) {
            map.computeIfAbsent(s.getMajor(), k -> new ArrayList<>()).add(s);
        }
        return map;
    }

    /**
     * Builds a text report with:
     * - total count
     * - global average GPA
     * - per-major average GPA and top 3 students by GPA (desc), then name
     *
     * Output is meant for human reading (logs/console).
     * For API or UI, expose structured data instead of a preformatted string.
     *
     * Complexity:
     * - O(n) to compute global average
     * - O(n) to group
     * - For each major, sorting to pick top-3 is O(m log m); total across majors ≤ O(n log n)
     */
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Student Report ===\n");
        sb.append("Total: ").append(data.size()).append("\n");

        // Global average GPA
        double avg = 0.0;
        for (Student s : data) avg += s.getGpa();
        if (!data.isEmpty()) avg /= data.size();
        sb.append("Avg GPA (all): ").append(String.format("%.2f", avg)).append("\n\n");

        // Group by major and compute per-major averages
        Map<String, Double> avgByMajor = new LinkedHashMap<>();
        Map<String, List<Student>> grouped = groupByMajor();
        for (Map.Entry<String, List<Student>> e : grouped.entrySet()) {
            double sum = 0.0;
            for (Student s : e.getValue()) sum += s.getGpa();
            avgByMajor.put(e.getKey(), sum / e.getValue().size());
        }

        // For each major: print avg GPA and the top-3 students by GPA desc, then name
        for (String major : avgByMajor.keySet()) {
            sb.append("[Major] ").append(major)
                    .append(" | Avg GPA: ").append(String.format("%.2f", avgByMajor.get(major))).append("\n");

            // Top 3 by GPA for that major
            List<Student> top = grouped.get(major).stream()
                    .sorted(Comparator.comparingDouble(Student::getGpa).reversed()
                            .thenComparing(Student::getName))
                    .limit(3)
                    .collect(Collectors.toList());

            for (int i = 0; i < top.size(); i++) {
                sb.append("  #").append(i + 1).append(" ").append(top.get(i)).append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}

/*
Possible extensions (optional):

1) Null-safety on major:
   - If majors might be null, sanitize input or normalize null to "Unknown".

2) Comparator injection:
   - Expose a sort method that accepts Comparator<Student> to allow custom sorting strategies.

3) Pagination/limits for report:
   - If the registry grows large, consider limiting items per major, or preselecting top-3 without full sort
     using a bounded priority queue.

4) Data persistence:
   - This is in-memory only. In a real app, back with a database or file store.

5) Stream-based alternatives:
   - filterByMajor: return data.stream().filter(...).toList();
   - findById: data.stream().filter(...).findFirst();
   These can be more concise but have similar complexity.

6) Concurrency:
   - If accessed by multiple threads, guard 'data' or use thread-safe collections or immutability.
*/
