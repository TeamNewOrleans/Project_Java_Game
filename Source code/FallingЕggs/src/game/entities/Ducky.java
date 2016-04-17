package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ducky extends Entity {
    private int x, y, width, height;
    private int column = 0;
    public Rectangle boundingBox;
    private SpriteSheet flyingChick; //for flying chick
    private Random random;

    public Ducky(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.flyingChick = Assets.chickSheet;
        this.random = new Random();
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
        this.column %= 2;

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics g) {
        g.drawImage(this.flyingChick.crop(this.column * this.width, 348, this.width, this.height), this.x, this.y, null);
    }

    public static void addDucky(Ducky dc, ArrayList<Ducky> duckies) { //da se iznese w obshtiq klas
        duckies.add(dc);
    }
}