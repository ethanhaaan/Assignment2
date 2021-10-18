package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

public class EnemyTest {

    @Test
    public void testEnemyLoad() {
        Map map = new Map();
        map.constructMap("level1.txt");
        List<Enemy> enemies = GameObject.load_enemies("level1.txt", null, null, map);
        assertNotNull(enemies);
        assertNotNull(enemies.get(0));

    }

}