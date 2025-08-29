package com.son.oop.basics;

public class Person {
    private String name; // encapsulation: private field
    private int age;

    // constructor
    public Person(String name, int age) {
        setName(name);
        setAge(age);
    }

    public void setName(String name) {
        //validate
        if(name == null || name.isBlank()) throw new IllegalArgumentException("name must not be blank");
        this.name = name.trim();
    }

    public void setAge(int age) {
        //validate
        if(age < 0) throw new IllegalArgumentException("age must be >=0");
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{name='%s', age='%d'}".formatted(name, age);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Person that)) return false; // pattern matching JDK 16+
        return  age == that.age && name.equals(that.name);
    }

    public static void main(String[] args) {
        Person p1 = new Person("John", 20);
        Person p2 = new Person("John", 21);
        System.out.println(p1.equals(p2));
    }
}
