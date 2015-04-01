/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import java.awt.Font;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.Image;

/**
 *
 * @author Admin
 */
public class ButtonTest {
    
    public ButtonTest() {
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
     * Test of togglePress method, of class Button.
     */
    @Test
    public void testTogglePress() {
        System.out.println("togglePress");
        Button instance = null;
        instance.togglePress();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnimation method, of class Button.
     */
    @Test
    public void testGetAnimation() {
        System.out.println("getAnimation");
        Button instance = null;
        Image expResult = null;
        Image result = instance.getAnimation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class Button.
     */
    @Test
    public void testRender() {
        System.out.println("render");
        Button instance = null;
        instance.render();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInButton method, of class Button.
     */
    @Test
    public void testIsInButton() {
        System.out.println("isInButton");
        float x = 0.0F;
        float y = 0.0F;
        Button instance = null;
        boolean expResult = false;
        boolean result = instance.isInButton(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class Button.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Button instance = null;
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitle method, of class Button.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        Button instance = null;
        instance.setTitle(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFont method, of class Button.
     */
    @Test
    public void testGetFont() {
        System.out.println("getFont");
        Button instance = null;
        Font expResult = null;
        Font result = instance.getFont();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFont method, of class Button.
     */
    @Test
    public void testSetFont() {
        System.out.println("setFont");
        Font font = null;
        Button instance = null;
        instance.setFont(font);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class Button.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Button instance = null;
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setX method, of class Button.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        int x = 0;
        Button instance = null;
        instance.setX(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Button.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Button instance = null;
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setY method, of class Button.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        int y = 0;
        Button instance = null;
        instance.setY(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class Button.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Button instance = null;
        int expResult = 0;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWidth method, of class Button.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        int width = 0;
        Button instance = null;
        instance.setWidth(width);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class Button.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Button instance = null;
        int expResult = 0;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHeight method, of class Button.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        int height = 0;
        Button instance = null;
        instance.setHeight(height);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isClick method, of class Button.
     */
    @Test
    public void testIsClick() {
        System.out.println("isClick");
        Button instance = null;
        boolean expResult = false;
        boolean result = instance.isClick();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClick method, of class Button.
     */
    @Test
    public void testSetClick() {
        System.out.println("setClick");
        boolean click = false;
        Button instance = null;
        instance.setClick(click);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
