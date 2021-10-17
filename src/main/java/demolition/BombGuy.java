package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BombGuy extends GameObject {

    public BombGuy(int x, int y, int i_pos, int j_pos, PImage[][] sprites) {
        super(x, y, i_pos, j_pos, sprites);
    }

    public void tick() {
        sprite_cycle();
    }

    public boolean checkWin(Map map) {
        Tile[][] tiles = map.getMap();
        if(tiles[i_pos][j_pos].getType() == TileType.GOAL)
            return true;
        return false;
    }

}