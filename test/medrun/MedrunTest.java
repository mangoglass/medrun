/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Admin
 */
public class MedrunTest {
    
    public MedrunTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Medrun.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Medrun.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initStatesList method, of class Medrun.
     */
    @Test
    public void testInitStatesList() throws Exception {
        System.out.println("initStatesList");
        GameContainer gc = null;
        Medrun instance = null;
        instance.initStatesList(gc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readSettings method, of class Medrun.
     */
    @Test
    public void testReadSettings() throws Exception {
        System.out.println("readSettings");
        Medrun.readSettings();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeSettings method, of class Medrun.
     */
    @Test
    public void testWriteSettings() {
        System.out.println("writeSettings");
        Medrun.writeSettings();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFullScreen method, of class Medrun.
     */
    @Test
    public void testIsFullScreen() {
        System.out.println("isFullScreen");
        boolean expResult = false;
        boolean result = Medrun.isFullScreen();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFullScreen method, of class Medrun.
     */
    @Test
    public void testSetFullScreen() throws Exception {
        System.out.println("setFullScreen");
        boolean fullScreen = false;
        Medrun.setFullScreen(fullScreen);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isvSync method, of class Medrun.
     */
    @Test
    public void testIsvSync() {
        System.out.println("isvSync");
        boolean expResult = false;
        boolean result = Medrun.isvSync();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setvSync method, of class Medrun.
     */
    @Test
    public void testSetvSync() {
        System.out.println("setvSync");
        boolean vSync = false;
        Medrun.setvSync(vSync);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDisplayFPS method, of class Medrun.
     */
    @Test
    public void testIsDisplayFPS() {
        System.out.println("isDisplayFPS");
        boolean expResult = false;
        boolean result = Medrun.isDisplayFPS();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDisplayFPS method, of class Medrun.
     */
    @Test
    public void testSetDisplayFPS() {
        System.out.println("setDisplayFPS");
        boolean displayFPS = false;
        Medrun.setDisplayFPS(displayFPS);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGameWidth method, of class Medrun.
     */
    @Test
    public void testGetGameWidth() {
        System.out.println("getGameWidth");
        int expResult = 0;
        int result = Medrun.getGameWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGameWidth method, of class Medrun.
     */
    @Test
    public void testSetGameWidth() throws Exception {
        System.out.println("setGameWidth");
        int gameWidth = 0;
        Medrun.setGameWidth(gameWidth);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGameHeight method, of class Medrun.
     */
    @Test
    public void testGetGameHeight() {
        System.out.println("getGameHeight");
        int expResult = 0;
        int result = Medrun.getGameHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGameHeight method, of class Medrun.
     */
    @Test
    public void testSetGameHeight() throws Exception {
        System.out.println("setGameHeight");
        int gameHeight = 0;
        Medrun.setGameHeight(gameHeight);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTargetFramerate method, of class Medrun.
     */
    @Test
    public void testGetTargetFramerate() {
        System.out.println("getTargetFramerate");
        int expResult = 0;
        int result = Medrun.getTargetFramerate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTargetFramerate method, of class Medrun.
     */
    @Test
    public void testSetTargetFramerate() {
        System.out.println("setTargetFramerate");
        int targetFramerate = 0;
        Medrun.setTargetFramerate(targetFramerate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinUpdateTime method, of class Medrun.
     */
    @Test
    public void testGetMinUpdateTime() {
        System.out.println("getMinUpdateTime");
        int expResult = 0;
        int result = Medrun.getMinUpdateTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMinUpdateTime method, of class Medrun.
     */
    @Test
    public void testSetMinUpdateTime() {
        System.out.println("setMinUpdateTime");
        int minUpdateTime = 0;
        Medrun.setMinUpdateTime(minUpdateTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDifficulty method, of class Medrun.
     */
    @Test
    public void testGetDifficulty() {
        System.out.println("getDifficulty");
        float expResult = 0.0F;
        float result = Medrun.getDifficulty();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDifficulty method, of class Medrun.
     */
    @Test
    public void testSetDifficulty() {
        System.out.println("setDifficulty");
        int difficulty = 0;
        Medrun.setDifficulty(difficulty);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderScaledCenter method, of class Medrun.
     */
    @Test
    public void testRenderScaledCenter_4args_1() {
        System.out.println("renderScaledCenter");
        Animation anim = null;
        int x = 0;
        int y = 0;
        float scale = 0.0F;
        Medrun.renderScaledCenter(anim, x, y, scale);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderScaledCenter method, of class Medrun.
     */
    @Test
    public void testRenderScaledCenter_4args_2() {
        System.out.println("renderScaledCenter");
        Image image = null;
        int x = 0;
        int y = 0;
        float scale = 0.0F;
        Medrun.renderScaledCenter(image, x, y, scale);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderCenterdText method, of class Medrun.
     */
    @Test
    public void testRenderCenterdText_4args() {
        System.out.println("renderCenterdText");
        TrueTypeFont font = null;
        String whatChars = "";
        int x = 0;
        int y = 0;
        Medrun.renderCenterdText(font, whatChars, x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderCenterdText method, of class Medrun.
     */
    @Test
    public void testRenderCenterdText_5args() {
        System.out.println("renderCenterdText");
        TrueTypeFont font = null;
        String whatChars = "";
        int x = 0;
        int y = 0;
        Color color = null;
        Medrun.renderCenterdText(font, whatChars, x, y, color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class Medrun.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        int expResult = 0;
        int result = Medrun.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class Medrun.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        int expResult = 0;
        int result = Medrun.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSoundMuted method, of class Medrun.
     */
    @Test
    public void testIsSoundMuted() {
        System.out.println("isSoundMuted");
        boolean expResult = false;
        boolean result = Medrun.isSoundMuted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSoundMuted method, of class Medrun.
     */
    @Test
    public void testSetSoundMuted() {
        System.out.println("setSoundMuted");
        boolean soundMuted = false;
        Medrun.setSoundMuted(soundMuted);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSoundMaster method, of class Medrun.
     */
    @Test
    public void testGetSoundMaster() {
        System.out.println("getSoundMaster");
        int expResult = 0;
        int result = Medrun.getSoundMaster();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSoundMaster method, of class Medrun.
     */
    @Test
    public void testSetSoundMaster() {
        System.out.println("setSoundMaster");
        int soundMaster = 0;
        Medrun.setSoundMaster(soundMaster);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSoundEffects method, of class Medrun.
     */
    @Test
    public void testGetSoundEffects() {
        System.out.println("getSoundEffects");
        int expResult = 0;
        int result = Medrun.getSoundEffects();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSoundEffects method, of class Medrun.
     */
    @Test
    public void testSetSoundEffects() {
        System.out.println("setSoundEffects");
        int soundEffects = 0;
        Medrun.setSoundEffects(soundEffects);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSoundMusic method, of class Medrun.
     */
    @Test
    public void testGetSoundMusic() {
        System.out.println("getSoundMusic");
        int expResult = 0;
        int result = Medrun.getSoundMusic();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSoundMusic method, of class Medrun.
     */
    @Test
    public void testSetSoundMusic() {
        System.out.println("setSoundMusic");
        int soundMusic = 0;
        Medrun.setSoundMusic(soundMusic);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
