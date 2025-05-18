package Dependency_Inversion_Principle.Good_Code.Interfaces;

import Dependency_Inversion_Principle.Good_Code.Core_Classes.*;

public interface InventoryService {
    void updateStock(Order order);
    boolean checkAvailability(Product product);
}
