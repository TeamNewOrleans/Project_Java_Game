package display;

import gfx.Assets;
import gfx.SpriteSheet;
import javafx.stage.Screen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;


public class Menu {
   private static final int WIDHT = 800;
    private BufferedImage menuImg; // menu image
    private BufferedImage menuBckg; // menu background image

    // menu location
    int x = (WIDHT-100)/2;
    int y = 180;

    // menu size (according image size)
    int menuWidth = 201;
    int menuHeight = 236;




   //delete this
    /*public Rectangle playButton = new Rectangle((WIDHT-120)/2, 180, 100, 50);
    public Rectangle helpButton = new Rectangle((WIDHT-120)/2, 280, 100, 50);
    public Rectangle exitButton = new Rectangle((WIDHT-120)/2, 380, 100, 50);*/

    public Menu (){
        this.menuImg = Assets.menuButtons;
        this.menuBckg = Assets.menuBackground;
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(this.menuBckg, 0, 0, 800, 600, null);// draw background image on menu STATE
        g.drawImage(this.menuImg, this.x, this.y, this.menuWidth, this.menuHeight, null); //draw menu image


        //Delete this
        /*Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.green);
        g.drawString("Falling Eggs", (WIDHT-320)/2, 100);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.setColor(Color.yellow);
        g.drawString("Play", playButton.x + 20, playButton.y + 35);

        Font fnt2 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt2);
        g.setColor(Color.pink);
        g.drawString("Help", helpButton.x + 20, helpButton.y + 35);

        Font fnt3 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt3);
        g.setColor(Color.red);
        g.drawString("Exit", exitButton.x + 20, exitButton.y + 35);

        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(exitButton);*/


    }


}

