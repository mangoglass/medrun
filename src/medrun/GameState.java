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
 * The GameState class is the class that contains the game. here, all the game
 * logic is updated, and all the different sprites is rendered on the screen.
 *
 * @author Tom Axblad
 */
public class GameState extends State {

    public static final String gameMusicRef = "data/music/gamemusic.aif";
    public static final float startX = 500;
    public static final float startY = 600;
    public static final int lowestPoint = 100;

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
    public static float musicPos;
    public static int latestBlockX;
    public static int latestBlockWidth;
    public static int latestBlockY;
    public static ArrayList<Layer> layers;
    public static ArrayList<Block> blocks;
    public static boolean restarted = false;
    Random random;
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
                MusicPlayer.getMusic().setPosition(musicPos);
                MusicPlayer.play();
            } else if (!MusicPlayer.getMusic().playing() && !restarted) {
                MusicPlayer.getMusic().resume();
            } else if (restarted) {
                MusicPlayer.restart();
                MusicPlayer.play();
                restarted = false;
            }
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
        latestBlockX = (int) startX - 40;
        latestBlockY = (int) startY;
        blocks.add(new Block(Block.STARTBLOCK, latestBlockX, latestBlockY));
        latestBlockWidth = blocks.get(blocks.size() - 1).getWidth();
        for (int i = 0; i < 7; i++) {
            layers.add(new Layer(new Image("data/sprites/layers/layer" + i + ".png"), i)); // adding layers to the background.
        }
        player = new Player(startX, startY - Animations.startHeight + Block.TILEHEIGHT + 1);
        camera = new Camera();
        gui = new Gui();
        gametime = 0;
        frames = 0;
        translatedX = 0;
        translatedY = 0;
        dTranslatedX = 0;
        dTranslatedY = 0;
        timeFlow = 1;
        musicPos = 0;
        if (Medrun.music != null) {
            MusicPlayer.restart();
        }
    }
    
    public static void testInit(){
        latestBlockX = (int) startX - 40;
        latestBlockY = (int) startY;
        gametime = 0;
        frames = 0;
        translatedX = 0;
        translatedY = 0;
        dTranslatedX = 0;
        dTranslatedY = 0;
        timeFlow = 1;
        musicPos = 0;
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
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException { // the main update loop for the game. From here all the game logic is updated.
        input = gc.getInput(); // gets the current input from the game container.
        gametime += delta; // updates the played time. played time is stored as milliseconds.
        frames++; // updates the current frame.
        deltaRatio = ((float) (delta)) / (gametime / frames); // I devide the total gametime with the number of frames because that is the average time between frames in miliseconds.
        //System.out.println("delta Ratio: " + deltaRatio);
        layers.stream().forEach((layer) -> { // for each layer in the layers array.
            layer.update(deltaRatio); // Update the layer. The delta variable is the time in milliseconds between frames, it can be used to keep track of time and update positions properly.
        });
        if (latestBlockX + latestBlockWidth < translatedX + Medrun.width) { // if the latest block's right side is in the picture, we generate a new block.
            latestBlockX += latestBlockWidth + random.nextInt((int) (1 + dTranslatedX * 30)) + 200; //differates between 200 and 30 times the current speed the game is moving with
            if (latestBlockY < lowestPoint && player.getX() < 10000) { // if the player hasn't gottent to 10000 in the x-axis yet and the last block was over the 500 limit.
                latestBlockY += (random.nextInt((int) (80 + getBlockVaryingHeight())) - random.nextInt((int) (80 + getBlockVaryingHeight()))); // differates between +- the maximum jump height depending on how far the player has gotten.
            } else if (player.getX() < 10000) {
                latestBlockY -= random.nextInt((int) (80 + getBlockVaryingHeight())); // differates between 0 and -600 depending on how far the player has gotten.
            } else if (latestBlockY < lowestPoint) {
                latestBlockY += (random.nextInt(Player.MAXJUMPHEIGHT - 10) - random.nextInt(Player.MAXJUMPHEIGHT) - 10); // differates between +- the maximum jump height.
            } else {
                latestBlockY -= random.nextInt(Player.MAXJUMPHEIGHT - 10); // differates between 0 and -700
            }
            int randBlockType = random.nextInt((int) (dTranslatedX / 10) + 3) + 1; // decides what the next block shall be. CHANGE IF NECCECARY LATER!
            blocks.add(new Block(randBlockType, latestBlockX, latestBlockY));
            //System.out.println("(added) number of active blocks is now: " + blocks.size());
            latestBlockWidth = blocks.get(blocks.size() - 1).getWidth(); // gets the width of the last block in the blocks array and stores it.
        }
        for (int i = 0; i < blocks.size(); i++) { // checks all the curent active blocks.
            if (blocks.get(i).x + blocks.get(i).getWidth() < translatedX) { // if this block is not visible.
                blocks.remove(i); // remove the block.
                blocks.trimToSize(); // change the size of the active blocks array so that is is the right size.
                //System.out.println("(removed) number of active blocks is now: " + blocks.size());
            }
        }

        translatedX += dTranslatedX * deltaRatio; // changes the translate varable in the x-axis, this is used to "move" the camera.
        translatedY += dTranslatedY * deltaRatio; // changes the translate varable in the x-axis, this is used to "move" the camera.
        player.update(delta, deltaRatio, input); // updates the player object. 
        camera.update(gametime, deltaRatio, player); // updates the camera object.
        gui.update(player.xSpeed); // updates the gui object.
        //System.out.println("player X: " + player.getX() + "   player Y: " + player.getY() + "   player Xspeed: " + player.getxSpeed()+ "   player Yspeed:  " + player.getySpeed());

        if (input.isKeyPressed(Input.KEY_ESCAPE)) { // if the player pressed the ecape key in the last frame.
            gc.getGraphics().copyArea(PauseState.pauseFrame, 0, 0); // screenshots the current visible screent and sends it to the pauseFrame image object in the pauseState.
            sbg.enterState(Medrun.PAUSE); // Enter the pause state.
        }
    }

    /**
     * calculates the maximum height that the next block can have based on the
     * the last blocks position on the y-axis, the players maximum jump height,
     * and the players current position on the x-axis. The further the player
     * has gotten the higher the block can position it self.
     *
     * @return A float representing the maximum height the next block can take
     * based on the last blocks position on the y-axis.
     */
    public float getBlockVaryingHeight() {
        return (((float) Math.log10(1 + player.getX()) / 4) * Player.MAXJUMPHEIGHT) - 100;
    }

    /**
     * @return returns the amount the camera has translated in the x-axis.
     */
    public static float getTranslatedX() {
        return translatedX;
    }

    /**
     * @param translatedX The value to set translatedX to.
     */
    public static void setTranslatedX(float translatedX) {
        GameState.translatedX = translatedX;
    }

    /**
     * @return returns the amount the camera has translated in the y-axis.
     */
    public static float getTranslatedY() {
        return translatedY;
    }

    /**
     * @param translatedY The value to set translatedY to.
     */
    public static void setTranslatedY(float translatedY) {
        GameState.translatedY = translatedY;
    }

    /**
     * @return returns the speed that the camera currently translates in the
     * x-axis.
     */
    public static float getDTranslatedX() {
        return dTranslatedX;
    }

    /**
     * @param dTranslatedX The value to set dTranslatedX to.
     */
    public static void setDTranslatedX(float dTranslatedX) {
        GameState.dTranslatedX = dTranslatedX;
    }

    /**
     * @return returns the speed that the camera currently translates in the
     * y-axis.
     */
    public static float getDTranslatedY() {
        return dTranslatedY;
    }

    /**
     * @param dTranslatedY The value to set dTranslatedY to.
     */
    public static void setDTranslatedY(float dTranslatedY) {
        GameState.dTranslatedY = dTranslatedY;
    }

    /**
     * @return returns the array containing the current active blocks.
     */
    public static ArrayList<Block> getActiveBlocks() {
        return blocks;
    }

    /**
     * @return returns the time flow that the game currently has, a 0.5 means
     * that the game is running at 1/2 speed, 2 means x2 speed, and so on.
     */
    public static float getTimeFlow() {
        return timeFlow;
    }
}
