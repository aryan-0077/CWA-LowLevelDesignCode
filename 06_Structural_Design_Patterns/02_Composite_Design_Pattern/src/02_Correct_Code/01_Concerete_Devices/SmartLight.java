package Correct_Code.ConcereteDevices;

import Correct_Code.SmartComponent;

public class SmartLight implements SmartComponent {
    @Override
    public void turnOn() {
        System.out.println("Smart Light is now ON.");
    }
    @Override
    public void turnOff() {
        System.out.println("Smart Light is now OFF.");
    }
}
