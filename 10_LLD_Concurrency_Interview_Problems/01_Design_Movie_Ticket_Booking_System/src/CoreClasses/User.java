package CoreClasses;

public class User {
    private final String name; // Name of the user
    private final String emailAddress;  // Email of the User

    public User(final String name, final String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    // Getters Section Start
    public String getUserName() {
        return name;
    }
    public String getUserEmail() {
        return emailAddress;
    }
    // Getters Section End
}