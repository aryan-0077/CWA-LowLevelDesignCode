package Ugly_Code;

public class RemoteControl {
    private TV tv;
    public RemoteControl(TV tv) {
        this.tv = tv;
    }
    public void pressOnButton() {
        tv.turnOn();
    }
    public void pressOffButton() {
        tv.turnOff();
    }
    public void pressChannelButton(int channel) {
        tv.changeChannel(channel);
    }
    public void pressVolumeButton(int volume) {
        tv.adjustVolume(volume);
    }
    // New methods are added each time we need more actions
    public void pressOnChangeVolumeAndChannelButton(int volume, int channel) {
        tv.turnOn();
        tv.changeChannel(channel);
        tv.adjustVolume(volume);
    }
}