/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The main class, from here the game will start and will create a Medrun object
 * that is the game.
 *
 * @author Tom Axblad
 */
public class Medrun extends StateBasedGame {

    private static final String settingsRef = "data/settings.ini";
    
    public static final int width = 1920; // the games "width". Rendering something at 1920 in the x-axis will put it to the farthest right.
    public static final int height = 1080; // the games "height". Rendering something at 1080 in the y-axis will put it to the lowest bottom.

    public static AppGameContainer app; // app = application
    public static AppGameContainer scalable; // to be able to scale the window.
    public static final int SPLASH = 0;
    public static final int MENU = 1;
    public static final int GAME = 2;
    public static final int PAUSE = 3;
    public static final int LEADERBOARDS = 4;
    public static final int SETTINGS = 5;
    public static final int ACHIEVEMENTS = 6;
    
    public static Medrun game;
    public static boolean fullScreen;
    public static boolean vSync;
    public static boolean displayFPS;
    public static boolean soundMuted;
    public static int displayWidth;
    public static int displayHeight;
    public static int gameWidth;
    public static int gameHeight;
    public static int targetFramerate;
    public static int minUpdateTime;
    public static float difficulty;
    public static int soundMaster;
    public static int soundEffects;
    public static int soundMusic;
    public static MusicPlayer music;
    public static SettingsHandler settings;

    /**
     *
     * The constructor for the main class, from here the game will initiate the
     * different states that the game will use.
     *
     * @param name the string representing the games name.
     * @throws java.io.IOException
     * @throws org.newdawn.slick.SlickException
     */
    public Medrun(String name) throws IOException, SlickException {
        super(name);
        this.addState(new SplashState(SPLASH));
        this.addState(new MenuState(MENU));
        this.addState(new GameState(GAME));
        this.addState(new PauseState(PAUSE));
        this.addState(new LeaderState(LEADERBOARDS));
        this.addState(new SettingsState(SETTINGS));
        this.addState(new AchieveState(ACHIEVEMENTS));
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize(); //skapar ett display objekt där skärmens upplösning kan hämtas.   
        displayWidth = (int) display.getWidth();
        displayHeight = (int) display.getHeight();
        settings = new SettingsHandler(settingsRef);
        
        fullScreen = settings.readBolSetting("bFullScreen");
        vSync = settings.readBolSetting("bVSync");
        displayFPS = settings.readBolSetting("bDisplayFps");
        soundMuted = settings.readBolSetting("bSMute");
        gameWidth = settings.readIntSetting("iWidth");
        gameHeight = settings.readIntSetting("iHeight");
        targetFramerate = settings.readIntSetting("iTargetFramerate");
        minUpdateTime = settings.readIntSetting("iMinimumLogicUpdateInterval");
        difficulty = settings.readIntSetting("iDifficulty");
        soundMaster = settings.readIntSetting("iSMaster");
        soundEffects = settings.readIntSetting("iSEffects");
        soundMusic = settings.readIntSetting("iSMusic");
    }

