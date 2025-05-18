package Examples.Object_Oriented_Principles.Good_Code;

import Examples.Object_Oriented_Principles.Good_Code.Concrete_Buttons.*;

public class Main {
    public static void main(String[] args) {
        Button submit = new SubmitButton();
        submit.onClick();  // Output: Form submitted.
        Button cancel = new CancelButton();
        cancel.onClick();  // Output: Form canceled.
    }
}

/*

Output :
Form submitted.
Form Canceled.
*/