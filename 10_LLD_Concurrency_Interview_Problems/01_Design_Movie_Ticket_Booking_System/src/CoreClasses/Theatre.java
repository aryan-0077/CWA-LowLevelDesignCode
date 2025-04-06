package CoreClasses;

import java.util.ArrayList;
import java.util.List;

public class Theatre {


    // Unique identifier for the theatre
    private final int id;

    // Name of the theatre
    private final String name;

    // List of screens available in the theatre
    private final List<Screen> screens;

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
