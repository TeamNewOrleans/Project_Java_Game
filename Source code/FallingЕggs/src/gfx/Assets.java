package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;
    public static SpriteSheet player;
    public static SpriteSheet eggSheet; // egg sprite sheet
    public static SpriteSheet stoneSheet; // stone sprite sheet
    public static SpriteSheet chickSheet; // chick sprite sheet

    public static BufferedImage menuButtons; // about menu image with buttons
    //public static BufferedImage egg; // delete this
    public static BufferedImage ducky;// delete this
    public static BufferedImage stone;// delete this
    public static BufferedImage menuBackground;

    public static BufferedImage help; // image about help


    public static void init() {
        background = ImageLoader.loadImage("/game_bckg.png"); // image for game background
        menuBackground = ImageLoader.loadImage("/menu_bckg.png"); // image for menu background
        player = new SpriteSheet(ImageLoader.loadImage("/sprite_sheet.png")); // image for player


        eggSheet = new SpriteSheet(ImageLoader.loadImage(("/sprite_sheet.png"))); // image for rounded eggs
        stoneSheet = new SpriteSheet(ImageLoader.loadImage(("/sprite_sheet.png"))); // image for rounded stones
        chickSheet = new SpriteSheet(ImageLoader.loadImage(("/sprite_sheet.png"))); // image for flying chicks


        //egg = ImageLoader.loadImage("/egg.png"); // image for falling eggs
        ducky = ImageLoader.loadImage("/chicken.png");// image for falling chicken
        stone = ImageLoader.loadImage("/stone.png"); // image for falling stones

        menuButtons = ImageLoader.loadImage("/menu.png"); // Menu image with buttons
        help = ImageLoader.loadImage("/help.png"); // image for help

    }
}
