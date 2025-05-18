package Ugly_Code;

import java.util.Scanner;

public class CoffeeShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your coffee order:");
        String coffeeOrder = scanner.nextLine();
        if (coffeeOrder.equalsIgnoreCase("Espresso with Milk and Sugar")) {
            System.out.println("Preparing Espresso with Milk and Sugar...");
        } else if (coffeeOrder.equalsIgnoreCase("Cappuccino with Vanilla")) {
            System.out.println("Preparing Cappuccino with Vanilla...");
        } else if (coffeeOrder.equalsIgnoreCase("Latte with Caramel")) {
            System.out.println("Preparing Latte with Caramel...");
        } else if (coffeeOrder.equalsIgnoreCase("Mocha with Whipped Cream")) {
            System.out.println("Preparing Mocha with Whipped Cream...");
        } else if (coffeeOrder.equalsIgnoreCase("Black Coffee with Honey")) {
            System.out.println("Preparing Black Coffee with Honey...");
        } else {
            System.out.println("Order not recognized!");
        }
        scanner.close();
    }
}

/*

Output :
Enter your coffee order:
Latte with Caramel
Preparing Latte with Caramel...

*/
