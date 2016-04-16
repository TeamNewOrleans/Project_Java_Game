package display;

import game.InputHandler;
import gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class LastDispley implements Runnable{
    private String name;
    private int width, height;


    private Thread thread; //нишка
    private boolean isRunning;

    private InputHandler ih;

    private Display display;
    private BufferStrategy bufferStrategy;   //начина по който ние контролираме обектите да се визуализират
    private Graphics graphics;//този койот ги изрисува

    public LastDispley(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;

    }

    public void init() {

        this.display = new Display(this.name, this.width, this.height);
        this.ih = new InputHandler(this.display.getCanvas());

    }

    public void tick() { //визуализираме нещата

    }

    protected void render() { //визуализираме нещата
        this.bufferStrategy = this.display.getCanvas().getBufferStrategy();
        if (this.bufferStrategy == null) {
            this.display.getCanvas().createBufferStrategy(2); //два буфера за работа
            this.bufferStrategy = this.display.getCanvas().getBufferStrategy();
        }

        this.graphics = this.bufferStrategy.getDrawGraphics();


        this.graphics.drawImage(ImageLoader.loadImage("/about.jpg"), 0, 0, 800, 600, null);

        this.graphics.dispose();
        this.bufferStrategy.show();

    }

    @Override
    public void run() {
        this.init(); //инизиализация


        while (isRunning) {
            tick();
            render();
        }

        this.stop(); //за да спрем нишката
    }

    public synchronized void start() {
        this.isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        try {
            this.isRunning = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
