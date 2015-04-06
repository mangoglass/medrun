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
            tiledMap = new TiledMap("data/sprites/blocks/plain.tmx");
        } else if (type == RANDOMBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/plain.tmx");
        } else if (type == HARDBLOCK) {
            tiledMap = new TiledMap("data/sprites/blocks/plain.tmx");
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
        return tiledMap.getWidth() * tiledMap.getTileWidth();
    }

    public int getHeight() {
        return tiledMap.getHeight() * tiledMap.getTileHeight();
    }

    public boolean inBlock(float[] pos) {
        return (pos[0] > this.x && pos[0] < this.x + this.getWidth() && pos[1] > this.y && pos[1] < this.y + this.getHeight()); // pos[0] is the x value for the input, pos[1] is the y value. If all is true, you're in the block.
    }

    public boolean isColliding(float[] pos) {
        int[] block = {
            (int) ((pos[0] - (float) this.x - (pos[0] - (float) this.x) % (float) this.tiledMap.getTileWidth()) / (float) this.tiledMap.getTileWidth()),
            (int) ((pos[1] - (float) this.y - (pos[1] - (float) this.y) % (float) this.tiledMap.getTileHeight()) / (float) this.tiledMap.getTileHeight())
        };
        if (block[0] < 0 || block[1] < 0) {
            return false;
        } else {
            return tiledMap.getTileId(block[0], block[1], this.tiledMap.getLayerIndex("collision")) == 1;
        }
    }

    public float[] getBlockPos(float[] pos) {
        return new float[]{
            (pos[0] - (pos[0] - (float) this.x) % (float) this.tiledMap.getTileWidth()),
            (pos[1] - (pos[1] - (float) this.y) % (float) this.tiledMap.getTileHeight())
        };
    }
}
