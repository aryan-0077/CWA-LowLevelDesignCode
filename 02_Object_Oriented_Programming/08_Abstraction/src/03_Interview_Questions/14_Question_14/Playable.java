package Interview_Questions.Question_14;

public interface Playable {
    // Default method
    default void play() {
        System.out.println("Playing with the animal.");
    }
    // Attempt to declare an instance variable (not allowed in interfaces)
    // String name = "Buddy";

    // Interfaces can only contain static final variables, which are essentially
    // constants. Since it is static you cannot call it instance variable.
    // default void setName(String name) {
      //  this.name = name; // Error: Interfaces cannot have instance variables
    //}
}
