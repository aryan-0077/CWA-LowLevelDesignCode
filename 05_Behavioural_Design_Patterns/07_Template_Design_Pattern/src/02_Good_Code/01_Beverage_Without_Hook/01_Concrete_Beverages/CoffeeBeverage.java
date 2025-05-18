package Good_Code.Beverage_Without_Hook.Concrete_Beverages;

import Good_Code.Beverage_Without_Hook.Beverage;

public class CoffeeBeverage extends Beverage {
    @Override
    public void brew() {
        System.out.println("Brewing coffee...");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding sugar and milk...");
    }
}
