package Examples.Object_Oriented_Principles.Good_Code.Concrete_Buttons;

import Examples.Object_Oriented_Principles.Good_Code.Button;

public class SubmitButton extends Button {
    @Override
    protected void onClick() {
        System.out.println("Form submitted.");
    }
}