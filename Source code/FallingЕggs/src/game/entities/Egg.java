package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Egg extends Entity {
    private int x, y, width, height;
    private int column = 0;
    public Rectangle boundingBox;
    private SpriteSheet roundEgg; //for rounded egg
    private Random random;

    public Egg(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.roundEgg = Assets.eggSheet;
        random = new Random();
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void tick() {
        this.y += random.nextInt(10);
        if (this.y >= (Game.HEIGHT)) {
            this.y = 0;
            this.x = random.nextInt(Game.WIDTH - Game.MAX_X);
        }

        this.column++;
        this.column %= 24;

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics g) {
        g.drawImage(this.roundEgg.crop(this.column * this.width, 272, this.width, this.height), this.x, this.y, null); // draw the rounded eggs
    }

    public static void addEgg(Egg egg, ArrayList<Egg> eggs) {
        eggs.add(egg);
    }
}