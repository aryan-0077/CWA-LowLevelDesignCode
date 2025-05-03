package ObserverPatternImp.Interfaces;

import UtilityClasses.Expense;

public interface ExpenseObserver {
    // Called when a new expense is added to the system.
    void onExpenseAdded(Expense expense);

    // Called when an expense is updated in the system.
    void onExpenseUpdated(Expense expense);
}
