package Realization;

public class CashPayment implements Payment{
    @Override
    public void pay() {
         System.out.println("Paid using Cash.");
    }
}
