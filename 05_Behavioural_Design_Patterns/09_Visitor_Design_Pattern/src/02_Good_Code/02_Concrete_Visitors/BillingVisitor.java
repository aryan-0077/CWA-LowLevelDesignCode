package Good_Code.Concrete_Visitors;

import Good_Code.Concrete_Patients.*;
import Good_Code.Interfaces.Visitor;

public class BillingVisitor implements Visitor {
    @Override
    public void visit(ChildPatient childPatient) {
        System.out.println("Calculating billing for a child patient.");
    }
    @Override
    public void visit(AdultPatient adultPatient) {
        System.out.println("Calculating billing for an adult patient.");
    }
    @Override
    public void visit(SeniorPatient seniorPatient) {
        System.out.println("Calculating billing for a senior patient.");
    }
}