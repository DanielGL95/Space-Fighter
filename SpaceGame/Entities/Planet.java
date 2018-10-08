package SpaceGame.Entities;

import SpaceGame.Animations.Animation;
import SpaceGame.Audio.Audio;
import SpaceGame.Controller.Controller;
import SpaceGame.Controller.ExplosionManager;
import SpaceGame.Game;
import SpaceGame.Menu.State;
import SpaceGame.Texture.Texture;

import java.awt.*;

public class Planet implements EntityA {
    private double x;
    private double y;
    private Texture texture;
    private Animation anim;
    private Controller c;
    private ID id;
    private Game game;

    public Planet(double x, double y, ID id, Controller c, Texture t, Game game) {

        this.x = x;
        this.y = y;
        this.game = game;
        this.id = id;
        this.c = c;
        this.texture = t;
        this.anim = new Animation(18, texture.planet[0], texture.planet[1], texture.planet[2]);
    }


    @Override
    public double getX() {
        return 0;
    }

    @Override
    public void setX(double num) {

    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public void setY(double num) {
        
    }

    @Override
    public void tick() {

        for (int i = 0; i < game.getEnt().size(); i++) {
            if (game.getEnt().get(i).getId() == ID.EnemyBullet) {

                EnemyBullet entity = (EnemyBullet) game.getEnt().get(i);

                if (this.getBounds().intersects(entity.getBounds())) {

                    if (game.Healht <= 5) {
                        Game.state = State.GAMEOVER;
                    }
                    game.Healht -= 10;
                    if (game.sound)
                        Audio.explosion.play();
                    c.addExlosion(new ExplosionManager(entity, game.g, 30));
                    c.removeEntity(entity);
                }


                for (int j = 0; j < c.getAsteroids().size(); j++) {


                    if (c.getAsteroids().get(j).collision(this)) {
                        Asteroid enemy = c.getAsteroids().get(j);
                        Audio.explosion.play();
                        c.addExlosion(new ExplosionManager(enemy, game.g, 30));
                        c.removeAsteroid(c.getAsteroids().get(j));
                    }

                }
            }

        }


        anim.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        anim.drawAnimation(g, this.x, this.y, 0);

    }

    @Override
    public ID getId() {
        return null;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x + 50, (int) this.y + 20, 32 * 3, 32);
    }
}
