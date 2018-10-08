package SpaceGame.Entities;

import SpaceGame.Animations.Animation;
import SpaceGame.Audio.Audio;
import SpaceGame.Controller.Controller;
import SpaceGame.Controller.ExplosionManager;
import SpaceGame.Game;
import SpaceGame.Texture.Texture;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Enemies implements EntityA {
    private Game game;
    private Texture texture;
    private Random r;
    private Animation anim;

    Controller c;
    ID id;
    private double x;
    private double y;
    private int speed;


    public Enemies(int x, int y, Texture texture, Game game, Controller c, ID id) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.game = game;
        this.c = c;
        this.id = id;
        anim = new Animation(5, texture.enemy[0], texture.enemy[1], texture.enemy[2]);
        r = new Random();
        speed = r.nextInt(3) + 1;
    }


    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double num) {
        x = num;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double num) {
        y = num;
    }

    public void tick() {
        y += speed;

        if (getY() > game.getHeight()) {
            setY(0);
            setX(r.nextInt(Game.WIDTH * Game.SCALE));
            y = -10;
        }


        for (int i = 0; i < game.getEnt().size(); i++) {
            LinkedList entities = c.getE();
            EntityA entity = (EntityA) entities.get(i);

            if (this.getBounds().intersects(entity.getBounds())) {
                if (entity.getId() == ID.Bullet) {
                    c.removeEntity(this);
                    c.removeEntity(entity);
                    if (game.sound)
                        Audio.explosion.play();
                    c.addExlosion(new ExplosionManager(this, game.g, 30));
                    game.Points +=1;
                    c.createEnemy(1);
                }

            }

            if (this.getBounds().intersects(entity.getBounds())) {
                if (entity.getId() == ID.Planet) {
                    c.removeEntity(this);
                    game.Healht-=10;
                    if (game.sound)
                        Audio.explosion.play();
                    c.addExlosion(new ExplosionManager(this, game.g, 30));

                    c.createEnemy(1);
                }

            }

        }

        if (game.getShooting() && game.Points >=3){
            EnemyBullet newBullet = new EnemyBullet(getX(),getY(), ID.EnemyBullet, texture,game,c);
            c.addEntity(newBullet);
        }


        anim.runAnimation();


    }


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

}
