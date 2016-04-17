package game.entities;

import game.Game;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Stone {
    private int x, y, width, height;
    private int colum = 0; // set column of first stone sprite
    public Rectangle boundingBox;
    //private BufferedImage img; // this is for static stone
    private SpriteSheet roundStone;// for rounded stones

    Random random = new Random();

    public Stone(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.roundStone = Assets.stoneSheet; // rounded stone
    }

    public  void tick() {
        this.y += random.nextInt(10); //mooving;
        if(y >= (Game.HEIGHT)){
            this.y = 0;
            this.x = random.nextInt(Game.WIDTH - Game.MAX_X);
        }

        //rounded stones
        this.colum++;
        this.colum %= 23; //за да се върнем на първата картинка

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height); //ъпдейдваме боиндинбокса
    }


    public void render(Graphics g){


        //g.drawImage(this.img, x, this.y, this.width, this.height, null); // draw static stone
        g.drawImage(this.roundStone.crop(this.colum*this.width, 308, this.width, this.height), this.x, this.y, null); // draw the rounded stones
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y){
        this.y = y;
    }
}
