package Correct_Code.ConcreteAdapters;

import Correct_Code.ConcreteDevices.SmartLight;
import Correct_Code.SmartDevice;

public class SmartLightAdapter implements SmartDevice {
    private SmartLight smartLight;
    public SmartLightAdapter(SmartLight smartLight) {
        this.smartLight = smartLight;
    }

    @Override
    public void turnOn() {
        smartLight.connectToWiFi();
        smartLight.switchOn();
    }

    @Override
    public void turnOff() {
        smartLight.switchOff();
        smartLight.disconnectWiFi();
    }
}
