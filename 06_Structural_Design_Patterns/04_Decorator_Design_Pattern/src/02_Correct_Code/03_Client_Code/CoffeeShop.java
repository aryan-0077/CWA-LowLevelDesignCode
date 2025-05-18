package Correct_Code.Client_Code;

import Correct_Code.Coffee;
import Correct_Code.Coffee_Decorator.ConcreteDecorators.MilkDecorator;
import Correct_Code.Coffee_Decorator.ConcreteDecorators.SugarDecorator;
import Correct_Code.Coffee_Decorator.ConcreteDecorators.VanillaDecorator;
import Correct_Code.ConcreteCoffeeTypes.*;


public class CoffeeShop {
    public static void main(String[] args) {
        Coffee coffee = new Espresso();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        System.out.println("Order: " + coffee.getDescription());
        System.out.println("Total Cost: $" + coffee.getCost());
        Coffee anotherCoffee = new Cappuccino();
        anotherCoffee = new VanillaDecorator(anotherCoffee);
        System.out.println("nOrder: " + anotherCoffee.getDescription());
        System.out.println("Total Cost: $" + anotherCoffee.getCost());
    }
}

/*

Output :
Order: Espresso, Milk, Sugar
Total Cost: $2.75
nOrder: Cappuccino, Vanilla
Total Cost: $3.75
*/