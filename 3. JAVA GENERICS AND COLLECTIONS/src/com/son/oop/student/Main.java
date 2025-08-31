package com.son.oop.student;

/**
 * Main entry point for the Student Registry demo.
 *
 * What this program does:
 *  1) Creates an in-memory StudentRegistry and seeds it with sample students.
 *  2) Prints all students.
 *  3) Demonstrates Optional usage by finding a student by id.
 *  4) Filters students by major.
 *  5) Sorts students by GPA descending, then by name ascending.
 *  6) Groups students by major and prints counts per group.
 *  7) Prints a formatted report (totals, averages, top-3 per major).
 *
 * Notes:
 *  - This is a console demo for clarity; in real apps you'd expose structured
 *    data to a UI or API layer instead of printing to stdout.
 *  - The registry is in-memory and not persisted.
 */
public class Main {
    public static void main(String[] args) {

        // Create a fresh registry (starts empty).
        StudentRegistry reg = new StudentRegistry();

        // ---- Seed demo data -------------------------------------------------
        // Each Student is immutable and identified by a unique id.
        reg.add(new Student(101, "An",   19, "CS",      3.4));
        reg.add(new Student(102, "Binh", 20, "Math",    3.8));
        reg.add(new Student(103, "Chi",  21, "CS",      3.9));
        reg.add(new Student(104, "Dung", 20, "Physics", 3.2));
        reg.add(new Student(105, "Hoa",  19, "Math",    3.1));
        reg.add(new Student(106, "Linh", 22, "CS",      3.7));

        // ---- Print all students --------------------------------------------
        // all() returns an unmodifiable snapshot; we just iterate and print.
        System.out.println("--- All ---");
        for (Student s : reg.all()) System.out.println(s);

        // ---- Find by id using Optional -------------------------------------
        // findById returns Optional<Student>. Use ifPresentOrElse to handle both cases.
        System.out.println("\n--- Find by id=103 (Optional) ---");
        reg.findById(103).ifPresentOrElse(
                s  -> System.out.println("Found: " + s),   // present branch
                () -> System.out.println("Not found")      // empty branch
        );

        // ---- Filter by major (case-insensitive match) ----------------------
        System.out.println("\n--- Filter by major=CS ---");
        for (Student s : reg.filterByMajor("CS")) System.out.println(s);

        // ---- Sort by GPA desc, then name asc -------------------------------
        // sortByGpaDescThenName returns a new list; original registry order is unchanged.
        System.out.println("\n--- Sort by GPA desc, then name ---");
        for (Student s : reg.sortByGpaDescThenName()) System.out.println(s);

        // ---- Group by major and show counts --------------------------------
        // groupByMajor returns Map<major, List<Student>>.
        // We print the major and the number of students in that group.
        System.out.println("\n--- Group by major ---");
        for (var e : reg.groupByMajor().entrySet()) {
            System.out.println(e.getKey() + " => " + e.getValue().size() + " students");
        }

        // ---- Generate and print a textual report ---------------------------
        // The report includes:
        //  - total number of students
        //  - overall average GPA
        //  - per-major average GPA
        //  - top-3 students by GPA (desc), then name within each major
        System.out.println("\n--- Report ---");
        System.out.println(reg.report());

        // End of demo.
        // Complexity overview (rough):
        //  - Printing all: O(n)
        //  - findById: O(n) linear scan
        //  - filterByMajor: O(n)
        //  - sort: O(n log n)
        //  - groupByMajor: O(n) to build + O(m log m) per-major top-3 sorting (m = size of group)
        //  - report: dominated by grouping and per-group sorting (≤ O(n log n) overall)
    }
}
