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
    private int timer;

    public UI(PImage[] UI_s) {
        this.playerhead = UI_s[0];
        this.clock = UI_s[1];
        this.timer = 60;
    }

    public void draw(PApplet app, int lives) {
        app.fill(239, 129, 0);
        app.stroke(239, 129, 0);
        app.rect(0, 0, 480, 64);
        app.image(playerhead, 128,18);
        app.fill(0, 0, 0);
        app.text(lives, 168, 45);
        timer = 60;
    }
}