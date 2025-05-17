package RestaurantOrderSimulation;

public class RestaurantSimulation {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread waiter = new WaiterThread(lock);
        Thread chef = new ChefThread(lock);

        waiter.start();
        chef.start();
    }
}

/*

Output :
Waiter: Waiting for the food to be ready... ⏳
Chef: Food is ready! Notifying the waiter. 🔔
Waiter: Food is ready! Delivering to the customer. 🍽️
*/