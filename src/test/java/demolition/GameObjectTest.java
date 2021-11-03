package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.io.File;

public class GameObjectTest{

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
    public void NullCheck1() {
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        assertNotNull(map.getPlayer());
    }

    @Test
    public void tickTest1() {
        //PImage[][] sprites = Img.loadBombGuy();
        //BombGuy test_player = GameObject.load_player("level1.txt", null, 3 , null);

    }
    @Test
    public void getOriginalTest() {
        MapTest.createManualFile1();
        Map map = new Map("testcasemanuallvl101.txt", 3, 180, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        assertEquals(map.getPlayer().getI(), map.getPlayer().getOriginalI()); 
        assertEquals(map.getPlayer().getJ(), map.getPlayer().getOriginalJ()); 
        assertEquals(map.getPlayer().getX(), map.getPlayer().getOriginalX()); 
        assertEquals(map.getPlayer().getY(), map.getPlayer().getOriginalY()); 
    }

    @Test
    public void correct_IandJ_pos_check() {
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        map.constructMap("level1.txt");
        map.loadObjects("level1.txt", 3, 180);
        BombGuy test_player = map.getPlayer();
        assertEquals(test_player.getI(), 1);
        assertEquals(test_player.getJ(), 1);
    }
    
    @Test
    public void checkMovement1() {
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        map.constructMap("level1.txt");
        map.loadObjects("level1.txt", 3, 180);
        BombGuy test_player = map.getPlayer();
        test_player.move(Direction.RIGHT);
        test_player.move(Direction.RIGHT);
        test_player.move(Direction.RIGHT);
        test_player.move(Direction.RIGHT);
        test_player.move(Direction.DOWN);
        test_player.move(Direction.DOWN);
        test_player.move(Direction.DOWN);
        test_player.move(Direction.UP);
        test_player.move(Direction.LEFT);
        assertEquals(test_player.getI(), 3);
        assertEquals(test_player.getJ(), 4);
    }

}