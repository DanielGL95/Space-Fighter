package SpaceGame.Menu;

import SpaceGame.Game;

import java.awt.*;

public class ManuPage {

    public static Color menuColor = Color.WHITE;
    public static Color colorPLay = Color.WHITE;
    public static Color colorHelp = Color.WHITE;
    public static Color colorExit = Color.WHITE;
    public static Color menuColor1 = Color.WHITE;
    public static Color menuColor2 = Color.WHITE;
    private static Color titleColor = Color.WHITE;

    private boolean white = true;
    private Game game;
    private static int bx = Game.WIDTH /2 +65;
    private static int bw1 = 200;
    private static int bw2 = 200;
    private static int bw3 = 200;
    private Rectangle playButton = new Rectangle(bx ,150,bw1,50);

    private Rectangle helpButton = new Rectangle(bx ,250,bw2,50);

    private Rectangle exitButton = new Rectangle(bx ,350,bw3,50);

    public ManuPage(Game game){
        this.game = game;
    }

    public void render(Graphics g){

        Font font = new Font("arial", Font.BOLD,50);
        g.setFont(font);
        g.setColor(titleColor);
        g.drawString("Space Fighter", Game.WIDTH/2, 70);


        Graphics2D g2d = (Graphics2D) g;
        Font buttonFont = new Font("arial",Font.PLAIN, 30);
        g.setFont(buttonFont);

        g.setColor(colorPLay);
        g.drawString("Play",Game.WIDTH /2 +135,185);
        g.setColor(Color.WHITE);

        g.setColor(colorHelp);
        g.drawString("Help",Game.WIDTH /2 +135,285);
        g.setColor(Color.WHITE);

        g.setColor(colorExit);
        g.drawString("Exit",Game.WIDTH /2 +135,385);
        g.setColor(Color.WHITE);


        Font version = new Font("arial",Font.BOLD, 15);
        g.setFont(version);
        g.drawString("v.1,0",Game.WIDTH  +270,450);

        g.setColor(menuColor);
        g2d.draw(playButton);
        g.setColor(Color.WHITE);

        g.setColor(menuColor1);
        g2d.draw(helpButton);
        g.setColor(Color.WHITE);



        g.setColor(menuColor2);
        g2d.draw(exitButton);
        g.setColor(Color.WHITE);
        g.setColor(Color.WHITE);

//    if (game.sound) {
//        ((Graphics2D) g).drawImage(game.getSpeaker(), 15, Game.WIDTH + 100, null);
//    }else{
//        ((Graphics2D) g).drawImage(game.getMute(), 15, Game.WIDTH + 100, null);
//    }

    }

    public void tick(){
        if (white){
            titleColor = Color.lightGray;
            white=false;
        }else{
            titleColor = Color.WHITE;
            white=true;
        }
    }

}
