package game;

import display.Display;
import display.FirstDisplay;


public class Launcher {
    public static void main(String[] args) {
        FirstDisplay display = new FirstDisplay("Start", 800, 600);
        display.start();
        //  Game game = new Game("Street Fighter", 800, 600);
        // game.start();

    }
}
