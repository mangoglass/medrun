/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * A Block object is usually a building that the player can traverce trough out
 * the game. It has a multitude of parameters that decides how it looks and
 * where it should be rendered.
 *
 * @author Tom Axblad
 */
public class Block implements Renderable {

    public static final int X = 0;
    public static final int Y = 1;

    public static final int TILEWIDTH = 32;
    public static final int TILEHEIGHT = 32;

    public static final int STARTBLOCK = 0;
    public static final int REGULARCUBE = 1;
    public static final int SLIDEBLOCK = 2;
    public static final int LONGBLOCK = 3;
    public static final int WIERDBLOCK = 4;
    public static final int BIGBLOCK = 5;
    public static final int RANDOMBLOCK = 6;
    public static final int HARDBLOCK = 7;

    TiledMap tiledMap;
    int type;
    int x;
    int y;

    /**
     * The constructor for the Block Class.
     *
     * @param type Depending on what type that is inputed, the block will look
     * diffentently.
     * @param x The x value that the block should have and be rendered on.
     * @param y The y value that the block should have and be rendered on.
     * @throws SlickException
     */
    public Block(int type, int x, int y) throws SlickException {
        this.x = x;
        this.y = y;
        this.type = type;
        if (type == STARTBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/start.tmx");
        } else if (type == REGULARCUBE) {
            tiledMap = new TiledMap("data/sprites/blocks/regular.tmx");
        } else if (type == SLIDEBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/slide.tmx");
        } else if (type == LONGBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/long.tmx");
        } else if (type == WIERDBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/regular.tmx");
        } else if (type == BIGBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/regular.tmx");
        } else if (type == RANDOMBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/regular.tmx");
        } else if (type == HARDBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/regular.tmx");
        } else {
            System.out.println("invalid blocktype! " + type);
            tiledMap = new TiledMap("data/sprites/blocks/long.tmx");
        }
    }

    /**
     * The render function for a block object, draws the block object on the set
     * x and y values.
     */
    @Override
    public void render() {
        tiledMap.render((int) x, (int) y, 0);
    }

    /**
     * @return Returns the integer type of the block.
     */
    public int getType() {
        return type;
    }

    /**
     * @return Returns the integer x of the block.
     */
    public float getX() {
        return x;
    }

    /**
     * @return Returns the integer y of the block.
     */
    public float getY() {
        return y;
    }

    /**
     * @return Returns the static tile width.
     */
    public int getTiledWidth() {
        return tiledMap.getWidth();
    }

    /**
     * @return Returns the static tile HEIGHT.
     */
    public int getTiledHeight() {
        return tiledMap.getHeight();
    }

    /**
     * Returns the width of the block in pixels.
     *
     * @return an int representing the width.
     */
    public int getWidth() {
        //System.out.println(this.tiledMap.getWidth());
        return tiledMap.getWidth() * TILEWIDTH;
    }

    public int getHeight() {
        return tiledMap.getHeight() * TILEHEIGHT;
    }

    public boolean inBlock(float[] pos) {
        return (pos[X] > this.x && pos[X] < this.x + this.getWidth() && pos[Y] > this.y && pos[Y] < this.y + this.getHeight()); // pos[X] is the x value for the input, pos[Y] is the y value. If all is true, you're in the block.
    }

    public boolean isLeftmostTile(float[] pos) { // returns true if the tile to the left of this one isn't a cloission block.
        int[] tilePos = getTileMapPos(getTilePixelPos(pos));
        if (tilePos[X] == 0) {
            return true;
        } else {
            return this.tiledMap.getTileId(tilePos[X] - 1, tilePos[Y], this.tiledMap.getLayerIndex("Collision")) != 1;
        }
    }

    public boolean isRightmostTile(float[] pos) { // returns true if the tile to the right of this one isn't a cloission block.
        int[] tilePos = getTileMapPos(getTilePixelPos(pos));
        if (tilePos[X] == this.tiledMap.getWidth()) {
            return true;
        } else {
            try {
                return this.tiledMap.getTileId(tilePos[X] + 1, tilePos[Y], this.tiledMap.getLayerIndex("Collision")) != 1;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error in Block.java at row 120, exception type: ArrayIndexOutOfBoundsException");
                return true;
            }
        }
    }

    public boolean isTopTile(float[] pos) { // returns true if the tile above this one isn't a cloission block.
        int[] tilePos = getTileMapPos(getTilePixelPos(pos));
        if (tilePos[Y] == 0) {
            return true;
        } else {
            try {
                return this.tiledMap.getTileId(tilePos[X], tilePos[Y] - 1, this.tiledMap.getLayerIndex("Collision")) != 1;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error in Block.java at row 134, exception type: ArrayIndexOutOfBoundsException");
                return true;
            }
        }
    }

    public boolean isBottomTile(float[] pos) { // returns true if the tile below a cloission block.
        int[] tilePos = getTileMapPos(getTilePixelPos(pos));
        if (tilePos[Y] == this.tiledMap.getHeight()) {
            return true;
        } else {
            try {
                return this.tiledMap.getTileId(tilePos[X], tilePos[Y] + 1, this.tiledMap.getLayerIndex("Collision")) != 1;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error in Block.java at row 148, exception type: ArrayIndexOutOfBoundsException");
                return true;
            }
        }
    }

    public boolean isColliding(float[] pos) {
        int[] block = {
            (int) ((pos[X] - (float) this.x - (pos[X] - (float) this.x) % (float) TILEWIDTH) / (float) TILEWIDTH), // the input x position subtracted with the blocks x position subtracted with the distance to the nearest tiles' x value, this becomes the x position of the nearest tile in pixels, lastly we divide by the pixel width of one tile.
            (int) ((pos[Y] - (float) this.y - (pos[Y] - (float) this.y) % (float) TILEHEIGHT) / (float) TILEHEIGHT) // the same as above, but with the y positions.
        };
        if (block[X] < 0 || block[X] > this.tiledMap.getWidth() - 1 || block[Y] < 0 || block[Y] > this.tiledMap.getHeight() - 1) {
            return false;
        } else {
            return tiledMap.getTileId(block[X], block[Y], this.tiledMap.getLayerIndex("collision")) == 1;
        }
    }

    public float[] getTilePixelPos(float[] pos) {
        return new float[]{
            (pos[X] - (pos[X] - (float) this.x) % (float) this.tiledMap.getTileWidth()),
            (pos[Y] - (pos[Y] - (float) this.y) % (float) this.tiledMap.getTileHeight())
        };
    }

    public int[] getTileMapPos(float[] pos) {
        return new int[]{
            (int) pos[X] - this.x % TILEWIDTH,
            (int) pos[Y] - this.y % TILEHEIGHT
        };
    }

    /**
     *
     * @param pos The input position that we use.
     * @return
     */
    public boolean inXRangeOfBlock(float[] pos) {
        return pos[X] > this.x && pos[X] < this.x + this.getWidth();
    }
}
