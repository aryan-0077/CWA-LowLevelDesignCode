package DefaultConstructor;

public class Movie {
    private String title; // Default: null
    private int duration; // Default: 0

    // Custom default constructor
    public Movie() {
        this.title = "Untitled";
        this.duration = 90;
    }

    public void displayDetails() {
        System.out.println("Title: " + title + ", Duration: " + duration + " mins");
    }
}
