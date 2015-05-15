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

/**
 *
 * @author Admin
 */
public class GuiTest {
    
    public GuiTest() {
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
     * Test of update method, of class Gui.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        float distance = 0.0F;
        Gui instance = new Gui();
        instance.update(distance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class Gui.
     */
    @Test
    public void testRender() {
        System.out.println("render");
        Gui instance = new Gui();
        instance.render();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scoreNumber method, of class Gui.
     */
    @Test
    public void testScoreNumber() {
        System.out.println("scoreNumber");
        Gui instance = new Gui();
        String expResult = "";
        String result = instance.scoreNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of length method, of class Gui.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        Gui instance = new Gui();
        String expResult = "";
        String result = instance.length();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
