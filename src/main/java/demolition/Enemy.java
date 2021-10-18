package demolition;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Enemy extends GameObject {

    private int timer;
    public Direction current_dir;

    public Enemy(int x, int y, int i_pos, int j_pos, PImage[][] sprites, Map map) {
        super(x, y, i_pos, j_pos, sprites, map);
        timer = 60;
        setOrientation();

    }

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

    public void tick() {
        sprite_cycle();
        if(timer == 0) {
            move(current_dir);
            timer = 60;
        }
        else {
            timer--;
        }
    }

}