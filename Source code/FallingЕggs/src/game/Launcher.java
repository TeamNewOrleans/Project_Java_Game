package game;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Falling Eggs by team NewOrleans - 2016", 800, 620); // height is higher than image because title use 20 px
        game.start();
    }
}