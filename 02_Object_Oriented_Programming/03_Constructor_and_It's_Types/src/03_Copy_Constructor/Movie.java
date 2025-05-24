package CopyConstructor;

public class Movie {
    private String title;
    private int duration;

    // Parameterized constructor
    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    // Copy constructor
    public Movie(Movie other) {
        this.title = other.title;
        this.duration = other.duration;
    }
    public void displayDetails() {
        System.out.println("Title: " + title + ", Duration: " + duration + " mins");
    }
}