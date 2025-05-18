package Correct_Code.Coffee_Decorator.ConcreteDecorators;

import Correct_Code.Coffee;
import Correct_Code.Coffee_Decorator.CoffeeDecorator;

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }
    @Override
    public double getCost() {
        return coffee.getCost() + 0.25;
    }
}

