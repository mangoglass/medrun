/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Admin
 */
public class Block implements Renderable {

    public static final int X = 0;
    public static final int Y = 1;
    
    public static final int tileWidth = 32;
    public static final int tileHeight = 32;
    
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
        } else if (type == BIGBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/regular.tmx");
        } else if (type == RANDOMBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/regular.tmx");
        } else if (type == HARDBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/regular.tmx");
        }
    }

    @Override
    public void render() {
        tiledMap.render((int) x, (int) y, 0);
    }

    public int getType() {
        return type;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getTiledWidth() {
        return tiledMap.getWidth();
    }

    public int getTiledHeight() {
        return tiledMap.getHeight();
    }

    public int getWidth() {
        return tiledMap.getWidth() * tileWidth;
    }

    public int getHeight() {
        return tiledMap.getHeight() * tileHeight;
    }

    public boolean inBlock(float[] pos) {
        return (pos[X] > this.x && pos[X] < this.x + this.getWidth() && pos[Y] > this.y && pos[Y] < this.y + this.getHeight()); // pos[X] is the x value for the input, pos[Y] is the y value. If all is true, you're in the block.
    }

    public boolean isColliding(float[] pos) {
        int[] block = {
            (int) ((pos[X] - (float) this.x - (pos[X] - (float) this.x) % (float) tileWidth) / (float) tileWidth), // the input x position subtracted with the blocks x position subtracted with the distance to the nearest tiles x value, this becomes the x position of the nearest tile in pixels, lastly we divide by the pixel with of one tile
            (int) ((pos[Y] - (float) this.y - (pos[Y] - (float) this.y) % (float) tileHeight) / (float) tileHeight) // the same as above, but with the y positions.
        };
        if (block[X] < 0 || block[X] > this.tiledMap.getWidth() - 1 || block[Y] < 0 || block[Y] > this.tiledMap.getHeight() - 1) {
            return false;
        } else {
            return tiledMap.getTileId(block[X], block[Y], this.tiledMap.getLayerIndex("collision")) == 1;
        }
    }

    public float[] getBlockPos(float[] pos) {
        return new float[]{
            (pos[X] - (pos[X] - (float) this.x) % (float) this.tiledMap.getTileWidth()),
            (pos[Y] - (pos[Y] - (float) this.y) % (float) this.tiledMap.getTileHeight())
        };
    }
}
