package Interview_Questions.Question_6;

public class Example {
    private int value;
    // Constructor with a return statement
    public Example(int value) {
        if (value < 0) {
            System.out.println("Invalid value! Constructor exiting early.");
            return; // Exits the constructor early
        }
        this.value = value; // Initializes the value if valid
    }
    public void display() {
        System.out.println("Value: " + value);
    }
}
