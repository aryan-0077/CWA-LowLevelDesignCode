package Good_Code.Concrete_Commands;

import Good_Code.Command;
import Good_Code.Core_Classes.TV;

public class AdjustVolumeCommand implements Command {
    private TV tv;
    private int volume;
    public AdjustVolumeCommand(TV tv, int volume) {
        this.tv = tv;
        this.volume = volume;
    }
    @Override
    public void execute() {
        tv.adjustVolume(volume);
    }
}