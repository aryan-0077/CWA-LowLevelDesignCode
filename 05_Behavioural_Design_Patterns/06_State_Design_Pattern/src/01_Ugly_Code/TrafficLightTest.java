package Ugly_Code;

public class TrafficLightTest {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.next(); // from RED to GREEN
        trafficLight.next(); // from GREEN to YELLOW
        trafficLight.next(); // from YELLOW to RED
    }
}
