package SpaceGame.Menu;

import SpaceGame.Game;

import java.awt.*;

public class GameOverPage {

    private Game game;

    public GameOverPage(Game game) {
        this.game = game;
    }

    public void render(Graphics g) {

        if (Game.state == State.GAMEOVER) {
            Font font = new Font("arial", Font.BOLD, 50);
            g.setFont(font);
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Game Over ", Game.WIDTH / 2 + 30, 200);

            Font gameOverFont = new Font("arial", Font.PLAIN, 30);
            g.setFont(gameOverFont);
            g.drawString("Total Kills: " + game.Points, Game.WIDTH / 2 + 70, 280);

        }

    }
}
