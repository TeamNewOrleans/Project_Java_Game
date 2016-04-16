package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;
    public static SpriteSheet player;
    public static BufferedImage egg;

    public static void init(){
       //background = ImageLoader.loadImage("/bcp.jpg");
        player = new SpriteSheet(ImageLoader.loadImage("/boy.png"));
        egg = ImageLoader.loadImage("/ball.png");
    }
}
