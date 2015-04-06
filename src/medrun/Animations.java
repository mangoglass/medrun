/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Admin
 */
public class Animations {
    
    public static int width = 32;
    public static int height = 50;
    public Animation[] animations;
    public Animation current;
    
    public Animations() throws SlickException{
        current = new Animation(new SpriteSheet("data/sprites/placeholder.png", width, height), 500);
        current.setLooping(true);
        current.setAutoUpdate(false);
    }
    
    public void update(int delta){
        current.update(delta);
    }
    
    public void draw(float x, float y){
        current.draw(x, y);
    }

    public Animation[] getAnimations() {
        return animations;
    }

    public void setAnimations(Animation[] animations) {
        this.animations = animations;
    }

    public Animation getCurrent() {
        return current;
    }

    public void setCurrent(Animation current) {
        this.current = current;
    }
}
