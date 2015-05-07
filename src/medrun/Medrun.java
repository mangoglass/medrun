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
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

public class Medrun extends StateBasedGame {

    public static final int width = 1920; // the games "width" rendering something at 1920 in the x-axis will put it to the farthest right.
    public static final int height = 1080; // the games "height" rendering something at 1080 in the y-axis will put it to the lowest bottom.

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

    /**
     *
     * @param name
     * @throws java.io.IOException
     */
    public Medrun(String name) throws IOException {
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
        try {
            readSettings();
        } catch (FileNotFoundException e) {
            writeSettings();
        }
    }

    /**
     *
     * The Main class of the game Medrun. 
     * 
     * @author  Tom Axblad
     * @version 1.0
     * @since   2015-04-01  
     * @param args
     * @throws org.newdawn.slick.SlickException
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws SlickException, FileNotFoundException, IOException {
        
        game = new Medrun("Medrun");
        app = new AppGameContainer(new ScalableGame(game, width, height, false));
        app.setDisplayMode(gameWidth, gameHeight, fullScreen);
        app.setShowFPS(displayFPS);
        app.setTargetFrameRate(targetFramerate);
        app.setVSync(vSync);
        //app.setMinimumLogicUpdateInterval(minUpdateTime);
        app.start();
        game.enterState(SPLASH);
    }

    /**
     *
     * @param gc
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
     *
     * @throws java.io.IOException
     */
    public static void readSettings() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/settings.ini"));
        String line = reader.readLine();
        while (line != null) {
            if (line.startsWith("bFullScreen")) {
                switch (line.substring(line.indexOf("=") + 1).toLowerCase().trim()) {
                    case "true":
                        fullScreen = true;
                        break;
                    case "false":
                        fullScreen = false;
                        break;
                    default:
                        System.out.println("error in settings.ini\nbFullscreen holds invalid value, only \"true\" or \"false\" is allowed.");
                        fullScreen = false;
                        break;
                }
            } else if (line.startsWith("bVSync")) {
                switch (line.substring(line.indexOf("=") + 1).toLowerCase().trim()) {
                    case "true":
                        vSync = true;
                        break;
                    case "false":
                        vSync = false;
                        break;
                    default:
                        System.out.println("error in settings.ini\nbVSync holds invalid value, only \"true\" or \"false\" is allowed.");
                        vSync = true;
                        break;
                }
            } else if (line.startsWith("bDisplayFps")) {
                switch (line.substring(line.indexOf("=") + 1).toLowerCase().trim()) {
                    case "true":
                        displayFPS = true;
                        break;
                    case "false":
                        displayFPS = false;
                        break;
                    default:
                        System.out.println("error in settings.ini\nbDisplayFps holds invalid value, only \"true\" or \"false\" is allowed.");
                        displayFPS = false;
                        break;
                }
            } else if (line.startsWith("iWidth")) {
                gameWidth = Integer.parseInt(line.substring(line.indexOf("=") + 1).toLowerCase().trim());
            } else if (line.startsWith("iHeight")) {
                gameHeight = Integer.parseInt(line.substring(line.indexOf("=") + 1).toLowerCase().trim());
            } else if (line.startsWith("iTargetFramerate")) {
                targetFramerate = Integer.parseInt(line.substring(line.indexOf("=") + 1).toLowerCase().trim());
            } else if (line.startsWith("iMinimumLogicUpdateInterval")) {
                minUpdateTime = Integer.parseInt(line.substring(line.indexOf("=") + 1).toLowerCase().trim());
            } else if (line.startsWith("iDifficulty")) {
                difficulty = 1 + (1 - (Integer.parseInt(line.substring(line.indexOf("=") + 1).toLowerCase().trim())))/2; // a 1 represents 1, a 2 represents 1.5, a 3 is 2, and so on.  
            } else if (line.startsWith("bSMute")) {
                switch (line.substring(line.indexOf("=") + 1).toLowerCase().trim()) {
                    case "true":
                        soundMuted = true;
                        break;
                    case "false":
                        soundMuted = false;
                        break;
                    default:
                        System.out.println("error in settings.ini\nbSMute holds invalid value, only \"true\" or \"false\" is allowed.");
                        soundMuted = false;
                        break;
                }
            } else if (line.startsWith("iSMaster")) {
                soundMaster = Integer.parseInt(line.substring(line.indexOf("=") + 1).toLowerCase().trim());
            } else if (line.startsWith("iSEffects")) {
                soundEffects = Integer.parseInt(line.substring(line.indexOf("=") + 1).toLowerCase().trim());
            } else if (line.startsWith("iSMusic")) {
                soundMusic = Integer.parseInt(line.substring(line.indexOf("=") + 1).toLowerCase().trim());
            }
            line = reader.readLine();
        }
        try {
            reader.close();
        } catch (Exception ex) {
        }
    }

    public static void writeSettings() {

        File f = new File("data/settings.ini");
        f.getParentFile().mkdirs();
        Writer writer = null;
        try {
            f.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(f.getPath()), "utf-8"));
            writer.write("\n\n# Video Settings:");
            writer.write("\n");
            writer.write("bFullScreen = false\n");
            writer.write("bVSync = true\n");
            writer.write("bDisplayFps = false\n");
            writer.write("iWidth = " + displayWidth / 2 + "\n");
            writer.write("iHeight = " + displayHeight / 2 + "\n");
            writer.write("iTargetFramerate = 60\n");
            writer.write("iMinimumLogicUpdateInterval = 10\n");
            writer.write("\n\n# Game Settings:");
            writer.write("\n");
            writer.write("# 1 = easy, 2 = medium, 3 = hard\n");
            writer.write("iDifficulty = 1");
            writer.write("\n\n# Sound Settings:");
            writer.write("\n");
            writer.write("bSMute = false\n");
            writer.write("iSMaster = 100\n");
            writer.write("iSEffects = 100\n");
            writer.write("iSMusic = 100\n");
        } catch (IOException i) {
            System.err.println("Caught IOException: " + i.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }

        fullScreen = false;
        vSync = true;
        displayFPS = false;
        soundMuted = false;
        gameWidth = displayWidth / 2;
        gameHeight = displayHeight / 2;
        targetFramerate = 60;
        minUpdateTime = 10;
        difficulty = 1;
        soundMaster = 100;
        soundEffects = 100;
        soundMusic = 100;

    }

    public static boolean isFullScreen() {
        return fullScreen;
    }

    public static void setFullScreen(boolean fullScreen) throws SlickException {
        Medrun.fullScreen = fullScreen;
        app.setFullscreen(fullScreen);
    }

    public static boolean isvSync() {
        return vSync;
    }

    public static void setvSync(boolean vSync) {
        Medrun.vSync = vSync;
        app.setVSync(vSync);
    }

    public static boolean isDisplayFPS() {
        return displayFPS;
    }

    public static void setDisplayFPS(boolean displayFPS) {
        Medrun.displayFPS = displayFPS;
        app.setShowFPS(vSync);
    }

    public static int getGameWidth() {
        return gameWidth;
    }

    public static void setGameWidth(int gameWidth) throws SlickException {
        Medrun.gameWidth = gameWidth;
        app.setDisplayMode(gameWidth, gameHeight, fullScreen);
    }

    public static int getGameHeight() {
        return gameHeight;
    }

    public static void setGameHeight(int gameHeight) throws SlickException {
        Medrun.gameHeight = gameHeight;
        app.setDisplayMode(gameWidth, gameHeight, fullScreen);
    }

    public static int getTargetFramerate() {
        return targetFramerate;
    }

    public static void setTargetFramerate(int targetFramerate) {
        Medrun.targetFramerate = targetFramerate;
        app.setTargetFrameRate(targetFramerate);
    }

    public static int getMinUpdateTime() {
        return minUpdateTime;
    }

    public static void setMinUpdateTime(int minUpdateTime) {
        Medrun.minUpdateTime = minUpdateTime;
        app.setMinimumLogicUpdateInterval(minUpdateTime);
    }

    public static float getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        Medrun.difficulty = difficulty;
    }

    public static void renderScaledCenter(Animation anim, int x, int y, float scale) {
        anim.draw(x - 20 - anim.getWidth() * scale / 2, y - anim.getHeight() * scale / 2, anim.getWidth() * scale, anim.getHeight() * scale);
    }

    public static void renderScaledCenter(Image image, int x, int y, float scale) {
        image.draw(x - 20 - image.getWidth() * scale / 2, y - image.getHeight() * scale / 2, image.getWidth() * scale, image.getHeight() * scale);
    }
    
    public static void renderCenterdText(TrueTypeFont font, String whatChars, int x, int y) {
        font.drawString(x - 23 - font.getWidth(whatChars) / 2, y - font.getHeight() / 2, whatChars);
    }
    
    public static void renderCenterdText(TrueTypeFont font, String whatChars, int x, int y, Color color) {
        font.drawString(x - 23 - font.getWidth(whatChars) / 2, y - font.getHeight() / 2, whatChars, color);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static boolean isSoundMuted() {
        return soundMuted;
    }

    public static void setSoundMuted(boolean soundMuted) {
        Medrun.soundMuted = soundMuted;
    }

    public static int getSoundMaster() {
        return soundMaster;
    }

    public static void setSoundMaster(int soundMaster) {
        Medrun.soundMaster = soundMaster;
        MusicPlayer.setMusicVolume();
    }

    public static int getSoundEffects() {
        return soundEffects;
    }

    public static void setSoundEffects(int soundEffects) {
        Medrun.soundEffects = soundEffects;
    }

    public static int getSoundMusic() {
        return soundMusic;
    }

    public static void setSoundMusic(int soundMusic) {
        Medrun.soundMusic = soundMusic;
        MusicPlayer.setMusicVolume();
    }
    
}
