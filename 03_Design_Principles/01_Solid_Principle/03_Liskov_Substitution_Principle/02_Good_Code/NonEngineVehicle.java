package Liskov_Substitution_Principle.Good_Code;

public abstract class NonEngineVehicle extends Vehicle{
    @Override
    public void move() {
        // Movement logic
        System.out.println("Non-Engine Vehicles do not have start engine method");
    }
}
