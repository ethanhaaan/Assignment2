package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;

public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;

    public static PImage SolidWall_s;
    public static PImage BrokenWall_s;
    public static PImage EmptyTile_s;
    public static PImage GoalTile_s;
    public static PImage[][] BombGuy_s;

    public static String path = "level1.txt";
    public static int[] start_pos;
    public JSONArray levels;

    private Map map;
    private BombGuy player;
    private int timer;
    private int lives;

    public App() {
        map = new Map();
    }

    public void settings() {
        size(WIDTH, HEIGHT);
        
    }

    public void setup() {
        frameRate(FPS);

        //Loading wall sprites
        SolidWall_s = this.loadImage("bin/main/wall/solid.png");
        BrokenWall_s = this.loadImage("bin/main/broken/broken.png");
        EmptyTile_s = this.loadImage("bin/main/empty/empty.png");
        GoalTile_s = this.loadImage("bin/main/goal/goal.png");
        
        //Loading bomb guy sprites
        BombGuy_s = ImgLoad.loadBombGuy(this);
        player = GameObject.load_player(path);
        
        //Loading configuration
        JSONObject config = loadJSONObject("config.json");
        levels = config.getJSONArray("levels");
        lives = config.getInt("lives");
        map.constructMap(path);

    }

    public void draw() {
        map.draw(this);
        player.draw(this);
        
    }

    public static void main(String[] args) {
        PApplet.main("demolition.App");

    }
}
