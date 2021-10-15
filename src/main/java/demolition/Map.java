package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Map {
    
    private Tile[][] map;

    public Map() {
        this.map = new Tile[13][15];
    }
    
    public void constructMap(String path) {
        try {
            File lvl_file = new File(path);
            Scanner scanobj = new Scanner(lvl_file);
            for(int i = 0; i < 13; i++) {
                String line = scanobj.nextLine();
                for(int j = 0; j < 15; j++) {
                    if("W".equals(String.valueOf(line.charAt(j))))
                        map[i][j] = new SolidWall(32*j, 32*i+64);
                    else if("B".equals(String.valueOf(line.charAt(j))))
                        map[i][j] = new BrokenWall(32*j, 32*i+64);
                    else if("G".equals(String.valueOf(line.charAt(j))))
                        map[i][j] = new GoalTile(32*j, 32*i+64);
                    else
                        map[i][j] = new EmptyTile(32*j, 32*i+64);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Failed");
        }
    }

    public void draw(PApplet app) {
        for(Tile[] i : map) {
            for(Tile j : i) {
                j.draw(app);
            }
        }
    }

    public Tile[][] getMap() {
        return map;
    }

}