package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class Player {
    public int x, y, width, height, velocity, hitPoints;
    public Rectangle boundingBox;
    public static boolean isMovingLeft, isMovingRight;

    private SpriteSheet img;
    private int column = 0;
    private int row = 0;

    public Player(int x, int y, int hitPoints) {
        this.x = x;
        this.y = y;
        this.hitPoints = hitPoints;
        this.velocity = 20;
        this.width = 132;
        this.height = 131;
        this.img = Assets.player;
        this.boundingBox = new Rectangle(x, y, this.width, this.height);
    }

    public void tick() {
        if (isMovingRight) {
            this.x += this.velocity;
            if (this.x + 120 >= Game.WIDTH) {
                this.x -= this.velocity;
            }

            row = 0;
        } else if (isMovingLeft) {
            this.x -= this.velocity;
            if (this.x <= 0) {
                this.x = 0;
            }

            row = 1;
        } else {
            this.row = 0;
            this.column = 0;
        }

        this.column++;
        this.column %= 8;

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics gr) {
        gr.drawImage(this.img.crop(this.column * this.width, this.row * this.height, this.width, this.height), this.x, this.y, null);
    }

    public boolean intersect(Rectangle boundingBox) {
        return this.boundingBox.contains(boundingBox) || boundingBox.contains(this.boundingBox);
    }

    public void addDamega(int amount) {
        this.hitPoints += amount;

    }

    public void takeDamega(int amount) {
        this.hitPoints -= amount;

    }
}
