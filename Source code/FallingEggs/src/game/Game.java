package game;

import display.Display;
import game.entities.Egg;
import game.entities.Player;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private String name;
    private int width, height;


    private Thread thread; //нишка
    private boolean isRunning;


    private int l1 = 0;
    private int l2 = -100;
    private int l3 = -200;
    private int col;
    private Player boy;
    private Egg egg;
    private InputHandler ih;


    private Display display;
    private BufferStrategy bufferStrategy;   //начина по който ние контролираме обектите да се визуализират
    private Graphics graphics;//този койот ги изрисува
    private SpriteSheet spriteSheet;

    public Game(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;

    }

    public void init() {
        Assets.init();
        this.display = new Display(this.name, this.width, this.height);
        this.ih = new InputHandler(this.display.getCanvas());
        this.boy = new Player(120, 400, 20);
        this.egg = new Egg(300, 450, 148, 125);

    }

    public void tick() { //визуализираме нещата
        this.l1 += 5;
        this.l2 += 7;
        this.l3 += 10;

        if (this.egg != null && this.boy.boundingBox.intersects(this.egg.boundingBox)) {
            this.boy.getDamega(20);
            this.egg = null;
        }

        this.boy.tick();
        //  if (this.boy.hitPoints == 0) {
        //     System.out.println("DEAD");
        //     this.stop();
        //  }
        if (this.egg != null) {
            this.egg.tick();
        }
    }

    protected void render() { //визуализираме нещата
        this.bufferStrategy = this.display.getCanvas().getBufferStrategy();
        if (this.bufferStrategy == null) {
            this.display.getCanvas().createBufferStrategy(2); //два буфера за работа
            this.bufferStrategy = this.display.getCanvas().getBufferStrategy();
        }

        this.graphics = this.bufferStrategy.getDrawGraphics();

        this.graphics.clearRect(0, 0, this.width, this.height);

        //Start drowing
        this.graphics.drawImage(ImageLoader.loadImage("/bng.jpg"), 0, 0, 800, 600, null);
        this.boy.render(this.graphics);

        if (this.egg != null) {
            this.egg.render(this.graphics);
        }


        //this.graphics.setColor(Color.pink);
       // this.graphics.fillOval(100, this.l1, 20, 20);


       // this.graphics.setColor(Color.red);
       // this.graphics.fillOval(200, this.l2, 20, 20);


       // this.graphics.setColor(Color.green);
       // this.graphics.fillOval(300, this.l3, 20, 20);
       //End drawing

        this.graphics.dispose();
        this.bufferStrategy.show();
    }

    @Override
    public void run() {
        this.init(); //инизиализация

        int fps = 20;//60 фрейме се преизчисляват и пренарисъват за секунда, 60 за секунда

        double timePerFrame = 1_000_000_000 / fps; //колко време трябва да се изчака на всяко визуализиране
        double delta = 0;
        long now;
        long lastTimeTicked = System.nanoTime();

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTimeTicked) / timePerFrame;
            lastTimeTicked = now;
            System.out.println(delta);

            if (delta >= 1) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                tick();
                render();
                delta--;
            }

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
