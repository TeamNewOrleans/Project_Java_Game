package game;

import display.Display;
import game.entities.Ducky;
import game.entities.Egg;
import game.entities.Player;
import game.entities.Stone;
import gfx.Assets;
import gfx.SpriteSheet;
import display.Menu;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Game extends MouseInput implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int MAX_X = 35;
    private String name;
    private int width, height;


    private Thread thread; //нишка
    private boolean isRunning;
    public static boolean isEsc;
    protected Player rabbit;
    private ArrayList<Egg> eggs;
    private ArrayList<Ducky> duckies;
    private ArrayList<Stone> stones;

    Random random = new Random();
    private InputHandler ih;
    private MouseInput mi;

    private Display display;
    private BufferStrategy bufferStrategy;   //начина по който ние контролираме обектите да се визуализират
    private Graphics graphics;//този койот ги изрисува
    private SpriteSheet spriteSheet;
    private BufferedImage bckg; // game background

    private Menu menu;
    private Stone stone;

    public enum STATE {
        MENU,
        GAME
    }

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

        this.duckies = new ArrayList<>();
        for (int i = 0; i < random.nextInt(20); i++) {
            addDucky(new Ducky(random.nextInt(Game.WIDTH - Game.MAX_X), 0, 33, 36));
        }

        this.eggs = new ArrayList<>();
        for (int i = 0; i < random.nextInt(20); i++) {
            addEgg(new Egg(random.nextInt(Game.WIDTH - Game.MAX_X), 0, 20, 25));
        }

        this.stones = new ArrayList<>();
        for (int i = 0; i < random.nextInt(10); i++) {
        addStone(new Stone(random.nextInt(Game.WIDTH - Game.MAX_X), 0, 27, 17));}

        menu = new Menu();
        this.mi = new MouseInput(this.display.getCanvas());

    }

    public void tick() { //визуализираме нещата
        if (State == STATE.GAME) {
            this.rabbit.tick();

            for (int i = 0; i < eggs.size(); i++) {
                if (this.eggs.get(i).getY() >= (Game.HEIGHT)) {
                    this.eggs.get(i).setY(0);
                }

                if (this.eggs.get(i) != null) {
                    this.eggs.get(i).tick();
                }
            }

            for (int i = 0; i < duckies.size(); i++) {
                if (this.duckies.get(i).getY() >= (Game.HEIGHT)) {
                    this.duckies.get(i).setY(0);
                }

                if (this.duckies.get(i) != null) {
                    this.duckies.get(i).tick();
                }
            }

            for (int i = 0; i < stones.size(); i++) {
                if (stones.get(i).getY() >= (Game.HEIGHT)) {
                    stones.get(i).setY(0);
                }

                if (stones.get(i) != null) {
                    stones.get(i).tick();
                }
            }

            for (int i = 0; i < eggs.size(); i++) {
                if (this.eggs.get(i) != null && this.rabbit.boundingBox.intersects(this.eggs.get(i).boundingBox)) {
                    this.rabbit.addDamega(1);
                    eggs.remove(this.eggs.get(i));
                }
            }


            for (int i = 0; i < duckies.size(); i++) {
                if (this.duckies.get(i) != null && this.rabbit.boundingBox.intersects(this.duckies.get(i).boundingBox)) {
                    this.rabbit.addDamega(3);
                    duckies.remove(this.duckies.get(i));
                }
            }

            for (int i = 0; i < stones.size(); i++) {
                if (this.stones.get(i) != null && this.rabbit.boundingBox.intersects(this.stones.get(i).boundingBox)) {
                    this.rabbit.takeDamega(3);
                    stones.remove(this.stones.get(i));
                }
            }

            if (this.rabbit.hitPoints >= 50 || (stones.size() + eggs.size() + duckies.size()) == 0) {
                System.out.printf("\nCongratulation!\nYou can going home with %d eggs!", this.rabbit.hitPoints);

                this.stop();

            } else if (this.rabbit.hitPoints < 0) {
                System.out.printf("Sorry, all the eggs are broken!");
                this.stop();
            }

            if (isEsc) {
                System.exit(1);
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

        this.graphics.drawImage(this.bckg, 0, 0, 800, 600, null); // draw game background


        if (State == STATE.GAME) {

            this.graphics.drawImage(this.bckg, 0, 0, 800, 600, null); // draw game background

            this.rabbit.render(this.graphics);
            for (int i = 0; i < eggs.size(); i++) {
                if (this.eggs.get(i) != null) {
                    this.eggs.get(i).render(this.graphics);
                }
            }

            for (int i = 0; i < duckies.size(); i++) {
                if (this.duckies.get(i) != null) {
                    this.duckies.get(i).render(this.graphics);
                }
            }

            for (int i = 0; i < stones.size(); i++) {
                if (this.stones.get(i) != null) {
                    this.stones.get(i).render(graphics);
                }
            }
        } else if (State == STATE.MENU) {
            menu.render(graphics);
        }

        this.graphics.dispose();
        this.bufferStrategy.show();
    }

    public void addStone(Stone st) { //da se iznese w obshtiq klas
        stones.add(st);
    }

    public void addEgg(Egg egg) { //da se iznese w obshtiq klas
        eggs.add(egg);
    }

    public void addDucky(Ducky dc) { //da se iznese w obshtiq klas
        duckies.add(dc);
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
            now = System.nanoTime();
            delta += (now - lastTimeTicked) / timePerFrame;
            lastTimeTicked = now;
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