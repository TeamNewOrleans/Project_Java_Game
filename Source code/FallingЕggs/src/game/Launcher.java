package game;

import display.Display;
//import display.StartDisplay;


public class Launcher {
    public static void main(String[] args) {
      //  StartDisplay display = new StartDisplay("Falling Еggs", 800, 600);
       // display.start();

        Game game = new Game("Falling Eggs by team NewOrleans - 2016", 800, 620); // height is higher than image because title use 20 px
        game.start();

    }
}
