package SpaceGame.IO;

import SpaceGame.Game;
import SpaceGame.Menu.ManuPage;
import SpaceGame.Menu.State;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private static int minutes;
    private static int seconds;
    private static Thread t1;

    private Game game;

    public MouseInput(Game game) {
        this.game = game;

    }

    public static int getMinutes() {
        return minutes;
    }

    private static void setMinutes(int minutes) {
        MouseInput.minutes = minutes;
    }


    public static int getSeconds() {
        return seconds;
    }

    private static void setSeconds(int seconds) {
        MouseInput.seconds = seconds;
    }

    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

       if (mouseOver(mx,my,Game.WIDTH /2 +65 ,150)){
           ManuPage.menuColor = Color.GRAY;
            ManuPage.colorPLay = Color.GRAY;
       }else{
           ManuPage.menuColor = Color.WHITE;
           ManuPage.colorPLay = Color.WHITE;
       }

        if (mouseOver(mx,my,Game.WIDTH /2 +65 ,250)){
            ManuPage.menuColor1 = Color.GRAY;
            ManuPage.colorHelp = Color.GRAY;
        }else{
            ManuPage.menuColor1 = Color.WHITE;
            ManuPage.colorHelp = Color.WHITE;

        }


        if (mouseOver(mx,my,Game.WIDTH /2 +65 ,350)){
            ManuPage.menuColor2 = Color.GRAY;
            ManuPage.colorExit = Color.GRAY;
        }else{
            ManuPage.menuColor2 = Color.WHITE;
            ManuPage.colorExit = Color.WHITE;

        }
    }

    private boolean mouseOver(int mx, int my, int x, int y) {
        if (mx > x && mx < x + 200) {
            return my > y && my < y + 50;
        } else {
            return false;
        }
    }

    public void mousePressed(MouseEvent e) {
        game.mousePressed(e);

        int mx = e.getX();
        int my = e.getY();


        if (mx > Game.WIDTH / 2 + 50 && mx < Game.WIDTH / 2 + 300) {
            if (my > 130 && my < 185) {
                if (Game.state == State.MENU) {
                    game.Healht = 100;
                    game.Points = 0;
                    new Game();
                    Game.state = State.GAME;

                    //////////////////////////////////

                    t1 = new Thread() {
                        public void run() {
                            int timet = 1 * 60; // Convert to seconds
                            long delay = timet * 1000;

                            do {
                                minutes = timet / 60;
                                seconds = timet % 60;
                                setMinutes(minutes);
                                setSeconds(seconds);


                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                timet = timet - 1;
                                delay = delay - 1000;

                            }
                            while (delay != 0);
                            System.out.println("Time's Up!");
                        }
                    };

                    t1.start();


                    /////////////////////////////////

                }

            }
        }


        if (mx > Game.WIDTH / 2 + 65 && mx < Game.WIDTH / 2 + 200) {
            if (my > 250 && my < 300) {
                if (Game.state == State.MENU) {
                    Game.state =State.Help;
                }
            }
        }


        if (mx > 15 && mx < 40) {
            if (my > Game.WIDTH+100 && my < Game.WIDTH+132) {
                if (Game.state == State.MENU) {
                  if (game.isSound()){
                      game.setSound(false);
                  }else{
                      game.setSound(true);
                  }
                }
            }
        }


        if (mx > Game.WIDTH / 2 + 65 && mx < Game.WIDTH / 2 + 200) {
            if (my > 350 && my < 400) {
                if (Game.state == State.MENU) {
                    System.exit(1);
                }
            }
        }
    }

}
