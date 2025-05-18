package Good_Code.Beverage_Without_Hook.Concrete_Beverages;

import Good_Code.Beverage_Without_Hook.Beverage;

public class TeaBeverage extends Beverage {

    @Override
    public void brew() {
        System.out.println("Steeping tea bag...");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding lemon...");
    }
}