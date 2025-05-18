package Ugly_Code.ConcreteShapes;

import Ugly_Code.Shape;

public class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle using Raster Rendering");
    }
}
