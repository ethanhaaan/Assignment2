package demolition;

import processing.core.PApplet;
import processing.core.PImage;

enum TileType {
    SOLID, EMPTY, BROKEN, GOAL;
}

public abstract class Tile {

    protected PImage sprite;
    protected TileType type;
    protected int x;
    protected int y;

    public Tile(PImage sprite, TileType type, int x, int y) {
        this.sprite = sprite;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public void draw(PApplet app) {
        app.image(sprite, x, y);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public TileType getType() {
        return type;
    }

}

class SolidWall extends Tile {
    public SolidWall(PImage sprite, int x, int y) {
        super(sprite, TileType.SOLID, x, y);
    }
}

class BrokenWall extends Tile {
    public BrokenWall(PImage sprite, int x, int y) {
        super(sprite, TileType.BROKEN, x, y);
    }
}

class EmptyTile extends Tile {
    public EmptyTile(PImage sprite, int x, int y) {
        super(sprite, TileType.EMPTY, x, y);
    }
}

class GoalTile extends Tile {
    public GoalTile(PImage sprite, int x, int y) {
        super(sprite, TileType.GOAL, x, y);
    }
}