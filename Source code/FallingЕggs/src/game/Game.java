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
    public static boolean isRunning;
    public static final int MAX_X = 35;
    public static boolean isEsc;
    public static STATE State;

    protected Player rabbit;
    private String name;
    private int width, height;
    private Thread thread;
    private ArrayList<Egg> eggs;
    private ArrayList<Ducky> duckies;
    private ArrayList<Stone> stones;
    private Random random;
    private InputHandler ih;
    private MouseInput mi;

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private SpriteSheet spriteSheet;
    private BufferedImage bckg;
    private BufferedImage hlp;
    private Menu menu;

    private BufferedImage background1; // end State


    public enum STATE {
        MENU,
        GAME,
        HELP,
        ENDWIN,
        ENDLOST
    }

    public Game(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.random = new Random();
    }

    public void init() {
        Assets.init();
        this.display = new Display(this.name, this.width, this.height);
        this.ih = new InputHandler(this.display.getCanvas());
        this.rabbit = new Player(120, 450, 0);
        this.bckg = Assets.background;
        this.hlp = Assets.help;

        this.State = STATE.MENU;
        this.duckies = new ArrayList<>();
        for (int i = 0; i < random.nextInt(20); i++) {
            Ducky.addDucky(new Ducky(random.nextInt(Game.WIDTH - Game.MAX_X), 0, 44, 41), this.duckies); // for static duck (33,36)
        }

        this.eggs = new ArrayList<>();
        for (int i = 0; i < random.nextInt(20); i++) {
            Egg.addEgg(new Egg(random.nextInt(Game.WIDTH - Game.MAX_X), 0, 26, 29), this.eggs); // for static egg (20,25)
        }

        this.stones = new ArrayList<>();
        for (int i = 0; i < random.nextInt(10); i++) {
            Stone.addStone(new Stone(random.nextInt(Game.WIDTH - Game.MAX_X), 0, 33, 27), this.stones);
        }// for static stone (27,17)

        menu = new Menu();
        this.mi = new MouseInput(this.display.getCanvas());
    }

    public void tick() {
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

            if (this.rabbit.hitPoints >= 25 || (stones.size() + eggs.size() + duckies.size()) == 0) {
               // System.out.printf("\nCongratulation!\nYou can going home with %d eggs!", this.rabbit.hitPoints);
                State = STATE.ENDWIN;
                isRunning = false;
            } else if (this.rabbit.hitPoints < 0) {
               // System.out.printf("Sorry, all the eggs are broken!");
                State = STATE.ENDLOST;
                isRunning = false;
            }

            if (isEsc) {
                System.out.println("The Escape key was pressed");
                System.exit(1);
            }
        }
    }

    protected void render() {
        this.bufferStrategy = this.display.getCanvas().getBufferStrategy();
        if (this.bufferStrategy == null) {
            this.display.getCanvas().createBufferStrategy(2);
            this.bufferStrategy = this.display.getCanvas().getBufferStrategy();
        }

        this.graphics = this.bufferStrategy.getDrawGraphics();

        this.graphics.clearRect(0, 0, this.width, this.height);

        this.graphics.drawImage(this.bckg, 0, 0, 800, 600, null); // draw game background

        this.graphics.drawRect(326, 320, 120, 28); //quit na end state
        String text = "Quit";
        this.graphics.setColor(Color.yellow);
        this.graphics.setFont(new Font("arial", Font.ITALIC, 30));
        this.graphics.drawString(text, 350, 345);
        if (State == STATE.ENDWIN) {
            this.graphics.drawImage(this.background1, 154, 104, 506, 328, null); // draw end state
            String textWin = "\nCongratulation!\nYou can going home with " + this.rabbit.hitPoints + " eggs!" ;
            this.graphics.setColor(Color.pink);
            this.graphics.setFont(new Font("arial", Font.BOLD, 30));
            this.graphics.drawString(textWin, 50, 200);
        }

        if (State == STATE.ENDLOST) {
            this.graphics.drawImage(this.background1, 154, 104, 506, 328, null); //draw end state
            String textQuit = "Sorry, all the eggs are broken!";
            this.graphics.setColor(Color.pink);
            this.graphics.setFont(new Font("arial", Font.BOLD, 30));
            this.graphics.drawString(textQuit, 200, 200);
        }

        if (State == STATE.HELP) {
            this.graphics.drawImage(this.hlp, 154, 104, 506, 328, null); // draw help menu
        }

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

            //proba
            String textPlay = "Your eggs are: " + this.rabbit.hitPoints;
            this.graphics.setColor(Color.yellow);
            this.graphics.setFont(new Font("arial", Font.ITALIC, 30));
            this.graphics.drawString(textPlay, 10, 30);
            //end proba

        } else if (State == STATE.MENU) {
            menu.render(graphics);
        }

        this.graphics.dispose();
        this.bufferStrategy.show();
    }

    @Override
    public void run() {
        this.init();
        int fps = 15;

        double timePerFrame = 1_000_000_000 / fps;
        double delta = 0;
        long now;
        long lastTimeTicked = System.nanoTime();

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTimeTicked) / timePerFrame;
            lastTimeTicked = now;
            //System.out.printf("Your eggs are: %d\n", this.rabbit.hitPoints);

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

        this.stop();
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