package display;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends Canvas implements ActionListener {
    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    private JMenuItem menuExit;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(this.title);
        frame.setSize(this.width, this.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true);


        JMenuBar menuBar = new JMenuBar();

        JMenuItem menuFile = new JMenuItem("New Game");
        menuBar.add(menuFile);

        menuExit = new JMenuItem("Exit");
        menuExit.addActionListener(this);
        menuBar.add(menuExit);

        JMenuItem menuAbout = new JMenuItem("About");
        menuBar.add(menuAbout);

        this.frame.setJMenuBar(menuBar);
        this.frame.setVisible(true);


        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == menuExit) {
            System.exit(0);
        }
    }
}
