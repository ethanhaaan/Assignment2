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

    public List<Enemy> getEnemies() {
        return enemies;
    }
    
    public List<Enemy> getEnemiesDead() {
        return enemies_dead;
    }

    public BombGuy getPlayer() {
        return player;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public int getFPSTimer() {
        return fps_timer;
    }

    public void addBomb() {
        bombs.add(new Bomb(player.getX(), player.getY()+16, player.getI(), player.getJ(), Bomb_s, this));
    }

    public void tick() {
        player.tick();
        tickBombs();
        tickTimer();
        tickEnemies();
    }

    public void tickBombs() {
        for(Bomb b : bombs) {
            if(b.tick())
                return;
        }
    }

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

    public void tickEnemies() {
        for(Enemy e : enemies)
            e.tick();
    }

    public int getTime() {
        return time;
    }

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

    public Tile[][] getMap() {
        return map;
    }    

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