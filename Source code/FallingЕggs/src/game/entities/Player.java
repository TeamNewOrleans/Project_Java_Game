package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;
import interfaces.Renderable;
import interfaces.Tickable;

import java.awt.*;

public class Player extends Entity {
    public static boolean isMovingLeft, isMovingRight;
    public Rectangle boundingBox;
    public int hitPoints;
    private int x, y, velocity = 20;
    private static final int width = 132, height = 131;
    private SpriteSheet img;
    private int column = 0;
    private int row = 0;
    private int numberOfColumnSpriteSheet = 8;
    private int startPosition = 0;
    private int endPosition = 120;

    public Player(int x, int y, int hitPoints) {
        super(x, y, 132, 131);
        this.x = x;
        this.y = y;
        this.hitPoints = hitPoints;
        this.img = Assets.player;
        this.boundingBox = new Rectangle(x, y, this.width, this.height);
    }

    public void tick() {
        if (isMovingRight) {
            this.x += this.velocity;
            if (this.x + endPosition >= Game.WIDTH) {
                this.x -= this.velocity;
            }

            row = 0;
        } else if (isMovingLeft) {
            this.x -= this.velocity;
            if (this.x <= startPosition) {
                this.x = startPosition;
            }

            row = 1;
        } else {
            this.row = 0;
            this.column = 0;
        }

        this.column++;
        this.column %= numberOfColumnSpriteSheet;

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics gr) {
        gr.drawImage(this.img.crop(this.column * this.width, this.row * this.height, this.width, this.height), this.x, this.y, null);
    }

    public void addDamega(int amount) {
        this.hitPoints += amount;
    }

    public void takeDamega(int amount) {
        this.hitPoints -= amount;
    }
}