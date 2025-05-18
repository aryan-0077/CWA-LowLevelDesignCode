package Correct_Code.ConcreteAdapters;

import Correct_Code.ConcreteDevices.AirConditioner;
import Correct_Code.SmartDevice;

public class AirConditionerAdapter implements SmartDevice {
    private AirConditioner airConditioner;
    // Constructor
    public AirConditionerAdapter(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void turnOn() {
        airConditioner.connectViaBluetooth();
        airConditioner.startCooling();
    }

    @Override
    public void turnOff() {
        airConditioner.stopCooling();
        airConditioner.disconnectBluetooth();
    }
}
