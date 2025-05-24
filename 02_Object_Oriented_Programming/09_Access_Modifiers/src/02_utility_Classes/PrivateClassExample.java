package utilityClasses;

public class PrivateClassExample {
        private String secret = "Hidden Message";
        private void displaySecret() {
            System.out.println(secret);
        }
        private void show() {
            displaySecret(); // Accessible within the same class
        }
}
