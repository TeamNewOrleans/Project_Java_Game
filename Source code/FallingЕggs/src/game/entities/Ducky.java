package game.entities;

import game.Game;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ducky {
    private int x, y, width, height;
    private int colum = 0; // set column in spritesheet

    public Rectangle boundingBox;
    //private BufferedImage img; // for static chicken
    private SpriteSheet flyingChick;// for flying chick

    Random random = new Random();


    public Ducky(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.flyingChick = Assets.chickSheet;

    }

    public  void tick() {
        this.y += random.nextInt(10); //mooving;
        if(y >= (Game.HEIGHT)){
            this.y = 0;
            this.x = random.nextInt(Game.WIDTH - Game.MAX_X);
        }
        this.colum++;
        this.colum %= 2;

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height); //ъпдейдваме боиндинбокса
    }


    public void render(Graphics g){


        //g.drawImage(this.img, x, this.y, this.width, this.height, null); //draw static chicken
        g.drawImage(this.flyingChick.crop(this.colum*this.width, 348, this.width, this.height), this.x, this.y, null);

    }

    public int getY(){
        return this.y;
    }

    public void setY(int y){
        this.y = y;
    }
}
