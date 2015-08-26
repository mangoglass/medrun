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
 * The Animations class contains all the player animationList, and the logic
 * behind rendering the right animation at the right time.
 *
 * @author Tom Axblad
 */
public class Animations {

    // The indexes of the animationList in the "animationList" array, these are used instead of numbers to find the correct animation more easily.
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
     * The width that the animationList have from the start, the width can be
     * altered in the code and this integer will then be used to restore the
     * normal animation width.
     */
    public static final int STARTWIDTH = 100;
    /**
     * The height that the animationList have from the start, the height can be
     * altered in the code and this integer will then be used to restore the
     * normal animation height.
     */
    public static final int STARTHEIGHT = 100;

    /**
     * The duration difference that will be used when slowing down or speeding
 up the currentAnimation animation.
     */
    public static final int SPEEDCHANGER = 15;

    /**
     * The animation width.
     */
    public static int width;
    /**
     * The animation height.
     */
    public static int height;

    /**
     * How small the ySpeed value of the player needs to be to trigger the inAir
     * switch event.
     */
    public static final float AIRSPEEDSWITCH = 10;
    /**
     * The file containing the animationList that we will use.
     */
    public SpriteSheet spriteSheet;
    /**
     * The array containing all the animationList that we will use.
     */
    public Animation[] animationList;
    /**
     * The currentAnimation set animation.
     */
    public Animation currentAnimation;

    /**
     * A boolean telling the game if the animationList is currently sped up.
     */
    boolean fasterRun = false;
    /**
     * A boolean telling the game if the animationList is currently slowed down.
     */
    boolean slowerRun = false;

    /**
     * Creates a new Animations object, here we load the static animation sheet
     * from the data folder and divide the different animationList in an array
     * called "animationList".
     *
     * @throws SlickException
     */
    public Animations() throws SlickException {
        spriteSheet = new SpriteSheet("data/sprites/player.png", 70, 70, Color.green);
        width = STARTWIDTH;
        height = STARTHEIGHT;
        animationList = new Animation[]{ // rips all of the animationList out of the spriteSheet depending on the animation positions and puts them in their own position in the Animations list.
            new Animation(spriteSheet, 0, 0, 7, 0, true, NORMAL, false),
            new Animation(spriteSheet, 0, 1, 7, 1, true, NORMAL, false),
            new Animation(spriteSheet, 0, 2, 7, 2, true, NORMAL, false),
            new Animation(spriteSheet, 0, 3, 4, 5, true, SLOW, false),
            new Animation(spriteSheet, 0, 6, 1, 6, true, FAST, false),
            new Animation(spriteSheet, 0, 7, 3, 7, true, NORMAL, false),
            new Animation(spriteSheet, 0, 8, 2, 8, true, FAST, false),
            new Animation(spriteSheet, 0, 9, 3, 11, true, NORMAL, false),
            new Animation(spriteSheet, 0, 12, 7, 12, true, SLOW, false),
            new Animation(spriteSheet, 0, 13, 4, 13, true, FAST, false),
            new Animation(spriteSheet, 0, 14, 5, 14, true, SLOW, false),
            new Animation(spriteSheet, 0, 15, 2, 15, true, FAST, false),
            new Animation(spriteSheet, 0, 16, 1, 16, true, SLOW, false),
            new Animation(spriteSheet, 0, 17, 6, 17, true, Integer.MAX_VALUE, false),
            new Animation(spriteSheet, 0, 18, 2, 18, true, SLOW, false),
            new Animation(spriteSheet, 0, 19, 4, 19, true, FAST, false)
        };

        for (int i = 0; i < animationList.length; i++) {
            switch (i) {
                case DASH:
                case JUMP:
                case LAND:
                case POINT:
                case CRASH:
                case SLIDESTART:
                case SLIDESTOP:
                case VICTORY:
                    animationList[i].setLooping(false);
                    break;
            }
        }
        currentAnimation = animationList[IDLE];
    }

