package game;

import game.entities.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    public InputHandler(Canvas canvas) {
        canvas.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (Game.State == Game.STATE.GAME) {

        }

        if (code == KeyEvent.VK_RIGHT) {
            Player.isMovingRight = true;
            Player.isMovingLeft = false;
        } else if (code == KeyEvent.VK_LEFT) {
            Player.isMovingRight = false;
            Player.isMovingLeft = true;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            Game.isEsc = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_RIGHT) {
            Player.isMovingRight = false;
            Player.isMovingLeft = false;
        } else if (code == KeyEvent.VK_LEFT) {
            Player.isMovingRight = false;
            Player.isMovingLeft = false;
        }

        if (code == KeyEvent.VK_P && Game.State == Game.STATE.GAME) {
            Game.State = Game.STATE.PAUSE;
        } else if (code == KeyEvent.VK_P && Game.State == Game.STATE.PAUSE) {
            Game.State = Game.STATE.GAME;
        }
    }
}
