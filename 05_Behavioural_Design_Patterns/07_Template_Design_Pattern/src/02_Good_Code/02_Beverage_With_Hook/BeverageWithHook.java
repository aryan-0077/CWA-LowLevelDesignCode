package Good_Code.Beverage_With_Hook;

public abstract class BeverageWithHook {
    // The template method with a hook
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        // Only add condiments if the customer wants them
        if (customerWantsCondiments()) addCondiments();
    }

    void boilWater() {System.out.println("Boiling water...");}

    void pourInCup() {System.out.println("Pouring into cup...");}

    public abstract void brew();

    public abstract void addCondiments();

    // Hook method with default behavior
    public boolean customerWantsCondiments() {return true;}
}
