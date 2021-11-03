package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.List;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.io.File;

public class BombTest {

    private PImage[] Wall_s = new PImage[4];
    private PImage[] UI_s;
    private PImage[][] Bomb_s = new PImage[4][8];
    private PImage[][] BombGuy_s = new PImage[4][4];
    private PImage[][] Red_s = new PImage[4][4];
    private PImage[][] Yellow_s = new PImage[4][4];
    private JSONObject config = PApplet.loadJSONObject(new File("config.json"));
    private JSONArray levels = config.getJSONArray("levels");
    private String path = levels.getJSONObject(0).getString("path");
    private int lives = 3;
    private int time = 180;

    @Test
    public void constructBombTest() {
        MapTest.createManualFile2();
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        Bomb bomb = new Bomb(0, 0, 5, 7, Bomb_s, map);
        assertNotNull(bomb);
    }
    @Test
    public void explodeBombTest() {
        MapTest.createManualFile2();
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        Bomb bomb = new Bomb(0, 0, 5, 7, Bomb_s, map);
        //Tiles before explosion
        assertEquals(map.getMap()[4][6].getType(), TileType.BROKEN);
        assertEquals(map.getMap()[4][8].getType(), TileType.BROKEN);
        assertEquals(map.getMap()[3][7].getType(), TileType.BROKEN);
        assertEquals(map.getMap()[5][7].getType(), TileType.BROKEN);
        bomb.explode();
        assertEquals(map.getMap()[4][6].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[4][8].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[3][7].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[5][7].getType(), TileType.EMPTY);
    }
    @Test
    public void tickBombTest() {
        MapTest.createManualFile2();
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        Bomb bomb = new Bomb(0, 0, 5, 7, Bomb_s, map);
        //After 120 ticks (2 seconds)
        for(int i = 0; i < 120; i++) {
            bomb.tick();
        }

    }

}