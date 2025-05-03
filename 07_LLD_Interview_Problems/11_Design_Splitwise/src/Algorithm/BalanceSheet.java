package Algorithm;

import ObserverPatternImp.Interfaces.ExpenseObserver;
import UtilityClasses.Expense;
import UtilityClasses.Transaction;
import UtilityClasses.User;
import UtilityClasses.UserPair;

import java.util.*;

public class BalanceSheet implements ExpenseObserver {
    // Stores the net balance between pairs of users
    private Map<UserPair, Double> balances = new HashMap<>();

    @Override
    public void onExpenseAdded(Expense expense) {
        // Update balances when a new expense is added
        updateBalances(expense);
    }

    @Override
    public void onExpenseUpdated(Expense expense) {
        // To simplify logic, just update balances without reversing the previous state
        updateBalances(expense);
    }

    /**
     - Updates the balances based on a new or updated expense.
     - Each participant's share is added to their balance with the payer.
     -    - @param expense The expense to process.
     */
    private void updateBalances(Expense expense) {
        User payer = expense.getPayer(); // User who paid for the expense
        Map<User, Double> shares = expense.getShares(); // Participants and their shares
        for (Map.Entry<User, Double> entry : shares.entrySet()) {
            User participant = entry.getKey(); // A participant in the expense
            Double amount = entry.getValue(); // The amount owed by the participant
            if (!participant.equals(payer)) {
                // Create a unique pair for the payer and participant
                UserPair userPair = new UserPair(participant, payer);
                // Update the balance (add the amount owed by the participant)
                Double currentBalance = balances.getOrDefault(userPair, 0.0);
                balances.put(userPair, currentBalance + amount);
            }
        }
    }

    /**
     - Gets the net balance between two users.
     -    - @param user1 First user.
     - @param user2 Second user.
     - @return The amount user1 owes user2 (negative if user2 owes user1).
     */
    public double getBalance(User user1, User user2) {
        // Represent the balance both ways (user1 -> user2 and user2 -> user1)
        UserPair pair1 = new UserPair(user1, user2);
        UserPair pair2 = new UserPair(user2, user1);
        // Retrieve balances in both directions and calculate the net
        double balance1 = balances.getOrDefault(pair1, 0.0);
        double balance2 = balances.getOrDefault(pair2, 0.0);
        return balance1 - balance2;
    }

    /**
     - Calculates the total balance for a single user.
     - The balance is negative if the user owes money and positive if they are owed money.
     -    - @param user The user to calculate the balance for.
     - @return The total balance for the user.
     */
    public double getTotalBalance(User user) {
        double total = 0.0;
        // Iterate through all user pairs and calculate the total
        for (Map.Entry<UserPair, Double> entry : balances.entrySet()) {
            UserPair pair = entry.getKey();
            double amount = entry.getValue();
            if (pair.getUser1().equals(user)) {
                total -= amount; // Money owed by the user (He has to give money)
            } else if (pair.getUser2().equals(user)) {
                total += amount; // Money owed to the user (He has to take money)
            }
        }
        return total;
    }

    /**
     - Simplifies the balances into a list of transactions to settle all debts.
     - Simple and Straightforward implementation of the problem
     - @return List of transactions needed to settle all debts.
     */
    public List<Transaction> getSimplifiedSettlements() {
        // Step 1: Calculate net balances for each user
        Map<User, Double> netBalances = new HashMap<>();
        for (Map.Entry<UserPair, Double> entry : balances.entrySet()) {
            UserPair pair = entry.getKey();
            double amount = entry.getValue();

            User debtor = pair.getUser1(); // User who owes money (Who has to pay)
            User creditor = pair.getUser2(); // User who is owed money (Who gets the money)
            // Update the net balances for debtor and creditor
            netBalances.put(debtor, netBalances.getOrDefault(debtor, 0.0) - amount);
            netBalances.put(creditor, netBalances.getOrDefault(creditor, 0.0) + amount);
        }

        // Step 2: Separate users into debtors and creditors
        List<User> debtors = new ArrayList<>();
        List<User> creditors = new ArrayList<>();
        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
            User user = entry.getKey();
            double balance = entry.getValue();
            if (balance < 0) {
                debtors.add(user); // Users who owe money (Who has to pay)
            } else if (balance > 0) {
                creditors.add(user); // Users who are owed money (Who gets the money back)
            }
        }

        // Step 3: Match debtors and creditors to create transactions
        List<Transaction> transactions = new ArrayList<>();
        int debtorIndex = 0;
        int creditorIndex = 0;

