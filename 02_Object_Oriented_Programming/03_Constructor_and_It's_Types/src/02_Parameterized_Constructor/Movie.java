package ParameterizedConstructor;

public class Movie {
    private String title;
    private int duration;

    // Parameterized constructor
    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
    public void displayDetails() {
        System.out.println("Title: " + title + ", Duration: " + duration + " mins");
    }
}
