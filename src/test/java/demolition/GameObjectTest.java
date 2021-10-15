package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameObjectTest {

    @Test
    public void NullCheck() {
        BombGuy test_player = new BombGuy(60, 10);
        assertNotNull(test_player);
    }

    @Test
    public void NullCheck2() {
        BombGuy test_player = GameObject.load_player("level1.txt");
        assertNotNull(test_player);
        //assertNotNull(test_player.getDownSprites());
        //assertNotNull(test_player.getUpSprites());
        //assertNotNull(test_player.getLeftSprites());
        //assertNotNull(test_player.getRightSprites());
    }

}