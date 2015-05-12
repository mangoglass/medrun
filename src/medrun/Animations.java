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
 * The Animations class contains all the player animations, and the logic behind
 * rendering the right animation at the right time.
 *
 * @author Tom Axblad
 */
public class Animations {

    // The indexes of the animations in the "animations" array, these are used instead of numbers to find the correct animation more easily.
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

    public static final int FAST = 40; // Fast animation speed, mesured in miliseconds per frame.
    public static final int SLOW = 100; // Slow animation speed.
    public static final int NORMAL = 70; // Normal animation speed.

    /**
     * The width that the animations have from the start, the width can be
     * altered in the code and this integer will then be used to restore the
     * normal animation width.
     */
    public static final int startWidth = 100;
    /**
     * The height that the animations have from the start, the height can be
     * altered in the code and this integer will then be used to restore the
     * normal animation height.
     */
    public static final int startHeight = 100;

    /**
     * The duration difference that will be used when slowing down or speeding
     * up the current animation.
     */
    public static final int speedChanger = 15;

    /** The animation width. */
    public static int width;
    /** The animation height. */
    public static int height;

    /** How small the ySpeed value of the player needs to be to trigger the inAir switch event. */
    public static final float airSpeedSwitch = 10;
    /** The file containing the animations that we will use. */
    public SpriteSheet sprites;
    /** The array containing all the animations that we will use. */
    public Animation[] animations;
    /** The current set animation. */
    public Animation current;

    /** A boolean telling the game if the animations is currently sped up. */
    boolean fasterRun = false;
    /** A boolean telling the game if the animations is currently slowed down. */
    boolean slowerRun = false;

