package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Bomb {
    
    private int x;
    private int y;
    private int i_pos;
    private int j_pos;
    private PImage[][] sprites;
    private PImage current_sprite;
    private int countdown_timer;
    private int explosion_timer;
    private int sprite_timer;
    private int s_i;
    private int s_cycle;
    private boolean exploded;
    private boolean finished;
    private Map map;
    private List<Explosion> explosion;

    public Bomb(int x, int y, int i_pos, int j_pos, PImage[][] sprites, Map map) {
        this.x = x;
        this.y = y;
        this.i_pos = i_pos;
        this.j_pos = j_pos;
        this.sprites = sprites;
        this.exploded = false;
        this.finished = false;
        this.s_cycle = 0;
        this.s_i = 0;
        this.sprite_timer = 15;
        this.countdown_timer = 120;
        this.explosion_timer = 30;
        this.explosion = new ArrayList<Explosion>();
        this.map = map;
    }

    public void tick() {
        if(countdown_timer == 0) {
            explode();
            countdown_timer--;
        }
        else if(exploded) {
            if(explosion_timer == 0) {
                finished = true;
                return;
            }
            explosion_timer--; 
            checkContact();
        }
        else {
            countdown_timer--;
        }
        sprite_cycle();
    }

    public void draw(PApplet app) {
        if(finished) {
            return;
        }
        else if(!exploded) {
            app.image(current_sprite, x, y);
        }
        else if(exploded) {
            for(Explosion e : explosion) {
                app.image(e.getSprite(), e.getX(), e.getY());
            }
        }
            
    }

    public void sprite_cycle() {
        if(exploded)
            return;
        current_sprite = sprites[s_i][s_cycle];
        if(sprite_timer == 0) {
            s_cycle++;
            sprite_timer = 15;
        }
        sprite_timer--;
    }

    public void explode() {
        exploded = true;
        explosion.add(new Explosion(x, y, i_pos, j_pos, sprites[1][0]));
        //checkLeft
        for(int i = 1; i < 3; i++) {
            TileType temp_tiletype = map.getMap()[i_pos][j_pos-i].getType();
            if(j_pos > 0 && j_pos < 14 && (i == 2 || (temp_tiletype != TileType.EMPTY && temp_tiletype != TileType.GOAL))) {
                map.getMap()[i_pos][j_pos-i] = new EmptyTile(x-32*i, y);
                explosion.add(new Explosion(x-32*i, y, i_pos, j_pos-i, sprites[1][3]));
                break;
            }
            else {
                explosion.add(new Explosion(x-32*i, y, i_pos, j_pos-i, sprites[1][1]));
            }
        }
        //checkRight
        for(int i = 1; i < 3; i++) {
            TileType temp_tiletype = map.getMap()[i_pos][j_pos+i].getType();
            if(j_pos > 0 && j_pos < 14 && (i == 2 || (temp_tiletype != TileType.EMPTY && temp_tiletype != TileType.GOAL))) {
                map.getMap()[i_pos][j_pos+i] = new EmptyTile(x+32*i, y);
                explosion.add(new Explosion(x+32*i, y, i_pos, j_pos+i, sprites[1][4]));
                break;
            }
            else {
                explosion.add(new Explosion(x+32*i, y, i_pos, j_pos+i, sprites[1][1]));
            }
        }
        //checkUp
        for(int i = 1; i < 3; i++) {
            TileType temp_tiletype = map.getMap()[i_pos-i][j_pos].getType();
            if(j_pos > 0 && j_pos < 14 && (i == 2 || (temp_tiletype != TileType.EMPTY && temp_tiletype != TileType.GOAL))) {
                map.getMap()[i_pos-i][j_pos] = new EmptyTile(x, y-32*i);
                explosion.add(new Explosion(x, y-32*i, i_pos-i, j_pos, sprites[1][5]));
                break;
            }
            else {
                explosion.add(new Explosion(x, y-32*i, i_pos-i, j_pos, sprites[1][2]));
            }
        }
        //checkBottom
        for(int i = 1; i < 3; i++) {
            TileType temp_tiletype = map.getMap()[i_pos+i][j_pos].getType();
            if(j_pos > 0 && j_pos < 14 && (i == 2 || (temp_tiletype != TileType.EMPTY && temp_tiletype != TileType.GOAL))) {
                map.getMap()[i_pos+i][j_pos] = new EmptyTile(x, y+32*i);
                explosion.add(new Explosion(x, y+32*i, i_pos+i, j_pos, sprites[1][6]));
                break;
            }
            else {
                explosion.add(new Explosion(x, y+32*i, i_pos+i, j_pos, sprites[1][2]));
            }
        }
    }

    public void checkContact() {
        for(Explosion e : explosion) {
            if(e.getI() == map.getPlayer().getI() && e.getJ() == map.getPlayer().getJ())
                map.getPlayer().kill();
        }
    }
}

class Explosion {
    private int x;
    private int y;
    private int i_pos;
    private int j_pos;
    private PImage sprite;

    public Explosion(int x, int y, int i_pos, int j_pos, PImage sprite) {
        this.x = x;
        this.y = y;
        this.i_pos = i_pos;
        this.j_pos = j_pos;
        this.sprite = sprite;
    }

    public PImage getSprite() {
        return sprite;
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