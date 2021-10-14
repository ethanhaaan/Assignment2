package demolition;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;

    public static final int FPS = 60;

    public static PImage SolidWall_s;
    public static PImage BrokenWall_s;
    public static PImage EmptyTile_s;
    public static PImage GoalTile_s;

    public App() {
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);
        SolidWall_s = this.loadImage("bin/main/wall/solid.png");
        BrokenWall_s = this.loadImage("bin/main/broken/broken.png");
        EmptyTile_s = this.loadImage("bin/main/empty/empty.png");
        GoalTile_s = this.loadImage("bin/main/goal/goal.png");
        // Load images during setup

    }

    public void draw() {
        //background(0, 0, 0);
    }

    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
