package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;

public class ImgLoad {

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

}