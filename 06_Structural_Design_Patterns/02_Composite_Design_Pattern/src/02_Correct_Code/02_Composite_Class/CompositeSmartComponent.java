package Correct_Code.CompositeClass;

import Correct_Code.SmartComponent;

import java.util.ArrayList;
import java.util.List;

public class CompositeSmartComponent implements SmartComponent {
    private List<SmartComponent> components = new ArrayList<>();
    public void addComponent(SmartComponent component) {
        components.add(component);
    }
    public void removeComponent(SmartComponent component) {
        components.remove(component);
    }
    @Override
    public void turnOn() {
        for (SmartComponent component : components) {
            component.turnOn();
        }
    }
    @Override
    public void turnOff() {
        for (SmartComponent component : components) {
            component.turnOff();
        }
    }
}
