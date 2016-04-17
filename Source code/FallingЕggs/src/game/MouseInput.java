package game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    public MouseInput(Canvas canvas) {
        canvas.addMouseListener(this);
    }

    public MouseInput() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        /* public Rectangle playButton = new Rectangle((WIDHT-120)/2, 180, 100, 50);
    public Rectangle helpButton = new Rectangle((WIDHT-120)/2, 280, 100, 50);
    public Rectangle exitButton = new Rectangle((WIDHT-120)/2, 380, 100, 50);*/

        int mx = e.getX();
        int my = e .getY();

        // Start Game
        if (mx >= (400-74) && mx <= (400+74)&& Game.State == Game.STATE.MENU){
            if (my >= 180+40 && my <= 180+68){
                //Pressed Play Button
                Game.State = Game.STATE.GAME;
            }
        }

        // Go to Help
        if (mx >= 400-74 && mx <= 400+74 && Game.State == Game.STATE.MENU){
            if (my >= 180+90 && my <= 180+118){
                //Pressed Help Button
                Game.State = Game.STATE.HELP;

            }

        }

        // Back to Menu from Help
        if (mx >= 160 && mx <= 400 && Game.State == Game.STATE.HELP){
            if (my >= 110 && my <= 200){
                //Pressed Back to Menu
                Game.State = Game.STATE.MENU;

            }
        }


        // Exit and Close
        if (mx >= 400-74 && mx <= 400+74 && Game.State == Game.STATE.MENU){
            if (my >= 180+140 && my <= 180+168){
                //Pressed Exit Button
                System.exit(1);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
