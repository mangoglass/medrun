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
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Admin
 */
public class StateTest {
    
    public StateTest() {
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
     * Test of getID method, of class State.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        State instance = null;
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enter method, of class State.
     */
    @Test
    public void testEnter() throws Exception {
        System.out.println("enter");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        State instance = null;
        instance.enter(gc, sbg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leave method, of class State.
     */
    @Test
    public void testLeave() throws Exception {
        System.out.println("leave");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        State instance = null;
        instance.leave(gc, sbg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of init method, of class State.
     */
    @Test
    public void testInit() throws Exception {
        System.out.println("init");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        State instance = null;
        instance.init(gc, sbg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class State.
     */
    @Test
    public void testRender() throws Exception {
        System.out.println("render");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        Graphics grphcs = null;
        State instance = null;
        instance.render(gc, sbg, grphcs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class State.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        GameContainer gc = null;
        StateBasedGame sbg = null;
        int i = 0;
        State instance = null;
        instance.update(gc, sbg, i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class StateImpl extends State {

        public StateImpl() {
            super(0);
        }

        public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        }

        public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        }

        public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        }

        public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        }

        public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        }
    }
    
}
