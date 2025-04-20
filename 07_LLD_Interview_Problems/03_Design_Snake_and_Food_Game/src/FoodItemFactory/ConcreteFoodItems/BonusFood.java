package FoodItemFactory.ConcreteFoodItems;

import FoodItemFactory.FoodItem;

public class BonusFood extends FoodItem {
    // Concrete class representing bonus food with 3 points
        public BonusFood(int row, int column) {
            super(row, column); // Call superclass constructor
            this.points = 3; // Assign higher point value
        }
}
