package Good_Code;

public // State Interface
interface TrafficLightState {
    void next(TrafficLightContext context);
    String getColor();
}