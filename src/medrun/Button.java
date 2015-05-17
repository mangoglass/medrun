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
 * The Button object is an object that is used to render click-able buttons that
 * is used in the menus of the game.
 *
 * @author Tom Axblad
 */
public class Button implements Renderable {

    public static final float scale = 1.5f;
    static final float fontSize = 26;

    String title;
    Font font;
    TrueTypeFont buttonFont;
    Image unclicked;
    Image clicked;
    Image image;
    boolean click;
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
        click = false;
        this.title = title;
        this.x = Medrun.getWidth() / 2 - this.width / 2;
        this.y = 0;
        unclicked = new Image("data/sprites/button.png");
        clicked = new Image("data/sprites/buttonclicked.png");
        unclicked.setFilter(Image.FILTER_NEAREST);
        clicked.setFilter(Image.FILTER_NEAREST);
        width = unclicked.getWidth() * scale;
        height = unclicked.getHeight() * scale;
        image = unclicked;
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
     * @param test separates the two constructors.
     * @throws SlickException
     */
    public Button(String title, int x, int y, boolean test) throws SlickException {
        click = false;
        this.title = title;
        unclicked = null;
        clicked = null;
        width = 100;
        height = 100;
        image = null;
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
        click = false;
        this.title = title;
        this.x = x;
        this.y = y;
        image = new Image("data/sprites/button.png");
        font = new Font("Chicago", Font.PLAIN, 40);
        buttonFont = new TrueTypeFont(font, false);
    }

    /**
     * Changes the image from clicked to un-clicked or the other way.
     */
    public void togglePress() {
        if (!click) {
            click = true;
            image = clicked;
        } else {
            click = false;
            image = unclicked;
        }
    }

    /**
     * Returns the image that is displaying right now.
     * @return the image to output.
     */
    public Image getAnimation() {
        return image;
    }

    /**
     * renders the button on the x and y variables.
     */
    @Override
    public void render() {
        Medrun.renderScaledCenter(image, (int) x, (int) y, scale);
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
     * Returns true if the button is currently in the "clicked" state.
     * @return returns the boolean representing the clicked state called "click"
     */
    public boolean isClick() {
        return click;
    }
    /**
     * sets the click boolean to the input boolean.
     * @param click the boolean to set the click variable to.
     */
    public void setClick(boolean click) {
        this.click = click;
    }

}
