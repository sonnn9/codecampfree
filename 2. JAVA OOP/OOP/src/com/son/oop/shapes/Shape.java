package com.son.oop.shapes;

public abstract class Shape implements Drawable {
    public abstract double area();
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
