package Good_Code;

public class TrafficLightTest {
    public static void main(String[] args) {
        TrafficLightContext trafficLight = new TrafficLightContext();
        trafficLight.next(); // RED -> GREEN
        trafficLight.next(); // GREEN -> YELLOW
        trafficLight.next(); // YELLOW -> RED
        trafficLight.next(); // RED -> GREEN
        // Adding new states like BLINKING or MAINTENANCE is easy now
    }
}

/*

Output :
Switching from RED to GREEN. Cars go!
Switching from GREEN to YELLOW. Slow down!
Switching from YELLOW to RED. Stop!
Switching from RED to GREEN. Cars go!

*/