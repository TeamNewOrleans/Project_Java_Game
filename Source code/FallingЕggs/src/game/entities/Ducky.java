package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ducky extends Entity {
    public Rectangle boundingBox;
    private int x, y, width, height;
    private int column = 0;
    private SpriteSheet flyingChick; //for flying chick
    private Random random;
    private int maxRandomNumber = 10;
    private int cropY = 348;

    public Ducky(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.flyingChick = Assets.chickSheet;
        this.random = new Random();
    }

    public void tick() {
        this.y += random.nextInt(maxRandomNumber);
        if (this.y >= (Game.HEIGHT)) {
            this.y = 0;
            this.x = random.nextInt(Game.WIDTH - Game.MAX_X);
        }

        this.column++;
        this.column %= 2;

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics g) {
        g.drawImage(this.flyingChick.crop(this.column * this.width, cropY, this.width, this.height), this.x, this.y, null);
    }

    public static void addDucky(Ducky dc, ArrayList<Ducky> duckies) { //da se iznese w obshtiq klas
        duckies.add(dc);
    }
}