package Correct_Code.Client_Code;

import Correct_Code.ConcreteAdapters.AirConditionerAdapter;
import Correct_Code.ConcreteAdapters.CoffeeMachineAdapter;
import Correct_Code.ConcreteAdapters.SmartLightAdapter;
import Correct_Code.ConcreteDevices.AirConditioner;
import Correct_Code.ConcreteDevices.CoffeeMachine;
import Correct_Code.ConcreteDevices.SmartLight;
import Correct_Code.SmartDevice;

public class SmartHomeController {
    public static void main(String[] args) {
        // Create adapters for each device
        SmartDevice airConditioner = new AirConditionerAdapter(new AirConditioner());
        SmartDevice smartLight = new SmartLightAdapter(new SmartLight());
        SmartDevice coffeeMachine = new CoffeeMachineAdapter(new CoffeeMachine());
        // Control devices through the unified interface
        airConditioner.turnOn();
        smartLight.turnOn();
        coffeeMachine.turnOn();
        airConditioner.turnOff();
        smartLight.turnOff();
        coffeeMachine.turnOff();
    }
}


/*

Output :

Air Conditioner connected via Bluetooth.
Air Conditioner is now cooling.
Smart Light connected to Wi-Fi.
Smart Light is now ON.
Coffee Machine connected via Zigbee.
Coffee Machine is now brewing coffee.
Air Conditioner stopped cooling.
Air Conditioner disconnected from Bluetooth.
Smart Light is now OFF.
Smart Light disconnected from Wi-Fi.
Coffee Machine stopped brewing coffee.
Coffee Machine disconnected from Zigbee.

 */