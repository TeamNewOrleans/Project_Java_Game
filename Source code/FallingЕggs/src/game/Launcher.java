package game;

import display.Display;
//import display.StartDisplay;


public class Launcher {
    public static void main(String[] args) {
      //  StartDisplay display = new StartDisplay("Falling Еggs", 800, 600);
       // display.start();

        Game game = new Game("Street Fighter", 800, 600);
        game.start();

    }
}
