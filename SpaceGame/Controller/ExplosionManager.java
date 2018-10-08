package SpaceGame.Controller;

import SpaceGame.Entities.EntityA;
import java.awt.*;

public class ExplosionManager {

    private int r;
    private int maxRadius;
    private EntityA enemy;
    private Graphics g;
    public ExplosionManager(EntityA enemy, Graphics g, int max ){

        this.g = g;
        this.enemy = enemy;
        this.maxRadius = max;
        render(g);

    }

    public boolean tick(){
        r++;

        return r >= maxRadius;

    }

    public void render(Graphics g) {

        g.setColor(Color.RED);
        g.drawOval((int)enemy.getX() -r, (int)enemy.getY() - r, 2*r,2*r);
        g.setColor(Color.ORANGE);
        g.fillOval((int)enemy.getX() -r, (int)enemy.getY() - r, 2*r,2*r);

    }

}

