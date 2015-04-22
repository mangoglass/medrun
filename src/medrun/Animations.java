/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Admin
 */
public class Animations {

    // these ar all so the animations are easier to find in the animations list.
    public static final int IDLE = 0;
    public static final int RUN = 1;
    public static final int VICTORY = 2;
    public static final int POINT = 3;
    public static final int SLIDESTART = 4;
    public static final int SLIDE = 5;
    public static final int SLIDESTOP = 6;
    public static final int SPIN = 7;
    public static final int CRASH = 8;
    public static final int FLIP = 9;
    public static final int DASH = 10;
    public static final int JUMP = 11;
    public static final int INAIRUP = 12;
    public static final int INAIRSHIWTCH = 13;
    public static final int INAIRDOWN = 14;
    public static final int LAND = 15;

    public static final int FAST = 30; // Fast animation speed, mesured in miliseconds per frame.
    public static final int SLOW = 100; // Slow animation speed.
    public static final int NORMAL = 75; // Normal animation speed.

    public static final int width = 100;
    public static final int height = 100;
    public static final float airSwitcher = 10; // How small the yspeed value of the player needs to be to trigger the inair switch event.
    public SpriteSheet sprites;
    public Animation[] animations;
    public Animation current;

    public Animations() throws SlickException {
        sprites = new SpriteSheet("data/sprites/player.png", 70, 70, Color.green);
        animations = new Animation[]{
            new Animation(sprites, 0, 0, 7, 0, true, NORMAL, false),
            new Animation(sprites, 0, 1, 7, 1, true, NORMAL, false),
            new Animation(sprites, 0, 2, 7, 2, true, NORMAL, false),
            new Animation(sprites, 0, 3, 4, 5, true, FAST, false),
            new Animation(sprites, 0, 6, 1, 6, true, FAST, false),
            new Animation(sprites, 0, 7, 3, 7, true, SLOW, false),
            new Animation(sprites, 0, 8, 2, 8, true, FAST, false),
            new Animation(sprites, 0, 9, 3, 11, true, NORMAL, false),
            new Animation(sprites, 0, 12, 7, 12, true, SLOW, false),
            new Animation(sprites, 0, 13, 4, 13, true, FAST, false),
            new Animation(sprites, 0, 14, 5, 14, true, SLOW, false),
            new Animation(sprites, 0, 15, 2, 15, true, FAST, false),
            new Animation(sprites, 0, 16, 1, 16, true, SLOW, false),
            new Animation(sprites, 0, 17, 6, 17, true, Integer.MAX_VALUE, false),
            new Animation(sprites, 0, 18, 2, 18, true, SLOW, false),
            new Animation(sprites, 0, 19, 4, 19, true, FAST, false)
        };

        for (int i = 0; i < animations.length; i++) {
            switch (i) {
                case DASH:
                case JUMP:
                case LAND:
                case POINT:
                case SLIDESTART:
                case SLIDESTOP:
                case VICTORY:
                    animations[i].setLooping(false);
                    break;
            }
        }
        current = animations[1];
        current.setLooping(true);
        current.setAutoUpdate(false);
    }

    public void update(int delta, Player player) {

        float ySpeed = player.getySpeed(); // to reduce code size.

        if (ySpeed < -airSwitcher && current != animations[INAIRUP]) {
            current = animations[INAIRUP];
        } else if (ySpeed > -airSwitcher && ySpeed < airSwitcher && !player.isOnGround()) {
            float fourth = airSwitcher / 4;
            if (current != animations[INAIRSHIWTCH]) {
                current = animations[INAIRSHIWTCH];
            }
            if (ySpeed < -airSwitcher + fourth && current.getFrame() != 0) {
                current.setCurrentFrame(0);
            } else if (ySpeed > -airSwitcher + fourth && ySpeed < -airSwitcher + 2 * fourth && current.getFrame() != 1) {
                current.setCurrentFrame(1);
            } else if (ySpeed > -airSwitcher + 2 * fourth && ySpeed < -airSwitcher + 3 * fourth && current.getFrame() != 2) {
                current.setCurrentFrame(2);
            } else if (ySpeed > -airSwitcher + 3 * fourth && ySpeed < airSwitcher - 3 * fourth && current.getFrame() != 3) {
                current.setCurrentFrame(3);
            } else if (ySpeed > airSwitcher - 3 * fourth && ySpeed < airSwitcher - 2 * fourth && current.getFrame() != 4) {
                current.setCurrentFrame(4);
            } else if (ySpeed > airSwitcher - 2 * fourth && ySpeed < airSwitcher - fourth && current.getFrame() != 5) {
                current.setCurrentFrame(5);
            } else if (ySpeed > airSwitcher - fourth && current.getFrame() != 6) {
                current.setCurrentFrame(6);
            }

        } else if (ySpeed > airSwitcher && current != animations[INAIRDOWN]) {
            current = animations[INAIRDOWN];
        } else if (player.isOnGround() && current == animations[INAIRDOWN]) {
            current = animations[LAND];
            current.restart();
        } else if (player.isOnGround() && current == animations[LAND] && current.isStopped()) {
            current = animations[RUN];
        }

        current.update(delta);
    }

    public void draw(float x, float y) {
        current.draw(x, y, width, height);
    }

    public Animation[] getAnimations() {
        return animations;
    }

    public void setAnimations(Animation[] animations) {
        this.animations = animations;
    }

    public Animation getCurrent() {
        return current;
    }

    public void setCurrent(Animation current) {
        this.current = current;
    }
}
