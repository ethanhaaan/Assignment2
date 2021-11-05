package demolition;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Enemy extends GameObject {

    private int timer;
    protected Direction current_dir;

    /**
    * Constructor for an Enemy
    * @param x x position of the Enemy, amount of pixels to the right (for drawing)
    * @param y y position of the Enemy, amount of pixels downwards (for drawing)
    * @param i_pos the row within the map array where Enemy is located
    * @param j_pos the column within the map array  where Enemy is located
    * @param sprites the sprite array, with each row corresponding to a sprite array for different movement directions 
    * @param map the map object that the Enemy is placed within
    */

    public Enemy(int x, int y, int i_pos, int j_pos, PImage[][] sprites, Map map) {
        super(x, y, i_pos, j_pos, sprites, map);
        timer = 60;
        setOrientation();
    }

    /**Chooses a random starting direction for the enemy, and chooses the sprite cycle array corresponding to the starting direction */
    private void setOrientation() {
        current_dir = Direction.getRandomDirection();
        if(current_dir == Direction.LEFT) {
            s_dir = 0;
        }
        else if(current_dir == Direction.RIGHT) {
            s_dir = 1;
        }
        else if(current_dir == Direction.UP) {
            s_dir = 2;
        }
        else {
            s_dir = 3;
        }
        s_cycle = 0;
    }
    /**To be executed once every frame, calls {@link #sprite_cycle()} to cycle the sprite in the array and calls {@link GameObject#move(Direction d)} once every 60 frames */
    public void tick() {
        sprite_cycle();
        if(timer == 1) {
            move(current_dir);
            timer = 60;
        }
        else {
            timer--;
        }
    }
    /**Resets the position of the enemy, and aligns the enemy's FPS timer (how many frames until the next second) with the map's FPS timer
    such that the enemies continue to move only at the start of every second  */
    public void reset() {
        resetPos();
        timer = map.getFPSTimer();
        s_cycle = 0;
    }


}