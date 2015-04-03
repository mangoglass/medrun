/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.Animation;

/**
 *
 * @author Admin
 */
public class PlayerAnimations {
    
    public Animation[] animations;
    public Animation current;
    
    public PlayerAnimations(){
        
    }
    
    public void update(int delta){
    
    }
    
    public void draw(float x, float y){
    
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
