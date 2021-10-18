package demolition;

import processing.core.PApplet;
import processing.core.PImage;

public class Red extends Enemy {

    public Red(int x, int y, int i_pos, int j_pos, PImage[][] sprites, Map map) {
        super(x, y, i_pos, j_pos, sprites, map);
    }

    public void collisionCondition(Direction d, Map map) {
        if(checkCollision(d, map)) {
            current_dir = Direction.getRandomDirection();
            collisionCondition(current_dir, map);
        }
        else {
            move(d);
        }
    }

}