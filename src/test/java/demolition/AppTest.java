package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test 
    public void AppTest() {
        App app = new App();
        app.noLoop();
        PApplet.runSketch(new String[] {"App"}, app);

//Testing Img class to ensure that all sprites are being loaded and not returning null.

        //loadWall
        PImage[] wall_sprite = Img.loadWall(app);
        assertNotNull(wall_sprite);
        for(PImage img : wall_sprite) {
            assertNotNull(img);
        }

        //loadBomb
        PImage[][] bomb_sprites = Img.loadBomb(app);
        assertNotNull(bomb_sprites);
        for(PImage[] img_array : bomb_sprites) {
            for(PImage img : img_array) {
                assertNotNull(img);
            }
        }

        //loadBombGuy
        PImage[][] bombguy_sprites = Img.loadBombGuy(app);
        assertNotNull(bombguy_sprites);
        for(PImage[] img_array : bombguy_sprites) {
            for(PImage img : img_array) {
                assertNotNull(img);
            }
        }

        //loadRed
        PImage[][] red_sprites = Img.loadRed(app);
        assertNotNull(red_sprites);
        for(PImage[] img_array : red_sprites) {
            for(PImage img : img_array) {
                assertNotNull(img);
            }
        }

        //loadYellow
        PImage[][] yellow_sprites = Img.loadYellow(app);
        assertNotNull(yellow_sprites);
        for(PImage[] img_array : yellow_sprites) {
            for(PImage img : img_array) {
                assertNotNull(img);
            }
        }

        //loadUI
        PImage[] UI_sprites = Img.loadUI(app);
        assertNotNull(UI_sprites);
        for(PImage img : UI_sprites) {
            assertNotNull(img);
        }

//After testing sprites, testing the normal operation of the app
        app.setConfig("src/test/resources/config.json");
        app.setup();
        app.delay(1000);
        
//Testing moving to the right, placing a bomb then moving to the left
        app.keyCode = 39;
        for(int i = 0; i < 10; i++) {
            app.keyPressed();
            app.keyReleased();
        }
        assertEquals(app.getMap().getPlayer().getI(), 1);
        assertEquals(app.getMap().getPlayer().getJ(), 5);
        app.keyCode = 32;
        app.keyPressed();
        app.keyReleased();
        assertEquals(app.getMap().getBombs().size(), 1);
        app.keyCode = 37;
        for(int i = 0; i < 10; i++) {
            app.keyPressed();
            app.keyReleased();
        }
        app.keyCode = 38;
        app.keyPressed();
        app.keyReleased();
        //Player sohuldn't be able to move up into a wall
        //Asserting that the position in as it should be
        assertEquals(app.getMap().getPlayer().getI(), 1);
        assertEquals(app.getMap().getPlayer().getJ(), 1);
        for(int i = 0; i < 260; i++) {
            app.draw();
        }
        assertEquals(app.getMap().getMap()[1][6].getType(), TileType.EMPTY);
        assertEquals(app.getMap().getMap()[0][5].getType(), TileType.SOLID);
        assertEquals(app.getMap().getMap()[1][7].getType(), TileType.BROKEN);
//Testing moving onto the next level
        app.keyCode = 39;
        for(int i = 0; i < 2; i++) {
            app.keyPressed();
            app.keyReleased();
        }
        app.keyCode = 40;
        for(int i = 0; i < 20; i++) {
            app.keyPressed();
            app.keyReleased();
        }
        app.keyCode = 39;
        for(int i = 0; i < 2; i++) {
            app.keyPressed();
            app.keyReleased();
        }
        app.keyCode = 40;
        for(int i = 0; i < 2; i++) {
            app.keyPressed();
            app.keyReleased();
        }
        app.keyCode = 39;
        for(int i = 0; i < 8; i++) {
            app.keyPressed();
            app.keyReleased();
        }
        assertEquals(app.getLevel(), 1);
        //Move right one more grid space
        app.keyPressed();
        app.keyReleased();
        assertEquals(app.getLevel(), 2);
        //Move right one more grid space to finish final level
        app.keyPressed();
        app.keyReleased();
        app.draw();

//Resetting entire game, this time dying in an explosion
        app.setLevel(0);
        app.setWin(false);
        app.newMap("src/test/resources/testcasemanuallvl101.txt");
        assertEquals(app.getMap().getPlayer().getLives(), 3);
        assertEquals(app.getMap().getPlayer().getI(), 1);
        assertEquals(app.getMap().getPlayer().getJ(), 1);
        app.keyCode = 32;
        app.keyPressed();
        app.keyReleased();
        for(int i = 0; i < 300; i++) {
            app.draw();
        }
        assertEquals(app.getMap().getPlayer().getLives(), 2);
        
        app.keyPressed();
        app.keyReleased();
        for(int i = 0; i < 300; i++) {
            app.draw();
        }
        assertEquals(app.getMap().getPlayer().getLives(), 1);
        app.keyPressed();
        app.keyReleased();
        for(int i = 0; i < 300; i++) {
            app.draw();
        }
        assertEquals(app.getMap().getPlayer().getLives(), 0);


    }
  
}