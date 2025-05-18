package Good_Code.Concrete_Approvers;

import Good_Code.Approver;

public class Supervisor extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays) {
        if (leaveDays <= 3) {
            System.out.println("Supervisor approved the leave.");
        } else if (nextApprover != null) {
            nextApprover.processLeaveRequest(leaveDays);
        }
    }
}