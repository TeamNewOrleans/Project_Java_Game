package game.entities;

import gfx.Assets;
import gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Egg {
    private int x, y, width, height;
    public Rectangle boundingBox;
    private BufferedImage img;
    private int z = y;
    private int sleep;

    public Egg(int x, int y, int width, int height, int sleep) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.img = Assets.egg;
        this.sleep = sleep;
    }

    public  void tick() {

        //this.z += 5;
        this.z += sleep;

        this.boundingBox.setBounds(this.x, this.z, this.width, this.height); //ъпдейдваме боиндинбокса
    }


    public void render(Graphics g){
        g.drawImage(this.img, x, this.z, this.width, this.height, null);
    }
}
