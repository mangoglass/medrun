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
public class Block implements Renderable{
    
    TiledMap tiles;
    int x;
    int y;
    
    public Block(String ref, int x, int y) throws SlickException{
        tiles = new TiledMap(ref);
        this.x = x;
        this.y = y;
    }

    @Override
    public void render() {
        tiles.render(x, y);
    }
}
