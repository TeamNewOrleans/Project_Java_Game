package game;

import game.entities.Player;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TouchPoint;
import display.Menu;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{
    private Canvas canvas;

    public InputHandler(Canvas canvas) {
        canvas.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (Game.State == Game.STATE.GAME){

        }
        if (code == KeyEvent.VK_RIGHT){
            Player.isMovingRight = true;
            Player.isMovingLeft = false;
        } else if (code == KeyEvent.VK_LEFT){
            Player.isMovingRight = false;
            Player.isMovingLeft = true;
        }

        if (code == KeyEvent.VK_ESCAPE){
            Game.isEsc = true;
        }

     //   if (code == KeyEvent.VK_SHIFT){
     //       Game.isPause = true;
     //   }
//
     //   if (code == KeyEvent.VK_ENTER){
     //       Game.isStart = true;
     //   }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_RIGHT){   //когато пуснем бутона и двете са фалс, т.е. не се движи
            Player.isMovingRight = false;
            Player.isMovingLeft = false;
        } else if (code == KeyEvent.VK_LEFT){
            Player.isMovingRight = false;
            Player.isMovingLeft = false;
        }

    }
}
