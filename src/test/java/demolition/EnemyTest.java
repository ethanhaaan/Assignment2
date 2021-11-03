package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.io.File;

public class EnemyTest {

    private PImage[] Wall_s = new PImage[4];
    private PImage[] UI_s;
    private PImage[][] Bomb_s = new PImage[4][4];
    private PImage[][] BombGuy_s = new PImage[4][4];
    private PImage[][] Red_s = new PImage[4][4];
    private PImage[][] Yellow_s = new PImage[4][4];
    private JSONObject config = PApplet.loadJSONObject(new File("config.json"));
    private JSONArray levels = config.getJSONArray("levels");
    private String path = levels.getJSONObject(0).getString("path");
    private int lives = 3;
    private int time = 180;

    @Test
    public void testEnemyLoad() {
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        map.constructMap("level1.txt");
        map.loadObjects("level1.txt", 3, 180);
        assertNotNull(map.getEnemies());
        assertNotNull(map.getEnemies().get(0));

    }

    @Test 
    public void testEnemyConstructor1() {
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        map.constructMap("level1.txt");
        Red enemy = new Red(0, 0, 0, 0, Red_s, map);
        assertNotNull(enemy);
        enemy.tick();
    }

}