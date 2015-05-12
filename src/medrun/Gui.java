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
 *
 * @author Admin
 */
public class Gui implements Renderable{
    
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
    
    public Gui(){
        width = Medrun.width;
        height = Medrun.height;
        score = 0;
        try {
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("data/fonts/retro.ttf"));
            font = font.deriveFont((float)fontSize);
            trueType = new TrueTypeFont(font, false);
        } catch (FontFormatException | IOException e) {
            System.out.println("Error in GUI.java on line 40");
        }
    }
    
    public void update(float distance){
        x = GameState.translatedX;
        y = GameState.translatedY;
        score += distance/10;
        length +=  distance/(Block.TILEWIDTH*2);
    }    

    @Override
    public void render() {
        trueType.drawString(x + width - scoreWidth - xMargin, y + yMargin, "Score   " + scoreNumber(), Color.white);
        trueType.drawString(x + xMargin, y + yMargin, "Distance   " + length(), Color.white);
    }
    
    public String scoreNumber(){
        int rounded = (int)(score + 0.5);
        int zeroes = 8 - String.valueOf(rounded).length();
        LinkedList list = new LinkedList();
        for(int i = 0; i < zeroes; i++){
            list.add("0");
        }
        list.add(String.valueOf(rounded));
        return String.join("", list);
    }
    
    public String length(){
        return String.valueOf((int)length) + " m";
    }
}
