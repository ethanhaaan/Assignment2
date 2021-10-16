package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BombGuy extends GameObject {

    public BombGuy(int x, int y, PImage[][] sprites) {
        super(x, y, sprites);
        
    }

    public void tick() {
        sprite_cycle();
    }

}