package SpaceGame.Entities;

import SpaceGame.Animations.Animation;
import SpaceGame.Audio.Audio;
import SpaceGame.Controller.Controller;
import SpaceGame.Controller.ExplosionManager;
import SpaceGame.Game;
import SpaceGame.Sprites.SpriteSheet;
import SpaceGame.Texture.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Bullet implements EntityA {
    private double x;
    private double y;

    public ID id;
    Game game;
    Animation anim;
    Controller c;
    public Bullet(double x, double y, Game game, Texture texture, ID id, Controller c){

        this.id = id;
        this.x = x;
        this.y = y;
        this.game = game;
        this.c =c;
        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
        BufferedImage image = ss.getImage(2, 1, 32, 32);
        anim = new Animation(5, texture.bullet[0],texture.bullet[1],texture.bullet[2]);

    }


    public void render (Graphics g ){
      anim.drawAnimation(g,x,y,0);

    }

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public Rectangle getBounds() {

        return new Rectangle((int)x, (int)y,32,32);
    }

    public void tick(){
        y-=7;
        anim.runAnimation();

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
}
