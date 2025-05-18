package Good_Code;

import Good_Code.Concrete_Subscribers.YouTubeSubscriber;

public class Main {
    public static void main(String[] args) {
        // Create a YouTube channel
        YouTubeChannelImpl channel = new YouTubeChannelImpl();
        // Create subscribers
        YouTubeSubscriber alice = new YouTubeSubscriber("Alice");
        YouTubeSubscriber bob = new YouTubeSubscriber("Bob");
        // Subscribe to the channel
        channel.addSubscriber(alice);
        channel.addSubscriber(bob);
        // Upload a new video and notify subscribers
        channel.uploadNewVideo("Java Design Patterns Tutorial");
        // Output:
        // Alice is watching the video: Java Design Patterns Tutorial
        // Bob is watching the video: Java Design Patterns Tutorial
        // You can also remove a subscriber and upload another video
        channel.removeSubscriber(bob);
        channel.uploadNewVideo("Observer Pattern in Action");
        // Output:
        // Alice is watching the video: Observer Pattern in Action
    }
}

/*

Output :
Alice is watching the video: Java Design Patterns Tutorial
Bob is watching the video: Java Design Patterns Tutorial
Alice is watching the video: Observer Pattern in Action

*/
