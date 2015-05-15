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
import org.newdawn.slick.Music;

/**
 *
 * @author Admin
 */
public class MusicPlayerTest {
    
    public MusicPlayerTest() {
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
     * Test of changeMusic method, of class MusicPlayer.
     */
    @Test
    public void testChangeMusic() throws Exception {
        System.out.println("changeMusic");
        String ref = "";
        MusicPlayer.changeMusic(ref);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMusic method, of class MusicPlayer.
     */
    @Test
    public void testGetMusic() {
        System.out.println("getMusic");
        Music expResult = null;
        Music result = MusicPlayer.getMusic();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of play method, of class MusicPlayer.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        MusicPlayer.play();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pause method, of class MusicPlayer.
     */
    @Test
    public void testPause() {
        System.out.println("pause");
        MusicPlayer.pause();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class MusicPlayer.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        MusicPlayer.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRef method, of class MusicPlayer.
     */
    @Test
    public void testGetRef() {
        System.out.println("getRef");
        String expResult = "";
        String result = MusicPlayer.getRef();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMuted method, of class MusicPlayer.
     */
    @Test
    public void testIsMuted() {
        System.out.println("isMuted");
        boolean expResult = false;
        boolean result = MusicPlayer.isMuted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMuted method, of class MusicPlayer.
     */
    @Test
    public void testSetMuted() {
        System.out.println("setMuted");
        boolean muted = false;
        MusicPlayer.setMuted(muted);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMusicVolume method, of class MusicPlayer.
     */
    @Test
    public void testSetMusicVolume() {
        System.out.println("setMusicVolume");
        MusicPlayer.setMusicVolume();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of restart method, of class MusicPlayer.
     */
    @Test
    public void testRestart() {
        System.out.println("restart");
        MusicPlayer.restart();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
