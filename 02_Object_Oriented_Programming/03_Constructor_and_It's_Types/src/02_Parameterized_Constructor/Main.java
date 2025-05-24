package ParameterizedConstructor;

public class Main {
    public static void main(String[] args) {
        Movie movie = new Movie("Inception", 148); // Parameterized constructor is called
        movie.displayDetails();
    }
}

/*
Output :
Title: Inception, Duration: 148 mins
*/