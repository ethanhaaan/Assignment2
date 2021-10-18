package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PImage;
import processing.core.PApplet;

public class GameObjectTest{

    @Test
    public void NullCheck() {

        BombGuy test_player = new BombGuy(60, 10, 1, 1, null, 3, null);
        assertNotNull(test_player);
    }

    @Test
    public void NullCheck2() {
        BombGuy test_player = GameObject.load_player("level1.txt", null, 3, null);
        assertNotNull(test_player);
    }

    @Test
    public void tickTest1() {
        //PImage[][] sprites = Img.loadBombGuy();
        BombGuy test_player = GameObject.load_player("level1.txt", null, 3 , null);

    }

    @Test
    public void correct_IandJ_pos_check() {
        BombGuy test_player = GameObject.load_player("level1.txt", null, 3, null);
        assertEquals(test_player.getI(), 1);
        assertEquals(test_player.getJ(), 1);
    }
    
    @Test
    public void checkMovement1() {
        Map map = new Map();
        map.constructMap("level1.txt");
        BombGuy test_player = GameObject.load_player("level1.txt", null, 3, map);
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