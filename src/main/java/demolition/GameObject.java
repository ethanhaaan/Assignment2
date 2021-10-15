package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected PImage[][] sprites;
    protected PImage current_sprite;

    public GameObject(int x, int y, PImage[][] sprites) {
        this.x = x;
        this.y = y;
        this.sprites = sprites;
        this.current_sprite = sprites[3][1];
    }

    public void tick() {

    }

    public void draw(PApplet app) {
        app.image(current_sprite, x, y);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void moveLeft() {
        x -= 32;
    }
    public void moveRight() {
        x += 32;
    }
    public void moveUp() {
        y -= 32;
    }
    public void moveDown() {
        y += 32;
    }
    public PImage[] getLeftSprites() {
        return sprites[0];
    }
    public PImage[] getRightSprites() {
        return sprites[1];
    }
        public PImage[] getUpSprites() {
        return sprites[2];
    }
    public PImage[] getDownSprites() {
        return sprites[3];
    }

    public static List<GameObject> load_enemies(String path) {
        List<GameObject> g_objs = new ArrayList<GameObject>();
        try {
            File file = new File(path);
            Scanner scanobj = new Scanner(file);
            for(int i = 0; i < 13; i++) {
                String line = scanobj.nextLine();
                for(int j = 0; j < 15; j++) {
                    if("Y".equals(String.valueOf(line.charAt(j))))
                        System.out.println("Do nothing");
                }
            }
            return g_objs;
        }
        catch(FileNotFoundException e) {

        }
        return g_objs;
    }
    public static BombGuy load_player(String path) {
        try {
            File file = new File(path);
            Scanner scanobj = new Scanner(file);
            for(int i = 0; i < 13; i++) {
                String line = scanobj.nextLine();
                for(int j = 0; j < 15; j++) {
                    if("P".equals(String.valueOf(line.charAt(j))))
                        return new BombGuy(32*j, 32*i+64);
                }
            }
            return null;
        }
        catch(FileNotFoundException e) {
            System.out.println("nothing");
            return null;
        }
    }
}