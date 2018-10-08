package SpaceGame.Menu;

import SpaceGame.Game;

import java.awt.*;

public class WinnerPage {
    private Game game;

    public WinnerPage(Game game) {
        this.game = game;
    }

    public void render(Graphics g) {
        if (Game.state == State.Win) {

            Font font = new Font("arial", Font.BOLD, 50);
            g.setFont(font);
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("You Win !", Game.WIDTH / 2 + 50, 200);

            Font gameOverFont = new Font("arial", Font.PLAIN, 30);
            g.setFont(gameOverFont);
            g.drawString("Great Game ", Game.WIDTH / 2 + 73, 280);
            Font a = new Font("arial", Font.PLAIN, 15);
            g.setFont(a);
            g.drawString("Press ESC to go back to the menu", Game.WIDTH / 2 +43, 450);
        }
    }

}


