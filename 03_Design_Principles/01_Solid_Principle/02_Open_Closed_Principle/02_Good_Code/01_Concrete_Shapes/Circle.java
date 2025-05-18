package Open_Closed_Principle.Good_Code.Concrete_Shapes;

import Open_Closed_Principle.Good_Code.Shape;

public class Circle extends Shape {
    private double radius;
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
