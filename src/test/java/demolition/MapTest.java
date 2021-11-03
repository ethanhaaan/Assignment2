package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.List;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class MapTest {

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

    public static void createManualFile1() {
        try {
            File file = new File("testcasemanuallvl101.txt");
            file.createNewFile();
            PrintWriter writeobj = new PrintWriter(file);
            writeobj.println("WWWWWWWWWWWWWWW");
            writeobj.println("WP    BBB BBBBW");
            writeobj.println("W W W W W W W W");
            writeobj.println("W         B B W");
            writeobj.println("WBW W W W WBW W");
            writeobj.println("W       R  B  W");
            writeobj.println("W W W W W W W W");
            writeobj.println("WB   B   B    W");
            writeobj.println("WBW W W WBW W W");
            writeobj.println("W    YBB   B BW");
            writeobj.println("W WBW W W W W W");
            writeobj.println("W       B    GW");
            writeobj.print("WWWWWWWWWWWWWWW");
            writeobj.close();
        }
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }
    }

    public static void createManualFile2() {
        try {
            File file = new File("testcasemanuallvlBOMBTEST.txt");
            file.createNewFile();
            PrintWriter writeobj = new PrintWriter(file);
            writeobj.println("WWWWWWWWWWWWWWW");
            writeobj.println("WBBBBBBBBBBBBBW");
            writeobj.println("WBBBBBBBBBBBBBW");
            writeobj.println("WBBBBBBBBBBBBBW");
            writeobj.println("WBBBBBBBBBBBBBW");
            writeobj.println("WBBBBBB BBBBBBW");
            writeobj.println("WBBBBBBBBBBBBBW");
            writeobj.println("WBBBBBBBBBBBBBW");
            writeobj.println("WBBBBBBBBBBBBBW");
            writeobj.println("WBBBBBBBBBBBBBW");
            writeobj.println("WBBBBBBBBBBBBBW");
            writeobj.println("WBBBBBBBBBBBBBW");
            writeobj.print("WWWWWWWWWWWWWWW");
            writeobj.close();
        }
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }
    }

    @Test
    public void constructMap1() {
        Map maptest = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        assertNotNull(maptest);
        assertNotNull(maptest);
        assertEquals(maptest.getMap()[1][2].getX(), 64);
        assertEquals(maptest.getMap()[1][2].getY(), 32+64);
        assertEquals(maptest.getMap()[4][4].getX(), 128); 
    }
    @Test
    public void constructMap2() {
        Map maptest = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        for(Tile[] i : maptest.getMap()) {
            for(Tile j : i) {
                assertNotNull(j);
            }
        }
    }
    @Test
    public void constructMapInvalid() {
        //Map should be created, but it will have no tiles
        Map maptest = new Map("non_existent_path1010.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        assertNotNull(maptest);
        assertNull(maptest.getMap()[0][0]);
    }
    @Test
    public void loadObjects1() {
        Map maptest = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        List<Enemy> enemies = maptest.getEnemies();
        BombGuy player = maptest.getPlayer();
        assertNotNull(enemies);
        assertNotNull(player);
    }
    @Test
    public void loadObjectsInvalid() {
        Map maptest = new Map("non_existent_path_10293", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        maptest.constructMap("level1.txt");
        List<Enemy> enemies = maptest.getEnemies();
        BombGuy player = maptest.getPlayer();
        assertEquals(enemies.size(), 0);
        assertNull(player); 
    }
    @Test
    public void getBombTest() {
        Map maptest = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        maptest.loadObjects("level1.txt", 3, 180);
        BombGuy player = maptest.getPlayer();
        maptest.addBomb();
        assertNotNull(maptest.getBombs());
        assertNotNull(maptest.getBombs().get(0));
    }
    @Test
    public void tickMap1() {
        Map maptest = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        maptest.loadObjects("level1.txt", 3, 180);
        //After ticking 60 frames, the timer should have elapsed 1 second
        for(int i = 0; i < 60; i++) {
            maptest.tick();
        }
        assertEquals(maptest.getTime(), 179);
    }
    @Test
    public void tickMap2() {
        Map maptest = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        //After ticking 240 frames, the timer should have elapsed 4 seconds
        for(int i = 0; i < 240; i++) {
            maptest.tick();
        }
        assertEquals(maptest.getTime(), 176);
    }
    @Test
    public void tickMap3() {
        Map maptest = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        //After ticking exactly 10800 frames, the timer should have elapsed 180 seconds
        //Player should instantaneously lose all lives, and game must have ended.
        for(int i = 0; i < 60*180; i++) {
            maptest.tick();
        }
        assertEquals(maptest.getTime(), 0);
        assertEquals(maptest.getPlayer().getLives(), 0);
    }
    @Test
    public void resetLevel1() {
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        //Allowing the enemies to move freely
        for(int i = 0; i < 60; i++) {
            map.tick();
            map.getPlayer().tick();
            for(Enemy e : map.getEnemies())
                e.tick();
            for(Bomb b : map.getBombs()) 
                b.tick();
        }
        //After 60 ticks (60 frames = 1 second)
        //all enemies must definitely have moved ONE space away from original position
        //Thus, original position != current position
        for(Enemy e : map.getEnemies()) {
            assertFalse(e.getOriginalI() == e.getI() && e.getOriginalJ() == e.getJ());
        }

        //Resetting the level
        map.resetLevel();

        //Now all GameObjects positions should be the same as their original positions
        for(Enemy e : map.getEnemies()) {
            assertTrue(e.getOriginalI() == e.getI() && e.getOriginalJ() == e.getJ());
        }
    }
    
}

