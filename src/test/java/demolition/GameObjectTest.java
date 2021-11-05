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
    private JSONObject config = PApplet.loadJSONObject(new File("src/test/resources/config.json"));
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
        Map map = new Map("src/test/resources/testcasemanuallvl101.txt", 3, 180, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        assertEquals(map.getPlayer().getI(), map.getPlayer().getOriginalI()); 
        assertEquals(map.getPlayer().getJ(), map.getPlayer().getOriginalJ()); 
        assertEquals(map.getPlayer().getX(), map.getPlayer().getOriginalX()); 
        assertEquals(map.getPlayer().getY(), map.getPlayer().getOriginalY()); 
    }

    @Test
    public void correct_IandJ_pos_check() {
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        map.constructMap("src/test/resources/level1.txt");
        map.loadObjects("level1.txt", 3, 180);
        BombGuy test_player = map.getPlayer();
        assertEquals(test_player.getI(), 1);
        assertEquals(test_player.getJ(), 1);
    }
    
    @Test
    public void checkMovement1() {
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        map.constructMap("src/test/resources/level1.txt");
        map.loadObjects("src/test/resources/level1.txt", 3, 180);
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
    @Test
    public void checkMovement2() {
        MapTest.createManualFile1();
        Map map = new Map("src/test/resources/testcasemanuallvl101.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        //Walk RIGHT into a wall
        for(int i = 0; i < 10; i++) {
            map.getPlayer().move(Direction.RIGHT);
        }
        assertEquals(map.getPlayer().getI(), 1);
        assertEquals(map.getPlayer().getJ(), 5);
        //Walk DOWN into a wall
        for(int i = 0; i < 10; i++) {
            map.getPlayer().move(Direction.DOWN);
        }
        assertEquals(map.getPlayer().getI(), 6);
        assertEquals(map.getPlayer().getJ(), 5);
        //Walk LEFT into a wall
        for(int i = 0; i < 10; i++) {
            map.getPlayer().move(Direction.LEFT);
        }
        assertEquals(map.getPlayer().getI(), 6);
        assertEquals(map.getPlayer().getJ(), 5);
        //Walk RIGHT into a wall
        for(int i = 0; i < 10; i++) {
            map.getPlayer().move(Direction.RIGHT);
        }
        assertEquals(map.getPlayer().getI(), 6);
        assertEquals(map.getPlayer().getJ(), 5);
    }
    @Test
    public void checkWinTest() {
        //This tests for winning by resetting the level and testing if the player can win by moving left, right, up and down into a goal tile
        MapTest.createManualFile3();
        Map map = new Map("src/test/resources/testcasemanuallvlBOMBALLGOALS.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        //Default position checkWin should return false
        assertFalse(map.getPlayer().checkWin());
        //Walk into a goal tile to the right
        map.getPlayer().move(Direction.RIGHT);
        assertTrue(map.getPlayer().checkWin());

        //Completely resetting game
        map = new Map("src/test/resources/testcasemanuallvlBOMBALLGOALS.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        //Default position checkWin should return false
        assertFalse(map.getPlayer().checkWin());
        //Walk into a goal tile to the right
        map.getPlayer().move(Direction.LEFT);
        assertTrue(map.getPlayer().checkWin());

        //Completely resetting game
        map = new Map("src/test/resources/testcasemanuallvlBOMBALLGOALS.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        //Default position checkWin should return false
        assertFalse(map.getPlayer().checkWin());
        //Walk into a goal tile to the right
        map.getPlayer().move(Direction.UP);
        assertTrue(map.getPlayer().checkWin());

        //Completely resetting game
        map = new Map("src/test/resources/testcasemanuallvlBOMBALLGOALS.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        //Default position checkWin should return false
        assertFalse(map.getPlayer().checkWin());
        //Walk into a goal tile to the right
        map.getPlayer().move(Direction.DOWN);
        assertTrue(map.getPlayer().checkWin());
    }
    @Test
    public void checkKillTest() {
        MapTest.createManualFile4();
        Map map = new Map("src/test/resources/testcasemanuallvlENEMIES.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        //Default position checkKill should do nothing and lives should remain the same
        map.getPlayer().checkKill();
        assertEquals(map.getPlayer().getLives(), lives);
        //Moving the player directly in front of enemy
        for(int i = 0; i < 3; i++) {
            map.getPlayer().move(Direction.RIGHT);
        }
        for(int i = 0; i < 2; i++) {
            map.getPlayer().move(Direction.DOWN);
        }
        map.getPlayer().move(Direction.RIGHT);

        //Allowing the map to tick 2 seconds (120 frames)
        for(int i = 0; i < 120; i++) {
            map.tick();
        }
        assertEquals(map.getPlayer().getLives(), lives-1);

    }


}