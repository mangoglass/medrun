/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.Image;

/**
 *
 * @author Admin
 */
public class Layer implements Renderable{
    
    Image image;
    float width;
    float height;
    int index;
    float x;
    float y;
    float scale;
    float transitionMultiplier;
    ;
    
    public Layer(Image image, int index){
        this.image = image;
        this.index = index; // the index is the layers position in relation to the other layers, higher index is closer to the "screen". 
        x = 0;
        if(index != 0){
            scale = 0.25f + (float)index/10;  // all except for the background will be effected.
            y = -113*(index - 2); // lower index lowers the position, a higher index will raise it.
        } else {
            y = 0;
            scale = 1;
        }
        width = image.getWidth() * scale;
        height = image.getHeight() * scale;
        this.transitionMultiplier = 1 - ((float)Math.pow(index,3)/240); /* higher index yields a higher multiplier. 
        A low scaling means that the layer will move with the camera, like it's farther away. */
    }
    
    @Override
    public void render() {
        image.draw(x, y, scale);
    }
    
    public void update(int delta){
        x += GameState.getXdChange()*transitionMultiplier*delta/24; // divided by 24 is so the delta gets a little lower, easier to qount with.
        y += GameState.getYdChange()*transitionMultiplier*delta/24;
        if(GameState.xChange - x > width/2){ // if the camer has moved across half of the layerr
            x += width/2; // the layer will be moved half of it's width to the right.
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
}
