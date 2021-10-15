package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    @Test
    public void constructMap1() {
        Map maptest = new Map("level1.txt");
        maptest.constructMap();
        assertEquals(maptest.getMap()[1][2].getX(), 64);
        assertEquals(maptest.getMap()[1][2].getY(), 32);
        assertEquals(maptest.getMap()[4][4].getX(), 128);
    }

    @Test
    public void mapNullTest() {
        Map maptest = new Map("level1.txt");
        maptest.constructMap();
        for(Tile[] i : maptest.getMap()) {
            for(Tile j : i) {
                assertNotNull(j);
            }
        }
    }

}

