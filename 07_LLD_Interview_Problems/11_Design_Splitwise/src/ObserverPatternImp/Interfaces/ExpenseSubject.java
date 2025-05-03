package ObserverPatternImp.Interfaces;

import UtilityClasses.Expense;

public interface ExpenseSubject {
    // Adds an observer to the notification list.
    void addObserver(ExpenseObserver observer);

    // Removes an observer from the notification list.
    void removeObserver(ExpenseObserver observer);

    // Notifies all observers about a new expense.
    void notifyExpenseAdded(Expense expense);

    // Notifies all observers about an updated expense.
    void notifyExpenseUpdated(Expense expense);
}
