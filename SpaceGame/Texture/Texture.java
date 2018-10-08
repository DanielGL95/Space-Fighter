package SpaceGame.Texture;

import SpaceGame.Game;
import SpaceGame.Sprites.BufferedImageLoader;
import SpaceGame.Sprites.SpriteSheet;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Texture {

    private SpriteSheet ss;
    public BufferedImage[] player = new BufferedImage[3];
    public BufferedImage[] enemy = new BufferedImage[3];
    public BufferedImage[] bullet = new BufferedImage[3];
    public BufferedImage[] expl = new BufferedImage[3];
    public BufferedImage[] enemyBullet = new BufferedImage[3];
    public BufferedImage[] shield = new BufferedImage[3];
    public BufferedImage[] planet = new BufferedImage[3];
    public BufferedImage[] heart= new BufferedImage[3];
    public BufferedImage[] asteroid= new BufferedImage[3];
    public BufferedImage[] player2= new BufferedImage[3];





    public Texture(Game game){
        ss = new SpriteSheet(game.getSpriteSheet());
        getImages();
    }



    private void getImages() {
        player[0] = ss.getImage(1,1,32,32);
        player[1] = ss.getImage(1,2,32,32);
        player[2] = ss.getImage(1,3,32,32);

        player2[0] = ss.getImage(6,1,32,32);
        player2[1] = ss.getImage(6,2,32,32);
        player2[2] = ss.getImage(6,3,32,32);


        bullet[0] = ss.getImage(2,1,32,32);
        bullet[1] = ss.getImage(2,2,32,32);
        bullet[2] = ss.getImage(2,3,32,32);


        enemy[0] = ss.getImage(3,1,32,32);
        enemy[1] = ss.getImage(3,2,32,32);
        enemy[2] = ss.getImage(3,3,32,32);

        expl[0] = ss.getImage(4,1,32,32);
        expl[1] = ss.getImage(4,2,32,32);
        expl[2] = ss.getImage(4,3,32,32);

        enemyBullet[0] = ss.getImage(5,1,32,32);
        enemyBullet[1] = ss.getImage(5,2,32,32);
        enemyBullet[2] = ss.getImage(5,3,32,32);

        shield[0] = ss.getImage(7,4,32*2,32);
        shield[1] = ss.getImage(7,5,32*2,32);
        shield[2] = ss.getImage(7,6,32*2,32);

        planet[0] = ss.getImage(1,4,32*6,32*2);
        planet[1] = ss.getImage(1,6,32*6,32*2);
        planet[2] = ss.getImage(1,8,32*6,32*2);


        heart[0] = ss.getImage(7,1,32,32);
        heart[1] = ss.getImage(7,2,32,32);
        heart[2] = ss.getImage(7,3,32,32);


        asteroid[0] = ss.getImage(8,7,32,32);
        asteroid[1] = ss.getImage(8,8,32,32);
        asteroid[2] = ss.getImage(8,9,32,32);
    }

}
