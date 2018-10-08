package SpaceGame.Menu;

import SpaceGame.Game;
import SpaceGame.Texture.Texture;
import org.w3c.dom.Text;

import java.awt.*;

public class HelpMenu {

    private Texture texture;
    public HelpMenu(Texture texture) {
        this.texture = texture;
    }

    public void render(Graphics g) {
        if (Game.state == State.Help) {


            Font font = new Font("arial", Font.BOLD, 15);
            g.setFont(font);
            g.setColor(Color.LIGHT_GRAY);


            g.drawString("You are the last standing defender of the Earth. You have to hold the upcoming ", 20,  35);
            g.drawString("alien spaceships, for the next 1 minute. Reinforcement will arrive. ", 20, 60);
            g.drawString("Your ship can take up to 3 enemy blows, luckily you have an energy shield powerful", 20, 85);
            g.drawString("enough to repel all incoming attacks. Unfortunately your ship is able to keep it up", 20, 110);
            g.drawString("for only couple of seconds. Good luck!", 20, 135);
            g.drawString("Commands:", Game.WIDTH-70, 200);
            g.drawString("W - Forward", Game.WIDTH-190, 240);
            g.drawString("S - Backward", Game.WIDTH-190, 260);
            g.drawString("A - Left ", Game.WIDTH-190, 280);
            g.drawString("D - Right", Game.WIDTH-190, 300);
            g.drawString("R - Reinforcment", Game.WIDTH-190, 320);
            g.drawString("Fire - Mouse", Game.WIDTH-190, 340);
            g.drawString("Shield - Space", Game.WIDTH-190, 360);

            g.drawImage(texture.player[2],Game.WIDTH+50,230,null);
            g.drawString(" - You", Game.WIDTH+90, 252);

            g.drawImage(texture.enemy[1],Game.WIDTH+53,280,null);
            g.drawString(" - Alien", Game.WIDTH+90, 302);

            g.drawImage(texture.asteroid[1],Game.WIDTH+53,330,null);
            g.drawString(" - Asteroid", Game.WIDTH+90, 352);

            g.drawString("Press ESC to go back to the menu", Game.WIDTH / 2 + 43, 450);
        }
    }


}
