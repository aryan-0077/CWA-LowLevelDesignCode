package Good_Code.Concrete_Subscribers;

import Good_Code.Interfaces.Subscriber;

public class YouTubeSubscriber implements Subscriber {
    private String name; // Name of the subscriber

    public YouTubeSubscriber(String name) {
        this.name = name; // Initialize the subscriber with their name
    }

    @Override
    public void update(String video) {
        // When notified, this method will execute, and the subscriber watches the
        // new video
        System.out.println(name + " is watching the video: " + video);
    }
}