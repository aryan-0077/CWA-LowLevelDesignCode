package Correct_Code.ConcreteRenderingMethods;

import Correct_Code.Renderer;

// VectorRenderer.java - Concrete implementation for Vector Rendering
public class VectorRenderer implements Renderer {
    @Override
    public void renderCircle(double radius) {
        System.out.println("Vector Rendering: Drawing Circle with radius " + radius);
    }
    @Override
    public void renderRectangle(double width, double height) {
        System.out.println("Vector Rendering: Drawing Rectangle with width " + width + " and height " + height);
    }
}