package game;

import display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Display display;
    private BufferStrategy bs;
    private Graphics g;

    public String title;

    private boolean isRunning = false;
    private Thread thread;

    public Game(String name) {
        this.title = name;
    }

    private void init() {
        this.display = new Display(this.title, WIDTH, HEIGHT);
    }

    private void tick() {

    }

    private void render() {
        this.bs = this.display.getCanvas().getBufferStrategy();

        if (this.bs == null) {
            this.display.getCanvas().createBufferStrategy(2);
            return;
        }

        this.g = this.bs.getDrawGraphics();

        //Start drawing

        //End drawing

        this.g.dispose();
        this.bs.show();
    }

    @Override
    public void run() {
        this.init();

        while (isRunning) {
            this.tick();
            this.render();
        }

        this.stop();
    }

    public synchronized void start() {
        this.thread = new Thread(this);

        this.isRunning = true;
        this.thread.start();
    }

    public synchronized void stop() {
        try {
            this.isRunning = false;
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}