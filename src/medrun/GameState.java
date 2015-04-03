/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Admin
 */
class GameState extends State {

    public static final String gameMusicRef = "data/music/gamemusic.aif";
    public static final float startX = 600;
    public static final float startY = 900;    
    
    public static int gametime;
    public static Player player;
    public static float dxChange;
    public static float dyChange;
    public static float xChange;
    public static float yChange;
    public static Block[] blocks;
    public static ArrayList<Layer> layers;
    public static ArrayList<Block> activeBlocks; 
    Input input;
    
    
    public GameState(int stateID) {
        super(stateID);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Entered Game State");
        if (Medrun.music != null) {
            if (!MusicPlayer.getRef().equals(gameMusicRef)) {
                MusicPlayer.changeMusic(gameMusicRef);
            }
            Medrun.music.play();
        }
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Left Menu State");
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        layers = new ArrayList<Layer>();
        activeBlocks = new ArrayList<Block>();
        for (int i = 0; i < 7; i++){
            layers.add(new Layer(new Image("data/sprites/layers/layer"+i+".png"), i)); // 1 - (i/10) is the number that will multiply with the layers movement, higher the number, the more it will move.
        }
        player = new Player(startX, startY);
        
        gametime = 0;
        
        dxChange = 64;
        dyChange = 0;
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.translate(-xChange, -yChange);
        layers.stream().forEach((layer) -> {
            layer.render();
        });
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        input = gc.getInput();
        layers.stream().forEach((layer) -> {
            layer.update(delta);
        });
        
        gametime += delta;
        
        xChange += dxChange*delta/24;
        yChange += dyChange*delta/24;
    }

    public static float getxChange() {
        return xChange;
    }

    public static void setxChange(float xChange) {
        GameState.xChange = xChange;
    }

    public static float getyChange() {
        return yChange;
    }

    public static void setyChange(float yChange) {
        GameState.yChange = yChange;
    }

    public static float getXdChange() {
        return dxChange;
    }

    public static void setXdChange(float xdChange) {
        GameState.dxChange = xdChange;
    }

    public static float getYdChange() {
        return dyChange;
    }

    public static void setYdChange(float ydChange) {
        GameState.dyChange = ydChange;
    }
    
    
}
