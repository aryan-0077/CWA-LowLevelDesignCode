package CoreClasses;

import java.util.ArrayList;
import java.util.List;

public class Theatre {

    private final int id;  // Unique identifier for the theatre
    private final String name; // Name of the theatre
    private final List<Screen> screens; // List of screens available in the theatre

    public Theatre(final int id,final String name) {
        this.id = id;
        this.name = name;
        this.screens = new ArrayList<>();
    }

    public void addScreen(final  Screen screen) {
        screens.add(screen);
    }

    // Getters Section Start
    public int getTheatreId() {
        return id;
    }
    public List<Screen> getScreen() {
        return screens;
    }
    // Getters Section End
}
