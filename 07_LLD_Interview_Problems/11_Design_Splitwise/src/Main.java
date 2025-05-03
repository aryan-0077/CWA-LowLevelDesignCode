import Algorithm.BalanceSheet;
import ObserverPatternImp.Classes.ExpenseManager;
import SplitTypeFactoryPattern.Split;
import SplitTypeFactoryPattern.SplitFactory;
import UtilityClasses.Expense;
import UtilityClasses.Transaction;
import UtilityClasses.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create users
        User alice = new User("u1", "Alice", "alice@example.com");
        User bob = new User("u2", "Bob", "bob@example.com");
        User charlie = new User("u3", "Charlie", "charlie@example.com");

        // Create expense manager and balance sheet
        ExpenseManager expenseManager = new ExpenseManager();
        BalanceSheet balanceSheet = new BalanceSheet();

        // Register the balance sheet as an observer
        expenseManager.addObserver(balanceSheet);

        // Create and add users to a list for expenses
        List<User> participants = new ArrayList<>();
        participants.add(alice);
        participants.add(bob);
        participants.add(charlie);

        // Alice pays for dinner - Create an equal split expense
        Split equalSplit = SplitFactory.createSplit("EQUAL");
        Map<String, Object> splitDetails = new HashMap<>();
        Map<User, Double> dinnerShares = equalSplit.calculateSplit(60.0, participants, splitDetails);

        Expense dinnerExpense = new Expense("e1", "Dinner", 60.0, alice, participants, dinnerShares);

        // Add the expense to the expense manager which will notify observers
        expenseManager.addExpense(dinnerExpense);

        // Bob pays for movie tickets - Create a percentage split expense
        Map<String, Object> percentageSplitDetails = new HashMap<>();
        Map<User, Double> percentages = new HashMap<>();
        percentages.put(alice, 40.0);
        percentages.put(bob, 30.0);
        percentages.put(charlie, 30.0);
        percentageSplitDetails.put("percentages", percentages);

        Split percentageSplit = SplitFactory.createSplit("PERCENTAGE");
        Map<User, Double> movieShares = percentageSplit.calculateSplit(45.0, participants, percentageSplitDetails);

        Expense movieExpense = new Expense("e2", "Movie", 45.0, bob, participants, movieShares);

        // Add the movie expense to the expense manager
        expenseManager.addExpense(movieExpense);

        // Get individual balances
        System.out.println("Individual balances:");
        System.out.println("Alice's total balance: $" + balanceSheet.getTotalBalance(alice));
        System.out.println("Bob's total balance: $" + balanceSheet.getTotalBalance(bob));
        System.out.println("Charlie's total balance: $" + balanceSheet.getTotalBalance(charlie));

        // Print specific balances between users
        System.out.println("nPairwise balances:");
        System.out.println("Alice and Bob: $" + balanceSheet.getBalance(alice, bob));
        System.out.println("Alice and Charlie: $" + balanceSheet.getBalance(alice, charlie));
        System.out.println("Bob and Charlie: $" + balanceSheet.getBalance(bob, charlie));

        // Get the simplified settlements
        List<Transaction> settlements = balanceSheet.getSimplifiedSettlements();

        // Display optimal minimum settlements (DP algorithm)
        System.out.println("n=== OPTIMAL MINIMUM SETTLEMENTS ===");
        int optimalSettlements = balanceSheet.getOptimalMinimumSettlements();
        System.out.println(optimalSettlements);

        // Print the settlements
        System.out.println("nSimplified settlements:");
        for (Transaction transaction : settlements) {
            System.out.println(transaction.getFrom().getName() + " pays " +
                    transaction.getTo().getName() + " $" +
                    transaction.getAmount());
        }
    }
    }

    /*

    Output :
    Individual balances:
Alice's total balance: $22.0
Bob's total balance: $11.5
Charlie's total balance: $-33.5
nPairwise balances:
Alice and Bob: $0.0
Alice and Charlie: $0.0
Bob and Charlie: $0.0
n=== OPTIMAL MINIMUM SETTLEMENTS ===
2
nSimplified settlements:
Charlie pays Alice $22.0
Charlie pays Bob $11.5

Process finished with exit code 0


     */