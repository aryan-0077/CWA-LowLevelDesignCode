package Good_Code.Interfaces;

public interface YouTubeChannel {
    void addSubscriber(Subscriber subscriber); // Method to add a new subscriber
    void removeSubscriber(Subscriber subscriber); // Method to remove a subscriber
    void notifySubscribers(); // Method to notify all subscribers
}