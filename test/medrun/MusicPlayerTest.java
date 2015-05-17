/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Admin
 */
public class MusicPlayerTest {

    static MusicPlayer instance;

    public MusicPlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws SlickException {
        instance = new MusicPlayer("test/testMusic.aif");
    }

    /**
     * Test of changeMusic method, of class MusicPlayer.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testChangeMusic() throws SlickException {
        System.out.println("changeMusic");
        String expResult = "testMusic2.aif";
        MusicPlayer.changeMusic(expResult);
        String result = MusicPlayer.ref;
        assertEquals(expResult, result);
        MusicPlayer.ref = "testMusic.aif";
    }

    /**
     * Test of getMusic method, of class MusicPlayer.
     */
    @Test
    public void testGetMusic() {
        System.out.println("getMusic");
        Music expResult = MusicPlayer.music;
        Music result = MusicPlayer.getMusic();
        assertEquals(expResult, result);
    }

    /**
     * Test of play method, of class MusicPlayer.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        MusicPlayer.play();
        MusicPlayer.music.pause();
        MusicPlayer.music.setPosition(0);
    }

    /**
     * Test of pause method, of class MusicPlayer.
     */
    @Test
    public void testPause() {
        System.out.println("pause");
        MusicPlayer.pause();
    }

    /**
     * Test of stop method, of class MusicPlayer.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        MusicPlayer.stop();
    }

    /**
     * Test of getRef method, of class MusicPlayer.
     */
    @Test
    public void testGetRef() {
        System.out.println("getRef");
        String expResult = "testMusic.aif";
        String result = MusicPlayer.getRef();
        assertEquals(expResult, result);
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
    }

    /**
     * Test of setMuted method, of class MusicPlayer.
     */
    @Test
    public void testSetMuted() {
        System.out.println("setMuted");
        boolean expResult = true;
        MusicPlayer.setMuted(expResult);
        boolean result = MusicPlayer.muted;
        assertEquals(expResult, result);
        MusicPlayer.muted = false;
    }

    /**
     * Test of restart method, of class MusicPlayer.
     */
    @Test
    public void testRestart() {
        System.out.println("restart");
        MusicPlayer.music.setPosition(10);
        if(MusicPlayer.music.playing()){
            MusicPlayer.music.pause();
        }
        MusicPlayer.restart();
        assertEquals(0, MusicPlayer.music.getPosition(), 0.0f);
    }
}
