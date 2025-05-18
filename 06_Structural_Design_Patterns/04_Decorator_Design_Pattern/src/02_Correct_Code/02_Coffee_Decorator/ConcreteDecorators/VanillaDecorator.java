package Correct_Code.Coffee_Decorator.ConcreteDecorators;

import Correct_Code.Coffee;
import Correct_Code.Coffee_Decorator.CoffeeDecorator;

public class VanillaDecorator extends CoffeeDecorator {
    public VanillaDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Vanilla";
    }
    @Override
    public double getCost() {
        return coffee.getCost() + 0.75;
    }
}