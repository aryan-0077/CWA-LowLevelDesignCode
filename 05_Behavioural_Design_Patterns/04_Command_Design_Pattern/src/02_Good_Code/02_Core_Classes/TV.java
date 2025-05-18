package Good_Code.Core_Classes;

// The TV class with methods for the different operations
public class TV {
    public void turnOn() {
        System.out.println("TV is ON");
    }
    public void turnOff() {
        System.out.println("TV is OFF");
    }
    public void changeChannel(int channel) {
        System.out.println("Channel changed to " + channel);
    }
    public void adjustVolume(int volume) {
        System.out.println("Volume set to " + volume);
    }
}