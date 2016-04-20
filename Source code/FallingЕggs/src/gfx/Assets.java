package gfx;

import interfaces.Initializingable;

import java.awt.image.BufferedImage;

public class Assets {

    public static SpriteSheet player; // rabbit sprite sheet
    public static SpriteSheet eggSheet; // falling egg sprite sheet
    public static SpriteSheet stoneSheet; // falling stone sprite sheet
    public static SpriteSheet chickSheet; // falling chick sprite sheet

    public static BufferedImage background; // background on Game state
    public static BufferedImage menuButtons; // about menu image with buttons
    public static BufferedImage menuBackground; // background on Menu state
    public static BufferedImage help; // image about help
    public static BufferedImage gameOverWin; //image for end the game - Winner
    public static BufferedImage gameOverLost; //image for end the game - Looser

    public static void init() {
        background = ImageLoader.loadImage("/game_bckg.png"); // image for game background
        menuBackground = ImageLoader.loadImage("/menu_bckg.png"); // image for menu background
        player = new SpriteSheet(ImageLoader.loadImage("/sprite_sheet.png")); // image for player
        eggSheet = new SpriteSheet(ImageLoader.loadImage(("/sprite_sheet.png"))); // image for rounded eggs
        stoneSheet = new SpriteSheet(ImageLoader.loadImage(("/sprite_sheet.png"))); // image for rounded stones
        chickSheet = new SpriteSheet(ImageLoader.loadImage(("/sprite_sheet.png"))); // image for flying chicks
        menuButtons = ImageLoader.loadImage("/menu.png"); // Menu image with buttons
        help = ImageLoader.loadImage("/help.png"); // image for help
        gameOverWin = ImageLoader.loadImage("/game_over_win.png");  //image for end of the game
        gameOverLost = ImageLoader.loadImage("/game_over_lost.png");  //image for end of the game
    }
}