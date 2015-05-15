/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The splash state is the first state that will run when the game is started, it displays a splash animation and then transitions to the main menu.
 * @author Tom Axblad
 */
public class SplashState extends State {

    int displayWidth;
    int displayHeight;
    Animation water;
    SpriteSheet splash;

    /**
     * The constructor for the splash state.
     * @param stateID The integer identification of this state.
     */
    public SplashState(int stateID) {
        super(stateID);
        displayWidth = Medrun.width;
        displayHeight = Medrun.height;
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Entered splash state");
        //sbg.enterState(Medrun.GAME);
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Left splash state");
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        water = new Animation(new SpriteSheet("data/sprites/water.png", 192, 192), 80);
        water.stopAt(water.getFrameCount()-1);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        renderScaledCenter(water, displayWidth/2, displayHeight/2, 3);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        water.update(delta);
        if (water.isStopped()){
            sbg.enterState(Medrun.MENU);
        }
    }
    
     /**
     * Renders the input animation at the input position as center, using the input scale to scale the animation.
     * @param anim the animation to render.
     * @param x the x position to render at.
     * @param y the y position to render at.
     * @param scale the scale to use when rendering.
     */
    public static void renderScaledCenter(Animation anim, int x, int y, int scale){
        anim.draw(x - 20 - anim.getWidth()*scale/2, y - anim.getHeight()*scale/2, anim.getWidth()*scale, anim.getHeight()*scale);
    }
}