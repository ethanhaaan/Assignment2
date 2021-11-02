package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

public class EnemyTest {

    private PImage[] Wall_s = new PImage[4];
    private PImage[] UI_s;
    private PImage[][] Bomb_s;
    private PImage[][] BombGuy_s;
    private PImage[][] Red_s = new PImage[4][4];
    private PImage[][] Yellow_s;

    @Test
    public void testEnemyLoad() {
        Map map = new Map(Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        map.constructMap("level1.txt");
        map.loadObjects("level1.txt", 3, 180);
        assertNotNull(map.getEnemies());
        assertNotNull(map.getEnemies().get(0));

    }

    @Test 
    public void testEnemyConstructor1() {
        Map map = new Map(Wall_s, UI_s, Bomb_s, BombGuy_s, Red_s, Yellow_s);
        map.constructMap("level1.txt");
        Red enemy = new Red(0, 0, 0, 0, Red_s, map);
        assertNotNull(enemy);
        enemy.tick();
    }

}