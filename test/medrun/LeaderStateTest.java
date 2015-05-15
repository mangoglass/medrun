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
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Admin
 */
public class LeaderStateTest {
    
    public LeaderStateTest() {
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
     * Test of init method, of class LeaderState.
     */
    @Test
    public void testInit() throws Exception {
        System.out.println("init");
        GameContainer container = null;
        StateBasedGame game = null;
        LeaderState instance = null;
        instance.init(container, game);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class LeaderState.
     */
    @Test
    public void testRender() throws Exception {
        System.out.println("render");
        GameContainer container = null;
        StateBasedGame game = null;
        Graphics g = null;
        LeaderState instance = null;
        instance.render(container, game, g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class LeaderState.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        GameContainer container = null;
        StateBasedGame game = null;
        int delta = 0;
        LeaderState instance = null;
        instance.update(container, game, delta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enter method, of class LeaderState.
     */
    @Test
    public void testEnter() throws Exception {
        System.out.println("enter");
        GameContainer container = null;
        StateBasedGame game = null;
        LeaderState instance = null;
        instance.enter(container, game);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leave method, of class LeaderState.
     */
    @Test
    public void testLeave() throws Exception {
        System.out.println("leave");
        GameContainer container = null;
        StateBasedGame game = null;
        LeaderState instance = null;
        instance.leave(container, game);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
