package com.son.oop.app;

import com.son.oop.basics.Person;
import com.son.oop.basics.Constants;
import com.son.oop.basics.Counter;
import com.son.oop.shapes.*;
import com.son.oop.bank.domain.Account;
import com.son.oop.bank.service.Bank;
import com.son.oop.shapes.Rectangle;
import com.son.oop.shapes.Shape;

import java.awt.*;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println(Constants.APP_NAME);

        var p = new Person("Son", 30);
        System.out.println(p);
        System.out.println("equals test: " + p.equals(new Person("Son", 30)));

        new Counter();
        new Counter();
        new Counter();
        System.out.println("Counter instances: " + Counter.getInstances());

        Shape s1 = new Circle(2);
        Shape s2 = new Rectangle(3, 4);
        System.out.println("Area 1: " + s1.area());
        System.out.println("Area 2: " + s2.area());

        var book = new Constants.Book("978-123456789","JAVA TUTORIALS","JAVA");
        System.out.println("Book: " + book);

        var a1 = new Account("A001",Constants.AccountType.SAVINGS);
        var a2 = new Account("A002",Constants.AccountType.CHECKING);
        a1.deposit(new BigDecimal("50000"));
        a2.deposit(new BigDecimal("20000"));

        var bank = new Bank();
        bank.transfer(a1, a2, new BigDecimal("15000"));
        System.out.println(a1);
        System.out.println(a2);

        var user = new com.son.oop.immutablility.ImmutableUser.Builder()
                .id("U01").name("Alice").build();
        System.out.println("user: " + user.name());
        var user2 = user.withName("Alice Nguyen");
        System.out.println("user2: " + user2.name());
    }
}
