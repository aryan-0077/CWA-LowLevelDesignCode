package Ugly_Code.ConcreteShapes;

import Ugly_Code.Shape;

public class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle using Raster Rendering");
    }
}