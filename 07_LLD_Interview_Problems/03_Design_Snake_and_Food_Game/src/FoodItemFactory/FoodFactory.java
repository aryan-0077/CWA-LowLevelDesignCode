package FoodItemFactory;

import FoodItemFactory.ConcreteFoodItems.BonusFood;
import FoodItemFactory.ConcreteFoodItems.NormalFood;

// Factory class for creating food items based on type
class FoodFactory {
    // Static method to create a food item at a given position and type
    public static FoodItem createFood(int[] position, String type) {
        if ("bonus".equals(type)) {
            return new BonusFood(position[0], position[1]); // Create bonus food
        }
        return new NormalFood(position[0], position[1]); // Default to normal food
    }
}
