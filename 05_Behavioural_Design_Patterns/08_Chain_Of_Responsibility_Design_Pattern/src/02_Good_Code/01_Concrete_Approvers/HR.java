package Good_Code.Concrete_Approvers;

import Good_Code.Approver;

public class HR extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays) {
        System.out.println("HR: Leave request requires further discussion.");
    }
}