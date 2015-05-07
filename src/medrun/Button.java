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
 *
 * @author Admin
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
        Medrun.renderScaledCenter(image, (int) x, (int) y, scale);
        Medrun.renderCenterdText(buttonFont, title, (int) x, (int) y, new Color(Color.black));
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isInButton(float x, float y) {
        float startX = this.x - width / 2; // hämtar det x-värde som är längst till vänster.
        float startY = this.y - height / 2; // hämtar det y-värde som är längst upp.
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
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

}
