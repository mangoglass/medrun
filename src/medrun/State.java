/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

/**
 * The abstract class state is used to creates different states for the game
 * which all uses the same main functions: enter, leave, init, render, and
 * update.
 * The enter function decides what happens when the game enters that state.
 * The leave function decides what happens when the game leaves that state.
 * The init function is run once the game starts, and is then run of the player decides to restart the game.
 * The render function is run eveytime the game is drawn on the screen. It renders all of the elements that should be rendered from that state.
 * The update function is run every frame and updates all of the game logic.
 * @author Tom Axblad
 */
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class State extends BasicGameState {

    Input input;
    int stateID;

    public State(int stateID) {
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
