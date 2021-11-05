package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Bomb {
    /**x value of the bomb in terms of pixels */
    private int x;
    /**y value of the bomb in terms of pixels */
    private int y;
    /**the row within the map array */ 
    private int i_pos;
    /**the column within the map array */ 
    private int j_pos;
    /**sprites of the bomb,
    *with first row displaying bomb sprites and second row displaying explosion sprites */ 
    private PImage[][] sprites;
    /**the current sprite that is displayed upon draw() */
    private PImage current_sprite;
    /**countdown timer until the bomb explodes */
    private int countdown_timer;
    /**timer to time explosion duration*/
    private int explosion_timer;
    /**times how many frames each sprite lasts for before moving to next sprite*/
    private int sprite_timer;
    /**the group of sprites iterator.
    * when s_i = 0, sprites displayed are bomb sprites
    * when s_i = 1, sprites displayed are explosion sprites
    */
    private int s_i;
    /**The sprite iterator */
    private int s_cycle;
    private boolean exploding;
    private boolean exploded;
    private Map map;
    private List<Explosion> explosion;

    /**
    * Constructor for bomb object
    * @param x x position of the bomb, amount of pixels to the right (for drawing)
    * @param y y position of the bomb, amount of pixels downwards (for drawing)
    * @param i_pos the row within the map array where bomb is located
    * @param j_pos the column within the map array  where bomb is located
    * @param sprites the sprite array with first row an array of bomb sprites and second row an array of explosion sprites to be cycled
    * @param map the map object that the bomb is placed within
    */

    public Bomb(int x, int y, int i_pos, int j_pos, PImage[][] sprites, Map map) {
        this.x = x;
        this.y = y;
        this.i_pos = i_pos;
        this.j_pos = j_pos;
        this.sprites = sprites;
        this.exploding = false;
        this.exploded = false;
        this.s_cycle = 0;
        this.s_i = 0;
        this.sprite_timer = 15;
        this.countdown_timer = 120;
        this.explosion_timer = 30;
        this.explosion = new ArrayList<Explosion>();
        this.map = map;
    }

    /**
    * To be executed once every frame,
    * decrements timers, calls {@link #sprite_cycle()} per execution and calls {@link #explode()} once exploded.
    * @return true if explosion made contact with player
    */

    public boolean tick() {
        boolean player_contact = false;
        if(!exploding && countdown_timer == 0) {
            explode();
        }
        else if(exploding) {
            if(explosion_timer == 0) {
                exploded = true;
                return false;
            }
            explosion_timer--; 
            if(checkContact())
                player_contact = true;
        }
        else {
            countdown_timer--;
        }
        sprite_cycle();
        return player_contact;
    }

    /**
    * To be executed once every frame,
    * draws the bomb
    * @param app of PApplet 
     */

    public void draw(PApplet app) {
        //Explosion finished
        if(exploded) {
            return;
        }
        //Explosion not started
        else if(!exploding) {
            app.image(current_sprite, x, y);
        }
        //In process of exploding
        else {
            for(Explosion e : explosion) {
                app.image(e.getSprite(), e.getX(), e.getY());
            }
        }
            
    }

    /**
    * To be executed once every frame by {@link #tick()},
    * cycles the sprite by moving to the next PImage in the PImage[] array
     */

    public void sprite_cycle() {
        if(exploding)
            return;
        current_sprite = sprites[s_i][s_cycle];
        if(sprite_timer == 0) {
            s_cycle++;
            sprite_timer = 15;
        }
        sprite_timer--;
    }

    /**
    * Creates explosion objects for every grid tile that the explosion extends to, and checks whether explosion can continue
     */

    public void explode() {
        exploding = true;
        explosion.add(new Explosion(x, y, i_pos, j_pos, sprites[1][0]));
        //checkLeft
        for(int i = 1; i < 3; i++) {
            TileType adj_tiletype = map.getMap()[i_pos][j_pos-i].getType();

            if(adj_tiletype == TileType.SOLID) {
                break;
            }

            else if(adj_tiletype == TileType.BROKEN) {
                map.getMap()[i_pos][j_pos-i] = new EmptyTile(map.Wall_s[2], x-32*i, y);
                explosion.add(new Explosion(x-32*i, y, i_pos, j_pos-i, sprites[1][3]));
                break;     
            }

            else if(adj_tiletype == TileType.EMPTY || adj_tiletype == TileType.GOAL) {
                TileType last_tiletype = map.getMap()[i_pos][j_pos-2].getType();
                if((last_tiletype == TileType.SOLID && i == 1) || i == 2) {
                    explosion.add(new Explosion(x-32*i, y, i_pos, j_pos-i, sprites[1][3]));
                    break;     
                }
                else {
                    explosion.add(new Explosion(x-32*i, y, i_pos, j_pos-i, sprites[1][1]));
                }
            }
        }
        //checkRight
        for(int i = 1; i < 3; i++) {
            TileType adj_tiletype = map.getMap()[i_pos][j_pos+i].getType();

            if(adj_tiletype == TileType.SOLID) {
                break;
            }

            else if(adj_tiletype == TileType.BROKEN) {
                map.getMap()[i_pos][j_pos+i] = new EmptyTile(map.Wall_s[2], x+32*i, y);
                explosion.add(new Explosion(x+32*i, y, i_pos, j_pos+i, sprites[1][4]));
                break;     
            }

            else if(adj_tiletype == TileType.EMPTY || adj_tiletype == TileType.GOAL) {
                TileType last_tiletype = map.getMap()[i_pos][j_pos+2].getType();
                if((last_tiletype == TileType.SOLID && i == 1) || i == 2) {
                    explosion.add(new Explosion(x+32*i, y, i_pos, j_pos+i, sprites[1][4]));
                    break;     
                }
                else {
                    explosion.add(new Explosion(x+32*i, y, i_pos, j_pos+i, sprites[1][1]));
                }
            }
        }
        //checkUp
        for(int i = 1; i < 3; i++) {
            TileType adj_tiletype = map.getMap()[i_pos-1][j_pos].getType();

            if(adj_tiletype == TileType.SOLID) {
                break;
            }

            else if(adj_tiletype == TileType.BROKEN) {
                map.getMap()[i_pos-i][j_pos] = new EmptyTile(map.Wall_s[2], x, y-32*i);
                explosion.add(new Explosion(x, y-32*i, i_pos-i, j_pos, sprites[1][5]));
                break;     
            }

            else if(adj_tiletype == TileType.EMPTY || adj_tiletype == TileType.GOAL) {
                TileType last_tiletype = map.getMap()[i_pos-2][j_pos].getType();
                if((last_tiletype == TileType.SOLID && i == 1) || i == 2) {
                    explosion.add(new Explosion(x, y-32*i, i_pos-i, j_pos, sprites[1][5]));
                    break;     
                }
                else {
                    explosion.add(new Explosion(x, y-32*i, i_pos-i, j_pos, sprites[1][2]));
                }
            }
        }

        //checkDown
        for(int i = 1; i < 3; i++) {
            TileType adj_tiletype = map.getMap()[i_pos+1][j_pos].getType();

            if(adj_tiletype == TileType.SOLID) {
                break;
            }

            else if(adj_tiletype == TileType.BROKEN) {
                map.getMap()[i_pos+i][j_pos] = new EmptyTile(map.Wall_s[2], x, y+32*i);
                explosion.add(new Explosion(x, y+32*i, i_pos+i, j_pos, sprites[1][6]));
                break;     
            }

            else if(adj_tiletype == TileType.EMPTY || adj_tiletype == TileType.GOAL) {
                TileType last_tiletype = map.getMap()[i_pos+2][j_pos].getType();
                if((last_tiletype == TileType.SOLID && i == 1) || i == 2) {
                    explosion.add(new Explosion(x, y+32*i, i_pos+i, j_pos, sprites[1][6]));
                    break;     
                }
                else {
                    explosion.add(new Explosion(x, y+32*i, i_pos+i, j_pos, sprites[1][2]));
                }
            }
        }

    }

    /**
    * Checks explosion for any contact between the player or enemies.
    * If explosion makes contact with enemies, enemies are moved from the map's enemies array and transferred to enemies_dead, no longer rendered.
    * If explosion makes contact with player, {@link BombGuy#kill()} is called.
    * @return true if explosion makes contact with player.
    */

    public boolean checkContact() {
        List<Enemy> for_removal = new ArrayList<Enemy>();
        for(Explosion e : explosion) {
            if(e.getI() == map.getPlayer().getI() && e.getJ() == map.getPlayer().getJ()) {
                map.getPlayer().kill();
                return true;
            }
            for(Enemy en : map.getEnemies()) {
                if(e.getI() == en.getI() && e.getJ() == en.getJ()) {
                    for_removal.add(en);
                }
            }
        }
        for(Enemy e : for_removal) {
            map.getEnemiesDead().add(e);
            map.getEnemies().remove(e);
        }
        return false;
    }
}

class Explosion {
    private int x;
    private int y;
    private int i_pos;
    private int j_pos;
    private PImage sprite;

    /**
    * Constructor for an explosion 
    * @param x x position of the explosion (for drawing)
    * @param y y position of the explosion (for drawing)
    * @param i_pos the row within the map array where the explosion is located
    * @param j_pos the column within the map array where the explosion is located
    * @param sprite sprite which explosion is drawn with
    */

    public Explosion(int x, int y, int i_pos, int j_pos, PImage sprite) {
        this.x = x;
        this.y = y;
        this.i_pos = i_pos;
        this.j_pos = j_pos;
        this.sprite = sprite;
    }

    /** @return PImage sprite object */
    public PImage getSprite() {
        return sprite;
    }
    /** @return x position of the explosion */
    public int getX() {
        return x;
    }
    /** @return y position of the explosion */
    public int getY() {
        return y;
    }
    /** @return the row within the map array */
    public int getI() {
        return i_pos;
    }
    /** @return the column within the map array */
    public int getJ() {
        return j_pos;
    }
}