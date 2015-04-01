/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Admin
 */
public class Button implements renderable {

    public static final int scale = 2;
    
    String title;
    Font font;
    TrueTypeFont buttonFont;
    Image unclicked;
    Image clicked;
    Image image;
    boolean click;
    int x;
    int y;
    int width;
    int height;

    /**
     *
     * @param title
     * @throws SlickException
     */
    public Button(String title) throws SlickException {
        click = false;
        this.title = title;
        this.x = Medrun.getWidth() / 2 - this.width / 2;
        this.y = 0;
        unclicked = new Image("data/sprites/button.png");
        clicked = new Image("data/sprites/buttonclicked.png");
        width = unclicked.getWidth() * scale;
        height = unclicked.getHeight() * scale;
        image = unclicked;
        font = new Font("Chicago", Font.BOLD, 25*scale);
        buttonFont = new TrueTypeFont(font, true);
    }

    /**
     *
     * @param title
     * @param x
     * @param y
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
     *
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
     *
     * @return
     */
    public Image getAnimation() {
        return image;
    }

    /**
     * 
     */
    @Override
    public void render() {
        Medrun.renderScaledCenter(image, x, y, scale);
        Medrun.renderCenterdText(buttonFont, title, x, y, new Color(0,0,100));
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isInButton(float x, float y) {
        int startX = this.x - width/2; // hämtar det x-värde som är längst till vänster.
        int startY = this.y - height/2; // hämtar det y-värde som är längst upp.
        if (x > startX && x < startX + this.width && y > startY && y < startY + this.height) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public Font getFont() {
        return font;
    }

    /**
     *
     * @param font
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

}
