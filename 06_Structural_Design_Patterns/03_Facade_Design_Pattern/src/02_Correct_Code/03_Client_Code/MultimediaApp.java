package Correct_Code.Client_Code;

import Correct_Code.MediaFacade.MediaFacade;

import java.util.Scanner;

public class MultimediaApp {
    public static void main(String[] args) {
        MediaFacade mediaFacade = new MediaFacade();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Multimedia App!");
        System.out.println("Choose an action: playMusic, playVideo, viewImage");
        String action = scanner.nextLine();
        mediaFacade.performAction(action);
        scanner.close();
    }
}

/*

Welcome to Multimedia App!
Choose an action: playMusic, playVideo, viewImage
playVideo
Rendering engine set up.
Video file loaded.
Video playback started.

*/