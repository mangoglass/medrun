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

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Tom
 */
public class Slider implements Renderable{
    
    public static final float scale = 1.5f;
    static final float fontSize = 26;
    
    Image image;
    float x;
    float y;
    float width;
    float height;
    String setting;
    
    /**
     * The constructor for the slider.
     *
     * @param setting
     * @param x
     * @param y
     * @throws org.newdawn.slick.SlickException
     */
    
    public Slider(String setting, int x, int y) throws SlickException {
        this.x = x;
        this.y = y;
        this.setting = setting;
        image = new Image("data/sprites/slider.png");
        image.setFilter(Image.FILTER_NEAREST);
        width = image.getWidth() * scale;
        height = image.getHeight() * scale;
    }
    
    @Override
    public void render() {
        Medrun.renderScaledCenter(image, (int) x, (int) y, scale);
    }
    
    public boolean inSlider(int x, int y){
        return(x > this.x - width/2 && x < this.x + width/2 && y > this.y - height/2 && y < this.y + height/2);
    }

    public String getSetting() {
        return setting;
    }
}
