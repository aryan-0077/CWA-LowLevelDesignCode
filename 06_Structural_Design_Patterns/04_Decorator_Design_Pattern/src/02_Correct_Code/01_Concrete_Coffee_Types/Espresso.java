package Correct_Code.ConcreteCoffeeTypes;

import Correct_Code.Coffee;

public class Espresso implements Coffee {
    @Override
    public String getDescription() {
        return "Espresso";
    }
    @Override
    public double getCost() {
        return 2.00;
    }
}
