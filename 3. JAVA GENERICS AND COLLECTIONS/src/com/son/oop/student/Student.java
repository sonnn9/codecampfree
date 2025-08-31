package com.son.oop.student;

import java.util.Objects;

/**
 * Student is a small, immutable domain model.
 *
 * Key points:
 * - All fields are final, so once a Student is created its state cannot change.
 * - Equality and hash code are based on id only (identity semantics by id).
 *   That means two Student objects with the same id are considered equal,
 *   even if name, age, major, or gpa differ.
 * - toString prints a compact, human-friendly line for logs and debugging.
 *
 * When to use:
 * - Safe as a value placed in collections (List, Set, Map).
 * - Because it's immutable, it is naturally thread-safe for read sharing.
 *
 * Caveats:
 * - If your application assigns ids later (e.g., after saving to a database),
 *   be careful: objects put into a HashSet/HashMap before id is assigned
 *   will behave incorrectly if id changes. Here id is final, so that scenario
 *   is impossible with this class.
 */
public class Student {

    /** Unique identifier for the student. Used for equals and hashCode. */
    private final int id;

    /** Display name of the student. Not used for equality. */
    private final String name;

    /** Age in years. Not used for equality. */
    private final int age;

    /** Major (e.g., "CS", "Math"). Not used for equality. */
    private final String major;

    /** Grade Point Average, typically on a 0.0–4.0 scale. Not used for equality. */
    private final double gpa;

    /**
     * Constructs an immutable Student.
     *
     * No validation is performed here. In a real system you might check:
     * - name not null or blank
     * - age within an expected range
     * - major not null or from an allowed list
     * - gpa within 0.0..4.0 (or your system's scale)
     */
    public Student(int id, String name, int age, String major, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.major = major;
        this.gpa = gpa;
    }

    /** Returns the unique id. */
    public int getId() { return id; }

    /** Returns the student's name. May be null if caller passed null. */
    public String getName() { return name; }

    /** Returns the student's age in years. */
    public int getAge() { return age; }

    /** Returns the student's major. May be null if caller passed null. */
    public String getMajor() { return major; }

    /** Returns the student's GPA. */
    public double getGpa() { return gpa; }

    /**
     * Human-readable representation for logs and debugging.
     * Example: "101 - An | CS | age 19 | GPA 3.40"
     *
     * Note: This is not meant for parsing or UI localization.
     * If you need structured output, expose fields or build DTOs instead.
     */
    @Override
    public String toString() {
        return "%d - %s | %s | age %d | GPA %.2f".formatted(id, name, major, age, gpa);
    }

    /**
     * Equality is defined solely by id.
     *
     * Consequences:
     * - Two Student objects with the same id are considered equal even if other fields differ.
     * - This is a common pattern when id is a stable, unique identifier in your domain.
     * - If you need value-based equality (across multiple fields), change equals/hashCode accordingly.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;               // quick check for reference equality
        if (!(o instanceof Student)) return false;
        Student that = (Student) o;
        return id == that.id;                      // identity semantics by id only
    }

    /**
     * Hash code is computed from id to stay consistent with equals.
     *
     * Contract:
     * - If two objects are equal according to equals, they must have the same hash code.
     * - Using Objects.hash(id) is simple and clear; it is fine for small models.
     */
    @Override
    public int hashCode() { return Objects.hash(id); }
}

/*
Usage tips:

1) As a key in HashMap/HashSet:
   Because equals/hashCode use id only and id is final, Student works well as a key.
   Example:
     Map<Student, Double> attendance = new HashMap<>();

2) In lists and sorting:
   If you need to sort by GPA then name, use a Comparator:
     Comparator<Student> byGpaDescThenName =
         Comparator.comparingDouble(Student::getGpa).reversed()
                   .thenComparing(Student::getName);

3) Validation:
   For robust domain models, consider validating constructor arguments
   or use a factory that enforces invariants.

4) Floating-point accuracy:
   GPA is a double for simplicity. If you need precise decimal arithmetic,
   consider BigDecimal with a defined scale and rounding mode.
*/
