package SpaceGame.Physics;

import SpaceGame.Entities.EntityA;
import org.w3c.dom.Entity;

import java.util.LinkedList;

public class Physics {

    public static boolean Collision(EntityA entity, LinkedList<EntityA> entities){

        for (EntityA entity1 : entities) {
            if(entity.getBounds().intersects(entity1.getBounds())){
                return true;
            }
        }

        return false;
    }
}
