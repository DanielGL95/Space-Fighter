package SpaceGame.Controller;


import SpaceGame.Audio.Audio;
import SpaceGame.Entities.*;
import SpaceGame.Game;
import SpaceGame.Texture.Texture;


import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    private Random r = new Random();
    private EntityA ent;
    private LinkedList<EntityA> e = new LinkedList<>();
    private LinkedList<ExplosionManager> explosions = new LinkedList<>();
    private LinkedList<Asteroid> asteroids = new LinkedList<>();
    private LinkedList<Heart> hearts = new LinkedList<>();
    private Game game;
    private Texture texture;

    public Controller(Game game, Texture texture) {
        this.game = game;
        this.texture = texture;


    }

    public void createEnemy(int enemyCount) {
        for (int i = 0; i < enemyCount; i++) {
            addEntity(new Enemies(r.nextInt(Game.WIDTH * Game.SCALE), -10, texture, game, this, ID.Enemi));

        }
    }

    public void createBullet(Partner partner) {
        for (int i = 0; i < 1; i++) {
            addEntity(new Bullet(partner.x + 5, partner.y, game, texture, ID.Bullet, this));

        }
    }

    public void createAsteroid() {

        asteroids.add(new Asteroid(r.nextInt(Game.WIDTH * Game.SCALE), -10, this.texture, this, ID.Asteroid));
    }


    public void createHeart(int heartNumber) {
        int x = 5;
        for (int i = 0; i < heartNumber; i++) {
            if (i == 0) {
                hearts.add(new Heart(texture, this, game, x, 40, ID.Heart1));
                x += 35;
            }
            if (i == 1) {
                hearts.add(new Heart(texture, this, game, x, 40, ID.Heart2));
                x += 35;
            }
            if (i == 2) {
                hearts.add(new Heart(texture, this, game, x, 40, ID.Heart3));
                x += 35;
            }

        }
    }

    public void removeHeart(ID id) {

        for (int i = 0; i < hearts.size(); i++) {

            Heart heart = hearts.get(i);
            if (heart.getId() == id) {
                hearts.remove(heart);
            }
        }


    }

    public void tick() {

        for (int i = 0; i < e.size(); i++) {

            if (getE().size() > i ) {
                ent = getE().get(i);
            }else{
                ent = getE().get(i-1);
            }
            ent = e.get(i);
            ent.tick();


        }


        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).tick();

        }


        for (int i = 0; i < explosions.size(); i++) {
            boolean remove = explosions.get(i).tick();

            if (remove) {
                explosions.remove(i);
                i--;
                break;
            }
        }


        for (int i = 0; i < e.size(); i++) {
            EntityA entity = e.get(i);
            if (e.get(i).getId() == ID.Enemi) {
                Enemies enemy = (Enemies) e.get(i);
                if (enemy.getBounds().intersects(game.getPlanet().getBounds())) {


                    Audio.explosion.play();
                    addExlosion(new ExplosionManager(enemy, game.g, 30));
                    removeEntity(enemy);
                    game.Healht--;
                    createEnemy(1);


                }
            }


            for (int j = 0; j < asteroids.size(); j++) {
                if (entity.getId() == ID.Bullet) {
                    if (asteroids.get(j).collision(entity)) {
                        Asteroid enemy = asteroids.get(j);
                        enemy.bounce = true;
                    }
                }

            }

            for (int j = 0; j < asteroids.size(); j++) {
                Asteroid asteroid = asteroids.get(j);
                if (asteroid.collision(entity)){
                    if (entity.getId()==ID.Enemi){
                        this.createEnemy(1);
                    }

                    this.removeEntity(entity);
                    Audio.explosion.play();
                    addExlosion(new ExplosionManager(entity, game.g, 30));

                }
            }

            for (int j = 0; j < asteroids.size(); j++) {
                Asteroid asteroid = asteroids.get(j);
                if (asteroid.collision(game.getPlanet())){

                    this.removeAsteroid(asteroid);
                    Audio.explosion.play();
                    addExlosion(new ExplosionManager(asteroid, game.g, 30));
                    game.Healht--;
                }
            }
        }


        for (int i = 0; i < hearts.size(); i++) {

            hearts.get(i).tick();

        }

    }


    public void render(Graphics g) {


        for (int i = 0; i < e.size(); i++) {
            ent = e.get(i);

            ent.render(g);
        }

        for (int i = 0; i < explosions.size(); i++) {

            explosions.get(i).render(g);

        }

        for (int i = 0; i < asteroids.size(); i++) {

            asteroids.get(i).render(g);

        }

        for (int i = 0; i < hearts.size(); i++) {

            hearts.get(i).render(g);

        }


    }

    public void addEntity(EntityA block) {
        e.add(block);

    }

    public void removeEntity(EntityA block) {
        e.remove(block);

    }

    public void removeAsteroid(Asteroid as) {
        asteroids.remove(as);
    }

    public LinkedList<EntityA> getE() {
        return e;
    }

    public LinkedList<Heart> getHearts() {
        return hearts;
    }

    public void addExlosion(ExplosionManager explosion) {
        this.explosions.add(explosion);
    }

    public boolean haveHearts() {
        return getHearts().size() <= 1;
    }


    public LinkedList<Asteroid> getAsteroids() {
        return asteroids;
    }
}


