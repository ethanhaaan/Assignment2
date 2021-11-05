package demolition;

import processing.core.PApplet;
import processing.core.PImage;

public class Yellow extends Enemy {

    /**
    * Constructor for Yellow Enemy
    * @param x x position of the Yellow Enemy, amount of pixels to the right (for drawing)
    * @param y y position of the Yellow Enemy, amount of pixels downwards (for drawing)
    * @param i_pos the row within the map array where Yellow Enemy is located
    * @param j_pos the column within the map array  where Yellow Enemy is located
    * @param sprites the sprite array, with each row corresponding to a sprite array for different movement directions 
    * @param map the map object that the Yellow Enemy is placed within
    */

    public Yellow(int x, int y, int i_pos, int j_pos, PImage[][] sprites, Map map) {
        super(x, y, i_pos, j_pos, sprites, map);
    }
    /**The recursive method that is executed upon collision with a wall.
    * The Yellow enemy's direction is changed to the next clockwise direction, until
    {@link GameObject#checkCollision(Direction d, Map map)} is no longer true and there is no longer a collision.
     */

    public void collisionCondition(Direction d, Map map) {
        if(checkCollision(d, map)) {
            if(d == Direction.UP)
                current_dir = Direction.RIGHT;
            else if(d == Direction.RIGHT)
                current_dir = Direction.DOWN;
            else if(d == Direction.DOWN)
                current_dir = Direction.LEFT;
            else
                current_dir = Direction.UP;
            collisionCondition(current_dir, map);
        }
        else {
            move(d);
        }
    }

}