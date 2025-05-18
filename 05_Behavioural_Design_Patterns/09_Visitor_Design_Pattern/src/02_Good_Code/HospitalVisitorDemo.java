package Good_Code;

import Good_Code.Concrete_Patients.*;
import Good_Code.Concrete_Visitors.*;
import Good_Code.Interfaces.*;

public class HospitalVisitorDemo {
    public static void main(String[] args) {
        // Create an array of patients
        Patient[] patients = {new ChildPatient(), new AdultPatient(), new SeniorPatient()};

        // Create visitors for different operations
        Visitor diagnosisVisitor = new DiagnosisVisitor();
        Visitor billingVisitor = new BillingVisitor();

        // Each patient accepts the visitors to perform the operations
        for (Patient patient : patients) {
            patient.accept(diagnosisVisitor);
            patient.accept(billingVisitor);
        }
    }
}

/*

Output :

Diagnosing a child patient: Check-up and pediatric care.
Calculating billing for a child patient.
Diagnosing an adult patient: Routine exams and lifestyle advice.
Calculating billing for an adult patient.
Diagnosing a senior patient: Comprehensive geriatric evaluation.
Calculating billing for a senior patient.

*/