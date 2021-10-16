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
    public int lives;
    private boolean released;

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
        BombGuy_s = Img.loadBombGuy(this);
        player = GameObject.load_player(path, BombGuy_s);
        
        //Loading configuration
        JSONObject config = loadJSONObject("config.json");
        released = true;
        levels = config.getJSONArray("levels");
        lives = config.getInt("lives");
        map.constructMap(path);

    }

    public void draw() {
        player.tick();
        map.draw(this);
        player.draw(this);
        
    }

    public void keyPressed() {
        if(released) {
            if(keyCode == 38) {
                player.move(direction.UP);
                System.out.println("registered UP");
            }
            else if(keyCode == 40) {
                player.move(direction.DOWN);
                System.out.println("registered DOWN");
            }
            else if(keyCode == 37) {
                player.move(direction.LEFT);
                System.out.println("registered LEFT");
            }
            else if(keyCode == 39) {
                player.move(direction.RIGHT);
                System.out.println("registered RIGHT");
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
