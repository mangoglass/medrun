/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import static medrun.Medrun.soundMaster;
import static medrun.Medrun.soundMusic;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 * The MusicPlayer class handles the music in the game.
 *
 * @author Tom Axblad
 */
public class MusicPlayer {

    public static Music music;
    public static String ref;
    public static boolean muted;

    /**
     * The MusicPlayers constructor, creates a new music player and import a
     * reference to use when playing.
     *
     * @param ref The reference to connect to the music player.
     * @throws SlickException
     */
    public MusicPlayer(String ref) throws SlickException {
        MusicPlayer.ref = ref;
        music = new Music(ref);
        setMusicVolume();
        MusicPlayer.setMuted(Medrun.isSoundMuted());
    }

    /**
     * Changes the current reference.
     *
     * @param ref The reference to switch to.
     * @throws SlickException
     */
    public static void changeMusic(String ref) throws SlickException {
        if (music.playing()) {
            music.stop();
        }
        MusicPlayer.ref = ref;
        music = new Music(ref);
    }

    /**
     * @return Returns the music object used in the player.
     */
    public static Music getMusic() {
        return music;
    }

    /**
     * PLays the music from the music object
     */
    public static void play() {
        if(!music.playing()){
            music.play();
        }
    }

    /**
     * Pauses the music from the music object
     */
    public static void pause() {
        music.pause();
    }

    /**
     * Stops the music from the music object.
     */
    public static void stop() {
        music.stop();
    }

    /**
     * @return Returns the music objects current reference.
     */
    public static String getRef() {
        return ref;
    }

    /**
     * Sets the volume of the music object.
     * @param volume The volume to set to.
     */
    private static void setVolume(float volume) {
        music.setVolume(volume);
    }

    /**
     * @return Returns true is the music object is muted, otherwise it returns false.
     */
    public static boolean isMuted() {
        return muted;
    }

    /**
     * Changes the mute setting for the music object.
     * @param muted The boolean to set the muted setting to. 
     */
    public static void setMuted(boolean muted) {
        MusicPlayer.muted = muted;
        if (muted) {
            music.setVolume(0);
        } else {
            setMusicVolume();
        }
    }

    /**
     * Sets the music volume for the music player.
     */
    public static void setMusicVolume() {
        MusicPlayer.setVolume(((float) soundMaster * (float) soundMusic / 100) / 100);
    }

    /**
     * sets the position of the music in the music object to 0.
     */
    public static void restart() {
        music.setPosition(0);
    }
}