        while (debtorIndex < debtors.size() && creditorIndex < creditors.size()) {
            User debtor = debtors.get(debtorIndex);
            User creditor = creditors.get(creditorIndex);
            double debtorBalance = netBalances.get(debtor);
            double creditorBalance = netBalances.get(creditor);

            // Determine the transfer amount as the smaller of the two balances
            double transferAmount = Math.min(Math.abs(debtorBalance), creditorBalance);

            // Create a transaction for the transfer amount
            transactions.add(new Transaction(debtor, creditor, transferAmount));

            // Update net balances after the transaction
            netBalances.put(debtor, debtorBalance + transferAmount);
            netBalances.put(creditor, creditorBalance - transferAmount);

            // Move to the next debtor or creditor if their balance is settled
            if (Math.abs(netBalances.get(debtor)) < 0.001) {
                debtorIndex++;
            }
            if (Math.abs(netBalances.get(creditor)) < 0.001) {
                creditorIndex++;
            }
        }
        return transactions;
    }

    /**
     - Calculates the minimum number of transactions needed to settle all balances.
     - Uses backtracking approach to find the solution.
     -    - @return minimum count needed to settle all debts.
     */
    public int getSubOptimalMinimumSettlements() {
        // Step 1: Calculate net balances for each user
        Map<User, Double> netBalances = new HashMap<>();
        for (Map.Entry<UserPair, Double> entry : balances.entrySet()) {
            UserPair pair = entry.getKey();
            double amount = entry.getValue();
            User debtor = pair.getUser1(); // The user who owes money
            User creditor = pair.getUser2(); // The user who is owed money

            // Update the net balance of each user
            netBalances.put(debtor, netBalances.getOrDefault(debtor, 0.0) - amount);
            netBalances.put(creditor, netBalances.getOrDefault(creditor, 0.0) + amount);
        }
        List<Double> creditList = new ArrayList<>();
        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
            if (Math.abs(entry.getValue()) > 0.001) { // Ignore near-zero balances
                creditList.add(entry.getValue()); // Store the net balance
            }
        }
        int n = creditList.size(); // Total number of users with non-zero balance
        return subOptimalDfs(0, creditList, n); // Call DFS to compute the minimum transactions
    }

    /**
     - Recursively finds the minimum number of transactions required to settle debts.
     - Uses a greedy approach by settling the current user's balance with future users.
     -    - @param currentUserIndex Index of the user whose balance needs to be settled.
     - @param creditList List of net balances for all users.
     - @param n Number of users with non-zero balances.
     - @return Minimum transactions required to settle all debts.
     */
    private int subOptimalDfs(int currentUserIndex, List<Double> creditList, int n) {
        // Skip already settled users (those with zero balance)
        while (currentUserIndex < n && creditList.get(currentUserIndex) == 0) {
            currentUserIndex++;
        }

        // Base case: If all users have zero balance, no further transactions are needed
        if (currentUserIndex == n)
            return 0;
        int cost = Integer.MAX_VALUE; // Variable to track the minimum number of transactions
        // Try to settle currentUserBalance with a future user having an opposite balance
        for (int nextIndex = currentUserIndex + 1; nextIndex < n; nextIndex++) {
            // Ensure we only settle debts between users with opposite balances
            if (creditList.get(nextIndex) * creditList.get(currentUserIndex) < 0) {
                // Transfer current user's balance to the next valid user
                creditList.set(nextIndex, creditList.get(nextIndex) + creditList.get(currentUserIndex));
                // Recursively settle the remaining balances
                cost = Math.min(cost, 1 + subOptimalDfs(currentUserIndex + 1, creditList, n));

                // Backtrack: Undo the transaction to explore other possibilities
                creditList.set(nextIndex, creditList.get(nextIndex) - creditList.get(currentUserIndex));
            }
        }
        return cost; // Return the minimum transactions required
    }

    public int getOptimalMinimumSettlements() {
        // Step 1: Calculate net balances for each user
        Map<User, Double> netBalances = new HashMap<>();
        for (Map.Entry<UserPair, Double> entry : balances.entrySet()) {
            UserPair pair = entry.getKey();
            double amount = entry.getValue();
            User debtor = pair.getUser1(); // The user who owes money
            User creditor = pair.getUser2(); // The user who is owed money
            // Update the net balance of each user
            netBalances.put(debtor, netBalances.getOrDefault(debtor, 0.0) - amount);
            netBalances.put(creditor, netBalances.getOrDefault(creditor, 0.0) + amount);
        }
        List<Double> creditList = new ArrayList<>();


        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
            if (Math.abs(entry.getValue()) > 0.001) { // Ignore near-zero balances
                creditList.add(entry.getValue()); // Store the net balance
            }
        }
        // Step 3: Apply Dynamic Programming to find the minimum transactions required
        int n = creditList.size(); // Number of users with non-zero balance
        int[] dp = new int[1 << n]; // DP array for memoization
        Arrays.fill(dp, -1);
        dp[0] = 0; // Base case: No users left means zero transactions


        // Find the maximum number of fully settled subgroups using DFS + DP
        int maxSubGroups = dfs((1 << n) - 1, dp, creditList);

        // Minimum transactions needed = Total users - Maximum fully settled groups
        return n - maxSubGroups;
    }
    /**
     - Helper method to calculate the sum of balances in a subset, given by a bitmask.
     -
     - @param values The list of credit balances.
     - @param mask The bitmask representing a subset of users.
     - @return The sum of balances in the subset.
     */
    private double sumOfMask(List<Double> values, int mask) {
        double sum = 0;
        for (int i = 0; i < values.size(); i++) {
            if ((mask & (1 << i)) != 0) { // Check if the i-th bit is set in the mask
                sum += values.get(i); // Add the corresponding balance to the sum
            }
        }
        return sum;
    }
    /**
     - DFS with memoization to determine the maximum number of balanced subgroups.
     -
     - @param mask Bitmask representing the remaining users.
     - @param dp Memoization array for storing computed results.
     - @param creditList List of net balances for each user.
     - @return The maximum number of fully settled subgroups.
     */
    private int dfs(int mask, int[] dp, List<Double> creditList) {
        if (mask == 0) // Base case: No users left to process
            return 0;
        if (dp[mask] != -1) // Return cached result if already computed
            return dp[mask];
        int maxSubGroups = 0;
        int n = creditList.size();
        // Try all possible subsets (submasks) of the current mask
        for (int submask = 1; submask < (1 << n); submask++) {
            // Check if submask is a subset of mask and sums to zero (i.e., can be settled)
            if ((submask & mask) == submask && Math.abs(sumOfMask(creditList, submask)) < 0.001) {
                // If a subset can be settled, find the remaining subgroups recursively
                maxSubGroups = Math.max(maxSubGroups, 1 + dfs(mask ^ submask, dp, creditList));
            }
        }
        dp[mask] = maxSubGroups; // Store result in memoization table
        return maxSubGroups;
    }
}
