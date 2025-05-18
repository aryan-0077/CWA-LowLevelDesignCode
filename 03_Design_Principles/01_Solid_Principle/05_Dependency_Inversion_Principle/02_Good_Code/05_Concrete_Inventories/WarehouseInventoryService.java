package Dependency_Inversion_Principle.Good_Code.Concrete_Inventories;

import Dependency_Inversion_Principle.Good_Code.Core_Classes.Order;
import Dependency_Inversion_Principle.Good_Code.Core_Classes.Product;
import Dependency_Inversion_Principle.Good_Code.Interfaces.InventoryService;

public class WarehouseInventoryService implements InventoryService {
    @Override
    public void updateStock(Order order) {

    }

    @Override
    public boolean checkAvailability(Product product) {
        return false;
    }
}
