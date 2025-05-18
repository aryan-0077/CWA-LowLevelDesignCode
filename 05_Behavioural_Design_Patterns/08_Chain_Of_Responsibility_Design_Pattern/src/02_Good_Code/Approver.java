package Good_Code;

public abstract class Approver {
        protected Approver nextApprover;
        // Set the next handler in the chain
        public void setNextApprover(Approver nextApprover) {
            this.nextApprover = nextApprover;
        }
        // Abstract method to process the leave request
        public abstract void processLeaveRequest(int leaveDays);
}
