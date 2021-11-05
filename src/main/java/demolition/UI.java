package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;
import java.util.List;

/** the UI object has the purpose of drawing the UI elements of the game, such as the timer, lives remaining, win and lose conditions */

public class UI {

    private PImage playerhead;
    private PImage clock;
    private int fps_timer;

    /**Constructor for UI object 
    * @param UI_s Sprite array of the playerhead sprite and clock sprite
    */

    public UI(PImage[] UI_s) {
        this.playerhead = UI_s[0];
        this.clock = UI_s[1];
        this.fps_timer = 60;
    }
    /**Draws the timer, lives remaining and area above the map.
    * @param app applet in which to draw the UI
    * @param lives number of lives remaining of the player
    * @param time number of seconds left of the map
    */
    public void draw(PApplet app, int lives, int time) {
        app.fill(239, 129, 0);
        app.stroke(239, 129, 0);
        app.rect(0, 0, 480, 64);
        app.image(playerhead, 128,18);
        app.image(clock, 256, 18);
        app.fill(0, 0, 0);
        app.text(lives, 168, 45);
        app.text(time, 300, 45);
    }
    /**Draws the win screen, displaying an orange background with "YOU WIN" text 
    *@param app applet in which to draw the win screen
    */
    public void drawWin(PApplet app) {
        app.fill(239, 129, 0);
        app.stroke(239, 129, 0);
        app.rect(-1, -1, 482, 482);
        app.fill(0, 0, 0);
        app.text("YOU WIN", 168, 230);
    }
    /**Draws the losing screen, displaying an orange background with "GAME OVER" text 
    *@param app applet in which to draw the lose screen
    */
    public void drawLose(PApplet app) {
        app.fill(239, 129, 0);
        app.stroke(239, 129, 0);
        app.rect(-1, -1, 482, 482);
        app.fill(0, 0, 0);
        app.text("GAME OVER", 148, 230);
    }
}