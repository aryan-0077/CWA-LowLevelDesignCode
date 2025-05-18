package Good_Code;

import Good_Code.Interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannelImpl implements YouTubeChannel {
    private List<Subscriber> subscribers = new ArrayList<>(); // List of subscribers
    private String video; // The video that will be uploaded

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber); // Add a subscriber to the channel
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber); // Remove a subscriber from the channel
    }

    @Override
    public void notifySubscribers() {
        // Notify all subscribers about the new video
        for (Subscriber subscriber : subscribers) {
            subscriber.update(video); // Call update() for each subscriber
        }
    }

    public void uploadNewVideo(String video) {
        this.video = video; // Set the video that is being uploaded
        notifySubscribers(); // Notify all subscribers about the new video
    }
}
