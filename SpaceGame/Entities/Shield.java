package SpaceGame.Entities;

import SpaceGame.Animations.Animation;
import SpaceGame.Game;
import SpaceGame.Texture.Texture;

import java.awt.*;

public class Shield implements EntityA {

    private final long createdMillis;
    public ID id;
    private double x;
    private double y;
    private Game game;
    private Animation shield;
    private EntityA p;

    public Shield(double x, double y, Texture texture, ID id, Game game, EntityA p) {

        this.x = x;
        this.y = y;
        this.id = id;
        this.game = game;
        this.p = p;
        shield = new Animation(5, texture.shield[0], texture.shield[1], texture.shield[2]);
        createdMillis = System.currentTimeMillis();
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double num) {

    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double num) {

    }

    @Override
    public void tick() {
        y = p.getY() - 10;
        shield.runAnimation();


    }

    @Override
    public void render(Graphics g) {
        shield.drawAnimation(g, p.getX() - 15, getY() - 10, 0);


    }

    public int getAgeInSeconds() {
        long nowMillis = System.currentTimeMillis();
        return (int) ((nowMillis - this.createdMillis) / 1000);
    }


    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) p.getX(), (int) y, 32, 32);
    }
}
