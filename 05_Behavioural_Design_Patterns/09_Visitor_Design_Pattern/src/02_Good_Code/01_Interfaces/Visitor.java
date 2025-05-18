package Good_Code.Interfaces;

import Good_Code.Concrete_Patients.*;

public interface Visitor {
    void visit(ChildPatient childPatient);
    void visit(AdultPatient adultPatient);
    void visit(SeniorPatient seniorPatient);
}
