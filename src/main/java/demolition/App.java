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

    public static PImage SolidWall_s;
    public static PImage BrokenWall_s;
    public static PImage EmptyTile_s;
    public static PImage GoalTile_s;
    public static PImage[] UI_s;
    public static PImage[][] BombGuy_s;
    public static PImage[][] Red_s;
    public static PImage[][] Yellow_s;

    public static String path = "level1.txt";
    public static int[] start_pos;
    public JSONArray levels;
    public PFont font;
    
    private UI ui;
    private Map map;
    private int timer;
    private int lives;
    private int level;
    private boolean released;

    public App() {
        map = new Map();
        level = 0;
        released = true;
    }

    public void settings() {
        size(WIDTH, HEIGHT);
        
    }

    public void setup() {
        frameRate(FPS);

        //Font
        font = createFont("bin/main/PressStart2P-Regular.ttf", 20);
        textFont(font);

        //Loading wall sprites
        SolidWall_s = this.loadImage("bin/main/wall/solid.png");
        BrokenWall_s = this.loadImage("bin/main/broken/broken.png");
        EmptyTile_s = this.loadImage("bin/main/empty/empty.png");
        GoalTile_s = this.loadImage("bin/main/goal/goal.png");

        //Loading configuration
        JSONObject config = loadJSONObject("config.json");
        levels = config.getJSONArray("levels");
        lives = config.getInt("lives");
        map.constructMap(path);

        //Loading character sprites
        Red_s = Img.loadRed(this);
        Yellow_s = Img.loadYellow(this);
        BombGuy_s = Img.loadBombGuy(this);

        map.loadObjects(path, lives, BombGuy_s, Red_s, Yellow_s);

        //UI
        UI_s = Img.loadUI(this);
        ui = new UI(UI_s);
        
    }

    public void draw() {
        map.getPlayer().tick();
        for(Enemy e : map.getEnemies()) {
            e.tick();
        }
        map.draw(this);
        map.getPlayer().draw(this);
        for(Enemy e : map.getEnemies()) {
            e.draw(this);
        }
        ui.draw(this, map.getPlayer().getLives());
    }

    public void keyPressed() {
        if(released) {
            if(keyCode == 38) {
                map.getPlayer().move(Direction.UP);
                System.out.println("registered UP");
            }
            else if(keyCode == 40) {
                map.getPlayer().move(Direction.DOWN);
                System.out.println("registered DOWN");
            }
            else if(keyCode == 37) {
                map.getPlayer().move(Direction.LEFT);
                System.out.println("registered LEFT");
            }
            else if(keyCode == 39) {
                map.getPlayer().move(Direction.RIGHT);
                System.out.println("registered RIGHT");
            }
            if(map.getPlayer().checkWin()) {
                lives = map.getPlayer().getLives();
                path = levels.getJSONObject(++level).getString("path");
                map = new Map();
                
                map.constructMap(path);
                map.loadObjects(path, lives, BombGuy_s, Red_s, Yellow_s);
            }

            released = false;
        }
    }

	public void keyReleased() {
		released = true;
	} 

    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
