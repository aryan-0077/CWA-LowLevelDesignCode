package ObserverPatternImp.Classes;

import ObserverPatternImp.Interfaces.ExpenseObserver;
import ObserverPatternImp.Interfaces.ExpenseSubject;
import UtilityClasses.Expense;

import java.util.ArrayList;
import java.util.List;

// Concrete implementation of ExpenseSubject that manages expense notifications.
public class ExpenseManager implements ExpenseSubject {
    private List<ExpenseObserver> observers = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();

    @Override
    public void addObserver(ExpenseObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ExpenseObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyExpenseAdded(Expense expense) {
        for (ExpenseObserver observer : observers) observer.onExpenseAdded(expense);
    }

    @Override
    public void notifyExpenseUpdated(Expense expense) {
        for (ExpenseObserver observer : observers) observer.onExpenseUpdated(expense);
    }

    // Adds a new expense to the system and notifies observers.
    public void addExpense(Expense expense) {
        expenses.add(expense);
        notifyExpenseAdded(expense);
    }

    // Updates an existing expense and notifies observers.
    public void updateExpense(Expense expense) {
        // Find and replace the expense with the same ID in the list
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getId().equals(expense.getId())) { // Check if the IDs match
                expenses.set(i, expense); // Replace the old expense with the updated one
                notifyExpenseUpdated(expense); // Notify all observers about the update
                return; // Exit the method after updating
            }
        }
        // Throw an exception if the expense with the given ID is not found
        throw new IllegalArgumentException("Expense with ID " + expense.getId() + " not found.");
    }

    // Retrieves all expenses in the system.
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }
}