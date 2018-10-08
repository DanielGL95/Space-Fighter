package SpaceGame.Entities;

import SpaceGame.Animations.Animation;
import SpaceGame.Controller.Controller;
import SpaceGame.Game;
import SpaceGame.Texture.Texture;

import java.awt.*;

public class Heart implements EntityA {
    private Animation anim;
    private double x;
    private double y;
    private ID id;

    public Heart(Texture texture,Controller controller,Game game,double x, double y,ID id){

        this.y = y;
        this.x = x;
        this.anim= new Animation(5,texture.heart[0],texture.heart[1]);
        this.id = id;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double num) {
        this.x=num;
    }

    @Override
    public void setY(double num) {
        this.y = num;
    }

    @Override
    public void tick() {
        anim.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        anim.drawAnimation(g,getX(),getY(),0);
    }

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
