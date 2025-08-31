package com.son.oop.generics;

import java.util.ArrayList;
import java.util.List;

public class RawTypesDemo {
    public static void main(String[] args) {
        List raw = new ArrayList(); // raw type : not safe
        raw.add("hello");
        //raw.add(123);

        try {
            for (Object o : raw) {
                String s = (String) o;
                System.out.println(s.toUpperCase());
            }
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }

        List<String> safe = new ArrayList<>();
        safe.add("hello");
        //safe.add(123); // compile-time error, safe type
        for (String s : safe) {
            System.out.println(s.toUpperCase());
        }

    }
}
