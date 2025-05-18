package Ugly_Code;

public class Client_Code {
    // Main.java
        public static void main(String[] args) {
            VideoService videoService = new VideoService();
            // Free user trying to watch a video
            videoService.playVideo("free", "Free Video 1");
            // Premium user trying to watch a video
            videoService.playVideo("premium", "Premium Video 1");
            // Unauthorized user
            videoService.playVideo("guest", "Video 1");
        }
}

/*

Output 1:
Streaming free video: Free Video 1
Streaming premium video: Premium Video 1
Access denied: Invalid user type.

Output 2 :

Streaming new video: Free Video 1
Streaming new video: Premium Video 1
Streaming new video: Video 1

*/