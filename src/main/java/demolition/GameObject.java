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
    protected PImage[][] sprites;
    protected PImage current_sprite;
    protected int sprite_timer;
    protected int s_dir;
    protected int s_cycle;
    protected Map map;

    public GameObject(int x, int y, int i_pos, int j_pos, PImage[][] sprites, Map map) {
        this.x = x;
        this.y = y;
        this.i_pos = i_pos;
        this.j_pos = j_pos;
        this.sprites = sprites;
        this.sprite_timer = 12;
        this.map = map;
    }

    public abstract void tick();
    public abstract void collisionCondition(Direction d, Map map);

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

    public void draw(PApplet app) {
        app.image(current_sprite, x, y);
    }

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

    public static List<Enemy> load_enemies(String path, PImage[][] Red_s, PImage[][] Yellow_s, Map map) {
        List<Enemy> enemies = new ArrayList<Enemy>();
        try {
            File file = new File(path);
            Scanner scanobj = new Scanner(file);
            for(int i = 0; i < 13; i++) {
                String line = scanobj.nextLine();
                for(int j = 0; j < 15; j++) {
                    if("R".equals(String.valueOf(line.charAt(j))))
                        enemies.add(new Red(32*j, 32*i+64-16, i, j, Red_s, map));
                    else if("Y".equals(String.valueOf(line.charAt(j))))
                        enemies.add(new Yellow(32*j, 32*i+64-16, i, j, Yellow_s, map));
                }
            }
            return enemies;
        }
        catch(FileNotFoundException e) {
            System.out.println("not working");
        }
        return enemies;
    }
    public static BombGuy load_player(String path, PImage[][] sprites, int lives, Map map) {
        try {
            File file = new File(path);
            Scanner scanobj = new Scanner(file);
            for(int i = 0; i < 13; i++) {
                String line = scanobj.nextLine();
                for(int j = 0; j < 15; j++) {
                    if("P".equals(String.valueOf(line.charAt(j))))
                        return new BombGuy(32*j, 32*i+64-16, i, j, sprites, lives, map);
                }
            }
            return null;
        }
        catch(FileNotFoundException e) {
            System.out.println("nothing");
            return null;
        }
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getI() {
        return i_pos;
    }
    public int getJ() {
        return j_pos;
    }
}

enum Direction {
	LEFT, RIGHT, UP, DOWN;
    public static Direction getRandomDirection() {
        return values()[(int)(Math.random()*4)];
    }
}
