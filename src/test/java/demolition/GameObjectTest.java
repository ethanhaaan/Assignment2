package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PImage;
import processing.core.PApplet;

public class GameObjectTest{

    @Test
    public void NullCheck() {

        BombGuy test_player = new BombGuy(60, 10, null);
        assertNotNull(test_player);
    }

    @Test
    public void NullCheck2() {
        BombGuy test_player = GameObject.load_player("level1.txt", null);
        assertNotNull(test_player);
    }

    @Test
    public void tickTest1() {
        //PImage[][] sprites = Img.loadBombGuy();
        BombGuy test_player = GameObject.load_player("level1.txt", null);

    }

}