package Ugly_Code.Concrete_Beverages;

public class Coffee {
    public void prepare() {
        boilWater();
        brewCoffee();
        pourInCup();
        addSugarAndMilk();
    }

    private void boilWater() {
        System.out.println("Boiling water...");
    }

    private void brewCoffee() {
        System.out.println("Brewing coffee...");
    }

    private void pourInCup() {
        System.out.println("Pouring into cup...");
    }

    private void addSugarAndMilk() {
        System.out.println("Adding sugar and milk...");
    }
}