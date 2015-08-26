/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import java.awt.Font;
import java.io.File;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 * The Button object is an object that is used to render clicked-able buttons that
 is used in the menus of the game.
 *
 * @author Tom Axblad
 */
public class Button implements Renderable {

    public static final float scale = 1.5f;
    static final float fontSize = 26;

    String title;
    Font font;
    TrueTypeFont buttonFont;
    Image unclickedImage;
    Image clickedImage;
    Image CurrentImage;
    boolean clicked;
    float x;
    float y;
    float width;
    float height;

    /**
     * The constructor for the button. Takes in a String input to represent the
     * text that will be rendered in the button.
     *
     * @param title the string representation for the text.
     * @throws SlickException
     */
    public Button(String title) throws SlickException {
        clicked = false;
        this.title = title;
        this.x = Medrun.getWidth() / 2 - this.width / 2;
        this.y = 0;
        unclickedImage = new Image("data/sprites/button.png");
        clickedImage = new Image("data/sprites/buttonclicked.png");
        unclickedImage.setFilter(Image.FILTER_NEAREST);
        clickedImage.setFilter(Image.FILTER_NEAREST);
        width = unclickedImage.getWidth() * scale;
        height = unclickedImage.getHeight() * scale;
        CurrentImage = unclickedImage;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("data/fonts/retro.ttf"));
            font = font.deriveFont(fontSize);
            //System.out.println(font.getSize());
            //font = new Font("Chicago", Font.BOLD, 25 * (int) scale);
            buttonFont = new TrueTypeFont(font, false);
        } catch (Exception e) {
        }
    }
    /**
     * The test constructor for the button. Takes in a String input to represent the
     * text that will be rendered in the button.
     *
     * @param title the string representation for the text.
     * @param x
     * @param y
     * @param test separates the two constructors.
     * @throws SlickException
     */
    public Button(String title, int x, int y, boolean test) throws SlickException {
        clicked = false;
        this.title = title;
        unclickedImage = null;
        clickedImage = null;
        width = 100;
        height = 100;
        CurrentImage = null;
        this.x = x;
        this.y = y;
    }
    /**
     * The constructor for the button. Takes in a String input to represent the
     * text that will be rendered in the button. Also takes in two integer
     * values to represent the position that the button will be rendered on.
     *
     * @param title the string representation for the text.
     * @param x the x-position the button will be rendered at.
     * @param y the y-position the button will be rendered at.
     * @throws SlickException
     */
    public Button(String title, int x, int y) throws SlickException {
        clicked = false;
        this.title = title;
        this.x = x;
        this.y = y;
        CurrentImage = new Image("data/sprites/button.png");
        font = new Font("Chicago", Font.PLAIN, 40);
        buttonFont = new TrueTypeFont(font, false);
    }

    /**
     * Changes the CurrentImage from clickedImage to un-clickedImage or the other way.
     */
    public void togglePress() {
        if (!clicked) {
            clicked = true;
            CurrentImage = clickedImage;
        } else {
            clicked = false;
            CurrentImage = unclickedImage;
        }
    }

    /**
     * Returns the CurrentImage that is displaying right now.
     * @return the CurrentImage to output.
     */
    public Image getCurrentImage() {
        return CurrentImage;
    }

    /**
     * renders the button on the x and y variables.
     */
    @Override
    public void render() {
        Medrun.renderScaledCenter(CurrentImage, (int) x, (int) y, scale);
        Medrun.renderCenterdText(buttonFont, title, (int) x, (int) y, new Color(Color.black));
    }

    /**
     * Returns true if the two input positions is in the button, otherwise it returns false.
     * @param x the x-value to check.
     * @param y the y-value to check.
     * @return the boolean to represent the answer.
     */
    public boolean isInButton(float x, float y) {
        float startX = this.x - width / 2; // hämtar det x-värde som är längst till vänster.
        float startY = this.y - height / 2; // hämtar det y-värde som är längst upp.
        return x > startX && x < startX + this.width && y > startY && y < startY + this.height;
    }

    /**
     * Returns the string title variable.
     * @return the variable representing the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * changes the title variable to the input string.
     * @param title the string to change the title variable to.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * returns the font used to render the text with.
     * @return the representation of the font.
     */
    public Font getFont() {
        return font;
    }

    /**
     * sets the font variable used to render the text to the input font.
     * @param font the font to set the font variable to.
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * Returns the integer x variable.
     * @return a representation of the x variable.
     */
    public float getX() {
        return x;
    }
    /**
     * sets the x variable to the input integer.
     * @param x the integer to set the x variable to.
     */
    public void setX(float x) {
        this.x = x;
    }
    /**
     * Returns the integer y variable.
     * @return a representation of the y variable.
     */
    public float getY() {
        return y;
    }
    /**
     * sets the y variable to the input integer.
     * @param y the integer to set the y variable to.
     */
    public void setY(float y) {
        this.y = y;
    }
    /**
     * Returns the integer width variable.
     * @return a representation of the width variable.
     */
    public float getWidth() {
        return width;
    }
    /**
     * sets the width variable to the input integer.
     * @param width the integer to set the width variable to.
     */
    public void setWidth(float width) {
        this.width = width;
    }
    /**
     * Returns the integer height variable.
     * @return a representation of the height variable.
     */
    public float getHeight() {
        return height;
    }
    /**
     * sets the height variable to the input integer.
     * @param height the integer to set the height variable to.
     */
    public void setHeight(float height) {
        this.height = height;
    }
    
    /**
     * Returns true if the button is currently in the "clickedImage" state.
     * @return returns the boolean representing the clickedImage state called "clicked"
     */
    public boolean isClicked() {
        return clicked;
    }
    /**
     * sets the clicked boolean to the input boolean.
     * @param click the boolean to set the clicked variable to.
     */
    public void setClicked(boolean click) {
        this.clicked = click;
    }

}
