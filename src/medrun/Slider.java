/*
 * Copyright (C) 2015 Tom Axblad
 *
 * This Game is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This game is distributed in the hope that it will be entertaining,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package medrun;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Tom
 */
public class Slider implements Renderable {

    public static final float scale = 0.55f;
    static final float titleFontSize = 25;
    static final float textFontSize = 15;

    Image image;
    Image background;
    Font font;
    TrueTypeFont sliderFont;
    TrueTypeFont textFont;
    float x;
    float y;
    float sliderX;
    float sliderY;
    float sliderWidth;
    float sliderHeight;
    float width;
    float height;
    float stepWidth;
    int size;
    int value;
    String setting;
    String valueText;
    boolean unlimitedSize;

    /**
     * The constructor for the slider.
     *
     * @param setting
     * @param x
     * @param y
     * @param size
     * @param value
     * @throws org.newdawn.slick.SlickException
     */
    public Slider(String setting, int x, int y, int size, int value) throws SlickException {
        this.setting = setting;
        this.size = size;
        image = new Image("data/sprites/slider.png");
        image.setFilter(Image.FILTER_NEAREST);
        background = new Image("data/sprites/sliderback.png");
        background.setFilter(Image.FILTER_NEAREST);
        sliderWidth = image.getWidth() * scale;
        sliderHeight = image.getHeight() * scale;
        width = background.getWidth() * scale;
        height = background.getHeight() * scale;
        valueText = "";
        this.x = x - width / 2;
        this.y = y - height / 2;
        sliderY = y + (scale * 3 * height / 8);
        unlimitedSize = size == 0;
        if (value > size && !unlimitedSize) {
            this.value = size - 1;
        } else if (unlimitedSize && value > 100) {
            value = 100;
        } else if (value < 0) {
            this.value = 0;
        } else {
            this.value = value;
        }
        if (unlimitedSize) {
            sliderX = (int) (x + (60 * scale) + (value * (6.8 * scale)));
        } else {
            sliderX = (int) (x + (60 * scale) + (value / size * (680 * scale)));
        }
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("data/fonts/retro.ttf"));
            sliderFont = new TrueTypeFont(font.deriveFont(titleFontSize), false);
            textFont = new TrueTypeFont(font.deriveFont(textFontSize), false);
        } catch (FontFormatException | IOException e) {
            System.out.println(e.toString());
            Medrun.app.exit();
        }
    }

    public Slider(String setting, int x, int y) throws SlickException {
        this.setting = setting;
        image = new Image("data/sprites/slider.png");
        image.setFilter(Image.FILTER_NEAREST);
        background = new Image("data/sprites/sliderback.png");
        background.setFilter(Image.FILTER_NEAREST);
        sliderWidth = image.getWidth() * scale;
        sliderHeight = image.getHeight() * scale;
        width = background.getWidth() * scale;
        height = background.getHeight() * scale;
        valueText = "";
        this.x = x - width / 2;
        this.y = y - height / 2;
        sliderY = y + (207 * scale);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("data/fonts/retro.ttf"));
            sliderFont = new TrueTypeFont(font.deriveFont(titleFontSize), false);
            textFont = new TrueTypeFont(font.deriveFont(textFontSize), false);
        } catch (FontFormatException | IOException e) {
            System.out.println(e.toString());
            Medrun.app.exit();
        }
    }

    @Override
    public void render() {
        background.draw(x, y, scale);
        Medrun.renderCenterdText(sliderFont, setting, (int) (x + (width / 2)), (int) (y + (3 * height / 16)));
        Medrun.renderCenterdText(textFont, valueText, (int) (x + (width / 2)), (int) (y + (7 * height / 20)));
        image.draw(sliderX - sliderWidth / 2, sliderY - sliderHeight / 2, scale);
    }

    public boolean inSlider(int x, int y) {
        return (x > this.sliderX - sliderWidth / 2 && x < this.sliderX + sliderWidth / 2 && y > this.sliderY - sliderHeight / 2 && y < this.sliderY + sliderHeight / 2);
    }

    public String getSetting() {
        return setting;
    }

    public void setSliderX(float mouseX) {
        if (unlimitedSize) {
            if (mouseX < this.x + (60 * scale)) {
                sliderX = this.x + (60 * scale);
                value = 0;
            } else if (mouseX > this.x + (740 * scale)) {
                sliderX = this.x + (740 * scale);
                value = 100;
            } else {
                sliderX = mouseX;
                value = (int) ((mouseX - x) / (6.8 * scale));
            }
        } else {
            if (mouseX < x + (60 * scale)) {
                sliderX = x + (60 * scale);
                value = 0;
            } else if (mouseX > x + (740 * scale)) {
                sliderX = x + (740 * scale);
                value = size - 1;
            } else {
                value = (int) (((mouseX - (x + (60 * scale))) / ((680 * scale) / (size - 1))) + 0.5);
                sliderX = (x + (60 * scale)) + value * (680 * scale / (size - 1));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        unlimitedSize = size == 0;
        setValue(this.value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value > size) {
            this.value = size - 1;
        } else if (value < 0) {
            this.value = 0;
        } else {
            this.value = value;
        }
        if (unlimitedSize) {
            sliderX = (x + (60 * scale)) + value * (6.8f * scale);
        } else {
            sliderX = (x + (60 * scale)) + value * (680 * scale / (size - 1));
        }
    }

    public void setValueText(String text) {
        valueText = text;
    }
}
