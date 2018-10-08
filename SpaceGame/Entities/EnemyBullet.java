package SpaceGame.Entities;

import SpaceGame.Animations.Animation;
import SpaceGame.Audio.Audio;
import SpaceGame.Controller.Controller;
import SpaceGame.Controller.ExplosionManager;
import SpaceGame.Game;
import SpaceGame.Texture.Texture;

import java.awt.*;

public class EnemyBullet implements EntityA {
    private Animation enemyBullet;
    private Texture texture;
    private Game game;
    private Controller c;
    private double x, y;
    private ID id;

    EnemyBullet(double x, double y, ID id, Texture texture, Game game, Controller c) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.texture = texture;
        this.game = game;
        this.c = c;
        enemyBullet = new Animation(5, texture.enemyBullet[0], texture.enemyBullet[1], texture.enemyBullet[2]);
    }


    public double getX() {
        return this.x;
    }

    @Override
    public void setX(double num) {

    }

    public double getY() {
        return this.y;
    }

    @Override
    public void setY(double num) {

    }


    public void tick() {
        y += 5;
        enemyBullet.runAnimation();


        for (int i = 0; i <c.getE().size(); i++) {
            EntityA entity;
            if (c.getE().size() > i ) entity = c.getE().get(i);
            else entity = c.getE().get(i - 1);

            if (entity.getId() == ID.Shield) {



                if (this.getBounds().intersects(entity.getBounds())) {

                    c.removeEntity(this);
                }


            }

            if (entity.getId() == ID.Bullet) {


                if (this.getBounds().intersects(entity.getBounds())) {
                    game.getEnt().remove(entity);
                    game.getEnt().remove(this);
                    c.addExlosion(new ExplosionManager(entity, game.g, 15));
                    Audio.explosion.play();
                }
            }

            


        }
    }


    public void render(Graphics g) {
        enemyBullet.drawAnimation(g, this.getX(), this.getY(), 0);
    }


    public ID getId() {
        return this.id;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
