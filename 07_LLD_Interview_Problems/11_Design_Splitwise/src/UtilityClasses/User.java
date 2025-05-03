package UtilityClasses;

import java.util.Objects;

public class User {
    private String id;       // Unique identifier for the user
    private String name;     // Name of the user
    private String email;    // Email address of the user

    // Constructor to initialize User attributes
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters for the user's attributes
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    // Override equals() to compare users by ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    // Override hashCode() to generate hash based on user ID
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
