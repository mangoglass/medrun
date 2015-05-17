/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the non IO gameState class methods.
 * @author Tom Axblad
 */
public class GameStateTest {
    
    public GameStateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        GameState.testInit();
    }
    
    public int randint() {
        return (int) (Math.random()*100);
    }

    /**
     * Test of getTranslatedX method, of class GameState.
     */
    @Test
    public void testGetTranslatedX() {
        System.out.println("getTranslatedX");
        float expResult = 0;
        float result = GameState.getTranslatedX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTranslatedX method, of class GameState.
     */
    @Test
    public void testSetTranslatedX() {
        System.out.println("setTranslatedX");
        float expResult = randint();
        GameState.setTranslatedX(expResult);
        float result = GameState.translatedX;
        assertEquals(expResult, result, 0.0);
        GameState.translatedX = 0;
    }

    /**
     * Test of getTranslatedY method, of class GameState.
     */
    @Test
    public void testGetTranslatedY() {
        System.out.println("getTranslatedY");
        float expResult = 0;
        float result = GameState.getTranslatedY();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTranslatedY method, of class GameState.
     */
    @Test
    public void testSetTranslatedY() {
        System.out.println("setTranslatedY");
        float expResult = randint();
        GameState.setTranslatedY(expResult);
        float result = GameState.translatedY;
        assertEquals(expResult, result, 0.0);
        GameState.translatedY = 0;
    }

    /**
     * Test of getDTranslatedX method, of class GameState.
     */
    @Test
    public void testGetDTranslatedX() {
        System.out.println("getDTranslatedX");
        float expResult = 0;
        float result = GameState.getDTranslatedX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setDTranslatedX method, of class GameState.
     */
    @Test
    public void testSetDTranslatedX() {
        System.out.println("setDTranslatedX");
        float expResult = randint();
        GameState.setDTranslatedX(expResult);
        float result = GameState.dTranslatedX;
        assertEquals(expResult, result, 0.0);
        GameState.dTranslatedX = 0;
    }

    /**
     * Test of getDTranslatedY method, of class GameState.
     */
    @Test
    public void testGetDTranslatedY() {
        System.out.println("getDTranslatedY");
        float expResult = 0;
        float result = GameState.getDTranslatedY();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setDTranslatedY method, of class GameState.
     */
    @Test
    public void testSetDTranslatedY() {
        System.out.println("setDTranslatedY");
        float expResult = randint();
        GameState.setDTranslatedY(expResult);
        float result = GameState.dTranslatedY;
        assertEquals(expResult, result, 0.0);
        GameState.dTranslatedY = 0;
    }

    /**
     * Test of getTimeFlow method, of class GameState.
     */
    @Test
    public void testGetTimeFlow() {
        System.out.println("getTimeFlow");
        float expResult = 1;
        float result = GameState.getTimeFlow();
        assertEquals(expResult, result, 0.0);
    }
    
}
