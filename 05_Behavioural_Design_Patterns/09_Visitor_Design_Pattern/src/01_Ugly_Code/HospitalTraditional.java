package Ugly_Code;

import Ugly_Code.Concrete_Patients.*;

public class HospitalTraditional {
    public static void main(String[] args) {
        Object patient = new AdultPatient(); // Could be ChildPatient or SeniorPatient
        // Using if-else to perform operations
        if (patient instanceof ChildPatient) {
            ((ChildPatient) patient).diagnosis();
            ((ChildPatient) patient).billing();
        } else if (patient instanceof AdultPatient) {
            ((AdultPatient) patient).diagnosis();
            ((AdultPatient) patient).billing();
        } else if (patient instanceof SeniorPatient) {
            ((SeniorPatient) patient).diagnosis();
            ((SeniorPatient) patient).billing();
        }
    }
}


/*

Output :
Diagnosing an adult patient.
Calculating billing for an adult patient.

*/