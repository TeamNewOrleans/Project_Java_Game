package display;

import gfx.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu {
   private static final int WIDHT = 800;
    private BufferedImage menuImg; // menu image
    private BufferedImage menuBckg; // menu background image

    // menu location
    int x = (WIDHT-170)/2;
    int y = 180;

    // menu size (according image size)
    int menuWidth = 201;
    int menuHeight = 236;

    public Menu (){
        this.menuImg = Assets.menuButtons;
        this.menuBckg = Assets.menuBackground;
    }

    public void render(Graphics g) {
        g.drawImage(this.menuBckg, 0, 0, 800, 600, null);// draw background image on menu STATE
        g.drawImage(this.menuImg, this.x, this.y, this.menuWidth, this.menuHeight, null); //draw menu image
    }
}

