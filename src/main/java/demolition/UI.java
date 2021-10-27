package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;
import java.util.List;

public class UI {

    private PImage playerhead;
    private PImage clock;
    private int fps_timer;

    public UI(PImage[] UI_s) {
        this.playerhead = UI_s[0];
        this.clock = UI_s[1];
        this.fps_timer = 60;
    }

    public void draw(PApplet app, int lives, int time) {
        app.fill(239, 129, 0);
        app.stroke(239, 129, 0);
        app.rect(0, 0, 480, 64);
        app.image(playerhead, 128,18);
        app.fill(0, 0, 0);
        app.text(lives, 168, 45);
        app.text(time, 300, 45);
    }

    public void drawWin(PApplet app) {
        app.fill(239, 129, 0);
        app.stroke(239, 129, 0);
        app.rect(-1, -1, 482, 482);
        app.fill(0, 0, 0);
        app.text("YOU WIN", 168, 230);
    }

    public void drawLose(PApplet app) {
        app.fill(239, 129, 0);
        app.stroke(239, 129, 0);
        app.rect(-1, -1, 482, 482);
        app.fill(0, 0, 0);
        app.text("GAME OVER", 148, 230);
    }
}