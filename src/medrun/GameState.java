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
    public static final float startY = 600;    
    
    public static int gametime;
    public static Player player;
    public static float dxChange;
    public static float dyChange;
    public static float xChange;
    public static float yChange;
    public static ArrayList<Layer> layers;
    public static ArrayList<Block> blocks; 
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
            MusicPlayer.play();
        }
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Left Menu State");
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        layers = new ArrayList<>();
        blocks = new ArrayList<>();
        blocks.add(new Block(Block.STARTBLOCK, (int)startX-40, (int)startY));
        for (int i = 0; i < 7; i++){
            layers.add(new Layer(new Image("data/sprites/layers/layer"+i+".png"), i)); // adding layers to the background.
        }
        
        
        player = new Player(startX, startY - Animations.height - 1);
        
        gametime = 0;
        
        dxChange = 1; // CHANGE THIS LATER!
        dyChange = 0; // CHANGE THIS LATER!
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.translate(-xChange, -yChange);
        layers.stream().forEach((layer) -> {
            layer.render();
        });
        blocks.stream().forEach(block -> {
            block.render();
        });
        player.render();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        input = gc.getInput();
        layers.stream().forEach((layer) -> {
            layer.update(delta); // The delta variable is the time in milliseconds between updates, it can be used to keep track of time and update positions properly.
        });
        for(int i = 0; i < blocks.size(); i++){
            if(blocks.get(i).x + blocks.get(i).getWidth() < xChange){
                blocks.remove(i);
            }
        }
        gametime += delta;
        xChange += dxChange*delta/24; // CHANGE THIS LATER!
        yChange += dyChange*delta/24; // CHANGE THIS LATER!
        player.update(delta, input);
        System.out.println("player X: " + player.getX() + "   player Y: " + player.getY() + "player Xspeed: " + player.getxSpeed()+ "   player Yspeed:  " + player.getySpeed());
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

    public static ArrayList<Block> getActiveBlocks() {
        return blocks;
    }
}
