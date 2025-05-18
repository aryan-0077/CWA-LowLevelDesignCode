package Good_Code;

import Good_Code.Concrete_Commands.*;
import Good_Code.Core_Classes.*;

public class Main {
    public static void main(String[] args) {
        TV tv = new TV();
        // Create commands
        Command turnOn = new TurnOnCommand(tv);
        Command turnOff = new TurnOffCommand(tv);
        Command changeChannel = new ChangeChannelCommand(tv, 5);
        Command adjustVolume = new AdjustVolumeCommand(tv, 20);
        // Create remote control
        RemoteControl remote = new RemoteControl();
        remote.setOnCommand(turnOn);
        remote.setOffCommand(turnOff);
        remote.pressOnButton(); // Turn on the TV
        remote.pressOffButton(); // Turn off the TV
        // Execute other commands
        changeChannel.execute(); // Change the channel
        adjustVolume.execute(); // Adjust the volume
    }
}

/*

Output :
TV is ON
TV is OFF
Channel changed to 5
Volume set to 20

*/