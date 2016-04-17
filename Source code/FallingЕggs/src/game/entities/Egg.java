package game.entities;

import game.Game;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Egg {
    private int x, y, width, height;
    private int colum =0;
    public Rectangle boundingBox;


    //private BufferedImage img; // for static egg delete this
    private SpriteSheet roundEgg;// for rounded egg

    Random random = new Random();


    public Egg(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.roundEgg = Assets.eggSheet;

    }

    public  void tick() {

        this.y += random.nextInt(10); //mooving;
        if(y >= (Game.HEIGHT)){
            this.y = 0;
            this.x = random.nextInt(Game.WIDTH - Game.MAX_X);
        }
        this.colum++;
        this.colum %= 24;

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height); //ъпдейдваме боиндинбокса
    }


    public void render(Graphics g){

        g.drawImage(this.roundEgg.crop(this.colum*this.width, 272, this.width, this.height), this.x, this.y, null); // draw the rounded eggs

        //g.drawImage(this.img, x, this.y, this.width, this.height, null);
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y){
        this.y = y;
    }
}
