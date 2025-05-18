package Correct_Code.ConcreteAdapters;

import Correct_Code.ConcreteDevices.CoffeeMachine;
import Correct_Code.SmartDevice;

public class CoffeeMachineAdapter implements SmartDevice {
    private CoffeeMachine coffeeMachine;
    public CoffeeMachineAdapter(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void turnOn() {
        coffeeMachine.initializeZigbeeConnection();
        coffeeMachine.startBrewing();
    }

    @Override
    public void turnOff() {
        coffeeMachine.stopBrewing();
        coffeeMachine.terminateZigbeeConnection();
    }
}
