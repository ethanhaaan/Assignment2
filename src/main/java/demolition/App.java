package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;
import java.util.List;
import processing.core.PFont;

public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;

    private PImage[] Wall_s;
    private PImage[] UI_s;
    private PImage[][] Bomb_s;
    private PImage[][] BombGuy_s;
    private PImage[][] Red_s;
    private PImage[][] Yellow_s;

    public static String path;
    public JSONArray levels;
    public PFont font;
    private String config_path;
    
    private UI ui;
    private Map map;
    private int lives;
    private int time;
    private int level;
    private boolean released;
    private boolean bombKeyReleased;
    private boolean win;

    /**Constructor for App object */
    public App() {
        level = 0;
        released = true;
        win = false;
        config_path = "src/test/resources/config.json";
    }

    /**establishes the size of the PApplet window to be WIDTH (480) and HEIGHT (480) */
    public void settings() {
        size(WIDTH, HEIGHT); 
    }

    /**Called to setup the applet, loads and stores all sprites, loads config file, creates new {@link Map} and new {@link UI} */
    public void setup() {
        frameRate(FPS);

        //Font
        font = createFont("bin/main/PressStart2P-Regular.ttf", 20);
        textFont(font);

        //Loading sprites
        Wall_s = Img.loadWall(this);
        Red_s = Img.loadRed(this);
        Yellow_s = Img.loadYellow(this);
        BombGuy_s = Img.loadBombGuy(this);
        Bomb_s = Img.loadBomb(this);
        UI_s = Img.loadUI(this);

        //Loading configuration
        JSONObject config = loadJSONObject(config_path);
        levels = config.getJSONArray("levels");
        path = levels.getJSONObject(0).getString("path");
        lives = config.getInt("lives");
        time = levels.getJSONObject(level).getInt("time");

        //Loading map
        map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);        
        ui = new UI(UI_s);
        
    }
    /**Called upon once every frame, ticks every object in the map then draws all objects in the game.
    If player has won (reached goal tile of final level) then the win screen is constantly drawn
    If player has lost (lives are at 0), then the lose screen is constantly drawn
    Otherwise, all objects are drawn as normal including UI. */
    public void draw() {
        if(win) {
            ui.drawWin(this);
            return;
        }
        map.tick();
        map.draw(this);
        if(map.getPlayer().getLives() <= 0) {
            ui.drawLose(this);
            return;
        }
        ui.draw(this, map.getPlayer().getLives(), map.getTime());
    }
    /**Called when a key is pressed and previous key released, moves player in direction of arrow key press, or places a bomb at players position if spacebar pressed */
    public void keyPressed() {

        if(keyCode == 32) {
                map.addBomb();
                bombKeyReleased = false;
                released = true;
            }
        else if(released) {
            if(keyCode == 38)
                map.getPlayer().move(Direction.UP);
            else if(keyCode == 40)
                map.getPlayer().move(Direction.DOWN);
            else if(keyCode == 37)
                map.getPlayer().move(Direction.LEFT);
            else if(keyCode == 39)
                map.getPlayer().move(Direction.RIGHT);
            if(map.getPlayer().checkWin()) {
                if(level == levels.size()-1) {
                    win = true;
                    return;
                }
                lives = map.getPlayer().getLives();
                path = levels.getJSONObject(++level).getString("path");
                map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
                map.constructMap(path);
                map.loadObjects(path, lives, levels.getJSONObject(level).getInt("time"));
            }
            released = false;
        }
    }
    /**Sets released to true, indicating previous key has been released so a {@link #keyPressed()} will cause the player to move */
	public void keyReleased() {
		released = true;
        bombKeyReleased = true;
	} 
    /**Changes the path to the config file 
    * @param config_path path of the config file */
    public void setConfig(String config_path) {
        this.config_path = config_path;
    }
    /**Main method of game 
    * @param args Command line arguments (not used) */
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
    //Methods purely for testing
    public Map getMap() {return map;}
    public void newMap(String path) {map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);}
    public int getLevel() {return level;}
    public void setWin(boolean b) {win = b;}
    public void setLevel(int level) {this.level = level;}
}