    /**
     *
     * The Main function of the game Medrun. all the different settings will be
     * read from the settings.ini file and the application will start from here.
     *
     * @author Tom Axblad
     * @version 1.0
     * @since 2015-04-01
     * @param args The java arguments that will be used when the game starts.
     * @throws org.newdawn.slick.SlickException
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws SlickException, FileNotFoundException, IOException {

        game = new Medrun("Medrun");
        app = new AppGameContainer(new ScalableGame(game, width, height, false));
        try{
            app.setDisplayMode(gameWidth, gameHeight, fullScreen);
        }
        catch(SlickException e){
            System.out.println("\nERROR\nerror in settings.ini\niWidth and/or iHeight holds invalid values, only resolutions compatible with your screen is allowed.\n");
            app.setDisplayMode(displayWidth / 2, displayHeight / 2, false);
        }
        
        app.setShowFPS(displayFPS);
        app.setTargetFrameRate(targetFramerate);
        app.setVSync(vSync);
        app.setUpdateOnlyWhenVisible(true);
        app.setMinimumLogicUpdateInterval(minUpdateTime);
        app.start();
        game.enterState(SPLASH);
    }

    /**
     * This function will initiate all the states that the game will use.
     *
     * @param gc The game container, see Slick2d documentation for more
     * information.
     * @throws org.newdawn.slick.SlickException
     */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(SPLASH).init(gc, this);
        this.getState(MENU).init(gc, this);
        this.getState(GAME).init(gc, this);
        this.getState(PAUSE).init(gc, this);
        this.getState(LEADERBOARDS).init(gc, this);
        this.getState(SETTINGS).init(gc, this);
        this.getState(ACHIEVEMENTS).init(gc, this);
    }

    /**
     * Returns true if the game is in full-screen.
     *
     * @return a boolean that is true if the game is in full screen mode,
     * otherwise it will be false.
     */
    public static boolean isFullScreen() {
        return fullScreen;
    }

    /**
     * Sets the game to either full screen or windowed screen depending on the
     * boolean input variable.
     *
     * @param fullScreen the boolean to set the fullScreen variable to.
     * @throws SlickException
     */
    public static void setFullScreen(boolean fullScreen) throws SlickException {
        Medrun.fullScreen = fullScreen;
        app.setFullscreen(fullScreen);
    }

    /**
     * Returns true if the game has V-sync activated
     *
     * @return a boolean representing the current v-sync state. True means on,
     * false means off.
     */
    public static boolean isvSync() {
        return vSync;
    }

    /**
     * Sets the current v-sync state to the input boolean.
     *
     * @param vSync the input boolean to set the vSync variable to.
     */
    public static void setvSync(boolean vSync) {
        Medrun.vSync = vSync;
        app.setVSync(vSync);
    }

    /**
     * Returns true if the FPS counter is currently displayed on screen.
     *
     * @return a boolean representing the current FPS counter state. true means
     * on, false means off.
     */
    public static boolean isDisplayFPS() {
        return displayFPS;
    }

    /**
     * sets the displayFPS to the input boolean.
     *
     * @param displayFPS the boolean to set the displayFPS variable to.
     */
    public static void setDisplayFPS(boolean displayFPS) {
        Medrun.displayFPS = displayFPS;
        app.setShowFPS(vSync);
    }

    /**
     * returns the width of the window that the game is displayed in.
     *
     * @return an integer representing the current width of the game window.
     */
    public static int getGameWidth() {
        return gameWidth;
    }

    /**
     * sets the game window width to the input integer.
     *
     * @param gameWidth the integer to set the window width variable to.
     */
    public static void setGameWidth(int gameWidth) throws SlickException {
        Medrun.gameWidth = gameWidth;
        app.setDisplayMode(gameWidth, Medrun.gameHeight, fullScreen);
    }

    /**
     * returns the height of the window that the game is displayed in.
     *
     * @return an integer representing the current height of the game window.
     */
    public static int getGameHeight() {
        return gameHeight;
    }

    /**
     * sets the game window height to the input integer.
     *
     * @param gameHeight the integer to set the window height variable to.
     */
    public static void setGameHeight(int gameHeight) throws SlickException {
        Medrun.gameHeight = gameHeight;
        app.setDisplayMode(Medrun.gameWidth, gameHeight, fullScreen);
    }
    
    public static void setGameResolution(int gameWidth, int gameHeight) throws SlickException {
        Medrun.gameHeight = gameHeight;
        Medrun.gameWidth = gameWidth;
        app.setDisplayMode(gameWidth, gameHeight, fullScreen);
    }

    /**
     * Returns the current target framerate that the game uses to limit the
     * frames per seconds to.
     *
     * @return an integer representing the current target framerate.
     */
    public static int getTargetFramerate() {
        return targetFramerate;
    }

    /**
     * Sets the current target framerate to the input integer.
     *
     * @param targetFramerate the integer to set the target framerate to.
     */
    public static void setTargetFramerate(int targetFramerate) {
        Medrun.targetFramerate = targetFramerate;
        app.setTargetFrameRate(targetFramerate);
    }

    /**
     * Returns the minimum update time that the game uses to limit the number of
     * updated per frame. if the games updates faster than the
     * minimum update time the game will limit the updates to that time.
     *
     * @return an integer representing the current minimum update time in milliseconds.
     */
    public static int getMinUpdateTime() {
        return minUpdateTime;
    }

    /**
     * Sets the current minimum update time to the input integer.
     *
     * @param minUpdateTime the integer to set the target minimum update time to.
     */
    public static void setMinUpdateTime(int minUpdateTime) {
        Medrun.minUpdateTime = minUpdateTime;
        app.setMinimumLogicUpdateInterval(minUpdateTime);
    }

    /**
     * Returns the current game difficulty.
     * @return a float representing the current difficulty.
     */
    public static float getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the current difficulty to the input integer value.
     * @param difficulty the integer to set the difficulty to.
     */
    public static void setDifficulty(int difficulty) {
        Medrun.difficulty = difficulty;
    }

    /**
     * Renders the input animation at the input position as center, using the input scale to scale the animation.
     * @param anim the animation to render.
     * @param x the x position to render at.
     * @param y the y position to render at.
     * @param scale the scale to use when rendering.
     */
    public static void renderScaledCenter(Animation anim, int x, int y, float scale) {
        anim.draw(x - 20 - anim.getWidth() * scale / 2, y - anim.getHeight() * scale / 2, anim.getWidth() * scale, anim.getHeight() * scale);
    }

    /**
     * Renders the input image at the input position as center, using the input scale to scale the image.
     * @param image the image to render.
     * @param x the x position to render at.
     * @param y the y position to render at.
     * @param scale the scale to use when rendering.
     */
    public static void renderScaledCenter(Image image, int x, int y, float scale) {
        image.draw(x - 20 - image.getWidth() * scale / 2, y - image.getHeight() * scale / 2, image.getWidth() * scale, image.getHeight() * scale);
    }

    /**
     * Renders the input string using the input font at the input position as center.
     * @param font the font to use for the text render.
     * @param whatChars the string that represents the text to render.
     * @param x the x position to render at.
     * @param y the y position to render at.
     */
    public static void renderCenterdText(TrueTypeFont font, String whatChars, int x, int y) {
        font.drawString(x - font.getWidth(whatChars) / 2, y - font.getHeight() / 2, whatChars);
    }

    /**
     * Renders the input string using the input font at the input position as center. Uses the input color as the color to render the text.
     * @param font the font to use for the text render.
     * @param whatChars the string that represents the text to render.
     * @param x the x position to render at.
     * @param y the y position to render at.
     * @param color the color the text will be rendered width.
     */
    public static void renderCenterdText(TrueTypeFont font, String whatChars, int x, int y, Color color) {
        font.drawString(x - 23 - font.getWidth(whatChars) / 2, y - font.getHeight() / 2, whatChars, color);
    }

    /**
     * Returns the pseudo width of the window that the game elements will be using as render positions.
     * @return an integer representing the pseudo width.
     */
    public static int getWidth() {
        return width;
    }
    /**
     * Returns the pseudo height of the window that the game elements will be using as render positions.
     * @return an integer representing the pseudo height.
     */
    public static int getHeight() {
        return height;
    }

    /**
     * Returns true if the sound is currently muted. Otherwise it returns false.
     * @return a boolean representing the current muted state.
     */
    public static boolean isSoundMuted() {
        return soundMuted;
    }

    /**
     * Sets the muted variable to the input boolean.
     * @param soundMuted  the boolean to set the muted variable to.
     */
    public static void setSoundMuted(boolean soundMuted) {
        Medrun.soundMuted = soundMuted;
    }

    /**
     * Returns the soundMaster variable which is used to set the sound volume.
     * @return the current soundMaster setting as an integer.
     */
    public static int getSoundMaster() {
        return soundMaster;
    }
    /**
     * Sets the soundMaster variable to the input integer.
     * @param soundMaster  the integer to set the soundMaster variable to.
     */
    public static void setSoundMaster(int soundMaster) {
        Medrun.soundMaster = soundMaster;
        MusicPlayer.setMusicVolume();
    }
    /**
     * Returns the soundEffects variable which is used to set the effects volume.
     * @return the current soundEffects setting as an integer.
     */
    public static int getSoundEffects() {
        return soundEffects;
    }

    /**
     * Sets the soundEffects variable to the input integer.
     * @param soundEffects  the integer to set the soundEffects variable to.
     */
    public static void setSoundEffects(int soundEffects) {
        Medrun.soundEffects = soundEffects;
    }
    /**
     * Returns the soundMusic variable which is used to set the music volume.
     * @return the current soundMusic setting as an integer.
     */
    public static int getSoundMusic() {
        return soundMusic;
    }
    /**
     * Sets the soundMusic variable to the input integer.
     * @param soundMusic  the integer to set the soundMusic variable to.
     */
    public static void setSoundMusic(int soundMusic) {
        Medrun.soundMusic = soundMusic;
        MusicPlayer.setMusicVolume();
    }

}
