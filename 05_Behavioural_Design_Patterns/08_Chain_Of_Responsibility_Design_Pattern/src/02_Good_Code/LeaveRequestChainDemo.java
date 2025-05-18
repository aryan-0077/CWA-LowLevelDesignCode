package Good_Code;

import Good_Code.Concrete_Approvers.*;

public  class LeaveRequestChainDemo {
    public static void main(String[] args) {
        // Create handler instances
        Approver supervisor = new Supervisor();
        Approver manager = new Manager();
        Approver director = new Director();
        Approver hr = new HR();
        // Set up the chain: Supervisor -> Manager -> Director -> HR
        supervisor.setNextApprover(manager);
        manager.setNextApprover(director);
        director.setNextApprover(hr); // Now HR handles any unprocessed request

        // Process a leave request that exceeds Director's approval limit
        int leaveDays = 20;
        System.out.println("Employee requests " + leaveDays + " days of leave.");
        supervisor.processLeaveRequest(leaveDays);
    }
}

/*

Output 1:
Employee requests 10 days of leave.
Director approved the leave.

Output 2 :
Employee requests 20 days of leave.
HR: Leave request requires further discussion.

*/