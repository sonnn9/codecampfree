package com.son.oop.shapes;

public class Rectangle extends Shape{
    private final double w, h;
    public Rectangle(double w, double h) {
        this.w = w;
        this.h = h;
    }
    @Override public double area() {
        return w * h;
    }

    public static void main( String[] args ) {
        Rectangle rectangle = new Rectangle(2.0, 3.0);
        System.out.println(rectangle.area());
    }
}
