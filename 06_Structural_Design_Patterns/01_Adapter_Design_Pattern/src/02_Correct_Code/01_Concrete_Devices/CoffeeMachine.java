package Correct_Code.ConcreteDevices;

public class CoffeeMachine {
    // Method to initialize the Zigbee connection
    public void initializeZigbeeConnection() {
        System.out.println("Coffee Machine connected via Zigbee.");
    }

    // Method to start brewing coffee
    public void startBrewing() {
        System.out.println("Coffee Machine is now brewing coffee.");
    }

    // Method to stop brewing coffee
    public void stopBrewing() {
        System.out.println("Coffee Machine stopped brewing coffee.");
    }

    // Method to terminate the Zigbee connection
    public void terminateZigbeeConnection() {
        System.out.println("Coffee Machine disconnected from Zigbee.");
    }
}
