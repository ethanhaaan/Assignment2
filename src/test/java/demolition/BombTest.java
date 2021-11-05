package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.List;
import java.util.ArrayList;
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
    private JSONObject config = PApplet.loadJSONObject(new File("src/test/resources/config.json"));
    private JSONArray levels = config.getJSONArray("levels");
    private String path = levels.getJSONObject(0).getString("path");
    private int lives = 3;
    private int time = 180;

    @Test
    public void constructBombTest() {
        Map map = new Map(path, lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        Bomb bomb = new Bomb(0, 0, 5, 7, Bomb_s, map);
        assertNotNull(bomb);
    }
    @Test
    //Testing manually exploding using explode()
    public void explodeBombTest1() {
        MapTest.createManualFile2();
        Map map = new Map("src/test/resources/testcasemanuallvlBOMBTEST.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        Bomb bomb = new Bomb(0, 0, 5, 7, Bomb_s, map);
        //Tiles before explosion
        assertEquals(map.getMap()[5][6].getType(), TileType.BROKEN);
        assertEquals(map.getMap()[5][8].getType(), TileType.BROKEN);
        assertEquals(map.getMap()[4][7].getType(), TileType.BROKEN);
        assertEquals(map.getMap()[6][7].getType(), TileType.BROKEN);
        //Tiles after explosion 
        bomb.explode();
        assertEquals(map.getMap()[5][6].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[5][8].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[4][7].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[6][7].getType(), TileType.EMPTY);
    }
    @Test
    //Testing manually exploding using explode()
    public void explodeBombTest2() {
        MapTest.createManualFile1();
        Map map = new Map("src/test/resources/testcasemanuallvl101.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        Bomb bomb = new Bomb(0, 0, 3, 8, Bomb_s, map);
        //Tiles before explosion
        assertEquals(map.getMap()[3][7].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[3][9].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[3][10].getType(), TileType.BROKEN);
        assertEquals(map.getMap()[2][8].getType(), TileType.SOLID);
        assertEquals(map.getMap()[4][8].getType(), TileType.SOLID);
        //Tiles after explosion 
        bomb.explode();
        assertEquals(map.getMap()[3][7].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[3][9].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[3][10].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[2][8].getType(), TileType.SOLID);
        assertEquals(map.getMap()[4][8].getType(), TileType.SOLID);
    }
    @Test
    //Testing manually exploding using explode()
    public void explodeBombTest3() {
        MapTest.createManualFile1();
        Map map = new Map("src/test/resources/testcasemanuallvl101.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        Bomb bomb = new Bomb(0, 0, 6, 9, Bomb_s, map);
        //Tiles before explosion
        assertEquals(map.getMap()[6][8].getType(), TileType.SOLID);
        assertEquals(map.getMap()[6][10].getType(), TileType.SOLID);
        assertEquals(map.getMap()[5][9].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[7][9].getType(), TileType.BROKEN);
        //Tiles after explosion 
        bomb.explode();
        assertEquals(map.getMap()[6][8].getType(), TileType.SOLID);
        assertEquals(map.getMap()[6][10].getType(), TileType.SOLID);
        assertEquals(map.getMap()[5][9].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[7][9].getType(), TileType.EMPTY);
    }
    @Test
    //Testing manually exploding using explode()
    public void explodeBombTest4() {
        MapTest.createManualFile3();
        Map map = new Map("src/test/resources/testcasemanuallvlBOMBALLGOALS.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        Bomb bomb = new Bomb(0, 0, 5, 7, Bomb_s, map);
        //Tiles before explosion
        assertEquals(map.getMap()[5][6].getType(), TileType.GOAL);
        assertEquals(map.getMap()[5][8].getType(), TileType.GOAL);
        assertEquals(map.getMap()[4][7].getType(), TileType.GOAL);
        assertEquals(map.getMap()[6][7].getType(), TileType.GOAL);
        //Tiles after explosion 
        bomb.explode();
        assertEquals(map.getMap()[5][6].getType(), TileType.GOAL);
        assertEquals(map.getMap()[5][8].getType(), TileType.GOAL);
        assertEquals(map.getMap()[4][7].getType(), TileType.GOAL);
        assertEquals(map.getMap()[6][7].getType(), TileType.GOAL);
    }
    @Test
    //Allowing bomb to naturally tick to explosion
    public void tickBombTest1() {
        MapTest.createManualFile2();
        Map map = new Map("src/test/resources/testcasemanuallvlBOMBTEST.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        Bomb bomb = new Bomb(0, 0, 5, 7, Bomb_s, map);

        //After 120 ticks (2 seconds)
        for(int i = 0; i < 120; i++) {
            bomb.tick();
        }
        //Tiles before explosion - bomb should explode next tick
        assertEquals(map.getMap()[5][6].getType(), TileType.BROKEN);
        assertEquals(map.getMap()[5][8].getType(), TileType.BROKEN);
        assertEquals(map.getMap()[4][7].getType(), TileType.BROKEN);
        assertEquals(map.getMap()[6][7].getType(), TileType.BROKEN);

        bomb.tick();

        //Tiles after explosion 
        assertEquals(map.getMap()[5][6].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[5][8].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[4][7].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[6][7].getType(), TileType.EMPTY);

    }
    @Test
    //Bomb explodes into goal tiles (checking functionality of goal tiles)
    public void tickBombTest2() {
        MapTest.createManualFile2();
        Map map = new Map("src/test/resources/testcasemanuallvlALLGOALSNOPLAYER.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        Bomb bomb = new Bomb(0, 0, 3, 3, Bomb_s, map);
        //After 121 ticks (2 seconds + 1 frame)
        for(int i = 0; i < 121; i++) {
            bomb.tick();
        }
        //All goal tiles surrounding it should still be goal tiles
        assertEquals(map.getMap()[3][2].getType(), TileType.GOAL);
        assertEquals(map.getMap()[3][1].getType(), TileType.GOAL);
        assertEquals(map.getMap()[3][4].getType(), TileType.GOAL);
        assertEquals(map.getMap()[3][5].getType(), TileType.GOAL);
        assertEquals(map.getMap()[2][3].getType(), TileType.GOAL);
        assertEquals(map.getMap()[1][3].getType(), TileType.GOAL);
        assertEquals(map.getMap()[4][3].getType(), TileType.GOAL);
        assertEquals(map.getMap()[5][3].getType(), TileType.GOAL);
    }
    @Test
    //Player places the bomb
    public void naturalPlayBombTest1() {
        MapTest.createManualFile1();
        Map map = new Map("src/test/resources/testcasemanuallvl101.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        map.addBomb();
        map.getPlayer().move(Direction.RIGHT);
        map.getPlayer().move(Direction.RIGHT);
        map.getPlayer().move(Direction.RIGHT);

        //After 180 ticks (3 seconds, bomb should have disappeared)
        for(int i = 0; i < 180; i++) {
            map.tick();
        }

        //Tiles after explosion 
        assertEquals(map.getMap()[1][1].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[2][1].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[3][1].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[1][2].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[1][3].getType(), TileType.EMPTY);
        assertEquals(map.getMap()[1][0].getType(), TileType.SOLID);
        assertEquals(map.getMap()[0][1].getType(), TileType.SOLID);
    }
    @Test
    //Testing caught in explosion
    public void testKill1() {
        MapTest.createManualFile4();
        Map map = new Map("src/test/resources/testcasemanuallvlENEMIES.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        List<Enemy> original_enemies = new ArrayList<Enemy>(map.getEnemies());
        //Player quickly rushes in and places bombs
        for(int i = 0; i < 3; i++) {
            map.getPlayer().move(Direction.RIGHT);
        }
        for(int i = 0; i < 6; i++) {
            map.getPlayer().move(Direction.DOWN);
        }
        for(int i = 0; i < 6; i++) {
            map.addBomb();
            map.getPlayer().move(Direction.UP);
        }
        for(int i = 0; i < 6; i++) {
            map.getPlayer().move(Direction.RIGHT);
        }
        //Tick to explode
        for(int i = 0; i < 180; i++) {
            map.tick();
        }
        //Bomb is placed such that enemies cannot escape it in time.
        //Thus, enemies must certainly be all dead -> transferred to dead enemies array
        assertEquals(original_enemies.size(), map.getEnemiesDead().size());
        assertTrue(original_enemies.containsAll(map.getEnemiesDead()) && map.getEnemiesDead().containsAll(original_enemies));
        assertTrue(map.getEnemies().isEmpty());
    }

    @Test
    //Testing caught in explosion
    public void testKill2() {
        MapTest.createManualFile1();
        Map map = new Map("src/test/resources/testcasemanuallvl101.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        //Player places a bomb at its own location
        map.addBomb();
        //Allowing bomb to tick 3 seconds and explode
        for(int i = 0; i < 180; i++) {
            map.tick();
        }
        assertEquals(map.getPlayer().getLives(), 2);
    }

    @Test
    //Testing if explosion has finished, if player will still die if walks into where explosion previously was
    public void testKill3() {
        MapTest.createManualFile1();
        Map map = new Map("src/test/resources/testcasemanuallvl101.txt", lives, time, Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        //Player places a bomb at its own location
        map.addBomb();
        for(int i = 0; i < 5; i++) {
            map.getPlayer().move(Direction.RIGHT);
        }
        //Allowing bomb to tick 5 seconds and explode, then finish
        for(int i = 0; i < 300; i++) {
            map.tick();
        }
        for(int i = 0; i < 5; i++) {
            map.getPlayer().move(Direction.LEFT);
        }
        //Player must not have lost any lives
        assertEquals(map.getPlayer().getLives(), 3);
    }
}