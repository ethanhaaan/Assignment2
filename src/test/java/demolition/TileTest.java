package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    @Test
    public void constructor() {
        assertNotNull(new GoalTile(null, 20, 40));
    }

    @Test
    public void test1() {
        Tile wall = new SolidWall(null, 50, 10);
        assertEquals(wall.getX(), 50);
        assertEquals(wall.getY(), 10);
    }
    @Test
    public void test2() {
        Tile empty = new EmptyTile(null, 10, 10);
        assertEquals(empty.getType(), TileType.EMPTY);
    }

    @Test
    public void test3() {
        Tile empty = new BrokenWall(null, 10, 10);
        assertEquals(empty.getType(), TileType.BROKEN);
    }

}