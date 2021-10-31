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

    public static PImage[] Wall_s;
    public static PImage[] UI_s;
    public static PImage[][] Bomb_s;
    public static PImage[][] BombGuy_s;
    public static PImage[][] Red_s;
    public static PImage[][] Yellow_s;

    public static String path = "level1.txt";
    public JSONArray levels;
    public PFont font;
    
    private UI ui;
    private Map map;
    private int timer;
    private int lives;
    private int level;
    private boolean released;
    private boolean bombKeyReleased;
    private boolean win;

    public App() {
        map = new Map();
        level = 0;
        released = true;
        win = false;
    }

    public void settings() {
        size(WIDTH, HEIGHT); 
    }

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
        JSONObject config = loadJSONObject("config.json");
        levels = config.getJSONArray("levels");
        lives = config.getInt("lives");
        map.constructMap(path);
        map.loadObjects(path, lives, levels.getJSONObject(level).getInt("time"), BombGuy_s, Red_s, Yellow_s);
        ui = new UI(UI_s);
        
    }

    public void draw() {
        if(win) {
            ui.drawWin(this);
            return;
        }
        map.tick();
        map.getPlayer().tick();
        for(Enemy e : map.getEnemies())
            e.tick();
        for(Bomb b : map.getBombs()) 
            b.tick();
        map.draw(this);
        map.getPlayer().draw(this);
        for(Enemy e : map.getEnemies())
            e.draw(this);
        for(Bomb b : map.getBombs())
            b.draw(this);
        if(map.getPlayer().getLives() <= 0) {
            ui.drawLose(this);
            return;
        }
        ui.draw(this, map.getPlayer().getLives(), map.getTimer());
    }

    public void keyPressed() {

        if(keyCode == 32) {
                BombGuy b = map.getPlayer();
                map.addBomb(new Bomb(b.getX(), b.getY()+16,b.getI(), b.getJ(), Bomb_s, map));
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
                map = new Map();
                map.constructMap(path);
                map.loadObjects(path, lives, levels.getJSONObject(level).getInt("time"), BombGuy_s, Red_s, Yellow_s);
            }
            released = false;
        }
    }

	public void keyReleased() {
		released = true;
        bombKeyReleased = true;
	} 

    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
