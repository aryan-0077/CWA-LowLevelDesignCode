package Good_Code.Concrete_Commands;

import Good_Code.Command;
import Good_Code.Core_Classes.TV;

public class ChangeChannelCommand implements Command {
    private TV tv;
    private int channel;
    public ChangeChannelCommand(TV tv, int channel) {
        this.tv = tv;
        this.channel = channel;
    }
    @Override
    public void execute() {
        tv.changeChannel(channel);
    }
}