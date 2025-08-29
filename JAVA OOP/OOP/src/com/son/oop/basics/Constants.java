package com.son.oop.basics;

public class Constants {
    private Constants() {}

    public static final String APP_NAME = "JAVA OOP BASICS";
    public static final int MAX_AGE = 150;

    public enum AccountType { SAVINGS, CHECKING }
    public enum AccountStatus { ACTIVE, FROZEN, CLOSED }

    // record (JDK16+)
    public record Book(String isbn, String title, String author) {}

}