    /**
     * Updates the animationList so that the correct animation is rendered at
     * the right time.
     *
     * @param delta the time between the last frame and this on e measured in
     * milliseconds.
     * @param player the player object that we use for calculation the currentAnimation
 player position and the currentAnimation player parameters.
     */
    public void update(int delta, Player player) {

        float ySpeed = player.getySpeed(); // to reduce code size.

        if (player.dead && !player.onGround && isNotAnimation(SPIN)) {
            changeAnimation(SPIN);
        } else if (player.dead && player.onGround && isNotAnimation(CRASH)) {
            changeAnimation(CRASH);
        } else if (!player.dead && !Camera.started) {
            if (GameState.gameTime > 500 && GameState.gameTime < 1000) {
                changeAnimation(POINT);
            } else if (isAnimation(POINT) && currentAnimation.isStopped()) {
                changeAnimation(IDLE);
            }
        } else if (!player.dead && Camera.started) {
            if (ySpeed < -AIRSPEEDSWITCH && isNotAnimation(INAIRUP)) {
                changeAnimation(INAIRUP);
            } else if (ySpeed > -AIRSPEEDSWITCH && ySpeed < AIRSPEEDSWITCH && !player.isOnGround()) {
                float fourth = AIRSPEEDSWITCH / 4;
                if (isNotAnimation(INAIRSHIWTCH)) {
                    changeAnimation(INAIRSHIWTCH);
                }
                if (ySpeed < -AIRSPEEDSWITCH + fourth && currentAnimation.getFrame() != 0) {
                    currentAnimation.setCurrentFrame(0);
                } else if (ySpeed > -AIRSPEEDSWITCH + fourth && ySpeed < -AIRSPEEDSWITCH + 2 * fourth && currentAnimation.getFrame() != 1) {
                    currentAnimation.setCurrentFrame(1);
                } else if (ySpeed > -AIRSPEEDSWITCH + 2 * fourth && ySpeed < -AIRSPEEDSWITCH + 3 * fourth && currentAnimation.getFrame() != 2) {
                    currentAnimation.setCurrentFrame(2);
                } else if (ySpeed > -AIRSPEEDSWITCH + 3 * fourth && ySpeed < AIRSPEEDSWITCH - 3 * fourth && currentAnimation.getFrame() != 3) {
                    currentAnimation.setCurrentFrame(3);
                } else if (ySpeed > AIRSPEEDSWITCH - 3 * fourth && ySpeed < AIRSPEEDSWITCH - 2 * fourth && currentAnimation.getFrame() != 4) {
                    currentAnimation.setCurrentFrame(4);
                } else if (ySpeed > AIRSPEEDSWITCH - 2 * fourth && ySpeed < AIRSPEEDSWITCH - fourth && currentAnimation.getFrame() != 5) {
                    currentAnimation.setCurrentFrame(5);
                } else if (ySpeed > AIRSPEEDSWITCH - fourth && currentAnimation.getFrame() != 6) {
                    currentAnimation.setCurrentFrame(6);
                }

            } else if (ySpeed > AIRSPEEDSWITCH && isNotAnimation(INAIRDOWN)) {
                changeAnimation(INAIRDOWN);
            } else if (player.isOnGround() && (isAnimation(INAIRDOWN) || isAnimation(Animations.INAIRSHIWTCH))) {
                changeAnimation(LAND);
                currentAnimation.restart();
            } else if (player.isOnGround() && isAnimation(LAND) && currentAnimation.isStopped()) {
                changeAnimation(RUN);
            }

            if (player.isSliding() && isAnimation(RUN)) {
                changeAnimation(SLIDESTART);
                currentAnimation.restart();
            } else if (player.isSliding() && isAnimation(SLIDESTART) && currentAnimation.isStopped()) {
                changeAnimation(SLIDE);
            } else if (player.isOnGround() && !player.isSliding() && isAnimation(SLIDE)) {
                changeAnimation(SLIDESTOP);
                currentAnimation.restart();
            } else if (player.isOnGround() && !player.isSliding() && ((isAnimation(SLIDESTOP) && currentAnimation.isStopped()) || isAnimation(SLIDESTART))) {
                changeAnimation(RUN);
            }

            if (isAnimation(RUN)) {
                if (player.forwards && !fasterRun) {
                    fasterRun = true;
                    slowerRun = false;
                    changeDuration(NORMAL - SPEEDCHANGER);
                    System.out.println("aaaa");
                } else if (player.backwards && !slowerRun) {
                    slowerRun = true;
                    fasterRun = false;
                    changeDuration(NORMAL + SPEEDCHANGER);
                } else if (!player.backwards && !player.forwards && (fasterRun || slowerRun)) {
                    fasterRun = false;
                    slowerRun = false;
                    defaultSpeed();
                }
            }

        }

        currentAnimation.update(delta);
    }

    /**
     * Draws the currentAnimation player animation (stored in a animation object called
 "currentAnimation") on the screen.
     *
     * @param x Which point on the x-axis that the animation will be drawn at.
     * @param y Which point on the y-axis that the animation will be drawn at.
     */
    public void draw(float x, float y) {

        currentAnimation.draw(x, y, width, height);
    }

    /**
     * Returns the list containing all the player animationList.
     *
     * @return returns the array object called "animationList".
     */
    public Animation[] getAnimations() {
        return animationList;
    }

    /**
     * changes the currentAnimation player animation to the specified animation in the
 "animationList" array.
     *
     * @param animation An integer specifying what index in the "animationList"
 array to use. The "currentAnimation" object will be set to this animation.
     */
    public void changeAnimation(int animation) {
        currentAnimation = animationList[animation];
        if (GameState.timeFlow != 1) {
            defaultSpeed();
        }
    }

    /**
     * Changes the duration of the animation by altering the speed parameter for
 the "currentAnimation" object.
     *
     * @param duration
     */
    public void changeDuration(float duration) {
        if (GameState.timeFlow != 1) {
            currentAnimation.setSpeed((currentAnimation.getDuration(0) * GameState.timeFlow) / duration);
        } else {
            currentAnimation.setSpeed(currentAnimation.getDuration(0) / duration);
        }
    }

    public void defaultSpeed() {
        if (GameState.timeFlow != 1) {
            currentAnimation.setSpeed(currentAnimation.getDuration(0) / (currentAnimation.getDuration(0) * GameState.timeFlow));
        } else {
            currentAnimation.setSpeed(1);
        }
    }

    public boolean isAnimation(int animation) {
        return currentAnimation == animationList[animation];
    }

    public boolean isNotAnimation(int animation) {
        return currentAnimation != animationList[animation];
    }

    public void setAnimations(Animation[] animations) {
        this.animationList = animations;
    }

    public Animation getCurrent() {
        return currentAnimation;
    }

    public void setCurrent(Animation current) {
        this.currentAnimation = current;
    }
}
