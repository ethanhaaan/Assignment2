package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Map {
    
    private Tile[][] map;
    private List<Enemy> enemies;
    private List<Enemy> enemies_dead;
    private List<Bomb> bombs;
    private BombGuy player;
    private int time;
    private int lives;
    private int fps_timer;
    private String path;

    public PImage[] Wall_s;
    public PImage[] UI_s;
    public PImage[][] Bomb_s;
    public PImage[][] BombGuy_s;
    public PImage[][] Red_s;
    public PImage[][] Yellow_s;

    /**
    * Map constructor, requires the path of the level file in which the map is immediately constructed, and all GameObjects loaded with. Additionally,
    all sprite objects required.
    * @param path path to the level file
    * @param lives number of lives that the player should start with
    * @param time amount of time that the map allows 
    * @param Wall_s sprite array for all wall sprites, loaded from {@link Img#loadWall(PApplet app)}
    * @param UI_s sprites for UI, loaded from {@link Img#loadUI(PApplet app)}
    * @param Bomb_s sprite array for bomb sprites, loaded from {@link Img#loadBomb(PApplet app)}
    * @param BombGuy_s sprite array for BombGuy sprites, loaded from {@link Img#loadBombGuy(PApplet app)}
    * @param Red_s sprite array for red enemy sprites, loaded from {@link Img#loadRed(PApplet app)}
    * @param Yellow_s sprite array for yellow enemy sprites, loaded from {@link Img#loadYellow(PApplet app)}
    */

    public Map(String path, int lives, int time, PImage[] Wall_s, PImage[] UI_s, PImage[][] Bomb_s, PImage[][] BombGuy_s, PImage[][] Red_s, PImage[][] Yellow_s) {
        this.map = new Tile[13][15];
        this.enemies = new ArrayList<Enemy>();
        this.enemies_dead = new ArrayList<Enemy>();
        this.bombs = new ArrayList<Bomb>();
        this.fps_timer = 60;
        this.Wall_s = Wall_s;
        this.UI_s = UI_s;
        this.Bomb_s = Bomb_s;
        this.BombGuy_s = BombGuy_s;
        this.Red_s = Red_s;
        this.Yellow_s = Yellow_s;
        this.path = path;
        this.lives = lives;
        this.time = time;
        constructMap(path);
        loadObjects(path, lives, time);
    }
    /**@return array of enemies currently alive within the map */
    public List<Enemy> getEnemies() {
        return enemies;
    }
    /** gets enemies that were killed and tranferred to dead enemy array
    * @return array of enemies currently dead within the map */
    public List<Enemy> getEnemiesDead() {
        return enemies_dead;
    }
    /**@return the player */
    public BombGuy getPlayer() {
        return player;
    }
    /**@return ArrayList of bombs within the map */
    public List<Bomb> getBombs() {
        return bombs;
    }
    /**@return the current FPS timer (amount of frames until the next real-time second) */
    public int getFPSTimer() {
        return fps_timer;
    }
    /** calls the Bomb constructor and adds a new Bomb to the map at the player's current position*/
    public void addBomb() {
        bombs.add(new Bomb(player.getX(), player.getY()+16, player.getI(), player.getJ(), Bomb_s, this));
    }
    /** To be executed once every frame, calls {@link BombGuy#tick()}, {@link #tickBombs()}, {@link #tickTimer()}, {@link #tickEnemies()} to
    call the tick method of every object on the map */
    public void tick() {
        player.tick();
        tickBombs();
        tickTimer();
        tickEnemies();
    }
    /** Calls {@link Bomb#tick()} for every bomb on the map, if any of the bombs make contact with the player, {@link Bomb#tick()} returns true and method returns */
    public void tickBombs() {
        for(Bomb b : bombs) {
            if(b.tick())
                return;
        }
    }
    /** Decrements the fps timer every execution, if fps timer reaches 0, the real-time timer is decremented as a second has elapsed. Fps timer reset to 60 */
    public void tickTimer() {
        if(fps_timer == 1) {
            time--;
            fps_timer = 60;
        }
        else {
            fps_timer--;
        }
        if(time == 0) {
            player.setLives(0);
        }
    }
    /** Calls {@link Enemy#tick()} for every enemy on the map */
    public void tickEnemies() {
        for(Enemy e : enemies)
            e.tick();
    }
    /** @return the amount of seconds left on the timer */
    public int getTime() {
        return time;
    }
    /** Calls the draw() method for every object on the map
    * @param app applet for which images are displayed on
     */
    public void draw(PApplet app) {
        for(Tile[] i : map) {
            for(Tile j : i) {
                j.draw(app);
            }
        }
        for(Bomb b : bombs)
            b.draw(app);       
        for(Enemy e : enemies)
            e.draw(app);
        player.draw(app);
        
    }
    /** Gets the tile array of the map
    * @return tile array of the map
    */
    public Tile[][] getMap() {
        return map;
    }    
    /** Constructs the map from the level file
    * @param path the path to the level file
    */
    public void constructMap(String path) {
        try {
            File lvl_file = new File(path);
            Scanner scanobj = new Scanner(lvl_file);
            for(int i = 0; i < 13; i++) {
                String line = scanobj.nextLine();
                for(int j = 0; j < 15; j++) {
                    if("W".equals(String.valueOf(line.charAt(j))))
                        map[i][j] = new SolidWall(Wall_s[0], 32*j, 32*i+64);
                    else if("B".equals(String.valueOf(line.charAt(j))))
                        map[i][j] = new BrokenWall(Wall_s[1], 32*j, 32*i+64);
                    else if("G".equals(String.valueOf(line.charAt(j))))
                        map[i][j] = new GoalTile(Wall_s[3], 32*j, 32*i+64);
                    else
                        map[i][j] = new EmptyTile(Wall_s[2], 32*j, 32*i+64);
                }
            }
        }
        catch(FileNotFoundException e) {
        }
    }
    /** Loads the map from the level file
    * @param path the path to the level file
    * @param lives the number of lives that BombGuy should begin with
    * @param time the amount of time that the map should have
    */
    public void loadObjects(String path, int lives, int time) {
        this.time = time;
        try {
            File file = new File(path);
            Scanner scanobj = new Scanner(file);
            for(int i = 0; i < 13; i++) {
                String line = scanobj.nextLine();
                for(int j = 0; j < 15; j++) {
                    if("P".equals(String.valueOf(line.charAt(j))))
                        player = new BombGuy(32*j, 32*i+64-16, i, j, BombGuy_s, lives, this);
                    else if("R".equals(String.valueOf(line.charAt(j))))
                        enemies.add(new Red(32*j, 32*i+64-16, i, j, Red_s, this));
                    else if("Y".equals(String.valueOf(line.charAt(j))))
                        enemies.add(new Yellow(32*j, 32*i+64-16, i, j, Yellow_s, this));
                }
            }
        }
        catch(FileNotFoundException e) {
        }
    }
    /** Resets the level, all dead enemies respawn and all objects move back to their original positions.
    The number of lives remaining and the timer is not reset.
     */
    public void resetLevel() {
        bombs.clear();
        constructMap(path);
        for(Enemy e : enemies_dead) {
            enemies.add(e);
        }
        enemies_dead.clear();
        for(Enemy e : enemies) {
            e.reset();
        }
        player.reset();
    }
}