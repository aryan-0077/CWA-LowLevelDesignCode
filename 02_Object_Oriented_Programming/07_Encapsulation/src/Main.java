public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345", 1000.00);
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Initial Balance: " + account.getBalance());
        account.deposit(500.00);
        System.out.println("Updated Balance: " + account.getBalance());
        account.withdraw(200.00);
        System.out.println("Final Balance: " + account.getBalance());
    }
}

/*
Output :
Account Number: 12345
Initial Balance: 1000.0
Deposited: 500.0
Updated Balance: 1500.0
Withdrawn: 200.0
Final Balance: 1300.0

*/