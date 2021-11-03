package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

public class ImgTest {

    @Test 
    public void basicTest() {
        App app = new App();
        app.noLoop();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        PImage[] wall_sprite = Img.loadWall(app);
        assertNotNull(wall_sprite);
        assertNotNull(wall_sprite[1]);
    }
    
}