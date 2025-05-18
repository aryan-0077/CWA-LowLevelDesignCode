package Ugly_Code;

public class YouTubeSubscriber {
    private String name;

    public YouTubeSubscriber(String name) {
        this.name = name;
    }
    public void subscribe(YouTubeChannel channel) {
        channel.addSubscriber(name);
    }
    public void watchVideo(YouTubeChannel channel) {
        System.out.println(name + " is watching the video: " + channel.getVideo());
    }
}
