package Good_Code.Beverage_With_Hook;

import Good_Code.Beverage_With_Hook.Concrete_Beverage_With_Hook.CustomCoffee;

public class BeverageWithHookDemo {
    public static void main(String[] args) {
        BeverageWithHook coffee = new CustomCoffee();
        System.out.println("Making custom coffee...");
        coffee.prepareRecipe();
    }
}
/*

Output :
Making custom coffee...
Boiling water...
Brewing coffee...
Pouring into cup...

*/