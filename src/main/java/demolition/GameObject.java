package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int i_pos;
    protected int j_pos;
    protected final int x_original;
    protected final int y_original;
    protected final int i_pos_original;
    protected final int j_pos_original;
    protected PImage[][] sprites;
    protected PImage current_sprite;
    protected int sprite_timer;
    protected int s_dir;
    protected int s_cycle;
    protected Map map;

    /**
    * Constructor for a GameObject
    * @param x x position of the GameObject, amount of pixels to the right (for drawing)
    * @param y y position of the GameObject, amount of pixels downwards (for drawing)
    * @param i_pos the row within the map array where GameObject is located
    * @param j_pos the column within the map array  where GameObject is located
    * @param sprites the sprite array, with each row corresponding to a sprite array for different movement directions 
    * @param map the map object that the GameObject is placed within
    */

    public GameObject(int x, int y, int i_pos, int j_pos, PImage[][] sprites, Map map) {
        this.x = x;
        this.y = y;
        this.i_pos = i_pos;
        this.j_pos = j_pos;
        this.x_original = x;
        this.y_original = y;
        this.i_pos_original = i_pos;
        this.j_pos_original = j_pos;
        this.sprites = sprites;
        this.sprite_timer = 12;
        this.map = map;
    }
    /**To be executed once per frame */
    public abstract void tick();
    /**Resets the position and sprite cycle of the GameObject */
    public abstract void reset();
    /**
    * Condition upon colliding with wall 
    * @param d Cardinal direction in which GameObject is colliding in
    * @param map Map object which the GameObject is situated in
    */
    public abstract void collisionCondition(Direction d, Map map);

    /**To be executed once per frame, cycles the GameObject's current_sprite being rendered to the next sprite in the array */
    public void sprite_cycle() {
        current_sprite = sprites[s_dir][s_cycle];
        if(sprite_timer < 0) {
            if(s_cycle == 3) {
                s_cycle = 0;
            }
            else
                s_cycle++;
        sprite_timer = 12;
        }
        else 
            sprite_timer--;
    }
    /**To be executed once per frame, draws the GameObject using its current_sprite 
    * @param app PApplet object */
    public void draw(PApplet app) {
        app.image(current_sprite, x, y);
    }
    /**
    * Moves the GameObject in a cardinal direction, a distance of one grid space and updates its position in the map array (changing i_pos and j_pos)
    * @param d direction of movement.
    */
    public void move(Direction d) {
        if(checkCollision(d, map)) {
            collisionCondition(d, map);
            return;
        }
        if(d == Direction.LEFT) {
            x -= 32;
            s_dir = 0; 
            j_pos--;
        }
        else if(d == Direction.RIGHT) {
            x += 32;
            s_dir = 1;
            j_pos++;
        }
        else if(d == Direction.UP) {
            y -= 32;
            s_dir = 2;
            i_pos--;
        }
        else if(d == Direction.DOWN) {
            y += 32;
            s_dir = 3;
            i_pos++;
        }
    }
    /**
    * Checks whether a collision will occur if moved in the specified direction
    * @param d Direction of movement
    * @param map Map object that GameObject is moving in
    * @return true if a collision will occur, false if no collision will occur
     */
    public boolean checkCollision(Direction d, Map map) {
        Tile[][] tiles = map.getMap();
        TileType left_tile = tiles[i_pos][j_pos-1].getType();
        TileType right_tile = tiles[i_pos][j_pos+1].getType();
        TileType up_tile = tiles[i_pos-1][j_pos].getType();
        TileType down_tile = tiles[i_pos+1][j_pos].getType();

        if(d == Direction.LEFT && (left_tile == TileType.EMPTY || left_tile == TileType.GOAL))
            return false; 
        else if(d == Direction.RIGHT && (right_tile == TileType.EMPTY || right_tile == TileType.GOAL))
            return false; 
        else if(d == Direction.UP && (up_tile == TileType.EMPTY || up_tile == TileType.GOAL))
            return false; 
        else if(d == Direction.DOWN && (down_tile == TileType.EMPTY || down_tile == TileType.GOAL))
            return false;
        else 
            return true; 
    }
    /**@return x position of the GameObject */
    public int getX() {
        return x;
    }
    /**@return y position of the GameObject */
    public int getY() {
        return y;
    }
    /**@return the row within the map array that the GameObject is situated on */
    public int getI() {
        return i_pos;
    }
    /**@return the column within the map array that the GameObject is situated on */
    public int getJ() {
        return j_pos;
    }
    /**@return original x position of the GameObject */
    public int getOriginalX() {
        return x_original;
    }
    /**@return original y position of the GameObject */
    public int getOriginalY() {
        return y_original;
    }
    /**@return the original row within the map array that the GameObject is situated on */
    public int getOriginalI() {
        return i_pos_original;
    }
    /**@return the original column within the map array that the GameObject is situated on */
    public int getOriginalJ() {
        return j_pos_original;
    }
    /**moves GameObject back to original position */
    public void resetPos() {
        x = x_original;
        y = y_original;
        i_pos = i_pos_original;
        j_pos = j_pos_original;
    }
}

enum Direction {
	LEFT, RIGHT, UP, DOWN;
    public static Direction getRandomDirection() {
        return values()[(int)(Math.random()*4)];
    }
}
