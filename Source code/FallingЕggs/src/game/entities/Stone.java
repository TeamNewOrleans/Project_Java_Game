package game.entities;

import game.Game;
import gfx.Assets;
import gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Stone {
    private int x, y, width, height;
    public Rectangle boundingBox;
    private BufferedImage img;
    Random random = new Random();

    public Stone(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.img = Assets.stone;
    }

    public  void tick() {
        this.y += random.nextInt(10); //mooving;
        if(y >= (Game.HEIGHT)){
            this.y = 0;
            this.x = random.nextInt(Game.WIDTH - Game.MAX_X);
        }

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height); //ъпдейдваме боиндинбокса
    }


    public void render(Graphics g){
        g.drawImage(this.img, x, this.y, this.width, this.height, null);
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y){
        this.y = y;
    }
}
