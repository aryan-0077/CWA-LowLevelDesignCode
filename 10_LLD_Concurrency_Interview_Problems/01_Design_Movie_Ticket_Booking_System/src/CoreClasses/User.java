package CoreClasses;



public class User {



    // Name of the theatre

    private final String name;

    // Email of the User
    private final String emailAddress;

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