package Interview_Questions.Question_6;

public class Main {
    public static void main(String[] args) {
        Example obj1 = new Example(10); // Valid value
        obj1.display();
        Example obj2 = new Example(-5); // Invalid value, constructor exits early
        obj2.display();
    }
}

/*
Output :
Value: 10
Invalid value! Constructor exiting early.
Value: 0
*/