package Good_Code.Concrete_Patients;

import Good_Code.Interfaces.Patient;
import Good_Code.Interfaces.Visitor;

public class SeniorPatient implements Patient {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}