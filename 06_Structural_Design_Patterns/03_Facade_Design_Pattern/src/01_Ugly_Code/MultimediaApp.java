package Ugly_Code;

import java.util.Scanner;

public class MultimediaApp {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose an action: playMusic, playVideo, viewImage");
            String action = scanner.nextLine();
            if (action.equalsIgnoreCase("playMusic")) {
                MusicPlayer musicPlayer = new MusicPlayer();
                musicPlayer.initializeAudioDrivers();
                musicPlayer.decodeAudio();
                musicPlayer.startPlayback();
            } else if (action.equalsIgnoreCase("playVideo")) {
                VideoPlayer videoPlayer = new VideoPlayer();
                videoPlayer.setupRenderingEngine();
                videoPlayer.loadVideoFile();
                videoPlayer.playVideo();
            } else if (action.equalsIgnoreCase("viewImage")) {
                ImageViewer imageViewer = new ImageViewer();
                imageViewer.loadImageFile();
                imageViewer.applyScaling();
                imageViewer.displayImage();
            } else {
                System.out.println("Invalid action!");
            }
            scanner.close();
        }
}

/*

output :
Choose an action: playMusic, playVideo, viewImage
playMusic
Initializing audio drivers...
Decoding audio file...
Starting audio playback...

*/