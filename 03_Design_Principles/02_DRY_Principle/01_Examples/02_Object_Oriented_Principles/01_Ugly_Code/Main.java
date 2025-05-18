package Examples.Object_Oriented_Principles.Ugly_Code;

public class Main {
    public static void main(String[] args) {
        SubmitButton submit = new SubmitButton();
        submit.onClick();  // Output: Form submitted.
        CancelButton cancel = new CancelButton();
        cancel.onClick();  // Output: Action canceled.
    }
}

/*

Output :
Form submitted.
Action canceled.

*/