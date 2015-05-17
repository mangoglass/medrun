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
import java.util.LinkedList;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 * The GUI object renders text showing the user how far he/she has reached in
 * the game. It also shows a number representing how many points the user has
 * achieved in the current run.
 *
 * @author Tom Axblad
 */
public class Gui implements Renderable {

    final int xMargin = 50;
    final int yMargin = 20;
    final int fontSize = 40;
    final int scoreWidth = 500;

    float x;
    float y;
    float score;
    float length;
    int width;
    int height;
    Font font;
    TrueTypeFont trueType;

    public Gui() {
        width = Medrun.width;
        height = Medrun.height;
        score = 0;
        length = 0;
        try {
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("data/fonts/retro.ttf"));
            font = font.deriveFont((float) fontSize);
            trueType = new TrueTypeFont(font, false);
        } catch (FontFormatException | IOException e) {
            System.out.println("Error in GUI.java on line 40");
        }
    }
    
    public Gui(boolean test){
        width = Medrun.width;
        height = Medrun.height;
        score = 0;
        length = 0;
    }

    /**
     * The update function for the GUI object. this function sets the run
     * distance and the score achieved accordingly to the players position and
     * actions.
     *
     * @param distance a float variable representing the distance the player has
     * reached in the x-axis.
     */
    public void update(float distance) {
        x = GameState.translatedX;
        y = GameState.translatedY;
        score += distance / 10;
        length += distance / (Block.TILEWIDTH * 2);
    }

    /**
     * The render function for the GUI object. Renders the GUI object on the
     * screen depending on the current screen transformation and the x and y
     * margin.
     */
    @Override
    public void render() {
        trueType.drawString(x + width - scoreWidth - xMargin, y + yMargin, "Score   " + scoreNumber(), Color.white);
        trueType.drawString(x + xMargin, y + yMargin, "Distance   " + length(), Color.white);
    }

    /**
     * Returns the current score for the player.
     * @return a string representing the current score.
     */
    public String scoreNumber() {
        int rounded = (int) (score + 0.5);
        int zeroes = 8 - String.valueOf(rounded).length();
        LinkedList list = new LinkedList();
        for (int i = 0; i < zeroes; i++) {
            list.add("0");
        }
        list.add(String.valueOf(rounded));
        return String.join("", list);
    }

    /**
     * Returns the current length in meters (roughly measured) achieved in the current run by the player.
     * @return a string representing the distance in meters.
     */
    public String length() {
        return String.valueOf((int) length) + " m";
    }
}
