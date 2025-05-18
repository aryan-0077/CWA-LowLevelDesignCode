package Correct_Code.Client_Code;

import Correct_Code.ConcreteRenderingMethods.*;
import Correct_Code.ConcreteShapes.*;
import Correct_Code.*;

// DrawingApp.java
public class DrawingApp {
    public static void main(String[] args) {
        Renderer rasterRenderer = new RasterRenderer();
        Renderer vectorRenderer = new VectorRenderer();
        Shape rasterCircle = new Circle(rasterRenderer, 5);
        Shape vectorCircle = new Circle(vectorRenderer, 5);
        Shape rasterRectangle = new Rectangle(rasterRenderer, 10, 5);
        Shape vectorRectangle = new Rectangle(vectorRenderer, 10, 5);
        rasterCircle.draw();
        vectorCircle.draw();
        rasterRectangle.draw();
        vectorRectangle.draw();
    }
}

/*

Output :

Raster Rendering: Drawing Circle with radius 5.0
Vector Rendering: Drawing Circle with radius 5.0
Raster Rendering: Drawing Rectangle with width 10.0 and height 5.0
Vector Rendering: Drawing Rectangle with width 10.0 and height 5.0

*/