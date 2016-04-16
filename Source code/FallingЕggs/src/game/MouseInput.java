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
        if (mx >= (800-120)/2 && mx <= (800+120)/2){
            if (my >= 180 && my <= 230){
                //Pressed Play Button
                Game.State = Game.STATE.GAME;
            }
        }

        if (mx >= (800-120)/2 && mx <= (800+120)/2){
            if (my >= 280 && my <= 330){
                //Pressed Help Button

            }
        }

        if (mx >= (800-120)/2 && mx <= (800+120)/2){
            if (my >= 380 && my <= 430){
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
