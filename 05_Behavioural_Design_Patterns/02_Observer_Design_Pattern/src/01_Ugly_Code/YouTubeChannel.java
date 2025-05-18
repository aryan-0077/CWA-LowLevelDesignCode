package Ugly_Code;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel {
    private List<String> subscribers = new ArrayList<>();
    private String video;

    // Method to add a new subscriber
    public void addSubscriber(String subscriber) {
        subscribers.add(subscriber);
    }

    // Method to upload a new video
    public void uploadNewVideo(String video) {
        this.video = video;
        notifySubscribers(); // Notify all subscribers about the new video
    }

    // Notify all subscribers
    public void notifySubscribers() {
        for (String subscriber : subscribers) {
            System.out.println("Notifying " + subscriber + " about new video: " + video);

            // Add new feature: send an email notification
            sendEmail(subscriber);
        }
    }

    public void sendEmail(String subscriber) {
        System.out.println("Sending email to " + subscriber);
    }
    public String getVideo(){
        return video;
    }
}
