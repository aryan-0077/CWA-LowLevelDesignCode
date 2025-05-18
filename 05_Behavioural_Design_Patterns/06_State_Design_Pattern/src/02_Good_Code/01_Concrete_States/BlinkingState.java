package Good_Code.Concrete_States;

import Good_Code.*;

public class BlinkingState implements TrafficLightState {
    @Override
    public void next(TrafficLightContext context) {
        System.out.println("Switching from BLINKING to MAINTENANCE mode...");
        context.setState(new MaintenanceState());
    }
    @Override
    public String getColor() {
        return "BLINKING";
    }
}