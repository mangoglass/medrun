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
 *
 * @author Admin
 */
public class GuiTest {
    
    static Gui instance;
    
    public GuiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new Gui(true);
    }

    /**
     * Test of scoreNumber method, of class Gui.
     */
    @Test
    public void testScoreNumber() {
        System.out.println("scoreNumber");
        String expResult = "00000000";
        String result = instance.scoreNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of length method, of class Gui.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        String expResult = "0 m";
        String result = instance.length();
        assertEquals(expResult, result);
    }
    
}
