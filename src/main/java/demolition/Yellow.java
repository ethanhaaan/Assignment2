package demolition;

import processing.core.PApplet;
import processing.core.PImage;

public class Yellow extends Enemy {

    public Yellow(int x, int y, int i_pos, int j_pos, PImage[][] sprites, Map map) {
        super(x, y, i_pos, j_pos, sprites, map);
    }

    public void collisionCondition(Direction d, Map map) {
        if(checkCollision(d, map)) {
            if(d == Direction.UP)
                current_dir = Direction.RIGHT;
            else if(d == Direction.RIGHT)
                current_dir = Direction.DOWN;
            else if(d == Direction.DOWN)
                current_dir = Direction.LEFT;
            else if(d == Direction.LEFT)
                current_dir = Direction.UP;
            collisionCondition(current_dir, map);
        }
        else {
            move(d);
        }
    }

}