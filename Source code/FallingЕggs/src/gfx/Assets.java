package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;
    public static SpriteSheet player;
    public static BufferedImage menuButtons; // about menu image with buttons
    public static BufferedImage egg;
    public static BufferedImage ducky;
    public static BufferedImage stone;
    public static BufferedImage menuBackground;

    public static void init() {
        background = ImageLoader.loadImage("/game_bckg.png"); // image for game background
        menuBackground = ImageLoader.loadImage("/menu_bckg.png"); // image for menu background
        player = new SpriteSheet(ImageLoader.loadImage("/bunny.png")); // image for player
        egg = ImageLoader.loadImage("/egg.png"); // image for falling eggs
        ducky = ImageLoader.loadImage("/chicken.png");// image for falling chicken
        stone = ImageLoader.loadImage("/stone.png"); // image for falling stones

        menuButtons = ImageLoader.loadImage("/menu.png"); // Menu image with buttons

    }
}
