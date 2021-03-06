package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Egg extends Entity {
    public Rectangle boundingBox;
    private int x, y, width, height;
    private int column = 0;
    private SpriteSheet roundEgg; //for rounded egg
    private Random random;
    private int maxRandomNumber = 10;
    private int cropY = 272;

    public Egg(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.roundEgg = Assets.eggSheet;
        random = new Random();
    }

    public void tick() {
        this.y += random.nextInt(maxRandomNumber);
        if (this.y >= (Game.HEIGHT)) {
            this.y = 0;
            this.x = random.nextInt(Game.WIDTH - Game.MAX_X);
        }

        this.column++;
        this.column %= 24;

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics g) {
        g.drawImage(this.roundEgg.crop(this.column * this.width, cropY, this.width, this.height), this.x, this.y, null); // draw the rounded eggs
    }

    public static void addEgg(Egg egg, ArrayList<Egg> eggs) {
        eggs.add(egg);
    }
}