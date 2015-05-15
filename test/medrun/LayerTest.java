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
public class LayerTest {
    
    public LayerTest() {
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
     * Test of render method, of class Layer.
     */
    @Test
    public void testRender() {
        System.out.println("render");
        Layer instance = null;
        instance.render();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Layer.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        float deltaRatio = 0.0F;
        Layer instance = null;
        instance.update(deltaRatio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class Layer.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Layer instance = null;
        float expResult = 0.0F;
        float result = instance.getX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setX method, of class Layer.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        float x = 0.0F;
        Layer instance = null;
        instance.setX(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Layer.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Layer instance = null;
        float expResult = 0.0F;
        float result = instance.getY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setY method, of class Layer.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        float y = 0.0F;
        Layer instance = null;
        instance.setY(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
