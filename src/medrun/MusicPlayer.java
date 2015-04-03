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
 *
 * @author Admin
 */
public class MusicPlayer {

    public static Music music;
    public static String ref;
    public static boolean muted;

    public MusicPlayer(String ref) throws SlickException {
        MusicPlayer.ref = ref;
        music = new Music(ref);
        setMusicVolume();
        MusicPlayer.setMuted(Medrun.isSoundMuted());
    }

    public static void changeMusic(String ref) throws SlickException {
        if (music.playing()) {
            music.stop();
        }
        MusicPlayer.ref = ref;
        music = new Music(ref);
    }

    public static Music getMusic() {
        return music;
    }

    public static void play() {
        music.play();
    }

    public static void pause() {
        music.pause();
    }

    public static void stop(){
        music.stop();
    }

    public static String getRef() {
        return ref;
    }

    public static void setVolume(float volume) {
        music.setVolume(volume);
    }

    public static boolean isMuted() {
        return muted;
    }

    public static void setMuted(boolean muted) {
        MusicPlayer.muted = muted;
        if (muted) {
            music.setVolume(0);
        } else {
            setMusicVolume();
        }
    }

    public static void setMusicVolume() {
        MusicPlayer.setVolume(((float) soundMaster * (float) soundMusic / 100) / 100);
    }
}
