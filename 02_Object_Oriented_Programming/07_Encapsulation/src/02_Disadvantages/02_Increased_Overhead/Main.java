package Disadvantages.Increased_Overhead;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        // Verbose code for simple tasks
        account.setAccountNumber("12345");
        account.setBalance(1000.00);
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Balance: " + account.getBalance());
    }
}

/*

Output :
Account Number: 12345
Balance: 1000.0

*/
