package Good_Code.Concrete_States;

import Good_Code.*;

public class MaintenanceState implements TrafficLightState {
    @Override
    public void next(TrafficLightContext context) {
        System.out.println("Maintenance done, back to RED!");
        context.setState(new RedState());
    }
    @Override
    public String getColor() {
        return "MAINTENANCE";
    }
}