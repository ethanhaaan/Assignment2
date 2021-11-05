package demolition;

import processing.core.PApplet;
import processing.core.PImage;

public class Red extends Enemy {

    /**
    * Constructor for Red Enemy
    * @param x x position of the Red Enemy, amount of pixels to the right (for drawing)
    * @param y y position of the Red Enemy, amount of pixels downwards (for drawing)
    * @param i_pos the row within the map array where Red Enemy is located
    * @param j_pos the column within the map array  where Red Enemy is located
    * @param sprites the sprite array, with each row corresponding to a sprite array for different movement directions 
    * @param map the map object that the Red Enemy is placed within
    */

    public Red(int x, int y, int i_pos, int j_pos, PImage[][] sprites, Map map) {
        super(x, y, i_pos, j_pos, sprites, map);
    }

    /**The recursive method that is executed upon collision with a wall.
    * The enumeration method {@link Direction#getRandomDirection()} is called to change the Red enemy's direction to a random direction, until
    {@link GameObject#checkCollision(Direction d, Map map)} is no longer true and there is no longer a collision.
     */

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