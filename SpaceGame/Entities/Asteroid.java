package SpaceGame.Entities;

import SpaceGame.Animations.Animation;
import SpaceGame.Controller.Controller;
import SpaceGame.Game;
import SpaceGame.Texture.Texture;

import java.awt.*;

public class Asteroid implements EntityA {
    public double x;
    public double y;
    public int health;
    public boolean bounce;
    private Animation anim;
    private ID id;

    public Asteroid(double x, double y, Texture t, Controller c, ID id) {
        this.x = x;
        this.y = y;
        this.anim = new Animation(5, t.asteroid[0], t.asteroid[1], t.asteroid[2]);
        this.id = id;
        this.health = 10;
        this.bounce = false;
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
        return this.y;
    }

    @Override
    public void setY(double num) {
        this.y = num;
    }

    @Override
    public void tick() {
        if (this.bounce) {

            if (this.getX() < Game.WIDTH / 2) {
                y -= 4;
                x -= 4;
            } else {
                y -= 4;
                x += 4;
            }
        } else {
            y += 4;
        }


        anim.runAnimation();
    }

    public boolean collision(EntityA entityA) {
        return this.getBounds().intersects(entityA.getBounds());
    }

    @Override
    public void render(Graphics g) {

        anim.drawAnimation(g, this.x, this.y, 0);
    }

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x+9, (int) this.y+4, 15, 15);
    }
}
