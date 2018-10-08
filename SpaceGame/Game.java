package SpaceGame;


import SpaceGame.Audio.Audio;
import SpaceGame.Controller.Controller;
import SpaceGame.Entities.*;
import SpaceGame.IO.KeyInput;
import SpaceGame.IO.MouseInput;
import SpaceGame.Menu.*;
import SpaceGame.Sprites.BufferedImageLoader;
import SpaceGame.Texture.Texture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public static State state;

    static int minutes;
    public Graphics g;
    public int Healht;
    public int Points = 0;
    public boolean sound;
    private LinkedList<EntityA> ent;
    private boolean shooting;
    private Thread thread;
    private boolean running = false;
    private boolean hasStarted = false;
    private Random r;
    private Shield sh;
    private int enemyCount;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet;
    private BufferedImage background;
    private Player p;
    private Partner partner;
    private Planet planet;

    private Controller c;
    private Texture texture;
    private ManuPage menu;
    private HelpMenu help;
    private GameOverPage gameOverPage;
    private WinnerPage winnerPage;
    private boolean multiplayer;


    public static void main(String[] args) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame("Space Fighter");
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        game.start();


    }

    private static int getMinutes() {
        return MouseInput.getMinutes();
    }

    private static int getSeconds() {
        return MouseInput.getSeconds();
    }

    private void init() {
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("/res/sheet1.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sound = true;
        Game.state = State.MENU;

        Audio.menu.play();


        setEnemyCount(7);
        addKeyListener(new KeyInput(this));
        addMouseListener(new MouseInput(this));
        addMouseMotionListener(new MouseInput(this));
        texture = new Texture(this);


        c = new Controller(this, texture);
        p = new Player(200, this, texture, ID.Player, c);

        planet = new Planet(WIDTH - 100, HEIGHT * 2 - 45, ID.Planet, c, texture, this);


        c.createEnemy(getEnemyCount());
        c.createHeart(3);

        ent = c.getE();
        r = new Random();
        shooting = false;
        try {
            background = loader.loadImage("/res/background.png");

        } catch (IOException e) {
            e.printStackTrace();
        }
        menu = new ManuPage(this);
        gameOverPage = new GameOverPage(this);
        winnerPage = new WinnerPage(this);
        help = new HelpMenu(texture);

    }

    private synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);


        thread.start();

        Thread menuThread = new Thread() {
            @Override
            public void run() {
                super.run();

                while (state == SpaceGame.Menu.State.MENU) {
                    try {
                        sleep((long) (1000 * 0.5));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    menu.tick();
                }

            }
        };

        menuThread.start();

    }

    private synchronized void stop() {
        if (running) {
            return;
        }

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    @Override
    public void run() {
        init();

        long lastTime = System.nanoTime();
        final double amountOfTicts = 60.0;
        double ns = 1000000000 / amountOfTicts;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                delta--;
            }

            render();

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 100;

            }
        }
        stop();
    }

    private void tick() {


        if (state == State.GAME) {
            c.tick();
            p.tick();
            planet.tick();

            hasStarted = true;


            if (p.isShieled()) {
                if (sh.getAgeInSeconds() >= 2) {
                    c.removeEntity(sh);
                    p.setShieled(false);
                }
            }

            if (Points > 2) {

                if (getShooting()) {
                    c.createAsteroid();
                }


            }


            if (isMultiplayer()) {
                partner.tick();
            }


        }

        if (hasStarted) {
            if (getMinutes() == 0 && getSeconds() <= 1) {
                state = State.Win;
                hasStarted = false;

            }
        }


    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

        if (state == State.GAME) {
            p.render(g);
            c.render(g);
            planet.render(g);


            g.setColor(Color.GRAY);
            g.fillRect(5, 5, 100, 30);


            if (this.Healht <= 100) {
                g.setColor(Color.GREEN);
                g.fillRect(5, 5, this.Healht, 30);
            }
            if (this.Healht <= 50) {
                g.setColor(Color.YELLOW);
                g.fillRect(5, 5, this.Healht, 30);
            }
            if (this.Healht <= 25) {
                g.setColor(Color.RED);
                g.fillRect(5, 5, this.Healht, 30);

            }

            g.setColor(Color.lightGray);
            Font font = new Font("arial", Font.BOLD, 15);
            g.setFont(font);

            g.drawString(String.format("Min: %d sec: %d", getMinutes(), getSeconds()), 10, 90);

            Font font1 = new Font("arial", Font.BOLD, 15);
            g.setFont(font1);
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Kills: " + this.Points, this.getWidth() - 70, 20);

            if (isMultiplayer()) {
                partner.render(g);
            }

        } else if (state == State.MENU) {
            menu.render(g);
        } else if (state == State.GAMEOVER) {

            gameOverPage.render(g);
        } else if (state == State.Win) {
            winnerPage.render(g);

        } else if (state == State.Help) {
            help.render(g);

        }

        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_A) {
            p.setVelX(-5);
        } else if (keyCode == KeyEvent.VK_D) {
            p.setVelX(5);
        } else if (keyCode == KeyEvent.VK_W) {
            p.setVelY(-5);
        } else if (keyCode == KeyEvent.VK_S) {
            p.setVelY(5);
        } else if (keyCode == KeyEvent.VK_ESCAPE) {

            if (state == State.Win || state == State.Help) {
                this.stop();
                state = State.MENU;
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {

            if (!p.isShieled()) {
                sh = new Shield(p.x, p.y, texture, ID.Shield, this, p);
                p.setShieled(true);
                c.addEntity(sh);

            }
        } else if (keyCode == KeyEvent.VK_R) {
            partner = new Partner(400, this, texture, ID.PARTNER, c);
            multiplayer = true;
        } else if (keyCode == KeyEvent.VK_NUMPAD4) {
            partner.setVelX(-5);
        } else if (keyCode == KeyEvent.VK_NUMPAD6) {
            partner.setVelX(5);
        } else if (keyCode == KeyEvent.VK_NUMPAD8) {
            partner.setVelY(-5);
        } else if (keyCode == KeyEvent.VK_NUMPAD2) {
            partner.setVelY(5);
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_A) {
            p.setVelX(0);
        } else if (keyCode == KeyEvent.VK_D) {
            p.setVelX(0);
        } else if (keyCode == KeyEvent.VK_W) {
            p.setVelY(0);
        } else if (keyCode == KeyEvent.VK_S) {
            p.setVelY(0);
        } else if (keyCode == KeyEvent.VK_SPACE) {
            for (int i = 0; i < c.getE().size(); i++) {
                if (p.isShieled()) {
                    if (c.getE().get(i).getId() == ID.Shield) {
                        p.setShieled(false);
                        System.out.println(sh.getAgeInSeconds());
                        Shield shield = (Shield) c.getE().get(i);

                        c.removeEntity(shield);
                    }
                }
            }
        } else if (keyCode == KeyEvent.VK_NUMPAD5) {
            if (sound) {
                Audio.shot.play();
            }
            c.addEntity(new Bullet(partner.getX() + 5, partner.getY(), this, texture, ID.Bullet, c));

        }

        if (keyCode == KeyEvent.VK_NUMPAD4) {
            partner.setVelX(0);
        } else if (keyCode == KeyEvent.VK_NUMPAD6) {
            partner.setVelX(0);
        } else if (keyCode == KeyEvent.VK_NUMPAD8) {
            partner.setVelY(0);
        } else if (keyCode == KeyEvent.VK_NUMPAD2) {
            partner.setVelY(0);
        }

    }

    public void mousePressed(MouseEvent e) {
        int mouseCode = e.getButton();

        if (mouseCode == MouseEvent.BUTTON1) {
            if (Game.state == State.GAME) {
                if (sound) {
                    Audio.shot.play();
                }
                c.addEntity(new Bullet(p.getX() + 5, p.getY() + 5, this, texture, ID.Bullet, c));

            }
        }
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    private int getEnemyCount() {
        return enemyCount;
    }

    private void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public LinkedList<EntityA> getEnt() {
        return ent;
    }

    public boolean getShooting() {
        int n = r.nextInt(100);


        return n == 1;

    }

    public Planet getPlanet() {
        return this.planet;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    private boolean isMultiplayer() {
        return multiplayer;
    }


}
