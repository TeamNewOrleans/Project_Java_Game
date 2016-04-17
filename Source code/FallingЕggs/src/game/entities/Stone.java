package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Stone extends  Entity{
    public Rectangle boundingBox;
    private int x, y, width, height;
    private int column = 0;
    private SpriteSheet roundStone; // for rounded stones
    private Random random;

    public Stone(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.roundStone = Assets.stoneSheet; // rounded stone
        this.random = new Random();
    }

    public  void tick() {
        this.y += random.nextInt(10);
        if(y >= (Game.HEIGHT)){
            this.y = 0;
            this.x = random.nextInt(Game.WIDTH - Game.MAX_X);
        }

        //rounded stones
        this.column++;
        this.column %= 23;

        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics g){
        g.drawImage(this.roundStone.crop(this.column*this.width, 308, this.width, this.height), this.x, this.y, null); // draw the rounded stones
    }

    public static void addStone(Stone st, ArrayList<Stone> stones) { //da se iznese w obshtiq klas
        stones.add(st);
    }

   public int getY(){
       return this.y;
   }

   public void setY(int y){
       this.y = y;
   }
}
