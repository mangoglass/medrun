/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.Image;

/**
 * A layer object is a part of the pseudo 3-d background. It contains a position
 * index representing how it will be drawn, and also an x and y value which
 * decides where it will be drawn.
 *
 * @author Tom Axblad
 */
public class Layer implements Renderable {

    private final float ERROR = -1;

    Image image;
    float width;
    float height;
    int index;
    float x;
    float y;
    float drawX;
    float drawY;
    float drawX2;
    float drawY2;
    float sourceX;
    float sourceY;
    float sourceX2;
    float sourceY2;
    float scale;
    float transitionMultiplier;
    boolean render;

    /**
     * The constructor for the layer object. sets the image and layer index used for drawing the layer.
     * @param image the image representing the layer.
     * @param index the layer index that the layer is drawn as.
     */
    public Layer(Image image, int index) {
        this.image = image;
        this.image.setFilter(Image.FILTER_NEAREST);
        this.index = index; // the index is the layers position in relation to the other layers, higher index is closer to the "screen". 
        x = 0;
        if (index != 0) {
            scale = 0.25f + (float) index / 10;  // all except for the background will be effected.
            y = -113 * (index - 2); // lower index lowers the position, a higher index will raise it.
        } else {
            y = 0;
            scale = 1;
        }
        render = false;
        width = image.getWidth() * scale;
        height = image.getHeight() * scale;
        this.transitionMultiplier = 1 - ((float) Math.pow(index, 3) / 300); /* higher index yields a higher multiplier. 
         A low scaling means that the layer will move with the camera, like it's farther away. */

    }

    @Override
    public void render() {
        image.draw(x, y, scale);
        /*if(render){
         image.draw(drawX, drawY, drawX2, drawY2, sourceX, sourceY, sourceX2, sourceY2);
         }*/
    }

    /**
     * The update function for the layer object. In this function, the position for the layers is updated. This function runs every frame.
     * @param deltaRatio the ratio used to compliment for the difference in time between frames when calculation the layer position.
     */
    public void update(float deltaRatio) {
        x += GameState.getDTranslatedX() * transitionMultiplier * deltaRatio;
        y += GameState.getDTranslatedY() * transitionMultiplier * deltaRatio;

        if (GameState.translatedX - x > width / 2) { // if the camera has moved across half of the layer
            x += width / 2; // the layer will be moved half of it's width to the right.
        }
        //updateLimits();
    }

    /**
     * @return Returns the x value
     */
    public float getX() {
        return x;
    }

    /**
     * @param x Sets the x value to this float input value
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return Returns the y value.
     */
    public float getY() {
        return y;
    }

    /**
     * @param y Sets the y value to this input float value.
     */
    public void setY(float y) {
        this.y = y;
    }

    /*public void updateLimits() {
        drawX = setdrawnX();
        drawY = setdrawnY();
        drawX2 = setdrawnX2();
        drawY2 = setdrawnY2();
        sourceX = setSourceX();
        sourceY = setSourceY();
        sourceX2 = setSourceX2();
        sourceY2 = setSourceY2();
        if (index == 2) {
            System.out.println("drawX: " + drawX + " drawY: " + drawY + " drawX2: " + drawX2 + " drawY2: " + drawY2 + "   sourceX: " + sourceX + " sourceY: " + sourceY + " sourceX2: " + sourceX2 + " sourceY2: " + sourceY2);
        }
        if (render && (drawX == ERROR || drawY == ERROR || drawX2 == ERROR || drawY2 == ERROR || sourceX == ERROR || sourceY == ERROR || sourceX2 == ERROR || sourceY2 == ERROR)) {
            System.out.println("Error on frame: " + GameState.frames + " in layer: " + this.index);
            render = false;
        } else if (!render) {
            render = true;
        }
    }

    public float setdrawnX() {
        float output = x - GameState.translatedX;
        if (output < 0) {
            return 0;
        } else if (output > Medrun.width) {
            return ERROR;
        } else {
            return output;
        }
    }

    public float setdrawnY() {
        float output = y - GameState.translatedY;
        if (output < 0) {
            return 0;
        } else if (output > Medrun.height) {
            return ERROR;
        } else {
            return output;
        }
    }

    public float setdrawnX2() {
        float output = x + this.width - GameState.translatedX;
        if (output > Medrun.width) {
            return Medrun.width;
        } else if (output < 0) {
            return ERROR;
        } else {
            return output;
        }
    }

    public float setdrawnY2() {
        float output = y + this.height - GameState.translatedY;
        if (output > Medrun.height) {
            return Medrun.height;
        } else if (output < 0) {
            return ERROR;
        } else {
            return output;
        }
    }

    public float setSourceX() {
        float output = GameState.translatedX - x;
        if (output < 0) {
            return 0;
        } else if (output > this.width) {
            return ERROR;
        } else {
            return output;
        }
    }

    public float setSourceY() {
        float output = GameState.translatedY - y;
        if (output < 0) {
            return 0;
        } else if (output > this.height) {
            return ERROR;
        } else {
            return output;
        }
    }

    public float setSourceX2() {
        float output = GameState.translatedX + Medrun.width - x;
        if (output < 0) {
            return 0;
        } else if (output > this.width) {
            return ERROR;
        } else {
            return output;
        }
    }

    public float setSourceY2() {
        float output = GameState.translatedY - y;
        if (output < 0) {
            return 0;
        } else if (output > this.height) {
            return ERROR;
        } else {
            return output;
        }
    }*/
}
