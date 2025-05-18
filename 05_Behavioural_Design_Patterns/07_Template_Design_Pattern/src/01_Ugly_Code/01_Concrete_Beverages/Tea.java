package Ugly_Code.Concrete_Beverages;

public class Tea {
    public void prepare() {
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();
    }

    private void boilWater() {
        System.out.println("Boiling water...");
    }

    private void steepTeaBag() {
        System.out.println("Steeping tea bag...");
    }

    private void pourInCup() {
        System.out.println("Pouring into cup...");
    }

    private void addLemon() {
        System.out.println("Adding lemon...");
    }
}