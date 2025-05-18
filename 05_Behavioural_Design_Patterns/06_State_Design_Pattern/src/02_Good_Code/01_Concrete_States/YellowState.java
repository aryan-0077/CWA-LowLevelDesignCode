package Good_Code.Concrete_States;

import Good_Code.*;

public class YellowState implements TrafficLightState {
    @Override
    public void next(TrafficLightContext context) {
        System.out.println("Switching from YELLOW to RED. Stop!");
        context.setState(new RedState());
    }
    @Override
    public String getColor() {
        return "YELLOW";
    }
}