package Advantages.Increased_Flexibility;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00);
        // External code remains unchanged despite internal changes
        account.withdraw(200.00);
        System.out.println("Remaining Balance: " + account.getBalance());
    }
}

/*
Output :
Withdrawn: 200.0, Fee: 2.0
Remaining Balance: 798.0

*/