    /**
     * Creates a new Animations object, here we load the static animation sheet
     * from the data folder and divide the different animations in an array
     * called "animations".
     *
     * @throws SlickException
     */
    public Animations() throws SlickException {
        sprites = new SpriteSheet("data/sprites/player.png", 70, 70, Color.green);
        width = startWidth;
        height = startHeight;
        animations = new Animation[]{
            new Animation(sprites, 0, 0, 7, 0, true, NORMAL, false),
            new Animation(sprites, 0, 1, 7, 1, true, NORMAL, false),
            new Animation(sprites, 0, 2, 7, 2, true, NORMAL, false),
            new Animation(sprites, 0, 3, 4, 5, true, SLOW, false),
            new Animation(sprites, 0, 6, 1, 6, true, FAST, false),
            new Animation(sprites, 0, 7, 3, 7, true, NORMAL, false),
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
                case CRASH:
                case SLIDESTART:
                case SLIDESTOP:
                case VICTORY:
                    animations[i].setLooping(false);
                    break;
            }
        }
        current = animations[IDLE];
        current.setAutoUpdate(false);
    }

    /**
     * Updates the animations so that the correct animation is rendered at the
     * right time.
     *
     * @param delta the time between the last frame and this on e measured in
     * milliseconds.
     * @param player the player object that we use for calculation the current
     * player position and the current player parameters.
     */
    public void update(int delta, Player player) {

        float ySpeed = player.getySpeed(); // to reduce code size.

        if (player.dead && !player.onGround && isNotAnimation(SPIN)) {
            changeAnimation(SPIN);
        } else if (player.dead && player.onGround && isNotAnimation(CRASH)) {
            changeAnimation(CRASH);
        } else if (!player.dead && !Camera.started) {
            if (GameState.gametime > 500 && GameState.gametime < 1000) {
                changeAnimation(POINT);
            } else if (isAnimation(POINT) && current.isStopped()) {
                changeAnimation(IDLE);
            }
        } else if (!player.dead && Camera.started) {
            if (ySpeed < -airSpeedSwitch && isNotAnimation(INAIRUP)) {
                changeAnimation(INAIRUP);
            } else if (ySpeed > -airSpeedSwitch && ySpeed < airSpeedSwitch && !player.isOnGround()) {
                float fourth = airSpeedSwitch / 4;
                if (isNotAnimation(INAIRSHIWTCH)) {
                    changeAnimation(INAIRSHIWTCH);
                }
                if (ySpeed < -airSpeedSwitch + fourth && current.getFrame() != 0) {
                    current.setCurrentFrame(0);
                } else if (ySpeed > -airSpeedSwitch + fourth && ySpeed < -airSpeedSwitch + 2 * fourth && current.getFrame() != 1) {
                    current.setCurrentFrame(1);
                } else if (ySpeed > -airSpeedSwitch + 2 * fourth && ySpeed < -airSpeedSwitch + 3 * fourth && current.getFrame() != 2) {
                    current.setCurrentFrame(2);
                } else if (ySpeed > -airSpeedSwitch + 3 * fourth && ySpeed < airSpeedSwitch - 3 * fourth && current.getFrame() != 3) {
                    current.setCurrentFrame(3);
                } else if (ySpeed > airSpeedSwitch - 3 * fourth && ySpeed < airSpeedSwitch - 2 * fourth && current.getFrame() != 4) {
                    current.setCurrentFrame(4);
                } else if (ySpeed > airSpeedSwitch - 2 * fourth && ySpeed < airSpeedSwitch - fourth && current.getFrame() != 5) {
                    current.setCurrentFrame(5);
                } else if (ySpeed > airSpeedSwitch - fourth && current.getFrame() != 6) {
                    current.setCurrentFrame(6);
                }

            } else if (ySpeed > airSpeedSwitch && isNotAnimation(INAIRDOWN)) {
                changeAnimation(INAIRDOWN);
            } else if (player.isOnGround() && (isAnimation(INAIRDOWN) || isAnimation(Animations.INAIRSHIWTCH))) {
                changeAnimation(LAND);
                current.restart();
            } else if (player.isOnGround() && isAnimation(LAND) && current.isStopped()) {
                changeAnimation(RUN);
            }

            if (player.isSliding() && isAnimation(RUN)) {
                changeAnimation(SLIDESTART);
                current.restart();
            } else if (player.isSliding() && isAnimation(SLIDESTART) && current.isStopped()) {
                changeAnimation(SLIDE);
            } else if (player.isOnGround() && !player.isSliding() && isAnimation(SLIDE)) {
                changeAnimation(SLIDESTOP);
                current.restart();
            } else if (player.isOnGround() && !player.isSliding() && ((isAnimation(SLIDESTOP) && current.isStopped()) || isAnimation(SLIDESTART))) {
                changeAnimation(RUN);
            }

            if (isAnimation(RUN)) {
                if (player.forwards && !fasterRun) {
                    fasterRun = true;
                    slowerRun = false;
                    changeDuration(NORMAL - speedChanger);
                    System.out.println("aaaa");
                } else if (player.backwards && !slowerRun) {
                    slowerRun = true;
                    fasterRun = false;
                    changeDuration(NORMAL + speedChanger);
                } else if (!player.backwards && !player.forwards && (fasterRun || slowerRun)) {
                    fasterRun = false;
                    slowerRun = false;
                    defaultSpeed();
                }
            }

        }

        current.update(delta);
    }

    /**
     * Draws the current player animation (stored in a animation object called
     * "current") on the screen.
     *
     * @param x Which point on the x-axis that the animation will be drawn at.
     * @param y Which point on the y-axis that the animation will be drawn at.
     */
    public void draw(float x, float y) {

        current.draw(x, y, width, height);
    }

    /**
     * Returns the list containing all the player animations.
     *
     * @return returns the array object called "animations".
     */
    public Animation[] getAnimations() {
        return animations;
    }

    /**
     * changes the current player animation to the specified animation in the
     * "animations" array.
     *
     * @param animation An integer specifying what index in the "animations"
     * array to use. The "current" object will be set to this animation.
     */
    public void changeAnimation(int animation) {
        current = animations[animation];
        if (GameState.timeFlow != 1) {
            defaultSpeed();
        }
    }

    /**
     * Changes the duration of the animation by altering the speed parameter for
     * the "current" object.
     *
     * @param duration
     */
    public void changeDuration(float duration) {
        if (GameState.timeFlow != 1) {
            current.setSpeed((current.getDuration(0) * GameState.timeFlow) / duration);
        } else {
            current.setSpeed(current.getDuration(0) / duration);
        }
    }

    public void defaultSpeed() {
        if (GameState.timeFlow != 1) {
            current.setSpeed(current.getDuration(0) / (current.getDuration(0) * GameState.timeFlow));
        } else {
            current.setSpeed(1);
        }
    }

    public boolean isAnimation(int animation) {
        return current == animations[animation];
    }

    public boolean isNotAnimation(int animation) {
        return current != animations[animation];
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
