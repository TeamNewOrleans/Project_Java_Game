package game.entities;

import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class Player {
    public int x, y, width, height,  velocity, hitPoints;
    private SpriteSheet img;
    public Rectangle boundingBox; //външната кутия на играча, дали се е ударил в нещо

    public static boolean isMovingLeft, isMovingRight;
    private int colum = 0;
    private int row = 0;

    public Player(int x, int y, int hitPoints) {
        this.x = x;
        this.y = y;
        this.hitPoints = hitPoints;
        this.velocity = 20;
        this.width = 108;
        this.height = 140;
        this.img = Assets.player;
        this.boundingBox = new Rectangle(x, y, this.width, this.height);
    }

    public void tick(){
        if (isMovingRight){
            this.x += this.velocity;
            row = 0;
        }else if (isMovingLeft){
            this.x -= this.velocity;
            row = 1;
        } else {
            this.row = 0;
            this.colum = 0;
        }

        this.colum++;
        this.colum %= 8; //за да се върнем на първата картинка
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height); //ъпдейдваме боиндинбокса
    }

    public void render(Graphics gr){
        gr.drawImage(this.img.crop(this.colum*this.width, this.row*this.height, this.width, this.height), this.x, this.y, null);
    }

    public boolean intersects(Rectangle boundingBox){
        return this.boundingBox.contains(boundingBox) || boundingBox.contains(this.boundingBox);
    }

    public void getDamega(int amount){
        this.hitPoints -= amount;

    }
    public void speedUp(){
        this.velocity += 10;
    }
}
