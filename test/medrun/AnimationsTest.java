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
import org.newdawn.slick.SlickException;

/**
 *
 * @author Tom Axblad
 */
public class AnimationsTest {
    
    public AnimationsTest() {
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
     * Test of update method, of class Animations.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testUpdate() throws SlickException {
        System.out.println("update");
        int delta = 0;
        Player player = null;
        Animations instance = new Animations();
        instance.update(delta, player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class Animations.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testDraw() throws SlickException {
        System.out.println("draw");
        float x = 0.0F;
        float y = 0.0F;
        Animations instance = new Animations();
        instance.draw(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnimations method, of class Animations.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testGetAnimations() throws SlickException {
        System.out.println("getAnimations");
        Animations instance = new Animations();
        Animation[] expResult = null;
        Animation[] result = instance.getAnimations();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeAnimation method, of class Animations.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testChangeAnimation() throws SlickException {
        System.out.println("changeAnimation");
        int animation = 0;
        Animations instance = new Animations();
        instance.changeAnimation(animation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeDuration method, of class Animations.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testChangeDuration() throws SlickException {
        System.out.println("changeDuration");
        float duration = 0.0F;
        Animations instance = new Animations();
        instance.changeDuration(duration);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of defaultSpeed method, of class Animations.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testDefaultSpeed() throws SlickException {
        System.out.println("defaultSpeed");
        Animations instance = new Animations();
        instance.defaultSpeed();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAnimation method, of class Animations.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testIsAnimation() throws SlickException {
        System.out.println("isAnimation");
        int animation = 0;
        Animations instance = new Animations();
        boolean expResult = false;
        boolean result = instance.isAnimation(animation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNotAnimation method, of class Animations.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testIsNotAnimation() throws SlickException {
        System.out.println("isNotAnimation");
        int animation = 0;
        Animations instance = new Animations();
        boolean expResult = false;
        boolean result = instance.isNotAnimation(animation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAnimations method, of class Animations.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testSetAnimations() throws SlickException {
        System.out.println("setAnimations");
        Animation[] animations = null;
        Animations instance = new Animations();
        instance.setAnimations(animations);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrent method, of class Animations.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testGetCurrent() throws SlickException {
        System.out.println("getCurrent");
        Animations instance = new Animations();
        Animation expResult = null;
        Animation result = instance.getCurrent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrent method, of class Animations.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testSetCurrent() throws SlickException {
        System.out.println("setCurrent");
        Animation current = null;
        Animations instance = new Animations();
        instance.setCurrent(current);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
