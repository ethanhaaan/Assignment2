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

    /** Constructor for a tile 
    * @param sprite the sprite of the tile
    * @param type the tile type either solid, empty, broken or goal
    * @param x the x location of the tile (for drawing)
    * @param y the y location of the tile (for drawing)
    */

    public Tile(PImage sprite, TileType type, int x, int y) {
        this.sprite = sprite;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    /**@param app the applet that draws the tile */

    public void draw(PApplet app) {
        app.image(sprite, x, y);
    }
    /**@return x position of tile in pixels */
    public int getX() {
        return x;
    }
    /**@return y position of the tile in pixels */
    public int getY() {
        return y;
    }
    /**@return the the type of tile */
    public TileType getType() {
        return type;
    }

}

class SolidWall extends Tile {
    /** Constructor for a tile, tile type is automatically set as TileType.SOLID
    * @param sprite the sprite of the tile
    * @param x the x location of the tile (for drawing)
    * @param y the y location of the tile (for drawing)
    */
    public SolidWall(PImage sprite, int x, int y) {
        super(sprite, TileType.SOLID, x, y);
    }
}

class BrokenWall extends Tile {
    /** Constructor for a tile, tile type is automatically set as TileType.BROKEN
    * @param sprite the sprite of the tile
    * @param x the x location of the tile (for drawing)
    * @param y the y location of the tile (for drawing)
    */
    public BrokenWall(PImage sprite, int x, int y) {
        super(sprite, TileType.BROKEN, x, y);
    }
}

class EmptyTile extends Tile {
    /** Constructor for a tile, tile type is automatically set as TileType.EMPTY
    * @param sprite the sprite of the tile
    * @param x the x location of the tile (for drawing)
    * @param y the y location of the tile (for drawing)
    */
    public EmptyTile(PImage sprite, int x, int y) {
        super(sprite, TileType.EMPTY, x, y);
    }
}

class GoalTile extends Tile {
    /** Constructor for a tile, tile type is automatically set as TileType.GOAL
    * @param sprite the sprite of the tile
    * @param x the x location of the tile (for drawing)
    * @param y the y location of the tile (for drawing)
    */
    public GoalTile(PImage sprite, int x, int y) {
        super(sprite, TileType.GOAL, x, y);
    }
}