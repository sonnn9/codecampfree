package com.son.oop.basics;

public class Counter {
    private static int instances =0;
    public Counter() {
        instances++;
    }
    public static int getInstances() {
        return instances;
    }
}
