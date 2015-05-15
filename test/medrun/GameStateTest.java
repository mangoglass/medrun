/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Admin
 */
public class GameStateTest {
    
    public GameStateTest() {
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
     * Test of enter method, of class GameState.
     */
    @Test
    public void testEnter() throws Exception {
        System.out.println("enter");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        GameState instance = null;
        instance.enter(gc, sbg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leave method, of class GameState.
     */
    @Test
    public void testLeave() throws Exception {
        System.out.println("leave");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        GameState instance = null;
        instance.leave(gc, sbg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of init method, of class GameState.
     */
    @Test
    public void testInit() throws Exception {
        System.out.println("init");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        GameState instance = null;
        instance.init(gc, sbg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class GameState.
     */
    @Test
    public void testRender() throws Exception {
        System.out.println("render");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        Graphics grphcs = null;
        GameState instance = null;
        instance.render(gc, sbg, grphcs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class GameState.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        int delta = 0;
        GameState instance = null;
        instance.update(gc, sbg, delta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBlockVaryingHeight method, of class GameState.
     */
    @Test
    public void testGetBlockVaryingHeight() {
        System.out.println("getBlockVaryingHeight");
        GameState instance = null;
        float expResult = 0.0F;
        float result = instance.getBlockVaryingHeight();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTranslatedX method, of class GameState.
     */
    @Test
    public void testGetTranslatedX() {
        System.out.println("getTranslatedX");
        float expResult = 0.0F;
        float result = GameState.getTranslatedX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTranslatedX method, of class GameState.
     */
    @Test
    public void testSetTranslatedX() {
        System.out.println("setTranslatedX");
        float translatedX = 0.0F;
        GameState.setTranslatedX(translatedX);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTranslatedY method, of class GameState.
     */
    @Test
    public void testGetTranslatedY() {
        System.out.println("getTranslatedY");
        float expResult = 0.0F;
        float result = GameState.getTranslatedY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTranslatedY method, of class GameState.
     */
    @Test
    public void testSetTranslatedY() {
        System.out.println("setTranslatedY");
        float translatedY = 0.0F;
        GameState.setTranslatedY(translatedY);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDTranslatedX method, of class GameState.
     */
    @Test
    public void testGetDTranslatedX() {
        System.out.println("getDTranslatedX");
        float expResult = 0.0F;
        float result = GameState.getDTranslatedX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDTranslatedX method, of class GameState.
     */
    @Test
    public void testSetDTranslatedX() {
        System.out.println("setDTranslatedX");
        float dTranslatedX = 0.0F;
        GameState.setDTranslatedX(dTranslatedX);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getYdChange method, of class GameState.
     */
    @Test
    public void testGetYdChange() {
        System.out.println("getYdChange");
        float expResult = 0.0F;
        float result = GameState.getYdChange();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setYdChange method, of class GameState.
     */
    @Test
    public void testSetYdChange() {
        System.out.println("setYdChange");
        float dTranslatedY = 0.0F;
        GameState.setYdChange(dTranslatedY);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActiveBlocks method, of class GameState.
     */
    @Test
    public void testGetActiveBlocks() {
        System.out.println("getActiveBlocks");
        ArrayList<Block> expResult = null;
        ArrayList<Block> result = GameState.getActiveBlocks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeFlow method, of class GameState.
     */
    @Test
    public void testGetTimeFlow() {
        System.out.println("getTimeFlow");
        GameState instance = null;
        float expResult = 0.0F;
        float result = instance.getTimeFlow();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
