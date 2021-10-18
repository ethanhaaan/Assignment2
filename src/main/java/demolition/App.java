package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;
import java.util.List;

public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;

    public static PImage SolidWall_s;
    public static PImage BrokenWall_s;
    public static PImage EmptyTile_s;
    public static PImage GoalTile_s;
    public static PImage[][] BombGuy_s;
    public static PImage[][] Red_s;
    public static PImage[][] Yellow_s;

    public static String path = "level1.txt";
    public static int[] start_pos;
    public JSONArray levels;

    private Map map;
    private BombGuy player;
    private List<Enemy> enemies;
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
        
        //Loading bomb guy sprites
        BombGuy_s = Img.loadBombGuy(this);
        player = GameObject.load_player(path, BombGuy_s, lives, map);

        //Loading enemies
        Red_s = Img.loadRed(this);
        Yellow_s = Img.loadYellow(this);
        enemies = GameObject.load_enemies(path, Red_s, Yellow_s, map);
        
    }

    public void draw() {
        player.tick();
        for(Enemy e : enemies) {
            e.tick();
        }
        map.draw(this);
        player.draw(this);
        for(Enemy e : enemies) {
            e.draw(this);
        }
        
    }

    public void keyPressed() {
        if(released) {
            if(keyCode == 38) {
                player.move(Direction.UP);
                System.out.println("registered UP");
            }
            else if(keyCode == 40) {
                player.move(Direction.DOWN);
                System.out.println("registered DOWN");
            }
            else if(keyCode == 37) {
                player.move(Direction.LEFT);
                System.out.println("registered LEFT");
            }
            else if(keyCode == 39) {
                player.move(Direction.RIGHT);
                System.out.println("registered RIGHT");
            }
            if(player.checkWin()) {
                //
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
