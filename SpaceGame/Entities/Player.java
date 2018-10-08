package SpaceGame.Entities;

import SpaceGame.Animations.Animation;
import SpaceGame.Audio.Audio;
import SpaceGame.Controller.Controller;
import SpaceGame.Controller.ExplosionManager;
import SpaceGame.Game;
import SpaceGame.Menu.State;
import SpaceGame.Sprites.SpriteSheet;
import SpaceGame.Texture.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player implements EntityA {
    public double x;
    public double y;
    ID id;
    private Texture texture;
    private Animation anim;
    private Controller c;
    private boolean isShieled;
    int heartCounter = 0;
    private double velX;
    private double velY;
    private Game game;
    private BufferedImage player;
    private boolean justInitialised;

    public Player(double x, Game game, Texture texture, ID id, Controller c) {
        this.x = x;
        this.y = Game.HEIGHT*2;
        this.game = game;
        this.texture = texture;
        this.id = id;
        this.c = c;
        this.isShieled = false;
        this.justInitialised =true;
        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
        anim = new Animation(5, texture.player[0], texture.player[1], texture.player[2]);

    }

    public void tick() {

        x += velX;
        y += velY;

        if (justInitialised) {

            if (y>=350) {
                y -= 2;
            }else{
                justInitialised= false;
            }
        }

        if (x <= 0) {
            x = 0;
        } else if (x >= 640 - 18) {
            x = 640 - 18;
        } else if (y <= 0) {
            y = 0;
        } else if (y >= 480 - 42) {
            y = 480 - 42;
        }

        for (int i = 0; i < game.getEnt().size(); i++) {

            if (game.getEnt().get(i).getId() == ID.Enemi) {
                Enemies entity = (Enemies) game.getEnt().get(i);

                if (this.getBounds().intersects(entity.getBounds())) {

                    if (game.Healht <= 0 || (c.haveHearts() && !isShieled)) {

                        Game.state = State.GAMEOVER;
                    }

                    if (!isShieled) {

                        if (c.getHearts().size() == 3) {
                            c.removeHeart(ID.Heart1);
                            if (game.sound)
                                Audio.explosion.play();
                            c.addExlosion(new ExplosionManager(entity, game.g, 30));

                        } else if (c.getHearts().size() == 2) {
                            c.removeHeart(ID.Heart2);
                            if (game.sound)
                                Audio.explosion.play();
                            c.addExlosion(new ExplosionManager(entity, game.g, 30));

                        } else if (c.getHearts().size() == 1 || c.getHearts().size() == 0) {
                            c.removeHeart(ID.Heart3);
                            c.createHeart(3);
                        }


                        c.removeEntity(entity);
                        c.createEnemy(1);

                    } else {
                        c.createEnemy(1);
                        if (game.sound)
                            Audio.explosion.play();
                        c.addExlosion(new ExplosionManager(entity, game.g, 30));
                        c.removeEntity(entity);
                    }
                }
            }

            if (game.getEnt().get(i).getId() == ID.EnemyBullet) {

                EnemyBullet entity = (EnemyBullet) game.getEnt().get(i);

                if (this.getBounds().intersects(entity.getBounds())) {

                    if (game.Healht <= 0 || (c.haveHearts() && !isShieled)) {
                        Game.state = State.GAMEOVER;
                    }

                    if (!isShieled) {
                        if (c.getHearts().size() == 3) {
                            c.removeHeart(ID.Heart1);

                        } else if (c.getHearts().size() == 2) {
                            c.removeHeart(ID.Heart2);

                        } else if (c.getHearts().size() == 1) {
                            c.removeHeart(ID.Heart3);

                        }
                        Audio.explosion.play();
                        c.addExlosion(new ExplosionManager(entity, game.g, 30));
                        c.createEnemy(1);
                        c.removeEntity(entity);

                    }


                }
            }

            for (int j = 0; j < c.getAsteroids().size(); j++) {

                 if (this.getBounds().intersects( c.getAsteroids().get(j).getBounds()))


                     if (!isShieled) {
                         Game.state = State.GAMEOVER;
                     }else{
                         if ( c.getAsteroids().get(j).collision(this)) {
                             Asteroid enemy =  c.getAsteroids().get(j);
                             enemy.bounce = true;
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
        return this.id;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public boolean isShieled() {
        return isShieled;
    }

    public void setShieled(boolean shieled) {
        isShieled = shieled;
    }


}
