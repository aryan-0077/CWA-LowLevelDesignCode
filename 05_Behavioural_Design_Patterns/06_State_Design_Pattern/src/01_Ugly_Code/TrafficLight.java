package Ugly_Code;

public class TrafficLight {
    private String color;
    public TrafficLight() {
        this.color = "RED";
    }
    public void next() {
        if (color.equals("RED")) {
            color = "GREEN";
            System.out.println("Change to GREEN. Cars go!");
        } else if (color.equals("GREEN")) {
            color = "YELLOW";
            System.out.println("Change to YELLOW. Slow down!");
        } else if (color.equals("YELLOW")) {
            color = "RED";
            System.out.println("Change to RED. Stop!");
        } else if (color.equals("BLINKING")) {
            color = "MAINTENANCE";
            System.out.println("Switching to MAINTENANCE mode...");
        } else if (color.equals("MAINTENANCE")) {
            color = "RED";
            System.out.println("Maintenance done, back to RED!");
        }
        // Potentially more states and conditions...
    }
    public String getColor() {
        return color;
    }
}