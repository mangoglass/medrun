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
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Admin
 */
public class SplashStateTest {
    
    public SplashStateTest() {
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
     * Test of enter method, of class SplashState.
     */
    @Test
    public void testEnter() throws Exception {
        System.out.println("enter");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        SplashState instance = null;
        instance.enter(gc, sbg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leave method, of class SplashState.
     */
    @Test
    public void testLeave() throws Exception {
        System.out.println("leave");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        SplashState instance = null;
        instance.leave(gc, sbg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of init method, of class SplashState.
     */
    @Test
    public void testInit() throws Exception {
        System.out.println("init");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        SplashState instance = null;
        instance.init(gc, sbg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class SplashState.
     */
    @Test
    public void testRender() throws Exception {
        System.out.println("render");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        Graphics grphcs = null;
        SplashState instance = null;
        instance.render(gc, sbg, grphcs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class SplashState.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        int delta = 0;
        SplashState instance = null;
        instance.update(gc, sbg, delta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderScaledCenter method, of class SplashState.
     */
    @Test
    public void testRenderScaledCenter() {
        System.out.println("renderScaledCenter");
        Animation anim = null;
        int x = 0;
        int y = 0;
        int scale = 0;
        SplashState.renderScaledCenter(anim, x, y, scale);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
