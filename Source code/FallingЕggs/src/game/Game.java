package game;

import display.Display;
import game.entities.Ducky;
import game.entities.Egg;
import game.entities.Player;
import game.entities.Stone;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import display.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.ArrayList;

public class Game extends MouseInput implements Runnable {
    private String name;
    private int width, height;


    private Thread thread; //нишка
    private boolean isRunning;
    public static boolean isEsc;
    public static boolean isStop;
    // public static boolean isPause;
    // public static boolean isStart;


    // private int l1 = -100;
    // private int l2 = -300;
    // private int l3 = -500;
    //  private int l4 = -100;
    private int col;
    private Player rabbit;
    private ArrayList<Egg> eggs;
    private ArrayList<Ducky> duckies;
    //  private Ducky ducky;
    private ArrayList<Stone> stones;

    private InputHandler ih;
    private MouseInput mi;

    private Display display;
    private BufferStrategy bufferStrategy;   //начина по който ние контролираме обектите да се визуализират
    private Graphics graphics;//този койот ги изрисува
    private SpriteSheet spriteSheet;
    private BufferedImage bckg; // game background

    private Menu menu;

    public static enum STATE {
        MENU,
        GAME
    }

    ;

    public static STATE State = STATE.MENU;

    public Game(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;

    }

    public void init() {
        Assets.init();
        this.display = new Display(this.name, this.width, this.height);
        this.ih = new InputHandler(this.display.getCanvas());
        this.rabbit = new Player(120, 450, 0);

        this.bckg = Assets.background; // initialize game background image

        //  this.egg = new Egg(300, 450, 148, 125);
        //
        this.stones = new ArrayList<>();
        stones.add(new Stone(250, 0, 27, 17, 5));
        stones.add(new Stone(550, 0, 27, 17, 2));

        this.duckies = new ArrayList<>();
        duckies.add(new Ducky(350, 0, 33, 36, 4));
        duckies.add(new Ducky(600, 0, 33, 36, 6));

        this.eggs = new ArrayList<>();
        eggs.add(new Egg(150, 0, 20, 25, 3));
        eggs.add(new Egg(450, 0, 20, 25, 1));

        menu = new Menu();
        this.mi = new MouseInput(this.display.getCanvas());
        // this.addMouseListener(new MouseInput());

    }

    public void tick() { //визуализираме нещата
        //  isPause = false;
        //  isStart = false;
        //  isEsc = false;

        if (State == STATE.GAME) {
            this.rabbit.tick();
            for (int i = 0; i < eggs.size(); i++) {
                if (this.eggs.get(i) != null) {
                    this.eggs.get(i).tick();
                }
            }

            for (int i = 0; i < duckies.size(); i++) {
                if (this.duckies.get(i) != null) {
                    this.duckies.get(i).tick();
                }
            }

            for (int i = 0; i < stones.size(); i++) {
                if (this.stones.get(i) != null) {
                    this.stones.get(i).tick();
                }
            }

            if (isEsc) {
                System.exit(1);
            }


            //   if (isPause) {
            //       this.stop();
            //    //   isRunning = false;
            //    //   isEsc = true;
            //    //   isStart = false;


            //       if (isStart) {
            //           this.start();
            //           //isRunning = true;
            //          // isPause = false;
            //         //  isEsc = true;
            //       }
            //   }


            //   this.l1 += 5;
            //  this.l2 += 7;
            //  this.l3 += 10;
            //  this.l4 = +20;

            for (int i = 0; i < eggs.size(); i++) {
                if (this.eggs.get(i) != null && this.rabbit.boundingBox.intersects(this.eggs.get(i).boundingBox)) {
                    this.rabbit.addDamega(5);
                    eggs.remove(this.eggs.get(i));
                }
            }

            for (int i = 0; i < duckies.size(); i++) {
                if (this.duckies.get(i) != null && this.rabbit.boundingBox.intersects(this.duckies.get(i).boundingBox)) {
                    this.rabbit.addDamega(10);
                    duckies.remove(this.duckies.get(i));
                }
            }

            for (int i = 0; i < stones.size(); i++) {
                if (this.stones.get(i) != null && this.rabbit.boundingBox.intersects(this.stones.get(i).boundingBox)) {
                    this.rabbit.takeDamega(20);
                    stones.remove(this.stones.get(i));
                }
            }

            if (this.rabbit.hitPoints >= 30) {
                System.out.printf("\nCongratulation!\nYou can going home with %d eggs!", this.rabbit.hitPoints);

               // isStop = true;
                this.stop();
            }else if (this.rabbit.hitPoints < 0){
                System.out.printf("Sorry, all the eggs are broken!");
                this.stop();
            }

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
        //this.graphics.drawImage(ImageLoader.loadImage("/bng.jpg"), 0, 0, 800, 600, null);

        this.graphics.drawImage(this.bckg, 0, 0, 800, 600, null); // draw game background


        if (State == STATE.GAME) {

            this.graphics.drawImage(this.bckg, 0, 0, 800, 600, null); // draw game background

            this.rabbit.render(this.graphics);
            for (int i = 0; i < eggs.size(); i++) {
                if (this.eggs.get(i) != null) {
                    this.eggs.get(i).render(this.graphics);
                }
            }

            //    if (this.egg != null) {
            //       this.egg.render(this.graphics);
            //   }

            for (int i = 0; i < duckies.size(); i++) {
                if (this.duckies.get(i) != null) {
                    this.duckies.get(i).render(this.graphics);
                }
            }

            for (int i = 0; i < stones.size(); i++) {
                if (this.stones.get(i) != null) {
                    this.stones.get(i).render(this.graphics);
                }
            }


        } else if (State == STATE.MENU) {
            menu.render(graphics);
        }

      //  if (isEsc) {
      //     System.exit(1);
      // }
        // this.graphics.setColor(Color.pink);
        //  this.graphics.fillOval(100, this.l1, 20, 20);


        // this.graphics.setColor(Color.red);
        //  this.graphics.fillOval(200, this.l2, 20, 20);


        // this.graphics.setColor(Color.green);
        // this.graphics.fillOval(300, this.l3, 20, 20);
        //End drawing

        this.graphics.dispose();
        this.bufferStrategy.show();
    }

    @Override
    public void run() {
        this.init(); //инизиализация

        int fps = 15;//60 фрейме се преизчисляват и пренарисъват за секунда, 60 за секунда// change to 15 fps to be slower

        double timePerFrame = 1_000_000_000 / fps; //колко време трябва да се изчака на всяко визуализиране
        double delta = 0;
        long now;
        long lastTimeTicked = System.nanoTime();

        while (isRunning) {
            // for (int i = 0; i < 20; i++) {
            now = System.nanoTime();
            delta += (now - lastTimeTicked) / timePerFrame;
            lastTimeTicked = now;
           // System.out.println(delta);
            System.out.printf("Your eggs are: %d\n", this.rabbit.hitPoints);

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
            // }

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
