package Disadvantages.Complexity;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00);
        // Beginners may find concepts like access modifiers and getters challenging
        account.deposit(500.00);
        account.withdraw(200.00);
        System.out.println("Final Balance: " + account.getBalance());
    }
}

/*
Output :
Final Balance: 1300.0
*/