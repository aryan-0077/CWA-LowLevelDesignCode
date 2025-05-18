package Good_Code.Beverage_With_Hook.Concrete_Beverage_With_Hook;

import Good_Code.Beverage_With_Hook.BeverageWithHook;

public class CustomCoffee extends BeverageWithHook {
    @Override
    public void brew() {
        System.out.println("Brewing coffee...");
    }
    @Override
    public void addCondiments() {
        System.out.println("Adding sugar and milk...");
    }
    // Suppose this customer doesn't want condiments
    @Override
    public boolean customerWantsCondiments() {
        return false;
    }
}
