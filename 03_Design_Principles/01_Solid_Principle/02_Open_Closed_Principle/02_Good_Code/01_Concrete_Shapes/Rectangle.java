package Open_Closed_Principle.Good_Code.Concrete_Shapes;

import Open_Closed_Principle.Good_Code.Shape;

public class Rectangle extends Shape {
    private double width;
    private double height;
    @Override
    public double calculateArea() {
        return width * height;
    }
}
