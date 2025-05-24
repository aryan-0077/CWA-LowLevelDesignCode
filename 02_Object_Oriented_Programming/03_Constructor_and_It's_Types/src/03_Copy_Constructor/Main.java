package CopyConstructor;

public class Main {
    public static void main(String[] args) {
        Movie original = new Movie("Inception", 148);
        Movie copy = new Movie(original); // Copy constructor is called
        copy.displayDetails();
    }
}

/*
Output :
Title: Inception, Duration: 148 mins
*/