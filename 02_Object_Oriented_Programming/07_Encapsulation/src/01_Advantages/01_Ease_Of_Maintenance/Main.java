package Advantages.Ease_Of_Maintenance;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00);
        // Maintenance: Any bug in updateBalance can be fixed in one place
        account.updateBalance(500.00, true); // Deposit
        account.updateBalance(300.00, false); // Withdraw
        System.out.println("Final Balance: " + account.getBalance());
    }
}

/*
Output :
Deposited: 500.0
Withdrawn: 300.0
Account Balance: 1200.0

 */