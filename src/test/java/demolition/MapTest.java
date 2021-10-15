package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    @Test
    public void constructMap1() {
        Map maptest = new Map();
        maptest.constructMap("level1.txt");
        assertEquals(maptest.getMap()[1][2].getX(), 64);
        assertEquals(maptest.getMap()[1][2].getY(), 32+64);
        assertEquals(maptest.getMap()[4][4].getX(), 128);
    }

    @Test
    public void mapNullTest() {
        Map maptest = new Map();
        maptest.constructMap("level1.txt");
        for(Tile[] i : maptest.getMap()) {
            for(Tile j : i) {
                assertNotNull(j);
            }
        }
    }

}

