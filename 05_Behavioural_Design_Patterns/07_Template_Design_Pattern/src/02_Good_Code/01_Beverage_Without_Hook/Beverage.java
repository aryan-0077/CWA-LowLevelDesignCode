package Good_Code.Beverage_Without_Hook;

public abstract class Beverage {
    // The template method - makes sure the algorithm steps are followed
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // Common methods
    void boilWater() {
        System.out.println("Boiling water...");
    }

    void pourInCup() {
        System.out.println("Pouring into cup...");
    }

    // Steps to be customized by subclasses
    public abstract void brew();

    public abstract void addCondiments();
}