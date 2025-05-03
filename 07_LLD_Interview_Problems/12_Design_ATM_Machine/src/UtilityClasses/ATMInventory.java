package UtilityClasses;

import CommonEnum.CashType;

import java.util.HashMap;
import java.util.Map;

public class ATMInventory {
    private Map<CashType, Integer> cashInventory;

    // Constructor
    public ATMInventory() {
        cashInventory = new HashMap<>();
        initializeInventory();
    }

    // Initialize the inventory with
    private void initializeInventory() {
        // Initialize with some cash
        cashInventory.put(CashType.BILL_100, 10);
        cashInventory.put(CashType.BILL_50, 10);
        cashInventory.put(CashType.BILL_20, 20);
        cashInventory.put(CashType.BILL_10, 30);
        cashInventory.put(CashType.BILL_5, 20);
        cashInventory.put(CashType.BILL_1, 50);
    }

    // Get total cash available in the ATM
    public int getTotalCash() {
        int total = 0;
        for (Map.Entry<CashType, Integer> entry : cashInventory.entrySet()) {
            total += entry.getKey().value * entry.getValue();
        }
        return total;
    }

    // Check if ATM has sufficient cash for a withdrawal
    public boolean hasSufficientCash(int amount) {
        return getTotalCash() >= amount;
    }

    // Dispense cash for a withdrawal
    public Map<CashType, Integer> dispenseCash(int amount) {
        if (!hasSufficientCash(amount)) {
            return null;
        }
        Map<CashType, Integer> dispensedCash = new HashMap<>();
        int remainingAmount = amount;
        // Dispense from largest denomination to smallest
        for (CashType cashType : CashType.values()) {
            int count = Math.min(
                    remainingAmount / cashType.value, cashInventory.get(cashType));
            if (count > 0) {
                dispensedCash.put(cashType, count);
                remainingAmount -= count * cashType.value;
                cashInventory.put(cashType, cashInventory.get(cashType) - count);
            }
        }
        // If we couldn't make exact change
        if (remainingAmount > 0) {
            // Rollback the transaction
            for (Map.Entry<CashType, Integer> entry : dispensedCash.entrySet()) {
                cashInventory.put(entry.getKey(),
                        cashInventory.get(entry.getKey()) + entry.getValue());
            }
            return null;
        }
        return dispensedCash;
    }

    // Add cash to inventory (for maintenance/refill)
    public void addCash(CashType cashType, int count) {
        cashInventory.put(cashType, cashInventory.get(cashType) + count);
    }
}
