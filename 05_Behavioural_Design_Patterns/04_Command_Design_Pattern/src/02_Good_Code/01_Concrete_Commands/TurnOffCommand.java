package Good_Code.Concrete_Commands;

import Good_Code.Command;
import Good_Code.Core_Classes.TV;

public class TurnOffCommand implements Command {
    private TV tv;
    public TurnOffCommand(TV tv) {
        this.tv = tv;
    }
    @Override
    public void execute() {
        tv.turnOff();
    }
}