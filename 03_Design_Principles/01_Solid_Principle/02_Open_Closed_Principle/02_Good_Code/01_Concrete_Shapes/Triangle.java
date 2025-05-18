package Open_Closed_Principle.Good_Code.Concrete_Shapes;

import Open_Closed_Principle.Good_Code.Shape;

public class Triangle extends Shape {
    private double base;
    private double height;
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}
