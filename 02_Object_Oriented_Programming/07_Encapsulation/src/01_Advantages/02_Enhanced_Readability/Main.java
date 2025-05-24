package Advantages.Enhanced_Readability;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345", 1000.00);

        // Clearly defined interfaces for readability
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Initial Balance: " + account.getBalance());

        account.deposit(500.00);
        account.withdraw(200.00);
    }
}

/*

Output :
Account Number: 12345
Initial Balance: 1000.0
Deposited: 500.0
Withdrawn: 200.0
*/