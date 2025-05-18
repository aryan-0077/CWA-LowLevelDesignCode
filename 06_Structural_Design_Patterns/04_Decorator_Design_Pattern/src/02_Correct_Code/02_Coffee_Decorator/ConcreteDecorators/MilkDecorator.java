package Correct_Code.Coffee_Decorator.ConcreteDecorators;

import Correct_Code.Coffee;
import Correct_Code.Coffee_Decorator.CoffeeDecorator;

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
    @Override
    public double getCost() {
        return coffee.getCost() + 0.50;
    }
}