package Ugly_Code.Order_Processor_Example;

// Bad Code: Process order logic is combined into a single function
public class OrderProcessor {
    public static double processOrder(Item[] order, double taxRate) {
        double total = 0;
        for (Item item : order) {
            total += item.price * item.quantity; // Calculate item totals
        }
        total += total * taxRate; // Add tax
        return total; // Return final total
    }
}

/*

Output :
        Subtotal: 350.0
        Total: 385.0

 */