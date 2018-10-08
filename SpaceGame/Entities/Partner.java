package SpaceGame.Entities;

import SpaceGame.Animations.Animation;
import SpaceGame.Controller.Controller;
import SpaceGame.Game;
import SpaceGame.Menu.State;
import SpaceGame.Sprites.SpriteSheet;
import SpaceGame.Texture.Texture;

import java.awt.*;
import java.util.Random;

public class Partner implements EntityA {

    public double x;
    public double y;
    ID id;
    private Animation anim;
    private Controller c;
    private boolean isShieled;
    private Random r;
    private double velX;
    private double velY;
    private Game game;
    private boolean justInitialised;

    public Partner(double x, Game game, Texture texture, ID id, Controller c) {
        this.x = x;
        this.y = Game.HEIGHT * 2 + 80;
        this.game = game;
        this.id = id;
        this.c = c;
        this.isShieled = false;
        this.justInitialised = true;
        anim = new Animation(5, texture.player2[0], texture.player2[1], texture.player2[2]);
        r = new Random();
    }


    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public void setX(double num) {
        this.x = num;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double num) {
        this.y = num;

    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        int choose = r.nextInt(10);
        if (justInitialised) {

            if (y >= 350) {
                y -= 2;
            } else {
                justInitialised = false;
            }
        }

        if (choose == 0) {
            velX = (r.nextInt(6 - -6) + -6);

        }

        if (x <= 0) x = 0;
        else if (x >= 640 - 18) x = 640 - 18;
        else if (y <= 0) y = 0;
        else if (y >= 480 - 42) y = 480 - 42;

        for (int i = 0; i < game.getEnt().size(); i++) {
            if (game.getEnt().get(i).getId() == ID.EnemyBullet) {

                EnemyBullet entity = (EnemyBullet) game.getEnt().get(i);

                if (this.getBounds().intersects(entity.getBounds())) {

                    if (game.Healht <= 0 || (c.haveHearts() && !isShieled)) {

                        Game.state = State.GAMEOVER;
                    }

                    c.createBullet(this);


//
                }


            }


        }

        for (int i = 0; i < c.getAsteroids().size(); i++) {
            Asteroid asteroid = c.getAsteroids().get(i);
            if (this.getBounds().intersects(asteroid.getBounds())) {


                c.createBullet(this);


            }
        }


        for (int i = 0; i < game.getEnt().size(); i++) {
            if (game.getEnt().get(i).getId() == ID.Enemi) {

                Enemies entity = (Enemies) game.getEnt().get(i);

                if (this.getBounds().intersects(entity.getBounds())) {

                    if (game.Healht <= 0 || (c.haveHearts() && !isShieled)) {

                        Game.state = State.GAMEOVER;
                    }


                }

            }
        }
        anim.runAnimation();
    }

    @Override
    public void render(Graphics g) {

        anim.drawAnimation(g, x, y, 0);
    }

    @Override
    public ID getId() {
        return null;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y - 90, 32, 90);
    }

}
