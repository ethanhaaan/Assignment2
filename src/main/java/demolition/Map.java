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
    private List<Bomb> bombs;
    private BombGuy player;
    private int timer;
    private int fps_timer;

    public Map() {
        this.map = new Tile[13][15];
        this.enemies = new ArrayList<Enemy>();
        this.bombs = new ArrayList<Bomb>();
        this.fps_timer = 60;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public BombGuy getPlayer() {
        return player;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }
    public void addBomb(Bomb bomb) {
        //check if bomb exists in position
        boolean existing_bomb = false;
        //INCOMPLETE
        bombs.add(bomb);
    }

    public void tick() {
        if(fps_timer == 0) {
            timer--;
            fps_timer = 60;
        }
        else {
            fps_timer--;
        }
    }

    public int getTimer() {
        return timer;
    }

    public void draw(PApplet app) {
        for(Tile[] i : map) {
            for(Tile j : i) {
                j.draw(app);
            }
        }
    }

    public Tile[][] getMap() {
        return map;
    }    

    public void constructMap(String path, int time) {
        this.timer = time;
        try {
            File lvl_file = new File(path);
            Scanner scanobj = new Scanner(lvl_file);
            for(int i = 0; i < 13; i++) {
                String line = scanobj.nextLine();
                for(int j = 0; j < 15; j++) {
                    if("W".equals(String.valueOf(line.charAt(j))))
                        map[i][j] = new SolidWall(32*j, 32*i+64);
                    else if("B".equals(String.valueOf(line.charAt(j))))
                        map[i][j] = new BrokenWall(32*j, 32*i+64);
                    else if("G".equals(String.valueOf(line.charAt(j))))
                        map[i][j] = new GoalTile(32*j, 32*i+64);
                    else
                        map[i][j] = new EmptyTile(32*j, 32*i+64);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Failed");
        }
    }

    public void loadObjects(String path, int lives, PImage[][] sprites, PImage[][] Red_s, PImage[][] Yellow_s) {
        try {
            File file = new File(path);
            Scanner scanobj = new Scanner(file);
            for(int i = 0; i < 13; i++) {
                String line = scanobj.nextLine();
                for(int j = 0; j < 15; j++) {
                    if("P".equals(String.valueOf(line.charAt(j))))
                        player = new BombGuy(32*j, 32*i+64-16, i, j, sprites, lives, this);
                    else if("R".equals(String.valueOf(line.charAt(j))))
                        enemies.add(new Red(32*j, 32*i+64-16, i, j, Red_s, this));
                    else if("Y".equals(String.valueOf(line.charAt(j))))
                        enemies.add(new Yellow(32*j, 32*i+64-16, i, j, Yellow_s, this));
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("not working");
        }
    }
}