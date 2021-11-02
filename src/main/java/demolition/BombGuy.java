package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class BombGuy extends GameObject {

    private int lives;

    public BombGuy(int x, int y, int i_pos, int j_pos, PImage[][] sprites, int lives, Map map) {
        super(x, y, i_pos, j_pos, sprites, map);
        this.lives = lives;
        this.s_cycle = 0;
        this.s_dir = 3;
    }

    public void tick() {
        sprite_cycle();
        checkKill();
    }

    public void reset() {
        resetPos();
        s_cycle = 0;
    }

    public void collisionCondition(Direction d, Map map) {
        return;
    }

    public boolean checkWin() {
        Tile[][] tiles = map.getMap();
        if(tiles[i_pos][j_pos].getType() == TileType.GOAL)
            return true;
        return false;
    }
    public void checkKill() {
        for(Enemy e : map.getEnemies()) {
            if(i_pos == e.getI() && j_pos == e.getJ()) {
                kill();
                return;
            }
        }
    }

    public void kill() {
        lives--;
        map.resetLevel();
    }
    public int getLives() {
        return lives;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }

}