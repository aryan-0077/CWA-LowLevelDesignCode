package Good_Code.Order_Processor_Example;

public class OrderProcessor {
    public static double calculateSubtotal(Item[] order) {
        double subtotal = 0;
        for (Item item : order) {
            subtotal += item.price * item.quantity; // Calculate subtotal
        }
        return subtotal;
    }

    public static double calculateTotal(double subtotal, double taxRate) {
        return subtotal + subtotal * taxRate; // Add tax to subtotal
    }

    public static void main(String[] args) {
        Item[] order = {
                new Item(100, 2), // Item 1: Price = 100, Quantity = 2
                new Item(50, 3) // Item 2: Price = 50, Quantity = 3
        };
        double taxRate = 0.1; // 10% tax
        double subtotal = calculateSubtotal(order);
        double total = calculateTotal(subtotal, taxRate);
        System.out.println("Subtotal: " + subtotal); // Output: Subtotal: 350.0
        System.out.println("Total: " + total); // Output: Total: 385.0
    }
}

/*

Output :
Subtotal: 350.0
Total: 385.0

*/