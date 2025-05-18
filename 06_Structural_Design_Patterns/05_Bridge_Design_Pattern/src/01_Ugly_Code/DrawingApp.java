package Ugly_Code;

import Ugly_Code.ConcreteShapes.*;

import java.util.Scanner;

public class DrawingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Display available shapes
        System.out.println("Available shapes:");
        System.out.println("1. Circle");
        System.out.println("2. Rectangle");
        System.out.println("3. Triangle");
        System.out.println("4. Square");
        System.out.println("5. Hexagon");
        System.out.print("Enter the number for desired shape: ");
        int shapeChoice = scanner.nextInt();
        String shapeType = getShapeType(shapeChoice);
        // Display available rendering methods
        System.out.println("nAvailable rendering methods:");
        System.out.println("1. Raster");
        System.out.println("2. Vector");
        System.out.println("3. 3D");
        System.out.println("4. Wireframe");
        System.out.print("Enter the number for desired rendering method: ");
        int renderChoice = scanner.nextInt();
        String renderType = getRenderType(renderChoice);


        // Process the choices
        if (shapeType != null && renderType != null) {
            drawShape(shapeType, renderType);
        } else {
            System.out.println("Invalid selection. Please try again.");
        }
        scanner.close();
    }

    private static String getShapeType(int choice) {
        switch (choice) {
            case 1:
                return "Circle";
            case 2:
                return "Rectangle";
            case 3:
                return "Triangle";
            case 4:
                return "Square";
            case 5:
                return "Hexagon";
            default:
                return null;
        }
    }

    private static String getRenderType(int choice) {
        switch (choice) {
            case 1:
                return "Raster";
            case 2:
                return "Vector";
            case 3:
                return "3D";
            case 4:
                return "Wireframe";
            default:
                return null;
        }
    }

    private static void drawShape(String shapeType, String renderType) {
        // Circle combinations
        if (shapeType.equals("Circle") && renderType.equals("Raster")) {
            System.out.println("Drawing Circle using Raster Rendering");
        } else if (shapeType.equals("Circle") && renderType.equals("Vector")) {
            System.out.println("Drawing Circle using Vector Rendering");
        } else if (shapeType.equals("Circle") && renderType.equals("3D")) {
            System.out.println("Drawing Circle using 3D Rendering");
        } else if (shapeType.equals("Circle") && renderType.equals("Wireframe")) {
            System.out.println("Drawing Circle using Wireframe Rendering");
        }
        // Rectangle combinations
        else if (shapeType.equals("Rectangle") && renderType.equals("Raster")) {
            System.out.println("Drawing Rectangle using Raster Rendering");
        } else if (shapeType.equals("Rectangle") && renderType.equals("Vector")) {
            System.out.println("Drawing Rectangle using Vector Rendering");
        } else if (shapeType.equals("Rectangle") && renderType.equals("3D")) {
            System.out.println("Drawing Rectangle using 3D Rendering");
        } else if (shapeType.equals("Rectangle")
                && renderType.equals("Wireframe")) {
            System.out.println("Drawing Rectangle using Wireframe Rendering");
        }
        // Triangle combinations
        else if (shapeType.equals("Triangle") && renderType.equals("Raster")) {
            System.out.println("Drawing Triangle using Raster Rendering");
        } else if (shapeType.equals("Triangle") && renderType.equals("Vector")) {
            System.out.println("Drawing Triangle using Vector Rendering");
        } else if (shapeType.equals("Triangle") && renderType.equals("3D")) {
            System.out.println("Drawing Triangle using 3D Rendering");
        } else if (shapeType.equals("Triangle") && renderType.equals("Wireframe")) {
            System.out.println("Drawing Triangle using Wireframe Rendering");
        }
        // Square combinations
        else if (shapeType.equals("Square") && renderType.equals("Raster")) {
            System.out.println("Drawing Square using Raster Rendering");
        } else if (shapeType.equals("Square") && renderType.equals("Vector")) {
            System.out.println("Drawing Square using Vector Rendering");
        } else if (shapeType.equals("Square") && renderType.equals("3D")) {
            System.out.println("Drawing Square using 3D Rendering");
        } else if (shapeType.equals("Square") && renderType.equals("Wireframe")) {
            System.out.println("Drawing Square using Wireframe Rendering");
        }
        else {
            System.out.println("Unsupported combination of shape and rendering type");
        }
    }
}

/*

Output 1:
Drawing Circle using Raster Rendering
Drawing Rectangle using Raster Rendering

Output 2 :
Available shapes:
1. Circle
2. Rectangle
3. Triangle
4. Square
5. Hexagon
Enter the number for desired shape: 2
nAvailable rendering methods:
1. Raster
2. Vector
3. 3D
4. Wireframe
Enter the number for desired rendering method: 2
Drawing Rectangle using Vector Rendering

*/