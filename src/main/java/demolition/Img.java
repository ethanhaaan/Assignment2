package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;

public class Img {

    public static PImage[][] loadBombGuy(PApplet app) {
        PImage[][] img_array = new PImage[4][4];
        img_array[0][0] = app.loadImage("bin/main/player/player_left1.png");
        img_array[0][1] = app.loadImage("bin/main/player/player_left2.png");
        img_array[0][2] = app.loadImage("bin/main/player/player_left3.png");
        img_array[0][3] = app.loadImage("bin/main/player/player_left4.png");
        img_array[1][0] = app.loadImage("bin/main/player/player_right1.png");
        img_array[1][1] = app.loadImage("bin/main/player/player_right2.png");
        img_array[1][2] = app.loadImage("bin/main/player/player_right3.png");
        img_array[1][3] = app.loadImage("bin/main/player/player_right4.png");
        img_array[2][0] = app.loadImage("bin/main/player/player_up1.png");
        img_array[2][1] = app.loadImage("bin/main/player/player_up2.png");
        img_array[2][2] = app.loadImage("bin/main/player/player_up3.png");
        img_array[2][3] = app.loadImage("bin/main/player/player_up4.png");        
        img_array[3][0] = app.loadImage("bin/main/player/player1.png");
        img_array[3][1] = app.loadImage("bin/main/player/player2.png");
        img_array[3][2] = app.loadImage("bin/main/player/player3.png");
        img_array[3][3] = app.loadImage("bin/main/player/player4.png");

        return img_array;
    }

    public static PImage[][] loadRed(PApplet app) {
        PImage[][] img_array = new PImage[4][4];
        img_array[0][0] = app.loadImage("bin/main/red_enemy/red_left1.png");
        img_array[0][1] = app.loadImage("bin/main/red_enemy/red_left2.png");
        img_array[0][2] = app.loadImage("bin/main/red_enemy/red_left3.png");
        img_array[0][3] = app.loadImage("bin/main/red_enemy/red_left4.png");
        img_array[1][0] = app.loadImage("bin/main/red_enemy/red_right1.png");
        img_array[1][1] = app.loadImage("bin/main/red_enemy/red_right2.png");
        img_array[1][2] = app.loadImage("bin/main/red_enemy/red_right3.png");
        img_array[1][3] = app.loadImage("bin/main/red_enemy/red_right4.png");
        img_array[2][0] = app.loadImage("bin/main/red_enemy/red_up1.png");
        img_array[2][1] = app.loadImage("bin/main/red_enemy/red_up2.png");
        img_array[2][2] = app.loadImage("bin/main/red_enemy/red_up3.png");
        img_array[2][3] = app.loadImage("bin/main/red_enemy/red_up4.png");        
        img_array[3][0] = app.loadImage("bin/main/red_enemy/red_down1.png");
        img_array[3][1] = app.loadImage("bin/main/red_enemy/red_down2.png");
        img_array[3][2] = app.loadImage("bin/main/red_enemy/red_down3.png");
        img_array[3][3] = app.loadImage("bin/main/red_enemy/red_down4.png");

        return img_array;
    }

    public static PImage[][] loadYellow(PApplet app) {
        PImage[][] img_array = new PImage[4][4];
        img_array[0][0] = app.loadImage("bin/main/yellow_enemy/yellow_left1.png");
        img_array[0][1] = app.loadImage("bin/main/yellow_enemy/yellow_left2.png");
        img_array[0][2] = app.loadImage("bin/main/yellow_enemy/yellow_left3.png");
        img_array[0][3] = app.loadImage("bin/main/yellow_enemy/yellow_left4.png");
        img_array[1][0] = app.loadImage("bin/main/yellow_enemy/yellow_right1.png");
        img_array[1][1] = app.loadImage("bin/main/yellow_enemy/yellow_right2.png");
        img_array[1][2] = app.loadImage("bin/main/yellow_enemy/yellow_right3.png");
        img_array[1][3] = app.loadImage("bin/main/yellow_enemy/yellow_right4.png");
        img_array[2][0] = app.loadImage("bin/main/yellow_enemy/yellow_up1.png");
        img_array[2][1] = app.loadImage("bin/main/yellow_enemy/yellow_up2.png");
        img_array[2][2] = app.loadImage("bin/main/yellow_enemy/yellow_up3.png");
        img_array[2][3] = app.loadImage("bin/main/yellow_enemy/yellow_up4.png");        
        img_array[3][0] = app.loadImage("bin/main/yellow_enemy/yellow_down1.png");
        img_array[3][1] = app.loadImage("bin/main/yellow_enemy/yellow_down2.png");
        img_array[3][2] = app.loadImage("bin/main/yellow_enemy/yellow_down3.png");
        img_array[3][3] = app.loadImage("bin/main/yellow_enemy/yellow_down4.png");

        return img_array;
    }

    public static PImage[] loadUI(PApplet app) {
        PImage[] UI = new PImage[2];
        UI[0] = app.loadImage("bin/main/icons/player.png");
        UI[1] = app.loadImage("bin/main/icons/clock.png");
        return UI;
    }

}