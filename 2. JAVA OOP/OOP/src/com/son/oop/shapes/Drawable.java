package com.son.oop.shapes;

public interface Drawable {
    default void draw() {
        System.out.println("Drawing " + this);
    }
}

