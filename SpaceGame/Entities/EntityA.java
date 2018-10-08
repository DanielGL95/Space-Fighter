package SpaceGame.Entities;

import java.awt.*;

public interface EntityA {

    double getX();
    double getY();

    void setX(double num);
    void setY(double num);

    void tick();
    void render(Graphics g);

    ID getId();
    Rectangle getBounds();
}
