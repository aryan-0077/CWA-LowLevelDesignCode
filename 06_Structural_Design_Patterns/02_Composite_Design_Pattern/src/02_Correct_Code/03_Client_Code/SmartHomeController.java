package Correct_Code;

import Correct_Code.CompositeClass.CompositeSmartComponent;
import Correct_Code.ConcereteDevices.AirConditioner;
import Correct_Code.ConcereteDevices.SmartLight;

public class SmartHomeController {
    public static void main(String[] args) {
        // Create individual devices
        SmartComponent airConditioner = new AirConditioner();
        SmartComponent smartLight = new SmartLight();
        // Create a room and add devices
        CompositeSmartComponent room1 = new CompositeSmartComponent();
        room1.addComponent(airConditioner);
        room1.addComponent(smartLight);
        // Add more rooms for demonstration
        CompositeSmartComponent room2 = new CompositeSmartComponent();
        room2.addComponent(new AirConditioner());
        room2.addComponent(new SmartLight());
        // Create a floor and add rooms
        CompositeSmartComponent floor = new CompositeSmartComponent();
        floor.addComponent(room1);
        floor.addComponent(room2);
        // Create the house and add floors
        CompositeSmartComponent house = new CompositeSmartComponent();
        house.addComponent(floor);
        // Control the entire house
        System.out.println("Turning ON all devices in the house:");
        house.turnOn();
        System.out.println("nTurning OFF all devices in the house:");
        house.turnOff();
        // Control a single floor
        System.out.println("nTurning ON all devices on the first floor:");
        floor.turnOn();
        System.out.println("nTurning OFF all devices on the first floor:");
        floor.turnOff();
        // Control a single room
        System.out.println("nTurning ON all devices in Room 1:");
        room1.turnOn();
        System.out.println("nTurning OFF all devices in Room 1:");
        room1.turnOff();
    }
}

/*

Output :
Turning ON all devices in the house:
Air Conditioner is now ON.
Smart Light is now ON.
Air Conditioner is now ON.
Smart Light is now ON.
nTurning OFF all devices in the house:
Air Conditioner is now OFF.
Smart Light is now OFF.
Air Conditioner is now OFF.
Smart Light is now OFF.
nTurning ON all devices on the first floor:
Air Conditioner is now ON.
Smart Light is now ON.
Air Conditioner is now ON.
Smart Light is now ON.
nTurning OFF all devices on the first floor:
Air Conditioner is now OFF.
Smart Light is now OFF.
Air Conditioner is now OFF.
Smart Light is now OFF.
nTurning ON all devices in Room 1:
Air Conditioner is now ON.
Smart Light is now ON.
nTurning OFF all devices in Room 1:
Air Conditioner is now OFF.
Smart Light is now OFF.
*/