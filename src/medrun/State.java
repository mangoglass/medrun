/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

/**
 *
 * @author Admin
 */

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class State extends BasicGameState{

    Input input;
    int stateID;
    
    public State(int stateID){
        this.stateID = stateID;
    }
    
    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public abstract void enter(GameContainer gc, StateBasedGame sbg) throws SlickException;
    
    @Override
    public abstract void leave(GameContainer gc, StateBasedGame sbg) throws SlickException;
    
    @Override
    public abstract void init(GameContainer gc, StateBasedGame sbg) throws SlickException;

    @Override
    public abstract void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException;

    @Override
    public abstract void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException;
    
}
