/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import java.util.ArrayList;
import java.util.Random;
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
    public static final float startX = 500;
    public static final float startY = 600;    
    
    public static int gametime;
    public static int frames;
    public static Player player;
    public static Camera camera;
    public static Gui gui;
    public static float dTranslatedX;
    public static float dTranslatedY;
    public static float translatedX;
    public static float translatedY;
    public static float deltaRatio;
    public static float timeFlow;
    public static int latestBlockX;
    public static int latestBlockWidth;
    public static int latestBlockY;
    public static ArrayList<Layer> layers;
    public static ArrayList<Block> blocks; 
    Random random;
    Input input;
    boolean restart;
    
    
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
        random = new Random();
        latestBlockX = (int)startX-40;
        latestBlockY = (int)startY;
        blocks.add(new Block(Block.STARTBLOCK, latestBlockX, latestBlockY));
        latestBlockWidth = blocks.get(blocks.size()-1).getWidth();
        for (int i = 0; i < 7; i++){
            layers.add(new Layer(new Image("data/sprites/layers/layer"+i+".png"), i)); // adding layers to the background.
        }
        player = new Player(startX, startY - Animations.startHeight - 1);
        camera = new Camera();
        gui = new Gui();
        gametime = 0;
        frames = 0;
        translatedX = 0;
        translatedY = 0;
        dTranslatedX = 0;
        dTranslatedY = 0;
        timeFlow = 1;
        restart = false;
        if(Medrun.music != null){
            MusicPlayer.restart();
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.translate(-translatedX, -translatedY);
        layers.stream().forEach((layer) -> {
            layer.render();
        });
        blocks.stream().forEach(block -> {
            block.render();
        });
        gui.render();
        player.render();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        input = gc.getInput();
        gametime += delta;
        frames ++;
        deltaRatio = ((float)(delta)) / (gametime/frames); // I devide the total gametime with the number of frames because that is the average time between frames in miliseconds.
        //System.out.println("delta Ratio: " + deltaRatio);
        layers.stream().forEach((layer) -> {
            layer.update(deltaRatio); // The delta variable is the time in milliseconds between updates, it can be used to keep track of time and update positions properly.
        });
        
        if(latestBlockX + latestBlockWidth < translatedX + Medrun.width){ // if the latest block's right side is in the picture, we generate a new block.
            latestBlockX += latestBlockWidth + random.nextInt((int) (1 + dTranslatedX*20)) + 200; //differates between 200 and ten times the current speed the game is moving with
            if(latestBlockY < 500){
                latestBlockY += (random.nextInt((int) (300 + dTranslatedX*15)) - random.nextInt((int) (300 + dTranslatedX*15))); // differates between +- ten times the current moving speed added to the last block.
            } else{
                latestBlockY -= random.nextInt((int) (200 + dTranslatedX*15)); // differates between - ten times the current moving speed added to the last block.
            }
            int randBlockType = random.nextInt((int) (dTranslatedX/10) + 3) + 1; // decides what the next block shall be. CHANGE IF NECCECARY LATER!
            blocks.add(new Block(randBlockType, latestBlockX, latestBlockY));
            //System.out.println("(added) number of active blocks is now: " + blocks.size());
            latestBlockWidth = blocks.get(blocks.size()-1).getWidth();
        }
        for(int i = 0; i < blocks.size(); i++){
            if(blocks.get(i).x + blocks.get(i).getWidth() < translatedX){
                blocks.remove(i);
                blocks.trimToSize();
                //System.out.println("(removed) number of active blocks is now: " + blocks.size());
            }
        }
        
        translatedX += dTranslatedX * deltaRatio; 
        translatedY += dTranslatedY * deltaRatio; 
        //System.out.println("xChange:   " + xChange + "   yChange:   " + yChange);
        player.update(delta, deltaRatio, input);
        camera.update(gametime, deltaRatio, player);
        gui.update(player.xSpeed);
        //System.out.println("player X: " + player.getX() + "   player Y: " + player.getY() + "   player Xspeed: " + player.getxSpeed()+ "   player Yspeed:  " + player.getySpeed());
    
        if(input.isKeyPressed(Input.KEY_R) && !restart){
            restart = true;
            sbg.getCurrentState().init(gc, sbg);
        }
    }

    public static float getxChange() {
        return translatedX;
    }

    public static void setxChange(float xChange) {
        GameState.translatedX = xChange;
    }

    public static float getyChange() {
        return translatedY;
    }

    public static void setyChange(float yChange) {
        GameState.translatedY = yChange;
    }

    public static float getXdChange() {
        return dTranslatedX;
    }

    public static void setXdChange(float xdChange) {
        GameState.dTranslatedX = xdChange;
    }

    public static float getYdChange() {
        return dTranslatedY;
    }

    public static void setYdChange(float ydChange) {
        GameState.dTranslatedY = ydChange;
    }

    public static ArrayList<Block> getActiveBlocks() {
        return blocks;
    }
    
     public float getTimeFlow() {
        return timeFlow;
    }
}
