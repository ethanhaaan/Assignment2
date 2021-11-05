package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class BombGuy extends GameObject {

    private int lives;

    /**
    * Constructor for the main player 
    * @param x x position of BombGuy, amount of pixels to the right (for drawing)
    * @param y y position of BombGuy, amount of pixels downwards (for drawing)
    * @param i_pos the row within the map array where BombGuy is located
    * @param j_pos the column within the map array  where BombGuy is located
    * @param sprites the sprite array, with each row corresponding to a sprite array for different movement directions 
    * @param lives the number of lives that BombGuy is to start with
    * @param map the map object that the BombGuy is placed within
    */

    public BombGuy(int x, int y, int i_pos, int j_pos, PImage[][] sprites, int lives, Map map) {
        super(x, y, i_pos, j_pos, sprites, map);
        this.lives = lives;
        this.s_cycle = 0;
        this.s_dir = 3;
    }

    /** To be executed once every frame, calls {@link GameObject#cycleSprite()} and {@link #checkKill()} */
    public void tick() {
        cycleSprite();
        checkKill();
    }
    /** Calls {@link GameObject#resetPos()} and changes the sprite being drawn to the first sprite in the cycle array */
    public void reset() {
        resetPos();
        s_cycle = 0;
    }
    /** Called when BombGuy collides with a wall */
    public void collisionCondition(Direction d, Map map) {
        return;
    }
    /** 
    * Checks whether BombGuy is on a GOAL tile 
    * @return true if BombGuy is on a goal tile, false if on any other tile
    */
    public boolean checkWin() {
        Tile[][] tiles = map.getMap();
        if(tiles[i_pos][j_pos].getType() == TileType.GOAL)
            return true;
        return false;
    }
    /** Checks if BombGuy is on the same tile as an enemy and calls {@link #kill()} if so*/
    public void checkKill() {
        for(Enemy e : map.getEnemies()) {
            if(i_pos == e.getI() && j_pos == e.getJ()) {
                kill();
                return;
            }
        }
    }
    /** Decrements the number of lives, and calls {@link Map#resetLevel()} */
    public void kill() {
        lives--;
        map.resetLevel();
    }
    /** @return number of lives remaining */
    public int getLives() {
        return lives;
    }
    /** @param lives number of lives to change to */
    public void setLives(int lives) {
        this.lives = lives;
    }

}