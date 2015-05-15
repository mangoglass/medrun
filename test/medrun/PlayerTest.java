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
import org.newdawn.slick.Input;

/**
 *
 * @author Admin
 */
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of update method, of class Player.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        int delta = 0;
        float deltaRatio = 0.0F;
        Input input = null;
        Player instance = null;
        instance.update(delta, deltaRatio, input);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class Player.
     */
    @Test
    public void testRender() {
        System.out.println("render");
        Player instance = null;
        instance.render();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of die method, of class Player.
     */
    @Test
    public void testDie() {
        System.out.println("die");
        float[] pos = null;
        boolean onGround = false;
        Player instance = null;
        instance.die(pos, onGround);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOnGround method, of class Player.
     */
    @Test
    public void testSetOnGround_0args() {
        System.out.println("setOnGround");
        Player instance = null;
        instance.setOnGround();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOnGround method, of class Player.
     */
    @Test
    public void testSetOnGround_3args() {
        System.out.println("setOnGround");
        float[] pos = null;
        Block block = null;
        boolean leftPos = false;
        Player instance = null;
        instance.setOnGround(pos, block, leftPos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBelowRoof method, of class Player.
     */
    @Test
    public void testSetBelowRoof() {
        System.out.println("setBelowRoof");
        float[] pos = null;
        Block block = null;
        Player instance = null;
        instance.setBelowRoof(pos, block);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of groundCheck method, of class Player.
     */
    @Test
    public void testGroundCheck() {
        System.out.println("groundCheck");
        Block block = null;
        float[] botLeft = null;
        float[] botRight = null;
        int lastBlockIndex = 0;
        int index = 0;
        Player instance = null;
        instance.groundCheck(block, botLeft, botRight, lastBlockIndex, index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxJumpHeight method, of class Player.
     */
    @Test
    public void testGetMaxJumpHeight() {
        System.out.println("getMaxJumpHeight");
        int expResult = 0;
        int result = Player.getMaxJumpHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class Player.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Player instance = null;
        float expResult = 0.0F;
        float result = instance.getX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setX method, of class Player.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        float x = 0.0F;
        Player instance = null;
        instance.setX(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Player.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Player instance = null;
        float expResult = 0.0F;
        float result = instance.getY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setY method, of class Player.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        float y = 0.0F;
        Player instance = null;
        instance.setY(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getxSpeed method, of class Player.
     */
    @Test
    public void testGetxSpeed() {
        System.out.println("getxSpeed");
        Player instance = null;
        float expResult = 0.0F;
        float result = instance.getxSpeed();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setxSpeed method, of class Player.
     */
    @Test
    public void testSetxSpeed() {
        System.out.println("setxSpeed");
        float xSpeed = 0.0F;
        Player instance = null;
        instance.setxSpeed(xSpeed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getySpeed method, of class Player.
     */
    @Test
    public void testGetySpeed() {
        System.out.println("getySpeed");
        Player instance = null;
        float expResult = 0.0F;
        float result = instance.getySpeed();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setySpeed method, of class Player.
     */
    @Test
    public void testSetySpeed() {
        System.out.println("setySpeed");
        float ySpeed = 0.0F;
        Player instance = null;
        instance.setySpeed(ySpeed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getxAcc method, of class Player.
     */
    @Test
    public void testGetxAcc() {
        System.out.println("getxAcc");
        Player instance = null;
        float expResult = 0.0F;
        float result = instance.getxAcc();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setxAcc method, of class Player.
     */
    @Test
    public void testSetxAcc() {
        System.out.println("setxAcc");
        float xAcc = 0.0F;
        Player instance = null;
        instance.setxAcc(xAcc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getyAcc method, of class Player.
     */
    @Test
    public void testGetyAcc() {
        System.out.println("getyAcc");
        Player instance = null;
        float expResult = 0.0F;
        float result = instance.getyAcc();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setyAcc method, of class Player.
     */
    @Test
    public void testSetyAcc() {
        System.out.println("setyAcc");
        float yAcc = 0.0F;
        Player instance = null;
        instance.setyAcc(yAcc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOnGround method, of class Player.
     */
    @Test
    public void testIsOnGround() {
        System.out.println("isOnGround");
        Player instance = null;
        boolean expResult = false;
        boolean result = instance.isOnGround();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSliding method, of class Player.
     */
    @Test
    public void testIsSliding() {
        System.out.println("isSliding");
        Player instance = null;
        boolean expResult = false;
        boolean result = instance.isSliding();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
