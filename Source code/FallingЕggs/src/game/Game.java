package game;

import interfaces.Initializingable;
import interfaces.Reloadable;
import interfaces.Tickable;
import display.Display;
import display.Menu;
import game.entities.Ducky;
import game.entities.Egg;
import game.entities.Player;
import game.entities.Stone;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Game extends MouseInput implements Runnable, Tickable, Initializingable, Reloadable {
    public static final int WIDTH = 800; // width of Canvas size
    public static final int HEIGHT = 600; // height of Canvas size
    public static final int MAX_X = 35; // offset from end of the Canvas (in pixels)
    public static final int maxPoints = 25; // maximum point to win the game
    public static boolean isRunning;
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
    private Graphics graphics;

    private BufferStrategy bufferStrategy;
    private BufferedImage bckg;
    private BufferedImage hlp;
    private BufferedImage gameWinImg; // image about end State winner
    private BufferedImage gameLostImg; // image about end State looser

    private Menu menu;

    private int addEggsDamge = 1;
    private int addDuckyDamge = 3;
    private int takeStoneDamge = 3;

    public enum STATE {
        MENU, //state to open main menu
        GAME, // state to start game
        HELP, // state to open help
        ENDWIN, // state to finish as winner
        ENDLOST, // state to finish as loser
        PAUSE, // state to pause the game
        RELOAD // state to reload game after finishing
    }

    public Game(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.random = new Random();
        this.State = STATE.MENU; // set default State to STATE.MENU
    }

    //initialize Display
    public void init() {
        Assets.init();
        this.display = new Display(this.name, this.width, this.height);
        this.ih = new InputHandler(this.display.getCanvas());
        this.mi = new MouseInput(this.display.getCanvas());

        reload();
    }

    //initialize Display players, background and entities
    public void reload() {
        this.bckg = Assets.background; // game background
        this.hlp = Assets.help; // help menu
        this.gameWinImg = Assets.gameOverWin; // image when finished as winner
        this.gameLostImg = Assets.gameOverLost; // image when finished as looser

        this.rabbit = new Player(120, 450, 0); // position of Rabbit

        menu = new Menu(); // call main menu

        //create list with falling chicks
        this.duckies = new ArrayList<>();
        for (int i = 0; i < random.nextInt(20); i++) {
            Ducky.addDucky(new Ducky(random.nextInt(Game.WIDTH - Game.MAX_X), 0, 44, 41), this.duckies);
        }

        //create list with falling eggs
        this.eggs = new ArrayList<>();
        for (int i = 0; i < random.nextInt(20); i++) {
            Egg.addEgg(new Egg(random.nextInt(Game.WIDTH - Game.MAX_X), 0, 26, 29), this.eggs); // for static egg (20,25)
        }

        //create list with falling stones
        this.stones = new ArrayList<>();
        for (int i = 0; i < random.nextInt(20); i++) {
            Stone.addStone(new Stone(random.nextInt(Game.WIDTH - Game.MAX_X), 0, 33, 27), this.stones);
        }
    }

    //start game action
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
                    this.rabbit.addDamega(addEggsDamge);
                    eggs.remove(this.eggs.get(i));
                }
            }

            for (int i = 0; i < duckies.size(); i++) {
                if (this.duckies.get(i) != null && this.rabbit.boundingBox.intersects(this.duckies.get(i).boundingBox)) {
                    this.rabbit.addDamega(addDuckyDamge);
                    duckies.remove(this.duckies.get(i));
                }
            }

            for (int i = 0; i < stones.size(); i++) {
                if (this.stones.get(i) != null && this.rabbit.boundingBox.intersects(this.stones.get(i).boundingBox)) {
                    this.rabbit.takeDamega(takeStoneDamge);
                    stones.remove(this.stones.get(i));
                }
            }

            //state when the player finished the game as winner
            if (this.rabbit.hitPoints >= maxPoints || (eggs.size() + duckies.size()) == 0) {
                State = STATE.ENDWIN;

            } else if (this.rabbit.hitPoints < 0) { //state when the player finished the game as Looser
                State = STATE.ENDLOST;
            }

            // exit from the game if the Esc button is pressed
            if (isEsc) {
                System.exit(1);
            }
        }
    }

    // start to render the game images
    protected void render() {
        this.bufferStrategy = this.display.getCanvas().getBufferStrategy();
        if (this.bufferStrategy == null) {
            this.display.getCanvas().createBufferStrategy(2);
            this.bufferStrategy = this.display.getCanvas().getBufferStrategy();
        }

        this.graphics = this.bufferStrategy.getDrawGraphics();
        this.graphics.drawImage(this.bckg, 0, 0, 800, 600, null); // draw game background

        // render all entities which are in State Game
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

            // Show the live score on top left corner
            String textPlay = "Score: " + this.rabbit.hitPoints;
            this.graphics.setColor(Color.white);
            this.graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            this.graphics.drawString(textPlay, 10, 30);

            // show the main menu on the screen
        } else if (State == STATE.MENU) {
            menu.render(graphics);

        }

        //show to user that the game is in Pause state
        if (State == STATE.PAUSE) {
            String textPause = "PAUSE";
            String textREsume = "Press \"P\" to resume. ";
            this.graphics.setColor(Color.white);
            this.graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
            this.graphics.drawString(textPause, 350, 250);
            this.graphics.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
            this.graphics.drawString(textREsume, 310, 280);
        }

        // show to user that the game is End as winner
        if (State == STATE.ENDWIN) {
            this.graphics.drawImage(this.gameWinImg, 154, 104, 506, 328, null); // draw end state
            String textWin = this.rabbit.hitPoints + " points";
            this.graphics.setColor(Color.white);
            this.graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
            this.graphics.drawString(textWin, 353, 310);
        }

        // show to user that the game is End as loser
        if (State == STATE.ENDLOST) {
            this.graphics.drawImage(this.gameLostImg, 154, 104, 506, 328, null); //draw end state as Looser
        }

        // show the Help menu
        if (State == STATE.HELP) {
            this.graphics.drawImage(this.hlp, 154, 104, 506, 328, null); // draw help menu
        }

        this.graphics.dispose();
        this.bufferStrategy.show();

        // State where user can start new game after Game over and back to menu
        if (State == STATE.RELOAD) {
            reload();
            State = STATE.GAME;
        }
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