package Advantages.Data_Security;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345", 1000.00);

        // Accessing balance only through public methods
        account.deposit(500.00);
        account.withdraw(300.00);

        // Unauthorized access prevented: No direct modification
        System.out.println("Account Balance: " + account.getBalance());
    }
}

/*
Output :
Deposited: 500.0
Withdrawn: 300.0
Account Balance: 1200.0

*/