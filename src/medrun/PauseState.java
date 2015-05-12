/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The PauseSate is the state the game switches to when the user presses the set
 * pause button.
 *
 * @author Tom Axblad
 */
class PauseState extends State {

    public static Image pauseFrame;
    private final float PFONTSIZE = 50;
    private final float TFONTSIZE = 30;
    private final int xMargin = 300;
    private final int yMargin = Medrun.height / 2 - 100;
    private final int width = Medrun.width;
    private final int height = Medrun.height;
    Font font;
    TrueTypeFont pfont;
    TrueTypeFont tfont;

    public PauseState(int stateID) throws SlickException {
        super(stateID);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        MusicPlayer.pause();
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        pauseFrame = new Image(width, height, Image.FILTER_NEAREST);
        try {
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("data/fonts/retro.ttf"));
        } catch (FontFormatException | IOException e) {
            System.out.println("Error with importing font in PauseState.java on line 51");
        }
        pfont = new TrueTypeFont(font.deriveFont(PFONTSIZE), false);
        tfont = new TrueTypeFont(font.deriveFont(TFONTSIZE), false);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        pauseFrame.draw(0, 0, width + 960, height + 378);
        Medrun.renderCenterdText(pfont, "PAUSED", Medrun.width / 2, yMargin, Color.white);
        Medrun.renderCenterdText(tfont, "E - unpause", Medrun.width / 2 - xMargin, yMargin + 300, Color.white);
        Medrun.renderCenterdText(tfont, "ESC - Main Menu", Medrun.width / 2 + xMargin, yMargin + 300, Color.white);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_E)) {
            sbg.enterState(Medrun.GAME);
        } else if (input.isKeyPressed(Input.KEY_R)) {
            sbg.getState(Medrun.GAME).init(gc, sbg);
            GameState.restarted = true;
            sbg.enterState(Medrun.GAME);
        } else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(Medrun.MENU);
        }
    }

}
