/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import java.util.ArrayList;
import org.newdawn.slick.Image;

/**
 *
 * @author Admin
 */
public class Layer implements Renderable{
    
    Image image;
    int width;
    int height;
    float x;
    float y;
    float scale;
    ;
    
    public Layer(Image image, float x, float y, float scale){
        this.image = image;
        this.x = x;
        this.y = y;
        this.scale = scale;
    }
    
    @Override
    public void render() {
        image.draw(x, y);
    }
    
    public void update(){
        x += GameState.getxChange()*scale;
        y += GameState.getyChange()*scale;
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
