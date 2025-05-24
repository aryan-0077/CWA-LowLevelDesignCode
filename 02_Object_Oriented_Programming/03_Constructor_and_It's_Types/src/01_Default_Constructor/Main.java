package DefaultConstructor;

public class Main {
    public static void main(String[] args) {
        Movie movie = new Movie(); // Implicit default constructor is called
        movie.displayDetails(); // Displays default values
    }
}

/*

Output 1 :
Title: null, Duration: 0 mins

Output 2 :
Title: Untitled, Duration: 90 mins

*/