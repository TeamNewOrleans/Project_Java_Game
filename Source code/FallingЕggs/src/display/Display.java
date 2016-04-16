package display;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Display/* implements ActionListener*/ {
    private JFrame frame;//прозорез/панел върху който може да правим неща
    private Canvas canvas; //пано върху което ще рисуваме

  // private JMenuItem menuExit; //menu
  // private JMenuItem menuFile; //menu
  // private JMenuItem menuAbout; //menu

    public Display(String name, int width, int height) {
        this.frame = new JFrame(name);
        this.frame.setPreferredSize(new Dimension(width, height));
        this.frame.setMinimumSize(new Dimension(width, height));
        this.frame.setMaximumSize(new Dimension(width, height));
        this.frame.setVisible(true);
        this.frame.setFocusable(true);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null); //излиза пи средата на екрана
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    //   //Start menu
    //   JMenuBar menuBar = new JMenuBar();

    //   menuFile = new JMenuItem("New Game", KeyEvent.VK_N);
    //   menuFile.addActionListener(this);
    //   menuBar.add(menuFile);

    //   menuExit = new JMenuItem("Exit");
    //   menuExit.addActionListener(this);
    //   menuBar.add(menuExit);

    //   menuAbout = new JMenuItem("Help");
    //   menuAbout.addActionListener(this);
    //   menuBar.add(menuAbout);

    //   this.frame.setJMenuBar(menuBar);
    //   this.frame.setVisible(true);
    //   //end menu

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(width, height));
        this.canvas.setMinimumSize(new Dimension(width, height));
        this.canvas.setMaximumSize(new Dimension(width, height));
        this.canvas.setFocusable(true);

        this.frame.add(canvas);
        this.frame.pack();

    }

    public Canvas getCanvas() {
        return canvas;
    }

 // @Override
 // public void actionPerformed(ActionEvent e) {
 //     if (e.getSource() == menuFile) {
 //         Game game = new Game("Falling Еggs", 800, 600);
 //         game.start();
 //     }

 //     if (e.getSource() == menuExit) {
 //         System.exit(1);
 //     }

 //     if (e.getSource() == menuAbout) {
 //         HelpDisplay game = new HelpDisplay("Falling Еggs", 800, 600);
 //         game.start();
 //     }
 // }
}
